/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2024-04-16 22:42:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class CadastrarEndereco_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("  <title>Cadastro de Endereço</title>\n");
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
      out.write("\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</nav>\n");
      out.write("\n");
      out.write("<div class=\"container mt-5\">\n");
      out.write("    <h1 class=\"my-4 text-center\">Cadastre seu Endereço</h1>\n");
      out.write("    <div class=\"row\">\n");
      out.write("        <div class=\"col-md-6 offset-md-3\">\n");
      out.write("            <form id=\"enderecoForm\" action=\"/CadastrarEndereco\" method=\"POST\" class=\"needs-validation\" novalidate>\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                   <input type=\"hidden\" name=\"idCliente\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${idCliente}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("                    <label for=\"cep\" class=\"titulo-campo\">CEP:</label>\n");
      out.write("                    <div class=\"input-group\">\n");
      out.write("                        <input type=\"text\" name=\"cep\" id=\"cep\" class=\"form-control\" placeholder=\"CEP\" maxlength=\"8\">\n");
      out.write("                        <div class=\"input-group-append\">\n");
      out.write("                            <button class=\"btn btn-primary\" type=\"button\" id=\"btn-buscar-cep\">Buscar</button>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"logradouro\" class=\"titulo-campo\">Logradouro:</label>\n");
      out.write("                    <input type=\"text\" name=\"logradouro\" id=\"logradouro\" class=\"form-control\" placeholder=\"Rua, Avenida, etc.\" required maxlength=\"200\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"numero\" class=\"titulo-campo\">Número:</label>\n");
      out.write("                    <input type=\"text\" name=\"numero\" id=\"numero\" class=\"form-control\" placeholder=\"Número\" required maxlength=\"20\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"complemento\" class=\"titulo-campo\">Complemento:</label>\n");
      out.write("                    <input type=\"text\" name=\"complemento\" id=\"complemento\" class=\"form-control\" placeholder=\"Complemento (opcional)\" maxlength=\"200\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"bairro\" class=\"titulo-campo\">Bairro:</label>\n");
      out.write("                    <input type=\"text\" name=\"bairro\" id=\"bairro\" class=\"form-control\" placeholder=\"Bairro\" required maxlength=\"100\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"cidade\" class=\"titulo-campo\">Cidade:</label>\n");
      out.write("                    <input type=\"text\" name=\"cidade\" id=\"cidade\" class=\"form-control\" placeholder=\"Cidade\" required maxlength=\"100\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"estado\" class=\"titulo-campo\">Estado:</label>\n");
      out.write("                    <input type=\"text\" name=\"estado\" id=\"estado\" class=\"form-control\" placeholder=\"Estado\" required maxlength=\"2\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"text-center\">\n");
      out.write("                    <button type=\"submit\" id=\"btn-salvar\" class=\"btn btn-primary\">Cadastrar</button>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<!-- Bootstrap JS e dependências opcionais -->\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n");
      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    $(document).ready(function() {\n");
      out.write("        // Evento de clique no botão para buscar o CEP\n");
      out.write("        $('#btn-buscar-cep').click(function() {\n");
      out.write("            var cep = $('#cep').val();\n");
      out.write("            // Realizar a busca do endereço a partir do CEP\n");
      out.write("            $.getJSON(\"https://viacep.com.br/ws/\" + cep + \"/json/\", function(data) {\n");
      out.write("                // Verificar se os dados foram encontrados\n");
      out.write("                if (!(\"erro\" in data)) {\n");
      out.write("                    // Preencher os campos com os dados do endereço\n");
      out.write("                    $('#logradouro').val(data.logradouro);\n");
      out.write("                    $('#bairro').val(data.bairro);\n");
      out.write("                    $('#cidade').val(data.localidade);\n");
      out.write("                    $('#estado').val(data.uf);\n");
      out.write("                } else {\n");
      out.write("                    alert(\"CEP não encontrado. Por favor, verifique e tente novamente.\");\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("    });\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("</body>\n");
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
