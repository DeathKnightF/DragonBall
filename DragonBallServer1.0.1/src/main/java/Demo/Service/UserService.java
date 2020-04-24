package Demo.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import Demo.Dao.UserDao;
import Demo.Data.Protocol;

@Service
public class UserService {
	static SqlSessionFactory sqlSessionFactory;
	static {
		String resource="mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 登陆
	 * @param userName
	 * @param password
	 * @return
	 */
	public static int signIn(String userName,String password) {
		SqlSession session=null;
		try {
			session = sqlSessionFactory.openSession();
			UserDao mapper = session.getMapper(UserDao.class);
			mapper.signIn(userName, password);
		} catch(org.apache.ibatis.binding.BindingException e){
			return Protocol.USER_NAME_OR_PASSWORD_ERROR;
		}finally {
			session.close();
		}
		return getUserId(userName);
	}
	/**
	 * 根据用户名获取id
	 * @param userName
	 * @return
	 */
	public static int getUserId(String userName) {
		int id=0;
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			UserDao mapper = session.getMapper(UserDao.class);
			id=mapper.getUserId(userName);
		} finally {
			session.close();
		}
		return id;
	}
	/**
	 * 根据id获取最高成绩
	 * @param id
	 * @return
	 */
	public static int getTopScore(int id) {
		int score=0;
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			UserDao mapper = session.getMapper(UserDao.class);
			score=mapper.getTopScore(id);
		} finally {
			session.close();
		}
		return score;
	}
	/**
	 * 根据id获取boss模式最高成绩
	 * @param id
	 * @return
	 */
	public static int getTopScoreBoss(int id) {
		int score=0;
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			UserDao mapper = session.getMapper(UserDao.class);
			score=mapper.getTopScoreBoss(id);
		} finally {
			session.close();
		}
		return score;
	}
	/**
	 * 根据id获取金币
	 * @param id
	 * @return
	 */
	public static int getCoins(int id) {
		int coins=0;
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			UserDao mapper = session.getMapper(UserDao.class);
			coins=mapper.getCoins(id);
		} finally {
			session.close();
		}
		return coins;
	}
	
	//--------------------------------------------------------------------
	
	/**
	 * 注册
	 * @param userName
	 * @param password
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public static int signUp(String userName,String password) {
		SqlSession session = null;
		int id=0;
		try {
			session = sqlSessionFactory.openSession(true);
			UserDao mapper = session.getMapper(UserDao.class);
			mapper.signUp(userName, password, new Timestamp(System.currentTimeMillis()));
			id=getUserId(userName);
			mapper.initScores(id);
			mapper.initUserData(id);
			session.commit();
		} catch(Exception e){
			//用户名重复
			return Protocol.USER_NAME_IS_REPETITION;
		}finally {
			session.close();
		}
		return id;
	}
	/**
	 * 更新最高分数
	 * @param id
	 * @param score
	 */
	public static void updateTopScore(int id,int score) {
		SqlSession session = null;
		try {
			session=sqlSessionFactory.openSession(true);
			UserDao mapper = session.getMapper(UserDao.class);
			mapper.updateTopScore(id, score);
		}finally {
			session.close();
		}
	}
	/**
	 * 更新BOSS模式最高分数
	 * @param id
	 * @param score
	 */
	public static void updateTopScoreBoss(int id,int score) {
		SqlSession session = null;
		try {
			session=sqlSessionFactory.openSession(true);
			UserDao mapper = session.getMapper(UserDao.class);
			mapper.updateTopScoreBoss(id, score);
		}finally {
			session.close();
		}
	}
	/**
	 * 更新金币最高分数
	 * @param id
	 * @param score
	 */
	public static void updateCoins(int id,int coins) {
		SqlSession session = null;
		try {
			session=sqlSessionFactory.openSession(true);
			UserDao mapper = session.getMapper(UserDao.class);
			mapper.updateCoins(id, coins);
		}finally {
			session.close();
		}
	}
}
