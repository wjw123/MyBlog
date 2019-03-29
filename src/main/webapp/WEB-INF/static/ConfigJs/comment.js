
var commentNums=[];//要删除的ID集合
var comments="";//要删除的字符串连接体

//分页数据
var count=0;//数据总条数
var pageNumber=0;//总页数
var pageSize=5;//每一页显示的数据条数
var currentPageNumber=1;//当前显示的页面
var num=0;//标记

$ = layui.jquery; 
var titles=[ //标题栏
     {type: 'checkbox', fixed: 'left'}
	,{field: 'id', title:'ID', width:80, sort:true}
    ,{field: 'title', title: '文章题目', width: 120,
    	templet:function(row){
    		return row.aritcle.title;
    	}}
    ,{field: 'name', title: '发布人', minWidth: 120,
    	templet:function(row){
    		return row.consumer.name;
    	}}
    ,{field: 'time', title: '发布时间', width: 120,
      templet:function(row){
    	  return createTime(row.time);
      }}
    ,{field:  'content',title: '评论内容',width:120}
    ,{fixed: 'right', title:'操作', toolbar:'#barDemo', width:200}
  ];

$(function(){
	//添加表格数据
	adddatatables();
	
	//添加树形数据
	//addtree();
	
	//添加工具栏信息
	addtoolbars();	
});

//添加表格数据
var adddatatables=function(){
	  layui.use('table',function(){
		    var table=layui.table;
		    table.render({
		    elem:'#datatable'
		    ,height:350
		    ,url:'/blog/admin/getAllcomments'
		    ,page:false
		    ,loading:true
		    ,where:{pageNum:currentPageNumber,pageSize:pageSize} //传参数
		    ,toolbar:'#toolbar'
		    ,cellMinWidth:120 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    ,cols:[titles]
		    ,done:doneCallback  //设置回调函数
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

   		    jump:function(obj, first){ //触发分页后的回调  
   		        if(!first){ //一定要加此判断，否则初始时会无限刷新 
   		          currentPageNumber=obj.curr;
   		          adddatatables();
   		        }  
   		    }  
    }) 
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
			    
			if(obj.event=='deleteAll'){
				deleteAll(datas);
			 }
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

//删除多个评论
var deleteAll=function(datas){
    if (datas==""){
     	layer.alert('请选择相应选项'); //未选择提示
     } else{
             layer.confirm('确认要删除这些评论?', {icon: 3, title:'提示'}, 
                     function(index){
             	        //ajax传递删除数据
                         $.ajax({
                             type:'post',
                             dataType:'text',
                             url:'/blog/admin/delComments',
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

//删除单个评论
var del=function(id){
             layer.confirm('确认要删除该评论?', {icon: 3, title:'提示'}, 
                     function(index){
            	 
             	        //ajax传递删除数据
                         $.ajax({
                             type:'post',
                             dataType:'text',
                             url:'/blog/admin/deleteById',
                             data: {commentId:id},
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
                             error:function (XMLResponse){
                                 alert(XMLResponse.responseText);
                             }
                         });
                   }); 
}