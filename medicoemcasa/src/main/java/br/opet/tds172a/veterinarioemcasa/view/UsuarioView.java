package br.opet.tds172a.veterinarioemcasa.view;

import java.io.Serializable;

import br.opet.tds172a.veterinarioemcasa.controller.UsuarioController;
import br.opet.tds172a.veterinarioemcasa.vo.Usuario;

public class UsuarioView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5453962556049839651L;
	/**
	 * Atributos do Veterinario respeitando o princípio do encapsulamento
	 **/

	private UsuarioController usuarioController;

	private Usuario usuario;

	/**
	 * Construtor VeterinarioView passando como parametro a Classe
	 * VeterinarioController
	 **/
	public UsuarioView() {
		this.usuarioController = new UsuarioController();
		this.setUsuario(new Usuario());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
