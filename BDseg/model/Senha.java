package model;

public class Senha {
	private int id;
	private String chaveSecreta;
	
	public Senha(String chaveSecreta) {
		this.chaveSecreta = chaveSecreta;
	}
	
	public Senha() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChaveSecreta() {
		return chaveSecreta;
	}
	public void setChaveSecreta(String chaveSecreta) {
		this.chaveSecreta = chaveSecreta;
	}
	
	
}
