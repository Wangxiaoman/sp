package cn.smiles.domain;
import java.util.Date;
import lombok.Data;

@Data
public class SmilesCollect {

	private int id;
	private int userId;
	private int smilesId;
	private Date ctime;

}