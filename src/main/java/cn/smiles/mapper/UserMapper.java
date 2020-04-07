package cn.smiles.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.smiles.domain.User;

@Mapper
public interface UserMapper {
    
    @Insert("insert into user (account,password,phone) values  (#{account},#{password},#{phone})")
	@Options(useGeneratedKeys = true)
	int insert(User user); 
	
	@Delete("delete from user where id=#{id}")
	int delete(@Param("id")int id);
	
	@Select("select id,account,password,phone,ctime from user where id=#{id}")
	User getById(@Param("id")int id);
	
	@Select("select * from user where account=#{account} or phone=#{phone} limit 1")
    User getByAccountOrPhone(@Param("account")String account,@Param("phone")String phone);	
	
}
