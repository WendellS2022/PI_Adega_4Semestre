/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2024-04-19 22:13:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class AlterarDadosCliente_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"UTF-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("  <title>Alterar Dados</title>\n");
      out.write("  <!-- Bootstrap CSS -->\n");
      out.write("  <link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("  <!-- Font Awesome -->\n");
      out.write("  <link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <!-- Logo -->\n");
      out.write("        <a class=\"navbar-brand mr-auto\" href=\"#\">\n");
      out.write("            <img src=\"LOGO1.png\" alt=\"Logo\" height=\"70\">\n");
      out.write("        </a>\n");
      out.write("        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("            <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("        </button>\n");
      out.write("        <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n");
      out.write("            <ul class=\"navbar-nav ml-auto\">\n");
      out.write("                <li class=\"nav-item\" id=\"enderecoTab\" style=\"display: none;\">\n");
      out.write("                    <a class=\"nav-link font-weight-bold\" href=\"#\">Cadastrar Endereço</a>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</nav>\n");
      out.write("\n");
      out.write("<div class=\"container mt-5\">\n");
      out.write("    <h1 class=\"my-4 text-center\" >Alterar Dados</h1>\n");
      out.write("    <div class=\"row\">\n");
      out.write("        <div class=\"col-md-6 offset-md-3\">\n");
      out.write("\n");
      out.write("            <form id=\"alterarDadosForm\" action=\"/alterarCliente\" method=\"POST\" class=\"needs-validation\" novalidate>\n");
      out.write("               <div id=\"nome-error-message\" style=\"color: red;\"></div>\n");
      out.write("                <input type=\"hidden\" name=\"clienteLogado\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${clienteLogado}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"nome\" class=\"titulo-campo\" placeholder = >Nome:</label>\n");
      out.write("                    <input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\" \"Nome completo\" required maxlength=\"200\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cliente.nome}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"dataNascimento\" class=\"titulo-campo\">Data de Nascimento:</label>\n");
      out.write("                    <input type=\"date\" name=\"dataNascimento\" id=\"dataNascimento\" class=\"form-control\" required value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cliente.dataNascimento}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                 <label for=\"genero\" class=\"titulo-campo\">Gênero:</label>\n");
      out.write("                 <input type=\"txt\" name=\"genero\" id=\"genero\" class=\"form-control\" placeholder=\"Gênero\" required maxlength=\"200\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cliente.genero}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("                 </div>\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"senha\" class=\"titulo-campo\">Senha:</label>\n");
      out.write("                    <input type=\"password\" name=\"senha\" id=\"senha\" class=\"form-control\" placeholder=\"Senha\" required maxlength=\"200\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"text-center\">\n");
      out.write("                    <button type=\"submit\" id=\"btn-salvar\" class=\"btn btn-primary\">Salvar Alterações</button>\n");
      out.write("                    <button type=\"button\" id=\"btn-cancelar\" onclick=\"window.location.href='/TelaProdutos'\" class=\"btn btn-secondary\">Cancelar</button>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<!-- Bootstrap JS e dependências opcionais -->\n");
      out.write("<script src=\"Cpf.js\"></script>\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n");
      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    $(document).ready(function() {\n");
      out.write("        // Função para verificar se o nome do cliente atende aos requisitos\n");
      out.write("        function validarNome(nome) {\n");
      out.write("            var palavras = nome.split(\" \"); // Divide o nome em palavras\n");
      out.write("            if (palavras.length >= 2) { // Verifica se há pelo menos duas palavras\n");
      out.write("                // Verifica se cada palavra tem pelo menos 3 letras\n");
      out.write("                for (var i = 0; i < palavras.length; i++) {\n");
      out.write("                    if (palavras[i].length < 3) {\n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("                return true;\n");
      out.write("            } else {\n");
      out.write("                return false;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        // Função para atualizar o estado do botão de salvar e mostrar a mensagem de erro\n");
      out.write("        function atualizarBotaoSalvar() {\n");
      out.write("            var nome = $(\"#nome\").val(); // Obtém o valor do campo de nome\n");
      out.write("            var botaoSalvar = $(\"#btn-salvar\"); // Obtém o botão de salvar\n");
      out.write("            var mensagemErro = $(\"#nome-error-message\"); // Obtém o elemento para a mensagem de erro\n");
      out.write("\n");
      out.write("            // Verifica se o nome atende aos requisitos\n");
      out.write("            if (validarNome(nome)) {\n");
      out.write("                botaoSalvar.prop(\"disabled\", false); // Habilita o botão de salvar\n");
      out.write("                mensagemErro.text(\"\"); // Limpa a mensagem de erro\n");
      out.write("            } else {\n");
      out.write("                botaoSalvar.prop(\"disabled\", true); // Desabilita o botão de salvar\n");
      out.write("                mensagemErro.text(\"O nome deve conter pelo menos duas palavras com no mínimo três letras cada.\"); // Define a mensagem de erro\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        // Ao digitar no campo de nome, verificar se o botão de salvar deve ser habilitado ou desabilitado\n");
      out.write("        $(\"#nome\").keyup(function() {\n");
      out.write("            atualizarBotaoSalvar();\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        // Ao enviar o formulário, verificar novamente se o nome atende aos requisitos\n");
      out.write("        $(\"#cadastroForm\").submit(function(event) {\n");
      out.write("            var nome = $(\"#nome\").val(); // Obtém o valor do campo de nome\n");
      out.write("\n");
      out.write("            // Se o nome não atender aos requisitos, impedir o envio do formulário\n");
      out.write("            if (!validarNome(nome)) {\n");
      out.write("                event.preventDefault(); // Impede o envio do formulário\n");
      out.write("                // Exibe uma mensagem de erro na tela\n");
      out.write("                $(\"#nome-error-message\").text(\"O nome deve conter pelo menos duas palavras com no mínimo três letras cada.\");\n");
      out.write("            }\n");
      out.write("        });\n");
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
