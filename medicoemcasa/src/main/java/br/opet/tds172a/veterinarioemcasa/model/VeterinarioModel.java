package br.opet.tds172a.veterinarioemcasa.model;

import java.util.List;

import br.opet.tds172a.veterinarioemcasa.model.dao.VeterinarioDAO;
import br.opet.tds172a.veterinarioemcasa.vo.Usuario;
import br.opet.tds172a.veterinarioemcasa.vo.Veterinario;

public class VeterinarioModel {

	/**
	 * Atributo do VeterinarioModel respeitando o principio do encapsulamento
	 **/
	private VeterinarioDAO veterinarioDAO;

	/**
	 * Construtor publico da classe VeterinarioModel passando como parametro a
	 * classe VeterinarioDAO
	 **/
	public VeterinarioModel() {
		this.veterinarioDAO = new VeterinarioDAO();
	}

	/**
	 * Método publico para inserir o medico
	 **/
	public Veterinario inserirMedico(Veterinario veterinario) {
		{
			if (veterinario == null)
				return new Veterinario();
			System.out.println("Tentativa de inserir um veterinario nulo");

			// Chamando a camada de persistência
			veterinario = veterinarioDAO.inserirMedico(veterinario);

			// Verificando se houve erro de criação
			if (veterinario == null)
				return new Veterinario();
			System.out.println("Problemas na gravação do contato");

			// Retornando o indicativo de sucesso com o objeto criado
			return new Veterinario();
		}
	}

	/**
	 * Método publico para listar os medicos
	 **/
	public static List<Veterinario> listarVeterinario() {
		List<Veterinario> listaVeterinarioRecuperado = VeterinarioDAO.listarVeterinario();
		return listaVeterinarioRecuperado;
	}

	public Veterinario efetuarLogin(Veterinario veterinario) {
		return this.veterinarioDAO.efetuarLogin(veterinario);
	}

	public Veterinario alterarVeterinario(Veterinario veterinario) {
		return this.veterinarioDAO.alterarVeterinario(veterinario);
	}

	public String verificaTipo(Veterinario veterinario) {
		return this.veterinarioDAO.verificaTipo(veterinario);
	}

	public Veterinario excluirVeterinario(Veterinario veterinario) {
		return this.veterinarioDAO.excluirVeterinario(veterinario);
	}

	public Veterinario alterarSenha(Veterinario veterinario) {
		return this.veterinarioDAO.alterarSenha(veterinario);
	}

	public Veterinario marcarConsulta(Veterinario veterinario, Usuario usuario) {
		return this.veterinarioDAO.marcarConsulta(veterinario, usuario);
	}

	public boolean alteraStatusConsultaSim(int idConsulta) {
		boolean alterouStatus = this.veterinarioDAO.alteraStatusConsultaSim(idConsulta);
		return alterouStatus;
	}

	public boolean alteraStatusConsultaNao(int idConsulta) {
		boolean alterouStatus = this.veterinarioDAO.alteraStatusConsultaNao(idConsulta);	
		return alterouStatus;
	}
}
