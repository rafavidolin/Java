package br.opet.tds172a.veterinarioemcasa.view;

import java.io.Serializable;

import br.opet.tds172a.veterinarioemcasa.controller.VeterinarioController;
import br.opet.tds172a.veterinarioemcasa.vo.Veterinario;

public class VeterinarioView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5453962556049839651L;
	/**
	 * Atributos do Veterinario respeitando o princípio do encapsulamento
	 **/

	private VeterinarioController veterinarioController;

	private Veterinario veterinario;

	/**
	 * Construtor VeterinarioView passando como parametro a Classe
	 * VeterinarioController
	 **/
	public VeterinarioView() {
		this.veterinarioController = new VeterinarioController();
		this.setVeterinario(new Veterinario());
	}

	public String efetuarLogin() {
		this.setVeterinario(new Veterinario());
		return "pages/menu/menu";
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}
}
