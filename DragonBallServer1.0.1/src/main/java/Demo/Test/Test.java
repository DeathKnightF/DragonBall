package Demo.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import Demo.Dao.UserDao;
import Demo.Service.UserService;

public class Test {
	
	public static void main(String[] args) throws SQLException, IOException {
//		ApplicationContext ioc=new ClassPathXmlApplicationContext("ApplicationContext.xml");
//		DataSource bean = (DataSource)ioc.getBean("dataSource");
//		System.out.println(bean.getConnection());
//		JdbcTemplate bean2 = ioc.getBean(JdbcTemplate.class);
//		System.out.println(bean2.queryForObject("select top_score from scores where id=1",Integer.class));
		
//		System.out.println(bean2);
//		System.out.println(UpdateDao.signUp("a", "a"));
//		System.out.println(UpdateDao.jdbcTemplate);
//		System.out.println(UpdateDao.dataSource);
//		System.out.println(QueryDao.jdbcTemplate);
		
//		System.out.println(userDao.getCoins(1));
//		Test bean = ioc.getBean(Test.class);
//		System.out.println(bean.userDao);
//		System.out.println(bean.getCoins());
//		UserDao bean2 = ioc.getBean(UserDao.class);
//		System.out.println(bean2.getCoins(1));
		
//		System.out.println(UserService.signUp("wtf", "d"));
//		UserService.updateCoins(22, 66666);
//		UserService.updateTopScore(22, 66666);
//		UserService.updateTopScoreBoss(22, 66666);
//		System.out.println(UserService.getCoins(22)+" "+
//		UserService.getTopScore(22)+" "+UserService.getTopScoreBoss(22))
		System.out.println(UserService.signUp("test7", "test"));
	}
}
