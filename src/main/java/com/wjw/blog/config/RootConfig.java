package com.wjw.blog.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wjw.blog.dao.aritcleMapper;
import com.wjw.blog.dao.aritcletypeMapper;
import com.wjw.blog.dao.commentMapper;
import com.wjw.blog.dao.consumerMapper;
import com.wjw.blog.entity.aritcletype;

@Configuration
@ComponentScan(basePackages= {"com.wjw.blog"},
		excludeFilters={
			@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)	
		})
@PropertySource("classpath:generator/jdbc.properties")
@EnableTransactionManagement
//@MapperScan("com.wjw.blog.dao")
public class RootConfig{
	
	//@Value注解变得有效
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Autowired
	Environment environment;
	
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	
	@Bean
	public BasicDataSource dataSource(){
		
		BasicDataSource dSource=new BasicDataSource();

		dSource.setDriverClassName(driverClassName);

		dSource.setUrl(url);

		dSource.setUsername(username);

		dSource.setPassword(password);
		
		System.out.println(username);
		System.out.println(password);
		System.out.println(url);
		
		dSource.setInitialSize(5);
		dSource.setMaxActive(10);
		dSource.setMinIdle(5);
		dSource.setMaxWait(15);
		dSource.setMaxWait(15);
		return dSource;
	}
	

	@Bean
	DataSourceTransactionManager dataSourceTransactionManager(BasicDataSource dataSource) {
		DataSourceTransactionManager dstm=new DataSourceTransactionManager();
		dstm.setDataSource(dataSource);
		return dstm;
	}
	
	
	//SqlSessionFactoryBean
	@Bean
	SqlSessionFactoryBean sqlSessionFactoryBean(BasicDataSource dataSource){
		SqlSessionFactoryBean sqlSessionFactory=new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setConfigLocation(new ClassPathResource("sqlMapConfig.xml"));
		return sqlSessionFactory;
	}
	
	@Bean
	MapperFactoryBean<consumerMapper> mapperFactoryBean(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception
	{
		MapperFactoryBean<consumerMapper>mapperFactoryBean=new MapperFactoryBean<consumerMapper>();
		
		mapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
		
		mapperFactoryBean.setMapperInterface(consumerMapper.class);
		
		return mapperFactoryBean;
	}
	
	@Bean
	MapperFactoryBean<aritcleMapper> mapperFactoryBean1(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception
	{
		MapperFactoryBean<aritcleMapper>mapperFactoryBean=new MapperFactoryBean<aritcleMapper>();
		
		mapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
		
		mapperFactoryBean.setMapperInterface(aritcleMapper.class);
		
		return mapperFactoryBean;
	}
	
	@Bean
	MapperFactoryBean<aritcletypeMapper> mapperFactoryBean2(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception
	{
		MapperFactoryBean<aritcletypeMapper>mapperFactoryBean=new MapperFactoryBean<aritcletypeMapper>();
		
		mapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
		
		mapperFactoryBean.setMapperInterface(aritcletypeMapper.class);
		
		return mapperFactoryBean;
	}
	
	@Bean
 	MapperFactoryBean<commentMapper> mapperFactoryBean3(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception
	{
		MapperFactoryBean<commentMapper>mapperFactoryBean=new MapperFactoryBean<commentMapper>();
		
		mapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
		
		mapperFactoryBean.setMapperInterface(commentMapper.class);
		
		return mapperFactoryBean;
	}
	
	
}
