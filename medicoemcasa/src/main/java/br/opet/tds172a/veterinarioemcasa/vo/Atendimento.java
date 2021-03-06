package br.opet.tds172a.veterinarioemcasa.vo;

public class Atendimento {


	private String nomeUser;
	private String enderešo;	
	private java.sql.Timestamp dataConsulta;
	private String statusConsulta;
	private int id_consulta;
	
	public Atendimento(String nomeUser, String enderešo, java.sql.Timestamp dataConsulta, String statusConsulta, int id_consulta) {
	
		this.nomeUser = nomeUser;
		this.enderešo = enderešo;
		this.dataConsulta = dataConsulta;
		this.statusConsulta = statusConsulta;
		this.id_consulta = id_consulta;
	}


	public String getNomeUser() {
		return nomeUser;
	}


	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}


	public String getEnderešo() {
		return enderešo;
	}

	public int getId_consulta() {
		return id_consulta;
	}


	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}

	public void setEnderešo(String enderešo) {
		this.enderešo = enderešo;
	}


	public java.sql.Timestamp getDataConsulta() {
		return dataConsulta;
	}


	public void setDataConsulta(java.sql.Timestamp dataConsulta) {
		this.dataConsulta = dataConsulta;
	}


	public String getStatusConsulta() {
		return statusConsulta;
	}


	public void setStatusConsulta(String statusConsulta) {
		this.statusConsulta = statusConsulta;
	}
}
