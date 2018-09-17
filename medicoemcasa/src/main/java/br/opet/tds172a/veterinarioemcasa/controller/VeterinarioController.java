package br.opet.tds172a.veterinarioemcasa.controller;

import java.util.List;

import br.opet.tds172a.veterinarioemcasa.model.VeterinarioModel;
import br.opet.tds172a.veterinarioemcasa.vo.Usuario;
import br.opet.tds172a.veterinarioemcasa.vo.Veterinario;

public class VeterinarioController {

	/**
	 * Atributo do VeterinarioController respeitando o principio do encapsulamento
	 **/
	private VeterinarioModel veterinarioModel;

	/**
	 * Construtor publico da classe VeterinarioController passando como parametro a
	 * classe VeterinarioModel
	 **/
	public VeterinarioController() {
		this.veterinarioModel = new VeterinarioModel();
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
			veterinario = veterinarioModel.inserirMedico(veterinario);

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
	public List<Veterinario> listarVeterinario() {
		List<Veterinario> listaVeterinarioRecuperado = VeterinarioModel.listarVeterinario();
		return listaVeterinarioRecuperado;
	}
	
	public Veterinario alterarVeterinario(Veterinario veterinario) {
		return this.veterinarioModel.alterarVeterinario(veterinario);
	}	

	public Veterinario efetuarLogin(Veterinario veterinario) {
		return this.veterinarioModel.efetuarLogin(veterinario);
	}

	public String verificaTipo(Veterinario veterinario) {
		return this.veterinarioModel.verificaTipo(veterinario);
	}

	public Veterinario excluirVeterinario(Veterinario veterinario) {
		return this.veterinarioModel.excluirVeterinario(veterinario);
	}

	public Veterinario alterarSenha(Veterinario veterinario) {
		return this.veterinarioModel.alterarSenha(veterinario);
	}

	public Veterinario marcarConsulta(Veterinario veterinario, Usuario usuario) {
		return this.veterinarioModel.marcarConsulta(veterinario, usuario);		
	}

	public boolean alteraStatusConsultaSim(int idConsulta) {
		boolean alterouStatus = this.veterinarioModel.alteraStatusConsultaSim(idConsulta);	
		return alterouStatus;
	}

	public boolean alteraStatusConsultaNao(int idConsulta) {
		boolean alterouStatus = this.veterinarioModel.alteraStatusConsultaNao(idConsulta);	
		return alterouStatus;
	}
}
