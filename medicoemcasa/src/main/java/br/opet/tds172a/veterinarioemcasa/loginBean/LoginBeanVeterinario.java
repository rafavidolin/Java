package br.opet.tds172a.veterinarioemcasa.loginBean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.opet.tds172a.veterinarioemcasa.controller.UsuarioController;
import br.opet.tds172a.veterinarioemcasa.controller.VeterinarioController;
import br.opet.tds172a.veterinarioemcasa.vo.Atendimento;
import br.opet.tds172a.veterinarioemcasa.vo.Usuario;
import br.opet.tds172a.veterinarioemcasa.vo.Veterinario;

@Named("loginBeanVeterinario")
@SessionScoped
public class LoginBeanVeterinario implements Serializable {

	private boolean statusConsultar;
	private boolean statusIncluir;
	private boolean statusAlterar;
	private boolean statusExcluir;
	private boolean statusCodigo;
	private List<Veterinario> listaveterinarios;
	private List<Atendimento> retornaAgenda;
	private Veterinario veterinario;
	private Usuario usuario;
	private VeterinarioController veterinarioController;
	private UsuarioController usuarioController;
	private Atendimento agenda;

	FacesContext contexto = FacesContext.getCurrentInstance();
	
	public Atendimento getAgenda() {
		return agenda;
	}

	public void setAgenda(Atendimento agenda) {
		this.agenda = agenda;
	}

	/**
	 * @return the listaveterinarios
	 */
	public List<Veterinario> getListaveterinarios() {
		return listaveterinarios;
	}

	/**
	 * @param listaveterinarios
	 *            the listaveterinarios to set
	 */
	public void setListaveterinarios(List<Veterinario> listaveterinarios) {
		this.listaveterinarios = listaveterinarios;
	}

	private String nome;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5283694613334490671L;

	private void restaurarStatusBotoes() {
		// Acertando o status dos botoes
		statusCodigo = false;
		statusConsultar = false;
		statusIncluir = false;
		statusAlterar = true;
		statusExcluir = true;
	}

	/*
	 * private UsuarioController usuarioController;
	 */

	public LoginBeanVeterinario() {

		this.setVeterinario(new Veterinario());
		this.setUsuario(new Usuario());
		this.veterinarioController = new VeterinarioController();
		this.usuarioController = new UsuarioController();
		this.listaveterinarios = listarTodosVeterinarios();
	}

