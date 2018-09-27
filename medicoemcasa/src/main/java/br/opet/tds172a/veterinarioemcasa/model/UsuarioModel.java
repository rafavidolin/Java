package br.opet.tds172a.veterinarioemcasa.model;

import java.util.List;

import br.opet.tds172a.veterinarioemcasa.model.dao.UsuarioDAO;
import br.opet.tds172a.veterinarioemcasa.vo.Atendimento;
import br.opet.tds172a.veterinarioemcasa.vo.Usuario;
import br.opet.tds172a.veterinarioemcasa.vo.Veterinario;

public class UsuarioModel {
	/**
	 * Atributo do VeterinarioController respeitando o principio do encapsulamento
	 **/
	private UsuarioDAO usuarioDAO;	

	/**
	 * Construtor publico da classe VeterinarioController passando como parametro a
	 * classe VeterinarioModel
	 **/
	public UsuarioModel() {
		this.usuarioDAO = new UsuarioDAO();
	}

	/**
	 * Método publico para inserir o medico
	 **/

	public Usuario inserirUsuario(Usuario usuario) {
		{
			if (usuario == null)
				return new Usuario();
			
			// Chamando a camada de persistência
			usuario = usuarioDAO.inserirUsuario(usuario);

			// Verificando se houve erro de criação
			if (usuario == null)
				return new Usuario();
			System.out.println("Problemas na gravação do contato");

			// Retornando o indicativo de sucesso com o objeto criado
			return new Usuario();
		}
	}

	public Usuario excluirUsuario(Usuario usuario) {
		return this.usuarioDAO.excluirUsuario(usuario);
	}

	/**
	 * Método publico para listar os medicos
	 **/
	public List<Veterinario> listarVeterinario() {
		List<Veterinario> listaVeterinarioRecuperado = VeterinarioModel.listarVeterinario();
		return listaVeterinarioRecuperado;
	}

	public Usuario alterarUsuario(Usuario usuario) {
		return this.usuarioDAO.alterarUsuario(usuario);
	}

	public Usuario efetuarLogin(Usuario usuario) {
		return this.usuarioDAO.efetuarLogin(usuario);
	}

	public Usuario alterarSenha(Usuario usuario) {
		return this.usuarioDAO.alterarSenha(usuario);
	}

	public List<Veterinario> listarTodosVeterinarios() {
		return this.usuarioDAO.listarTodosVeterinarios();
	}

	public List<Atendimento> agenda(Veterinario veterinario) {
		return this.usuarioDAO.agenda(veterinario);
	}
}
