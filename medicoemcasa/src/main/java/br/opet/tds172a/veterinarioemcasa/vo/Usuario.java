package br.opet.tds172a.veterinarioemcasa.vo;

import java.util.Date;

public class Usuario extends Pessoa {

	public Usuario(String nome, String email, long telefone, long cpf, Date dataNascimento, String endereco,
			String senha,int ddd) {
		super(nome, email, telefone, cpf, dataNascimento, endereco, senha,ddd);
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
}
