/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2024-03-14 14:02:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.com.adega.Model.User;

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:th=\"https://www.thymeleaf.org\" lang=\"pt\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"CadastrarAlterarUsuario.css\">\r\n");
      out.write("    <title>Cadastrar ou Alterar Usuário</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("    <form action=\"/cadastrar\" method=\"POST\">\r\n");
      out.write("        <div id=\"info-campos\">\r\n");
      out.write("            <input type=\"hidden\" name=\"userId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("           <input type=\"hidden\" name=\"isSession\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isSession}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("            <label for=\"email\" class=\"titulo-campo\">E-mail:</label>\r\n");
      out.write("            <input type=\"email\" name=\"email\" id=\"email-usuario\" placeholder=\"E-mail do usuário\" required\r\n");
      out.write("                value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user != null ? user.email : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write('"');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user != null ? 'readonly' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("> <!-- Verifica se há um objeto User presente e preenche o campo de email -->\r\n");
      out.write("            <label for=\"email\" class=\"titulo-campo\">Nome:</label>\r\n");
      out.write("            <input type=\"text\" name=\"nome\" id=\"nome-usuario\" placeholder=\"Nome do usuário\" required\r\n");
      out.write("                value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user != null ? user.nome : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"> <!-- Verifica se há um objeto User presente e preenche o campo de nome -->\r\n");
      out.write("            <label for=\"email\" class=\"titulo-campo\">CPF:</label>\r\n");
      out.write("            <input type=\"text\" name=\"cpf\" id=\"cpf-usuario\" placeholder=\"CPF do usuário\" required\r\n");
      out.write("                value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user != null ? user.CPF : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"> <!-- Verifica se há um objeto User presente e preenche o campo de CPF -->\r\n");
      out.write("            <label for=\"email\" class=\"titulo-campo\">Grupo:</label>\r\n");
      out.write("           <!-- Verifica se o campo \"isSession\" tem valor -->\r\n");
      out.write("     <select name=\"grupo\" id=\"grupo-usuario\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty isSession ? 'disabled' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">\r\n");
      out.write("         <option value=\"1\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user != null && user.grupo == 1 ? 'selected' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">Administrador</option>\r\n");
      out.write("         <option value=\"2\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user != null && user.grupo == 2 ? 'selected' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">Estoquista</option>\r\n");
      out.write("     </select>\r\n");
      out.write("\r\n");
      out.write("            <label for=\"senha\" class=\"titulo-campo\">Senha:</label>\r\n");
      out.write("            <input type=\"password\" name=\"senha\" id=\"senha-usuario\" class=\"senha-usuario\" required>\r\n");
      out.write("            <label for=\"senha-2\" class=\"titulo-campo\">Confirme a senha:</label>\r\n");
      out.write("            <input type=\"password\" name=\"senha-2\" id=\"senha-usuario-confirmacao\" class=\"senha-usuario\" required>\r\n");
      out.write("            <div id=\"mensagem-erro-senha\" style=\"color: red;\"></div>\r\n");
      out.write("            ");

            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null) {
            
      out.write("\r\n");
      out.write("               <p>");
      out.print( mensagem );
      out.write("</p>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("          <button type=\"submit\" id=\"btn-confirmar\">Confirmar</button>\r\n");
      out.write("        </form>\r\n");
      out.write("        <div id=\"botoes\">\r\n");
      out.write("             <form action=\"/listar\" method=\"GET\">\r\n");
      out.write("                <button type=\"submit\" id=\"btn-cancelar\">Cancelar</button>\r\n");
      out.write("             </form>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    ");

        String isSession = (String) session.getAttribute("usuarioLogado");
    
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<script src=\"ValidaCPF.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"ValidaSenha.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("    var selectedIndex = document.getElementById('grupo-usuario').selectedIndex;\r\n");
      out.write("\r\n");
      out.write("    // Impede que o valor seja alterado se o campo estiver desabilitado\r\n");
      out.write("    document.getElementById('grupo-usuario').addEventListener('change', function() {\r\n");
      out.write("        if (this.disabled) {\r\n");
      out.write("            this.selectedIndex = selectedIndex;\r\n");
      out.write("        } else {\r\n");
      out.write("            selectedIndex = this.selectedIndex;\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("</script>\r\n");
      out.write("</html>\r\n");
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
