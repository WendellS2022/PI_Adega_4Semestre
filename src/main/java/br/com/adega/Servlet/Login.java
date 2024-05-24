package br.com.adega.Servlet;

import br.com.adega.Autenticacao.AutenticacaoService;
import br.com.adega.DAO.CarrinhoDAO;
import br.com.adega.DAO.ClienteDAO;
import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Carrinho;
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
        String mensagem = null;
        boolean reload = Boolean.parseBoolean(request.getParameter("reload"));
        boolean isCliente = Boolean.parseBoolean(request.getParameter("cliente"));

        if (isCliente) {
            if (reload) {
                mensagem = "Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.";
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("isCliente", isCliente);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
                dispatcher.forward(request, response);

                return;
            }

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
            request.setAttribute("isBackOffice", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");

        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        boolean isCliente = Boolean.parseBoolean(request.getParameter("clienteLogado"));

        try {
            if (isCliente) {
                autenticarCliente(email, senha, request, response);
            } else {
                autenticarUsuario(email, senha, usuarioLogado, request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String mensagem = "Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.";
            enviarMensagemErro(request, response, mensagem);
        }
    }

    private void autenticarCliente(String email, String senha, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AutenticacaoService autenticacaoService = new AutenticacaoService();
        List<Carrinho> produtosCarrinho = new ArrayList<>();

        Cliente autenticacaoCliente = autenticacaoService.autenticarCliente(email, senha);

        if (autenticacaoCliente.getIdCliente() > 0) {
            HttpSession session = request.getSession(true);
            session.setAttribute("clienteLogado", email);
            request.setAttribute("clienteLogado", email);
            emailToSessionMap.put(email, session);

            produtosCarrinho = (List<Carrinho>) session.getAttribute("carrinho");

            int idCliente = ClienteDAO.buscarIdClienteEmail((String) session.getAttribute("clienteLogado"));

            if (produtosCarrinho != null) {
                boolean login = true;
                CarrinhoDAO.inserirProdutosCarrinho(produtosCarrinho, idCliente, login);

                produtosCarrinho = CarrinhoDAO.obterProdutosCarrinhoPorIdCliente(idCliente);

                session.setAttribute("carrinho", produtosCarrinho);
            }

            if (!response.isCommitted()) {
                response.sendRedirect(request.getContextPath() + "/TelaProdutos?clienteLogado=" + email);
            }
        } else {
            enviarMensagemErro(request, response, "Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
        }
    }

    private void autenticarUsuario(String email, String senha, String usuarioLogado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AutenticacaoService autenticacaoService = new AutenticacaoService();
        Usuario autenticacaoUsuario = autenticacaoService.autenticarUsuario(email, senha);

        if (usuarioLogado != null) {
            enviarMensagemErro(request, response, "Você já está logado. Por favor, faça logout antes de tentar novamente.");
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("usuarioLogado", email);
        emailToSessionMap.put(email, session);
        request.setAttribute("grupo", autenticacaoUsuario.getGrupo());

        if (!response.isCommitted()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
            dispatcher.forward(request, response);
        } else {
            enviarMensagemErro(request, response, "Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
        }

    }


    private void enviarMensagemErro(HttpServletRequest request, HttpServletResponse response, String mensagem) throws ServletException, IOException {
        if (!response.isCommitted()) {
            request.setAttribute("mensagem", mensagem);
            forwardToLoginPage(request, response);
        }
    }

    private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isCliente = Boolean.parseBoolean(request.getParameter("clienteLogado"));
        if (!response.isCommitted()) {
            if (isCliente) {
                response.sendRedirect(request.getContextPath() + "/login?cliente=true&reload=true");
            } else {
                request.setAttribute("isBackOffice", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("TelaLogin.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}

