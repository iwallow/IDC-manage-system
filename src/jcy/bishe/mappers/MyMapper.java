package jcy.bishe.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;



import jcy.bishe.models.UserModle;
import jcy.bishe.models.userInf;

public interface MyMapper {
	
	
	
	@Insert("insert into computerinf values(#{id},#{name},#{IpAddress},#{MacAddress})")
	public void insert(UserModle usermodel);
	@Insert("insert into jcyuserinf values(#{id},#{name},#{password})")
	public void signuser(userInf userinf);
	
	@Insert("insert into jcyipaddress (ipAddress,mac) values(#{0},#{1})")
	public void insertIP(String ip,String mac);
	
	@Select("select id from jcyipaddress where ipAddress=#{0} ")
	public Integer selectIP(String  um);
	
	@Select("select * from jcyuserinf where name=#{0} ")
	public userInf selectUser(String  um);
	
	@Select("select * from computerinf")
	public ArrayList<UserModle> query();
	

}
