/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2024-05-18 00:10:36 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.com.adega.Model.Produto;

public final class CadastrarAlterarUsuario_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:th=\"https://www.thymeleaf.org\" lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <link href=\"CadastrarAlterarUsuario.css\" rel=\"stylesheet\">\n");
      out.write("    <title>Cadastrar ou Alterar Produto</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<header id=\"cabecalho-site\">\n");
      out.write("    <h1 id=\"identificacao-site\">e-Commerce e-Devs - Área de BackOffice</h1>\n");
      out.write("</header>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("    <article id=\"area-usuario\">\n");
      out.write("        <section id=\"caixa-usuario\">\n");
      out.write("            <header id=\"cabecalho-usuario\">\n");
      out.write("                <h2>Cadastrar ou Alterar Usuário</h2>\n");
      out.write("            </header>\n");
      out.write("\n");
      out.write("            <form action=\"/cadastrar\" method=\"POST\">\n");
      out.write("                <div id=\"info-campos\">\n");
      out.write("                    <input type=\"hidden\" name=\"usuarioId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${usuario.usuarioId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("                    <input type=\"hidden\" name=\"sessao\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessao}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\n");
      out.write("                    <label for=\"email\" class=\"titulo-campo\">E-mail:</label>\n");
      out.write("                    <input type=\"email\" name=\"email\" id=\"email-usuario\" placeholder=\"E-mail do usuário\" required\n");
      out.write("                        value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${usuario != null ? usuario.email : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write('"');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${usuario != null ? 'readonly' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("> <!-- Verifica se há um objeto User presente e preenche o campo de email -->\n");
      out.write("                    <label for=\"email\" class=\"titulo-campo\">Nome:</label>\n");
      out.write("                    <input type=\"text\" name=\"nome\" id=\"nome-usuario\" placeholder=\"Nome do usuário\" required\n");
      out.write("                        value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${usuario != null ? usuario.nome : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"> <!-- Verifica se há um objeto User presente e preenche o campo de nome -->\n");
      out.write("                    <label for=\"email\" class=\"titulo-campo\">CPF:</label>\n");
      out.write("                    <input type=\"text\" name=\"cpf\" id=\"cpf-usuario\" placeholder=\"CPF do usuário\" required\n");
      out.write("                        value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${usuario != null ? usuario.CPF : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"> <!-- Verifica se há um objeto User presente e preenche o campo de CPF -->\n");
      out.write("                    <label for=\"email\" class=\"titulo-campo\">Grupo:</label>\n");
      out.write("                    <!-- Verifica se o campo \"sessao\" tem valor -->\n");
      out.write("                    <select name=\"grupo\" id=\"grupo-usuario\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty sessao  ? 'disabled' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">\n");
      out.write("                        <option value=\"1\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${usuario != null && usuario.grupo == 1 ? 'selected' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">Administrador</option>\n");
      out.write("                        <option value=\"2\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${usuario != null && usuario.grupo == 2 ? 'selected' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">Estoquista</option>\n");
      out.write("                    </select>\n");
      out.write("\n");
      out.write("                    <label for=\"senha\" class=\"titulo-campo\">Senha:</label>\n");
      out.write("                    <input type=\"password\" name=\"senha\" id=\"senha-usuario\" class=\"senha-usuario\" required>\n");
      out.write("                    <label for=\"senha-2\" class=\"titulo-campo\">Confirme a senha:</label>\n");
      out.write("                    <input type=\"password\" name=\"senha-2\" id=\"senha-usuario-confirmacao\" class=\"senha-usuario\" required>\n");
      out.write("                    <div id=\"mensagem-erro-senha\" style=\"color: red;\"></div>\n");
      out.write("                    ");

                    String mensagem = (String) request.getAttribute("mensagem");
                    if (mensagem != null) {
                    
      out.write("\n");
      out.write("                    <p>");
      out.print( mensagem );
      out.write("</p>\n");
      out.write("                    ");

                    }
                    
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <button type=\"submit\" id=\"btn-salvar\">Salvar</button>\n");
      out.write("            </form>\n");
      out.write("            <div id=\"botoes\">\n");
      out.write("                <form action=\"/listar\" method=\"GET\">\n");
      out.write("                    <button type=\"submit\" id=\"btn-cancelar\">Cancelar</button>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            ");

            String usuarioLogado = (String) session.getAttribute("usuarioLogado");
            
      out.write("\n");
      out.write("        </section>\n");
      out.write("    </article>\n");
      out.write("</body>\n");
      out.write("<script src=\"ValidaCPF.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"ValidaSenha.js\" type=\"text/javascript\"></script>\n");
      out.write("<script>\n");
      out.write("    var selectedIndex = document.getElementById('grupo-usuario').selectedIndex;\n");
      out.write("\n");
      out.write("    // Impede que o valor seja alterado se o campo estiver desabilitado\n");
      out.write("    document.getElementById('grupo-usuario').addEventListener('change', function() {\n");
      out.write("        if (this.disabled) {\n");
      out.write("            this.selectedIndex = selectedIndex;\n");
      out.write("        } else {\n");
      out.write("            selectedIndex = this.selectedIndex;\n");
      out.write("        }\n");
      out.write("    });\n");
      out.write("</script>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
