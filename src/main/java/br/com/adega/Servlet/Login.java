package br.com.adega.Servlet;

import br.com.adega.Autenticacao.AutenticacaoService;
import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Cliente;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;
import br.com.adega.Model.Usuario;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    private static final Map<String, HttpSession> emailToSessionMap = new HashMap<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios;

        boolean isCliente = Boolean.parseBoolean(request.getParameter("cliente"));

        if (isCliente) {

            request.setAttribute("isCliente", isCliente);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");

            dispatcher.forward(request, response);

            return;

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
//        String clienteLogado = request.getParameter("clienteLogado");
//        HttpSession session = request.getSession(true);
//        session.setAttribute("clienteLogado", clienteLogado);
//        emailToSessionMap.put(clienteLogado, session);
//
//        if (clienteLogado != null && !clienteLogado.trim().isEmpty() && emailToSessionMap.containsKey(clienteLogado)) {
//            request.setAttribute("mensagem", "Você já está logado. Por favor, faça logout antes de tentar novamente!");
//            return;
//        }


        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        boolean isCliente = Boolean.parseBoolean(request.getParameter("clienteLogado"));

        try {
            if (isCliente) {
                autenticarCliente(email, senha, request, response);
            } else {
                autenticarUsuario(email, senha, request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
        }
    }


    private void autenticarCliente(String email, String senha, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AutenticacaoService autenticacaoService = new AutenticacaoService();

        Cliente autenticacaoCliente = autenticacaoService.autenticarCliente(email, senha);

        if (autenticacaoCliente.getIdCliente() > 0) {
            HttpSession session = request.getSession(true);
            session.setAttribute("clienteLogado", email);
            request.setAttribute("clienteLogado", email);
            emailToSessionMap.put(email, session);

            response.sendRedirect(request.getContextPath() + "/TelaProdutos?clienteLogado=" + email);
        } else {
            request.setAttribute("mensagem", "Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
        }
    }

    private void autenticarUsuario(String email, String senha, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AutenticacaoService autenticacaoService = new AutenticacaoService();
        Usuario autenticacaoUsuario = autenticacaoService.autenticarUsuario(email, senha);

        if (autenticacaoUsuario.getUsuarioId() > 0 && autenticacaoUsuario.isSituacao()) {
            if (emailToSessionMap.containsKey(email)) {
                request.setAttribute("mensagem", "Você já está logado. Por favor, faça logout antes de tentar novamente.");
                return;
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("usuarioLogado", email);
            emailToSessionMap.put(email, session);
            request.setAttribute("grupo", autenticacaoUsuario.getGrupo());

            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("mensagem", "Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
        }
    }

    private void enviarMensagemErro(HttpServletResponse response, String mensagem) throws IOException {
        String mensagemEncoded = URLEncoder.encode(mensagem, StandardCharsets.UTF_8.toString());
        String redirectURL = "TelaLogin.jsp?mensagem=" + mensagemEncoded;
        response.sendRedirect(redirectURL);
    }

}



