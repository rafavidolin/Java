package br.opet.tds172a.veterinarioemcasa.vo;

import java.util.Date;

public class Veterinario extends Pessoa {

	/**
	 * Atributos da classe Veterinario privado respeitando o principio do
	 * encapsulamento
	 **/
	private int crmv;
	private Veterinario veterinario;	

	/**
	 * Construtor da classe Veterinario passando como parametro:
	 * nome,email,telefone,cpf,dataNascimento,endereco,crmv
	 **/
	public Veterinario(String nome, String email, long telefone, long cpf, Date dataNascimento, String endereco,
			int crmv,String senha,int ddd) {
		super(nome, email, telefone, cpf, dataNascimento, endereco,senha,ddd);
		this.crmv = crmv;
	}

	/**
	 * Construtor da classe Veterinario passando nada como parametro
	 **/
	public Veterinario() {

	}

	/**
	 * @return metodo que retorna o crmv
	 */
	public int getCrmv() {
		return crmv;
	}

	/**
	 * @param metodo
	 *            que seta o crmv
	 */
	public void setCrmv(int crmv) {
		this.crmv = crmv;
	} 	
	
	public Veterinario setVeterinario(Veterinario veterinario) {
		return this.veterinario = veterinario;
	}
	
}