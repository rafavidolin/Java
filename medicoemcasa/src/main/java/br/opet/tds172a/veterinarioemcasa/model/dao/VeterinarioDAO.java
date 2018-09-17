package br.opet.tds172a.veterinarioemcasa.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.opet.tds172a.veterinarioemcasa.vo.Usuario;
import br.opet.tds172a.veterinarioemcasa.vo.Veterinario;

public class VeterinarioDAO {

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
	public VeterinarioDAO() {
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
	public Veterinario inserirMedico(Veterinario veterinario) {

		Connection connection = null;
		Veterinario tContato = null;
		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query
			pstmt = connection.prepareStatement(
					"INSERT INTO VETERINARIO" + "(nome,cpf,telefone,dataNascimento,email,endereco,crmv,senha,ddd) "
							+ "VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setString(1, veterinario.getNome());
			pstmt.setLong(2, veterinario.getCpf());
			pstmt.setLong(3, veterinario.getTelefone());
			pstmt.setDate(4, new java.sql.Date(veterinario.getDataNascimento().getTime()));
			pstmt.setString(5, veterinario.getEmail());
			pstmt.setString(6, veterinario.getEndereco());
			pstmt.setInt(7, veterinario.getCrmv());
			pstmt.setString(8, veterinario.getSenha());
			pstmt.setInt(9, veterinario.getDdd());

			// executar
			pstmt.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);

			tContato = veterinario;
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

	public Veterinario efetuarLogin(Veterinario veterinario) {

		Connection connection = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional

			PreparedStatement pstmt = null;
			// preparar a query
			pstmt = connection.prepareStatement("SELECT * FROM VETERINARIO WHERE CPF = ? AND SENHA = ?");

			pstmt.setLong(1, veterinario.getCpf());
			pstmt.setString(2, veterinario.getSenha());
			// executar

			ResultSet resultSet = pstmt.executeQuery();

			Veterinario usuarioRecuperado = null;
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

				usuarioRecuperado = new Veterinario(nome, email, telefone, cpf, dataNascimento, endereco, crmv, senha,
						ddd);
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

	public Veterinario alterarVeterinario(Veterinario veterinario) {
		Connection connection = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query

			pstmt = connection.prepareStatement(
					"UPDATE VETERINARIO SET NOME = ?, EMAIL = ?, ENDERECO = ?, CPF = ?, CRMV = ?, DATANASCIMENTO= ?, DDD = ?,TELEFONE = ? where cpf =?");

			pstmt.setString(1, veterinario.getNome());
			pstmt.setString(2, veterinario.getEmail());
			pstmt.setString(3, veterinario.getEndereco());
			pstmt.setLong(4, veterinario.getCpf());
			pstmt.setInt(5, veterinario.getCrmv());
			pstmt.setDate(6, new java.sql.Date(veterinario.getDataNascimento().getTime()));
			pstmt.setInt(7, veterinario.getDdd());
			pstmt.setLong(8, veterinario.getTelefone());
			pstmt.setLong(9, veterinario.getCpf());

			// executar
			pstmt.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);
			return veterinario;

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

	public Veterinario alterarSenha(Veterinario veterinario) {
		Connection connection = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query

			pstmt = connection.prepareStatement("UPDATE VETERINARIO SET SENHA = ? where cpf = ?");

			pstmt.setString(1, veterinario.getSenha());
			pstmt.setLong(2, veterinario.getCpf());

			// executar
			pstmt.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);
			return veterinario;

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

	public String verificaTipo(Veterinario veterinario) {
		Connection connection = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional

			PreparedStatement pstmt = null;

			// preparar a query
			pstmt = connection.prepareStatement("SELECT * FROM VETERINARIO WHERE CPF = ? AND SENHA = ?");

			pstmt.setLong(1, veterinario.getCpf());
			pstmt.setString(2, veterinario.getSenha());

			ResultSet resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				return "veterinario";
			}

			// preparar a query
			pstmt = connection.prepareStatement("SELECT * FROM USUARIO WHERE CPF = ? AND SENHA = ?");

			pstmt.setLong(1, veterinario.getCpf());
			pstmt.setString(2, veterinario.getSenha());

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				return "usuario";
			}

			return "erro";

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

	public Veterinario excluirVeterinario(Veterinario veterinario) {
		Connection connection = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query

			pstmt = connection.prepareStatement("DELETE FROM atendimento WHERE crmv_vet = ?");
			pstmt.setLong(1, veterinario.getCrmv());		
			pstmt.executeUpdate();			
			
			pstmt = connection.prepareStatement("DELETE FROM VETERINARIO WHERE cpf = ?");
			pstmt.setLong(1, veterinario.getCpf());			
			pstmt.executeUpdate();

			
			
			connection.commit();
			connection.setAutoCommit(true);
			return veterinario;

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

	public Veterinario marcarConsulta(Veterinario veterinario, Usuario usuario) {

		Connection connection = null;
		Veterinario tContato = null;
		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query
			pstmt = connection.prepareStatement(
					"INSERT INTO ATENDIMENTO " + "(ID_CONSULTA,CRMV_vet,CPF_user,DATA_CONSULTA,STATUS_CONSULTA) "
							+ "VALUES (ID_CONSULTA.NEXTVAL,?,?,?,?)");

			pstmt.setInt(1, veterinario.getCrmv());
			pstmt.setLong(2, usuario.getCpf());
			pstmt.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
			pstmt.setString(4, "NAO");

			// executar
			pstmt.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);

			tContato = veterinario;
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

	public boolean alteraStatusConsultaSim(int idConsulta) {
		Connection connection = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query

			pstmt = connection.prepareStatement("UPDATE ATENDIMENTO SET STATUS_CONSULTA = 'SIM' where ID_CONSULTA = ?");

			pstmt.setInt(1, idConsulta);

			// executar
			int alterou = pstmt.executeUpdate();

			if (alterou == 1) {

				return true;
			}

			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e) {

			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
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
		return false;
	}

	public boolean alteraStatusConsultaNao(int idConsulta) {
		Connection connection = null;

		try {
			// abrir a conexao
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// inicio o controle transacional
			connection.setAutoCommit(false);

			PreparedStatement pstmt = null;
			// preparar a query

			pstmt = connection.prepareStatement("UPDATE ATENDIMENTO SET STATUS_CONSULTA = 'NAO' where ID_CONSULTA = ?");

			pstmt.setInt(1, idConsulta);

			// executar
			int alterou = pstmt.executeUpdate();

			if (alterou == 1) {

				return true;
			}

			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e) {

			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
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
		return false;
	}
}