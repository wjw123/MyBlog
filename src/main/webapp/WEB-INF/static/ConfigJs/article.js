//类型编号
var typeId=0;
//类型集合
var types=[];

var articleNums=[];//要删除的ID集合
var articles="";//要删除的字符串连接体

//分页数据
var count=0;//数据总条数
var pageNumber=0;//总页数
var pageSize=10;//每一页显示的数据条数
var currentPageNumber=1;//当前显示的页面
var num=0;//标记

$ = layui.jquery; 
var titles=[ //标题栏
     {type: 'checkbox', fixed: 'left'}
	,{field: 'id', title: 'ID', width: 80, sort: true}
    ,{field: 'typeid', title: '类型', width: 120,templet:'#type'}
    ,{field: 'title', title: '标题', minWidth: 120}
    ,{field: 'phtime', title: '发布时间', width: 120,
      templet:function(row){
    	  return createTime(row.phtime);
      }}
    ,{field:  'number',title: '评论数量',width:120}
    ,{fixed: 'right', title:'操作', toolbar:'#barDemo', width:200}
  ];

$(function(){

	//添加表格数据
	adddatatables();
	
	//添加树形数据
	addtree();
	
	//添加工具栏信息
	addtoolbars();
	
	//添加页码信息()
	//addPage();
	
});


//添加表格数据
var adddatatables=function(){
	  layui.use('table',function(){
		    var table=layui.table;
		    table.render({
		    elem:'#datatable'
		    ,height:350
		    ,url:'/blog/admin/getAllarticles'
		    ,page:false
		    ,loading:true
		    ,where:{pageNum:currentPageNumber,pageSize:pageSize,typeId:typeId} //传参数
		    ,toolbar:'#toolbar'
		    ,cellMinWidth:120 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    ,cols:[titles]
		    ,done:doneCallback  //设置回调函数
		  });
	});
}

//添加树形数据
var addtree=function(){
	var typing=getAllarticles();
    $ = layui.jquery; 
    
    for(var i=0;i<typing.length;i++){
    	types.push({
              name:typing[i].typename
              ,id:typing[i].id
            });
    }

 
   layui.use(['tree','layer'],function(){
   var layer=layui.layer;
    
    layui.tree({
        elem:'#demo1' //指定元素
        ,target:'_blank' //是否新选项卡打开（比如节点返回href才有效）
        ,click:function(item){ //点击节点回调
        	typeId=item.id;
        	adddatatables();
        }
        ,nodes:[ //节点
        {
         name:'类型'
        ,spread:true//默认打开下拉
          ,id:0
          ,children:types
        }
        ]
        });
   });
}

//添加工具栏信息
var addtoolbars=function(){
	layui.use('table',function(){
		var table=layui.table;
	    
		  //头工具栏事件
		  table.on('toolbar(datatable)', function(obj){
			    var checkStatus = table.checkStatus(obj.config.id);
			    var data=checkStatus.data;
			    var datas="";
			    for(var i=0;i<data.length;i++){
			    	datas+=data[i].id;
			    	if(i<data.length-1){
			    		datas+="_";
			    	}
			    } 
			    
		    switch(obj.event){
		      case 'add':
		    	  add();
		      break;
		      case 'deleteAll':
		    	  deleteAll(datas); 
		    };
		  });
		  
		  //显示行工具栏
		  //监听行工具事件
		  table.on('tool(datatable)', function(obj){
		    var data = obj.data;
		    if(obj.event === 'check'){
		    	layer.alert("查看页面");
		    }else if(obj.event === 'edit'){
		    	layer.alert("编辑页面");
		    }else{
		    	del(data.id);
		    }
		  });
	});
}

//显示回调函数,分页控件
var doneCallback=function(res, curr, count){
	
	//总页数
	pageNumber=parseInt(count/pageSize)+1;
	var mol=count%pageSize;
	if(mol==0)
		pageNumber--;
	
    layui.use('laypage', function (){
  	 var laypage = layui.laypage;
    //layui分页 
    laypage.render({ 
	        elem:'page', 
   	        count:count,
   	        pages:pageNumber, //总页数  
   		    totalData:count,//总数据量
   		    everyPage:function(){//每页数据量
   		    	  return pageSize
   		    }(),      		    
   		    curr: function(){ //通过url获取当前页，也可以同上（pages）方式获取  
   		    	  return currentPageNumber
   		    }(),
   	        layout: ['count', 'prev', 'page', 'next', 'refresh', 'skip'],
   		    limit:pageSize,
   		    skip: true, //是否开启跳页  
   		    prev: '<', //若不显示，设置false即可  
   		    next: '>', //若不显示，设置false即可 
   		    
   		    jump: function(obj, first){ //触发分页后的回调  
   		        if(!first){ //一定要加此判断，否则初始时会无限刷新 
   		          currentPageNumber=obj.curr;
   		          adddatatables();
   		        }  
   		    }  
    }) 
    
  });
}


//新添加
function add(){
    layer.open({
        type: 2,
        title:'撰写博客',
        maxmin: true,
        area: ['800px', '450px'],
        content:'/blog/admin/writeArticle',
      }); 
}

//执行删除操作
var deleteAll=function(datas){
    if (datas==""){
     	layer.alert('请选择相应选项'); //未选择提示
     } else{
             layer.confirm('确认要删除这些文章?', {icon: 3, title:'提示'}, 
                     function(index){
             	
             	        //ajax传递删除数据
                         $.ajax({
                             type:'post',
                             dataType:'text',
                             url:'/blog/admin/delArticles',
                             data: {ids:datas},
                             traditional: true,
                             success:function (result){
                                 if (result == "success"){
                                     layer.msg('删除成功',{
                                         icon: 1,
                                         time: 1000 //2秒关闭（如果不配置，默认是3秒）
                                    }, function(){
                                    }); 
                                     window.location.reload();
                                 }
                             },
                             error: function (XMLResponse) {
                                 alert(XMLResponse.responseText);
                             }
                         });
                   }); 
     }
}

//删除单个文章
var del=function(id){
             layer.confirm('确认要删除这些文章?', {icon: 3, title:'提示'}, 
                     function(index){
             	
             	        //ajax传递删除数据
                         $.ajax({
                             type:'post',
                             dataType:'text',
                             url:'/blog/admin/delArticle',
                             data: {id:id},
                             traditional: true,
                             success:function (result){
                                 if (result == "success"){
                                     layer.msg('删除成功',{
                                         icon: 1,
                                         time: 1000 //2秒关闭（如果不配置，默认是3秒）
                                    }, function(){
                                    }); 
                                     window.location.reload();
                                 }
                             },
                             error: function (XMLResponse) {
                                 alert(XMLResponse.responseText);
                             }
                         });
                   }); 
}