	public String efetuarLogin() {

		FacesContext contexto = FacesContext.getCurrentInstance();

		String tipoUsuario = this.veterinarioController.verificaTipo(veterinario);

		if (tipoUsuario.equals("erro")) {

			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Inválido!", null));

			this.veterinario = new Veterinario();
			return "/pages/login/login";

		}

		if (tipoUsuario.equals("veterinario")) {
			this.veterinario = this.veterinarioController.efetuarLogin(veterinario);
			return "/pages/menu/menuVeterinario";

		}

		if (tipoUsuario.equals("usuario")) {
			this.usuario.setCpf(veterinario.getCpf());
			this.usuario.setSenha(veterinario.getSenha());
			this.usuario = this.usuarioController.efetuarLogin(usuario);
			return "/pages/menu/menuUsuario";
		}

		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Inválido!", null));

		this.veterinario = new Veterinario();
		return "/pages/login/login";

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String alterarSenha() {
		FacesContext contexto = FacesContext.getCurrentInstance();

		System.out.println("Cadastro Contato - Alterar - Entrada - " + veterinario);

		if (veterinarioController.alterarSenha(veterinario) != null) {
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha alterado com sucesso!", null));

			// Acertando o status dos botoes
			statusCodigo = true;
			statusConsultar = true;
			statusIncluir = true;
			statusAlterar = false;
			statusExcluir = false;
			this.listaveterinarios = listarTodosVeterinarios();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha não alterada !", null));
		}
		System.out.println("Cadastro Contato - Alterar - Saï¿½da   - " + veterinario);

		return "/pages/menu/alterarSenha";
	}

	public String alterarSenhaUsuario() {
		FacesContext contexto = FacesContext.getCurrentInstance();

		System.out.println("Cadastro Contato - Alterar - Entrada - " + usuario);

		if (usuarioController.alterarSenha(usuario) != null) {
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha alterado com sucesso!", null));

			// Acertando o status dos botoes
			statusCodigo = true;
			statusConsultar = true;
			statusIncluir = true;
			statusAlterar = false;
			statusExcluir = false;
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha não alterada !", null));
		}
		System.out.println("Cadastro Contato - Alterar - Saï¿½da   - " + usuario);

		return "/pages/menu/alterarSenhaUsuario";
	}

	public String efetuarLogout() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		this.veterinario = new Veterinario();
		this.usuario = new Usuario();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "LOGOUT REALIZADO!", null));
		return "/pages/login/login";
	}

	public String cadastrar() {
		this.veterinario = new Veterinario();
		return "/pages/login/cadastrar";
	}

	public String limparUsuario() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		System.out.println("Limpou");

		veterinario = new Veterinario();

		restaurarStatusBotoes();

		System.out.println("Limpou");

		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Você limpou a tela!", null));
		return "/pages/login/cadastrar";
	}

	public String limpar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		System.out.println("Limpou");

		usuario = new Usuario();

		restaurarStatusBotoes();

		System.out.println("Limpou");

		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Você limpou a tela!", null));
		return "/pages/login/cadastrar";
	}

	public String incluir() {
		FacesContext contexto = FacesContext.getCurrentInstance();

		System.out.println("Cadastro Contato - Incluir - Entrada - " + veterinario);

		// Verificando se existe algum erro. Caso haja sai do mï¿½todo e volta para a
		// tela
		if (!contexto.getMessageList().isEmpty()) {
			return "cadastrar";
		}

		veterinario = veterinarioController.inserirMedico(veterinario);
		if (veterinario.isOk()) {
			// Acertando o status dos botoes
			statusCodigo = true;
			statusConsultar = true;
			statusIncluir = true;
			statusAlterar = false;
			statusExcluir = false;
			this.listaveterinarios = listarTodosVeterinarios();
		} else {
		}
		System.out.println("Cadastro Contato - Incluir - Saï¿½da   - " + veterinario);

		return "CadastroContato";
	}

	public String incluirUsuario() {
		FacesContext contexto = FacesContext.getCurrentInstance();	

		// Verificando se existe algum erro. Caso haja sai do mï¿½todo e volta para a
		// tela
		if (!contexto.getMessageList().isEmpty()) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Preencha todos os campos!", null));
			return "/pages/login/cadastrarVeterinario";
		}

		usuario = usuarioController.inserirUsuario(usuario);
		if (usuario.isOk()) {
			// Acertando o status dos botoes
			statusCodigo = true;
			statusConsultar = true;
			statusIncluir = true;
			statusAlterar = false;
			statusExcluir = false;
		} else {
		}
		contexto.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Veterinario Cadastrado com sucesso!", null));
		System.out.println("Cadastro Contato - Incluir - Saï¿½da   - " + usuario);

		return "/pages/login/cadastrarUsuario";
	}

	public String editar() {
		
		if (veterinarioController.alterarVeterinario(veterinario) != null) {
			
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Veterinario alterado com sucesso!", null));

			// Acertando o status dos botoes
			statusCodigo = true;
			statusConsultar = true;
			statusIncluir = true;
			statusAlterar = false;
			statusExcluir = false;
			this.listaveterinarios = listarTodosVeterinarios();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Veterinario não alterado!", null));
		}
		System.out.println("Cadastro Contato - Alterar - Saï¿½da   - " + veterinario);

		return "/pages/menu/menuVeterinario";
	}

	public String editarUsuario() {
		FacesContext contexto = FacesContext.getCurrentInstance();

		if (usuarioController.alterarUsuario(usuario) != null) {
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario alterado com sucesso!", null));

			// Acertando o status dos botoes
			statusCodigo = true;
			statusConsultar = true;
			statusIncluir = true;
			statusAlterar = false;
			statusExcluir = false;
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario não alterado!", null));
		}
		System.out.println("Cadastro Contato - Alterar - Saï¿½da   - " + usuario);

		return "/pages/menu/menuUsuario";
	}

	public String excluirVeterinario() {
		FacesContext contexto = FacesContext.getCurrentInstance();

		if (veterinarioController.excluirVeterinario(veterinario) != null) {
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Veterinario excluido com sucesso!", null));

			// Acertando o status dos botoes
			statusCodigo = true;
			statusConsultar = true;
			statusIncluir = true;
			statusAlterar = false;
			statusExcluir = false;
			this.listaveterinarios = listarTodosVeterinarios();
			veterinario = new Veterinario();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Veterinario não excluido!", null));
		}

		return "/pages/login/login";
	}

	public String excluirUsuario() {
		FacesContext contexto = FacesContext.getCurrentInstance();

		if (usuarioController.excluirUsuario(usuario) != null) {
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario excluido com sucesso!", null));

			// Acertando o status dos botoes
			statusCodigo = true;
			statusConsultar = true;
			statusIncluir = true;
			statusAlterar = false;
			statusExcluir = false;

			usuario = new Usuario();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario não excluido!", null));
		}

		return "/pages/login/login";
	}

	public List<Veterinario> listarTodosVeterinarios() {
		this.listaveterinarios = usuarioController.listarTodosVeterinarios();
		return this.listaveterinarios;
	}

	public String agenda() {

		List<Atendimento> retornaAgenda = usuarioController.agenda(veterinario);
		setRetornaAgenda(retornaAgenda);

		return "/pages/menu/agenda";
	}

	public String alteraStatusConsultaSim(Atendimento agenda) {
		FacesContext contexto = FacesContext.getCurrentInstance();

		int idConsulta = agenda.getId_consulta();

		boolean alterouStatus = false;

		alterouStatus = veterinarioController.alteraStatusConsultaSim(idConsulta);

		if (alterouStatus) {

			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Status alterado!", null));

			return "/pages/menu/menuVeterinario";

		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Status não alterado!", null));
			return "/pages/menu/menuVeterinario";
		}
	}

	public String alteraStatusConsultaNao(Atendimento agenda) {
		FacesContext contexto = FacesContext.getCurrentInstance();

		int idConsulta = agenda.getId_consulta();

		boolean alterouStatus = false;

		alterouStatus = veterinarioController.alteraStatusConsultaNao(idConsulta);

		if (alterouStatus) {

			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Status alterado!", null));
			return "/pages/menu/menuVeterinario";

		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Status não alterado!", null));
			return "/pages/menu/menuVeterinario";
		}

	}

	public String marcarConsulta(Veterinario veterinario) {
		this.veterinario = veterinario;
		return "/pages/menu/marcarConsulta";
	}

	public String marcarConsultaVeterinario() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		this.veterinario = veterinarioController.marcarConsulta(this.veterinario, this.usuario);
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta marcada com sucesso!", null));
		return "/pages/menu/pesquisaVeterinario";
	}

	public boolean isStatusConsultar() {
		return statusConsultar;
	}

	public void setStatusConsultar(boolean statusConsultar) {
		this.statusConsultar = statusConsultar;
	}

	public boolean isStatusIncluir() {
		return statusIncluir;
	}

	public void setStatusIncluir(boolean statusIncluir) {
		this.statusIncluir = statusIncluir;
	}

	public boolean isStatusExcluir() {
		return statusExcluir;
	}

	public void setStatusExcluir(boolean statusExcluir) {
		this.statusExcluir = statusExcluir;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	public VeterinarioController getVeterinarioController() {
		return veterinarioController;
	}

	public void setVeterinarioController(VeterinarioController veterinarioController) {
		this.veterinarioController = veterinarioController;
	}

	/**
	 * @return the agenda
	 */
	public List<Atendimento> getRetornaAgenda() {
		return retornaAgenda;
	}

	/**
	 * @param agenda
	 *            the agenda to set
	 */
	public void setRetornaAgenda(List<Atendimento> agenda) {
		this.retornaAgenda = agenda;
	}

}
