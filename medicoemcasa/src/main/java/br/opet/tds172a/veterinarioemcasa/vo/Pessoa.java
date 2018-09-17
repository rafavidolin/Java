package br.opet.tds172a.veterinarioemcasa.vo;

import java.util.Date;

public class Pessoa {

	/**
	 * Atributos da classe Pessoa privados respeitando o principio do encapsulamento
	 **/
	private String nome;
	private String email;
	private long telefone;
	private long cpf;
	private Date dataNascimento;
	private String endereco;
	private String senha;
	private int ddd;
	private boolean ok;

	/**
	 * Construtor da classe Pessoa passando como parametro:
	 * nome,email,telefone,cpf,dataNascimento,endereco
	 **/
	public Pessoa(String nome, String email, long telefone, long cpf, Date dataNascimento, String endereco,
			String senha, int ddd) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.senha = senha;
		this.ddd = ddd;

	}

	/**
	 * Construtor da classe Pessoa passando nada como parametro
	 **/
	public Pessoa() {

	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public long getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the cpf
	 */
	public long getCpf() {
		return cpf;
	}

	/**
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento
	 *            the dataNascimento to set
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the ddd
	 */
	public int getDdd() {
		return ddd;
	}

	/**
	 * @param ddd
	 *            the ddd to set
	 */
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public boolean isOk() {
		return ok;
	}
}
