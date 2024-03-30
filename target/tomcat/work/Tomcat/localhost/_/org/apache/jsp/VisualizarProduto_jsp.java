/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2024-03-30 04:10:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.com.adega.Model.Produto;
import br.com.adega.Model.Imagem;
import java.util.List;

public final class VisualizarProduto_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-\trbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">\n");
      out.write("\n");
      out.write("    <link href=\"/VisualizarProduto.css\" rel=\"stylesheet\">\n");
      out.write("    <script src=\"/VisualizarProduto.js\" async></script>\n");
      out.write("    <title>Visualizar Produto</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<!-- Aqui fica o cabeçalho -->\n");
      out.write("<header id=\"cabecalho-site\">\n");
      out.write("    <h1 id=\"identificacao-site\">e-Commerce e-Devs - Área de BackOffice</h1>\n");
      out.write("</header>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("    <article id=\"area-vizualizacao\">\n");
      out.write("        <section id=\"caixa-vizualizacao\">\n");
      out.write("            <header id=\"cabecalho-vizualizacao\">\n");
      out.write("                <h2>Vizualização do Produto</h2>\n");
      out.write("            </header>\n");
      out.write("\n");
      out.write("            <div id=\"mensagem-erro\" style=\"color: red;\"></div>\n");
      out.write("            ");
 String mensagem = (String) request.getAttribute("mensagem");
                if (mensagem != null) { 
      out.write("\n");
      out.write("                    <p>");
      out.print( mensagem );
      out.write("</p>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("            <section class=\"area-produto\">\n");
      out.write("                <div class=\"area-imagem\">\n");
      out.write("                   <div id=\"carouselExampleControls\" class=\"carousel slide\" data-bs-ride=\"carousel\">\n");
      out.write("                      <div class=\"carousel-inner\">\n");
      out.write("                                             ");
 // Exibir as imagens correspondentes ao produto
                                                    List<Imagem> imagens = (List<Imagem>) request.getAttribute("imagens");
                                                    if (imagens != null) {
                                                        for (Imagem imagem : imagens) { 
      out.write("\n");
      out.write("                                                                      <div class=\"carousel-item active\">\n");
      out.write("                                                                              <img src=\"");
      out.print( imagem.getDiretorio() + "/" + imagem.getNome() );
      out.write("\" alt=\"");
      out.print( imagem.getNome() );
      out.write("\" class=\"d-block w-100\"/>\n");
      out.write("                                                                      </div>\n");
      out.write("                                                ");
     }
                                                    } 
      out.write("\n");
      out.write("                      </div>\n");
      out.write("                      <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#carouselExampleControls\" data-bs-slide=\"prev\">\n");
      out.write("                        <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n");
      out.write("                        <span class=\"visually-hidden\">Previous</span>\n");
      out.write("                      </button>\n");
      out.write("                      <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#carouselExampleControls\" data-bs-slide=\"next\">\n");
      out.write("                        <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n");
      out.write("                        <span class=\"visually-hidden\">Next</span>\n");
      out.write("                      </button>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"informacao-produto\">\n");
      out.write("\n");
      out.write("                    <label for=\"nomeProduto\" class=\"titulo-campo\">Nome do Produto</label>\n");
      out.write("                    <textarea name=\"nomeProduto\" id=\"nome-Produto\" readonly>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.getNomeProduto() : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\n");
      out.write("                    </textarea>\n");
      out.write("\n");
      out.write("                    <label for=\"dscDetalhadaProduto\" class=\"titulo-campo\">Descrição Detalhada do Produto</label>\n");
      out.write("                    <textarea name=\"dscDetalhadaProduto\" id=\"descricao-Produto\" readonly>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.getDscDetalhadaProduto() : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\n");
      out.write("                    </textarea>\n");
      out.write("\n");
      out.write("                    <label for=\"avaliacaoProduto\" class=\"titulo-campo\">Avaliação do Produto</label>\n");
      out.write("                    <input type=\"number\" name=\"avaliacaoProduto\" id=\"avaliacao-Produto\" readonly\n");
      out.write("                           value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.avaliacaoProduto : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\n");
      out.write("                    <label for=\"vlrVendaProduto\" class=\"titulo-campo\">Preço do Produto</label>\n");
      out.write("                    <input type=\"number\" name=\"vlrVendaProduto\" id=\"vlr-VendaProduto\" readonly\n");
      out.write("                           value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.vlrVendaProduto : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\n");
      out.write("                  <button id=\"btn-comprar\" class=\"btn btn-primary\" style=\"margin-top: 25px;\">Comprar</button>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </section>\n");
      out.write("        </section>\n");
      out.write("    </article>\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>\n");
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
