/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2024-03-15 12:42:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.com.adega.Model.Produto;

public final class CadastrarAlterarProduto_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:th=\"https://www.thymeleaf.org\" lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <link href=\"CadastrarAlterarProduto.css\" rel=\"stylesheet\">\r\n");
      out.write("    <title>Cadastrar ou Alterar Produto</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<header id=\"cabecalho-site\">\r\n");
      out.write("    <h1 id=\"identificacao-site\">e-Commerce e-Devs - Área de BackOffice</h1>\r\n");
      out.write("</header>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("    <article id=\"area-produto\">\r\n");
      out.write("        <section id=\"caixa-produto\">\r\n");
      out.write("            <header id=\"cabecalho-produto\">\r\n");
      out.write("                <h2>Cadastrar ou Alterar Produto</h2>\r\n");
      out.write("            </header>\r\n");
      out.write("\r\n");
      out.write("            <div id=\"mensagem-erro\" style=\"color: red;\"></div>\r\n");
      out.write("            ");

                String mensagem = (String) request.getAttribute("mensagem");
                if (mensagem != null) {
            
      out.write("\r\n");
      out.write("            <p>");
      out.print( mensagem );
      out.write("</p>\r\n");
      out.write("            ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <form action=\"/cadastrarProduto?id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto.codProduto}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" method=\"POST\">\r\n");
      out.write("                    <div id=\"informacao-produto\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"codProduto\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.codProduto : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("                        <label for=\"nomeProduto\" class=\"titulo-campo\">Nome do Produto:</label>\r\n");
      out.write("                        <input type=\"text\" name=\"nomeProduto\" id=\"nome-Produto\" placeholder=\"Nome do produto\" required\r\n");
      out.write("                            value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.nomeProduto : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("                        <label for=\"dscDetalhadaProduto\" class=\"titulo-campo\">Descrição Detalhada do Produto:</label>\r\n");
      out.write("                        <input type=\"text\" name=\"dscDetalhadaProduto\" id=\"dsc-Produto\" placeholder=\"Descrição detalhada do produto\" required\r\n");
      out.write("                            value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.dscDetalhadaProduto : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("                        <label for=\"avaliacaoProduto\" class=\"titulo-campo\">Avaliação do Produto:</label>\r\n");
      out.write("                        <input type=\"number\" name=\"avaliacaoProduto\" id=\"avaliacao-Produto\" required\r\n");
      out.write("                            value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.avaliacaoProduto : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("                        <label for=\"vlrVendaProduto\" class=\"titulo-campo\">Preço do Produto:</label>\r\n");
      out.write("                        <input type=\"text\" name=\"vlrVendaProduto\" id=\"vlr-VendaProduto\" required\r\n");
      out.write("                            value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.vlrVendaProduto : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("                        <label for=\"qtdEstoque\" class=\"titulo-campo\">Quantidade em Estoque:</label>\r\n");
      out.write("                        <input type=\"number\" name=\"qtdEstoque\" id=\"qtd-Estoque\" required\r\n");
      out.write("                            value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${produto != null ? produto.qtdEstoque : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <input value=\"Enviar\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("       <div id=\"imagens-produto\">\r\n");
      out.write("           <label for=\"selImagem\" class=\"titulo-campo\">Seleção de Imagem do Produto:</label>\r\n");
      out.write("           <input type=\"file\" name=\"selImagem\" id=\"selecao-imagem\" required multiple>\r\n");
      out.write("           <header id=\"cabecalho-imagem\">\r\n");
      out.write("               <h5>Imagem(ns) do Produto</h5>\r\n");
      out.write("           </header>\r\n");
      out.write("       </div>\r\n");
      out.write("\r\n");
      out.write("       <p id=\"total-imagens\">Total de imagens anexadas: 0</p>\r\n");
      out.write("\r\n");
      out.write("       <table id=\"tabela-imagem\">\r\n");
      out.write("           <tbody id=\"lista-imagens\">\r\n");
      out.write("               <!-- Esta parte será preenchida dinamicamente com JavaScript -->\r\n");
      out.write("           </tbody>\r\n");
      out.write("       </table>\r\n");
      out.write("                    <button type=\"submit\" id=\"btn-salvar\">Salvar</button>\r\n");
      out.write("                </form>\r\n");
      out.write("                <form action=\"/listarProdutos\" method=\"GET\">\r\n");
      out.write("                    <button type=\"submit\" id=\"btn-cancelar\">Cancelar</button>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("    </article>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
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
      out.write("    }\r\n");
      out.write("    );\r\n");
      out.write("\r\n");
      out.write("    document.getElementById('selecao-imagem').addEventListener('change', function(event) {\r\n");
      out.write("           var imagens = event.target.files;\r\n");
      out.write("           var listaImagens = document.getElementById('lista-imagens');\r\n");
      out.write("\r\n");
      out.write("           // Adiciona as novas imagens à lista existente\r\n");
      out.write("           for (var i = 0; i < imagens.length; i++) {\r\n");
      out.write("               var imagem = imagens[i];\r\n");
      out.write("               var tr = document.createElement('tr');\r\n");
      out.write("               var td = document.createElement('td');\r\n");
      out.write("               var div = document.createElement('div');\r\n");
      out.write("               div.className = 'informacao-imagem';\r\n");
      out.write("               div.innerHTML = `\r\n");
      out.write("                   <form action=\"/excluir-imagem\" method=\"DELETE\">\r\n");
      out.write("                       <button type=\"submit\" class=\"btn-excluir\">X</button>\r\n");
      out.write("                   </form>\r\n");
      out.write("                   <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${URL.createObjectURL(imagem)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" alt=\"Imagem do Produto\">\r\n");
      out.write("                   <input type=\"checkbox\" name=\"codQualificacao\" class=\"qualificacao-produto\" placeholder=\"Nome do produto\" required>\r\n");
      out.write("               `;\r\n");
      out.write("               td.appendChild(div);\r\n");
      out.write("               tr.appendChild(td);\r\n");
      out.write("               listaImagens.appendChild(tr);\r\n");
      out.write("           }\r\n");
      out.write("\r\n");
      out.write("           // Atualiza o total de imagens anexadas\r\n");
      out.write("           var totalAtual = parseInt(document.getElementById('total-imagens').textContent.split(' ')[4]); // Extrai o número atual de imagens anexadas\r\n");
      out.write("           var novoTotal = totalAtual + imagens.length;\r\n");
      out.write("           document.getElementById('total-imagens').textContent = 'Total de imagens anexadas: ' + novoTotal;\r\n");
      out.write("       });\r\n");
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
