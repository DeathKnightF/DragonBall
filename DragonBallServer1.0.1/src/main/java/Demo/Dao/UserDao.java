package Demo.Dao;

import java.sql.Timestamp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao {

	@Select("select id " + 
				"from accounts " + 
				"where user_name=#{userName} and pass_word=#{password}")
	public boolean signIn(@Param("userName")String userName,@Param("password")String password);

	@Select("select id " + 
				"from accounts " + 
				"where user_name=#{userName}")
	public int getUserId(@Param("userName")String userName) ;

	@Select("select top_score " + 
				"from scores " + 
				"where id=#{id}")
	public int getTopScore(@Param("id")int id) ;

	@Select("select top_score_boss " + 
				"from scores " + 
				"where id=#{id}")
	public int getTopScoreBoss(@Param("id")int id);

	@Select("select coins " + 
				"from user_data " + 
				"where id=#{id}")
	public int getCoins(@Param("id")int id) ;
	
	//<!--------------------------------更新--------------------------------------->
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Insert("insert into accounts(user_name,pass_word,sign_up_date) values(#{userName},#{password},#{date})")
	public boolean signUp(@Param("userName")String userName,@Param("password")String password,@Param("date")Timestamp date);
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Insert("insert into scores(id) values(#{id})")
	public void initScores(@Param("id")int id);

	@Transactional(propagation = Propagation.REQUIRED)
	@Insert("insert into user_data(id) values(#{id})")
	public void initUserData(@Param("id")int id);
	
	@Update("update user_data set coins=coins+#{addition} where id=#{id}")
	public void updateCoins(@Param("id")int id,@Param("addition")int addition) ;

	@Update("update scores set top_score=#{score} where id=#{id}")
	public void updateTopScore(@Param("id")int id,@Param("score")int score) ;

	@Update("update scores set top_score_boss=#{score} where id=#{id}")
	public void updateTopScoreBoss(@Param("id")int id,@Param("score")int score);

}
