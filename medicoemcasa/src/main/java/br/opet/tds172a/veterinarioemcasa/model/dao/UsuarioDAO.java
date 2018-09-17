package br.opet.tds172a.veterinarioemcasa.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.opet.tds172a.veterinarioemcasa.vo.Atendimento;
import br.opet.tds172a.veterinarioemcasa.vo.Usuario;
import br.opet.tds172a.veterinarioemcasa.vo.Veterinario;

public class UsuarioDAO {

	/**
	 * Atributo do VeterinarioDAO sendo final (não é alterado)
	 **/
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_USER = "system";
	private static final String DB_PASSWORD = "system";
	private static final String ERRO = "Nao foi possivel completar sua requisicao.";

	/**
	 * Construtor publico da classe VeterinarioDAO
	 **/
	public UsuarioDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que faz a conexao com o banco de dados e insere um medico
	 **/
	public Usuario inserirUsuario(Usuario usuario) {

		Connection connection = null;
		Usuario tContato = null;
		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query
			pstmt = connection.prepareStatement("INSERT INTO USUARIO"
					+ "(nome,cpf,telefone,dataNascimento,email,endereco,senha,ddd) " + "VALUES(?,?,?,?,?,?,?,?)");

			pstmt.setString(1, usuario.getNome());
			pstmt.setLong(2, usuario.getCpf());
			pstmt.setLong(3, usuario.getTelefone());
			pstmt.setDate(4, new java.sql.Date(usuario.getDataNascimento().getTime()));
			pstmt.setString(5, usuario.getEmail());
			pstmt.setString(6, usuario.getEndereco());
			pstmt.setString(7, usuario.getSenha());
			pstmt.setInt(8, usuario.getDdd());

			// executar
			pstmt.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);

