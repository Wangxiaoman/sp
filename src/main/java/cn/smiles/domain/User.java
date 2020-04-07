package cn.smiles.domain;
import java.util.Date;
import lombok.Data;

@Data
public class User {

	private int id;
	private String account;
	private String password;
	private String phone;
	private Date ctime;

}