package br.opet.tds172a.veterinarioemcasa.controller;

import java.util.List;

import br.opet.tds172a.veterinarioemcasa.model.UsuarioModel;
import br.opet.tds172a.veterinarioemcasa.model.VeterinarioModel;
import br.opet.tds172a.veterinarioemcasa.vo.Atendimento;
import br.opet.tds172a.veterinarioemcasa.vo.Usuario;
import br.opet.tds172a.veterinarioemcasa.vo.Veterinario;

public class UsuarioController {

	/**
	 * Atributo do VeterinarioController respeitando o principio do encapsulamento
	 **/
	private UsuarioModel usuarioModel;

	/**
	 * Construtor publico da classe VeterinarioController passando como parametro a
	 * classe VeterinarioModel
	 **/
	public UsuarioController() {
		this.usuarioModel = new UsuarioModel();
	}

	/**
	 * Método publico para inserir o medico
	 **/

	public Usuario inserirUsuario(Usuario usuario) {
		{
			if (usuario == null)
				return new Usuario();	

			// Chamando a camada de persistência
			usuario = usuarioModel.inserirUsuario(usuario);

			// Verificando se houve erro de criação
			if (usuario == null)
				return new Usuario();
			System.out.println("Problemas na gravação do contato");

			// Retornando o indicativo de sucesso com o objeto criado
			return new Usuario();			
		}
	}
	
	public Usuario excluirUsuario(Usuario usuario) {
		return this.usuarioModel.excluirUsuario(usuario);
	}	
	
	/**
	 * Método publico para listar os medicos
	 **/
	public List<Veterinario> listarVeterinario() {
		List<Veterinario> listaVeterinarioRecuperado = VeterinarioModel.listarVeterinario();
		return listaVeterinarioRecuperado;
	}
	
	public Usuario alterarUsuario(Usuario usuario) {
		return this.usuarioModel.alterarUsuario(usuario);
	}

	public Usuario efetuarLogin(Usuario usuario) {
		return this.usuarioModel.efetuarLogin(usuario);
	}

	public Usuario alterarSenha(Usuario usuario) {
		return this.usuarioModel.alterarSenha(usuario);
	}

	public List<Veterinario> listarTodosVeterinarios() {
		return this.usuarioModel.listarTodosVeterinarios();
	}

	public List<Atendimento> agenda(Veterinario veterinario) {
		return this.usuarioModel.agenda(veterinario);
	}	
}
