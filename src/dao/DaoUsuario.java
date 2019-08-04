package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Bean;
import connection.SingleConnection;

public class DaoUsuario {
	private Connection connection;

	public DaoUsuario() {
		setConnection(SingleConnection.getConnection());
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	//CADASTRA O USUÁRIO
	public void salvar(Bean usuario) {
		String sql = "insert into usuarios(login, senha)Values(?,?)";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, usuario.getLogin());
			st.setString(2, usuario.getSenha());
			st.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//CONSULTA OS USUÁRIOS
	public List<Bean> listar() throws Exception{
		String sql = "select * from usuarios";
		List<Bean> usuario = new ArrayList<Bean>();
			PreparedStatement listaTodos = connection.prepareStatement(sql);
			ResultSet result = listaTodos.executeQuery();
			while(result.next()){
				Bean usuarios = new Bean();
				usuarios.setId(result.getInt("id"));
				usuarios.setLogin(result.getString("login"));
				usuarios.setSenha(result.getString("senha"));
				usuario.add(usuarios);
			}
			return usuario;
	}
	//DELETAR
	public void delete(String login) throws SQLException{
		String sql = "delete from usuarios where login='"+login+"'";
		try {
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
	//CONSULTAR
	public Bean consultar(String login){
		Bean usuario = new Bean();
		String sql = "SELECT * FROM usuarios WHERE login='"+login+"'";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet result = st.executeQuery();
			while(result.next()){
				usuario.setId(result.getInt("id"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	//EDITAR
	public void editar(Bean usuario) {
		String sql = "update usuarios set login=?, senha=? where id="+usuario.getId();
		try {
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.execute();
			connection.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
