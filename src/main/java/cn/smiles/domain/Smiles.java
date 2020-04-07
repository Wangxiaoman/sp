package cn.smiles.domain;
import java.util.Date;

import lombok.Data;

@Data
public class Smiles {

	private int id;
	private String smiles;
	private String jme;
	private String pngStr;
	private Date ctime;
	private String description;
	private boolean userCollect;

}