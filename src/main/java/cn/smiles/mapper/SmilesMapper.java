package cn.smiles.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.smiles.domain.Smiles;

@Mapper
public interface SmilesMapper {
    
    @Insert("insert into smiles (smiles,jme,png_str,description) values  (#{smiles},#{jme},#{pngStr},#{description})")
	@Options(useGeneratedKeys = true)
	int insert(Smiles smiles); 
	
    @Delete("delete from smiles where id=#{id}")
    int remove(@Param("id")int id);
    
	@Update("update smiles set jme=#{jme},png_str=#{pngStr},smiles=#{smiles} where id=#{id}")
	int updateSmiles(Smiles smiles);
	
	@Update("update smiles set description=#{description} where id=#{id}")
    int updateDescription(@Param("id")int id, @Param("description")String description);
	
	@Select("select id,smiles,jme,png_str,ctime,description from smiles order by id desc limit #{offset},#{limit}")
	List<Smiles> queryList(@Param("offset")int offset,@Param("limit")int limit);
	
	@Select("select * from smiles where id=#{id}")
	Smiles getById(@Param("id")int id);
	
	@Select("select count(1) from smiles")
	int getCount();
}
