package cn.smiles.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.smiles.domain.SmilesCollect;

@Mapper
public interface SmilesCollectMapper {
    
    @Insert("insert into smiles_collect (user_id,smiles_id) values  (#{userId},#{smilesId})")
	int insert(@Param("userId")int userId,@Param("smilesId")int smilesId); 
	
	@Delete("delete from smiles_collect where smiles_id=#{smilesId} and user_id=#{userId}")
	int delete(@Param("userId")int userId,@Param("smilesId")int smilesId);
	
	@Select("select * from smiles_collect where smiles_id=#{smilesId} and user_id=#{userId}")
	SmilesCollect getSmilesCollect(@Param("userId")int userId,@Param("smilesId")int smilesId);
	
	@Select("select * from smiles_collect where user_id=#{userId}")
	List<SmilesCollect> queryList(@Param("userId")int userId);
	
	@Select({
        "<script>"
            + "select * from select id,user_id,smiles_id,ctime from smiles_collect WHERE id in "
            + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            +    "#{item}"
            + "</foreach>" 
        +"</script>" 
    })
    List<SmilesCollect> queryListByIds(@Param("ids")List<Integer> ids);
	
}