			tContato = usuario;
			return tContato;

		} catch (SQLException e) {

			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		} finally {

			try {
				// fechar a conexao
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return tContato;
	}

	/**
	 * Método que faz a conexao com o banco de dados e lista todos os medicos na
	 * base
	 **/
	public static List<Veterinario> listarVeterinario() {
		Connection connection = null;
		List<Veterinario> listaRecuperada = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			PreparedStatement pstmt = null;
			// preparar a query
			pstmt = connection.prepareStatement("SELECT * FROM VETERINARIO");

			ResultSet resultSet = pstmt.executeQuery();

			listaRecuperada = new ArrayList<Veterinario>();

			while (resultSet.next()) {
				String nome = resultSet.getString("NOME");
				String email = resultSet.getString("email");
				long telefone = resultSet.getLong("telefone");
				long cpf = resultSet.getLong("cpf");
				java.sql.Date dataNascimento = resultSet.getDate("dataNascimento");
				String endereco = resultSet.getString("endereco");
				int crmv = resultSet.getInt("crmv");
				String senha = resultSet.getString("senha");
				int ddd = resultSet.getInt("ddd");

				listaRecuperada
						.add(new Veterinario(nome, email, telefone, cpf, dataNascimento, endereco, crmv, senha, ddd));
			}

			return listaRecuperada;

		} catch (SQLException e) {

			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {

			try {
				// fechar a conexao
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public Usuario efetuarLogin(Usuario usuario) {

		Connection connection = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional

			PreparedStatement pstmt = null;
			// preparar a query
			pstmt = connection.prepareStatement("SELECT * FROM USUARIO WHERE CPF = ? AND SENHA = ?");

			pstmt.setLong(1, usuario.getCpf());
			pstmt.setString(2, usuario.getSenha());
			// executar

			ResultSet resultSet = pstmt.executeQuery();

			Usuario usuarioRecuperado = null;

			while (resultSet.next()) {

				String nome = resultSet.getString("NOME");
				String email = resultSet.getString("email");
				long telefone = resultSet.getLong("telefone");
				long cpf = resultSet.getLong("cpf");
				java.sql.Date dataNascimento = resultSet.getDate("dataNascimento");
				String endereco = resultSet.getString("endereco");
				String senha = resultSet.getString("senha");
				int ddd = resultSet.getInt("ddd");

				usuarioRecuperado = new Usuario(nome, email, telefone, cpf, dataNascimento, endereco, senha, ddd);
			}

			return usuarioRecuperado;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return null;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		} finally {

			try {
				// fechar a conexao
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	public Usuario alterarUsuario(Usuario usuario) {
		Connection connection = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query

			pstmt = connection.prepareStatement(
					"UPDATE USUARIO SET NOME = ?, EMAIL = ?, ENDERECO = ?, CPF = ?, DATANASCIMENTO= ?, DDD = ?,TELEFONE = ? where cpf =?");

			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getEmail());
			pstmt.setString(3, usuario.getEndereco());
			pstmt.setLong(4, usuario.getCpf());
			pstmt.setDate(5, new java.sql.Date(usuario.getDataNascimento().getTime()));
			pstmt.setInt(6, usuario.getDdd());
			pstmt.setLong(7, usuario.getTelefone());
			pstmt.setLong(8, usuario.getCpf());

			// executar
			pstmt.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);
			return usuario;

		} catch (SQLException e) {

			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {

			try {
				// fechar a conexao
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public Usuario alterarSenha(Usuario usuario) {
		Connection connection = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query

			pstmt = connection.prepareStatement("UPDATE USUARIO SET SENHA = ? where cpf = ?");

			pstmt.setString(1, usuario.getSenha());
			pstmt.setLong(2, usuario.getCpf());

			// executar
			pstmt.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);
			return usuario;

		} catch (SQLException e) {

			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {

			try {
				// fechar a conexao
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public Usuario excluirUsuario(Usuario usuario) {
		Connection connection = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query

			pstmt = connection.prepareStatement("DELETE FROM ATENDIMENTO WHERE cpf_user = ?");

			pstmt.setLong(1, usuario.getCpf());

			// executar
			pstmt.executeUpdate();
			
			pstmt = connection.prepareStatement("DELETE FROM USUARIO WHERE cpf = ?");

			pstmt.setLong(1, usuario.getCpf());

			// executar
			pstmt.executeUpdate();			
			
			connection.commit();
			connection.setAutoCommit(true);
			return usuario;

		} catch (SQLException e) {

			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {

			try {
				// fechar a conexao
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public List<Veterinario> listarTodosVeterinarios() {
		Connection connection = null;
		List<Veterinario> listaRecuperada = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			PreparedStatement pstmt = null;
			// preparar a query
			pstmt = connection.prepareStatement("SELECT * FROM VETERINARIO");

			ResultSet resultSet = pstmt.executeQuery();

			listaRecuperada = new ArrayList<Veterinario>();

			while (resultSet.next()) {
				String nome = resultSet.getString("NOME");
				String email = resultSet.getString("email");
				long telefone = resultSet.getLong("telefone");
				long cpf = resultSet.getLong("cpf");
				java.sql.Date dataNascimento = resultSet.getDate("dataNascimento");
				String endereco = resultSet.getString("endereco");
				int crmv = resultSet.getInt("crmv");
				String senha = resultSet.getString("senha");
				int ddd = resultSet.getInt("ddd");

				listaRecuperada
						.add(new Veterinario(nome, email, telefone, cpf, dataNascimento, endereco, crmv, senha, ddd));
			}

			return listaRecuperada;

		} catch (SQLException e) {

			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {

			try {
				// fechar a conexao
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public List<Atendimento> agenda(Veterinario veterinario) {

		Connection connection = null;
		List<Atendimento> agenda = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			PreparedStatement pstmt = null;
			// preparar a query
			pstmt = connection
					.prepareStatement("SELECT  u.nome, u.endereco,t.id_consulta, t.data_consulta, t.status_consulta, t.crmv_vet\r\n" + 
							"FROM usuario u\r\n" + 
							"inner join atendimento t on (t.cpf_user = u.cpf)\r\n" + 
							"inner join veterinario v on (t.crmv_vet = v.CRMV) where v.crmv = ? ");

			pstmt.setLong(1, veterinario.getCrmv());

			ResultSet resultSet = pstmt.executeQuery();

			agenda = new ArrayList<Atendimento>();

			
			while (resultSet.next()) {
				agenda.add(new Atendimento(resultSet.getString("NOME"), resultSet.getString("ENDERECO"),
						resultSet.getTimestamp("data_consulta"), resultSet.getString("STATUS_CONSULTA"),resultSet.getInt("ID_CONSULTA")));
			}

			return agenda;

		} catch (SQLException e) {

			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {

			try {
				// fechar a conexao
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
	 