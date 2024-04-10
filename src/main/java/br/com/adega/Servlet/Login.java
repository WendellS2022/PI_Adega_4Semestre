package br.com.adega.Servlet;

import br.com.adega.Autenticacao.AutenticacaoService;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Cliente;
import br.com.adega.Model.Usuario;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

    // Mapa para rastrear emails logados
    private static final Map<String, HttpSession> emailToSessionMap = new HashMap<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios;

        boolean isCliente = Boolean.parseBoolean(request.getParameter("cliente"));

        if (isCliente) {

            request.setAttribute("isCliente", isCliente);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
            dispatcher.forward(request, response);
        }


        usuarios = UsuarioDAO.ObterUsuarios();
        if (usuarios.isEmpty()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrar");
            dispatcher.forward(request, response);
        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario autenticacao;
        Cliente autenticacaoCliente;

        AutenticacaoService autenticacaoService = new AutenticacaoService();

        boolean isCliente = Boolean.parseBoolean(request.getParameter("cliente"));


        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        if (isCliente) {

            autenticacaoCliente = autenticacaoService.autenticarCliente(email, senha);

            try {
                if (autenticacaoCliente.getIdCliente() > 0) {
                    if (emailToSessionMap.containsKey(email)) {
                        response.sendRedirect("TelaLogin.jsp?mensagem=Você já está logado. Por favor, faça logout antes de tentar novamente.");
                        return;
                    }

//                    if (!autenticacaoCliente.isSituacao()) {
//                        response.sendRedirect("TelaLogin.jsp?mensagem=Usuário inativo!");
//                        return;
//                    }

                    HttpSession session = request.getSession(true);
                    session.setAttribute("usuarioLogado", email);
                    emailToSessionMap.put(email, session);
//                    request.setAttribute("grupo", autenticacaoC.getGrupo());

                    RequestDispatcher dispatcher = request.getRequestDispatcher("TelaDeProdutos.jsp");
                    dispatcher.forward(request, response);
                } else {

                    //Adicionar a tela de cadastro de cliente

                    response.sendRedirect("TelaLogin.jsp?mensagem=Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("TelaLogin.jsp?mensagem=Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
            }
        } else {
            autenticacao = autenticacaoService.autenticarUsuario(email, senha);


            try {
                if (autenticacao.getUsuarioId() > 0) {
                    if (emailToSessionMap.containsKey(email)) {
                        response.sendRedirect("TelaLogin.jsp?mensagem=Você já está logado. Por favor, faça logout antes de tentar novamente.");
                        return;
                    }

                    if (!autenticacao.isSituacao()) {
                        response.sendRedirect("TelaLogin.jsp?mensagem=Usuário inativo!");
                        return;
                    }

                    HttpSession session = request.getSession(true);
                    session.setAttribute("usuarioLogado", email);
                    emailToSessionMap.put(email, session);
                    request.setAttribute("grupo", autenticacao.getGrupo());

                    RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("TelaLogin.jsp?mensagem=Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("TelaLogin.jsp?mensagem=Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
            }
        }
    }
}

