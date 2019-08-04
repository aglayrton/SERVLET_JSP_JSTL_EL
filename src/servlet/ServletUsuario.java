package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bean;
import dao.DaoUsuario;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private DaoUsuario daoUsuario = new DaoUsuario();
	
    public ServletUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");
		
		//Recebe pelo parametro da url o valor delete
		if(acao.equalsIgnoreCase("delete")) {
			try {
				//manda o valor do parametro
				daoUsuario.delete(user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(acao.equalsIgnoreCase("editar")) {
			//Passamos a consulta dos dados para um objeto do tipo usuario
				Bean usuario = daoUsuario.consultar(user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				//Agora vai redirecionar para a expressao USER
				request.setAttribute("user", usuario);
				try {
					request.setAttribute("usuarios", daoUsuario.listar());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispatcher.forward(request, response);
		}else if(acao.equalsIgnoreCase("listar")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
			try {
				request.setAttribute("usuarios", daoUsuario.listar());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("password");
		
		Bean usuario = new Bean();
		//Se o id for diferente de de vazio, ele passara o dia como deve ser, senao, passara como zero
		usuario.setId(!id.isEmpty()?Integer.parseInt(id):0);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		//SALVA OU ATUALIZA
		if (id==null||id.isEmpty()) {
			daoUsuario.salvar(usuario);
		} else {
			daoUsuario.editar(usuario);
		}
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
