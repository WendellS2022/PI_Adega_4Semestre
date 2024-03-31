package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cadastrarProduto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,    // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CadastrarProduto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        int grupo = UsuarioDAO.ObterGrupo(usuarioLogado);
        request.setAttribute("grupo", grupo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Processar as informações do produto
        Produto produto = new Produto();
        Boolean isTemp = Boolean.parseBoolean(request.getParameter("temp"));
        String codProdutoParam = request.getParameter("codProdutoimg");
        String codProduto = request.getParameter("codProduto");

        int qtdImagens = Integer.parseInt(request.getParameter("quantidadeImagens"));

        if (isTemp) {
            List<Imagem> imagesTemp = processarImagensTemp(request, qtdImagens);
            request.setAttribute("imagensTempProduto", imagesTemp);
            request.setAttribute("imagensProduto",imagesTemp);
            request.setAttribute("imagemTempProdutoDir", imagesTemp.get(0).getDiretorio() + "/" + imagesTemp.get(0).getNome());



            // Passa a lista de nomes de imagens para o lado do cliente

            RequestDispatcher dispatcher=request.getRequestDispatcher("/GerenciarImagem.jsp");
            dispatcher.forward(request,response);
            return;
        }



        if(codProduto!=null&&!codProduto.isEmpty())

        {
        boolean imagemAdcionada=false;
        imagemAdcionada=processarImagens(request,Integer.parseInt(codProduto));
        if(imagemAdcionada){
        List<Imagem> imagensProduto=ProdutoDAO.obterImagensPorProdutoId(Integer.parseInt(codProduto));
        request.setAttribute("imagensProduto",imagensProduto);
        request.setAttribute("codProduto",codProduto);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/GerenciarImagem.jsp");
        dispatcher.forward(request,response);
        return;
        }else{

        request.setAttribute("codProduto",codProduto);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/GerenciarImagem.jsp");
        dispatcher.forward(request,response);
        }
        return;
        }else

        {

        if(codProdutoParam!=null&&!codProdutoParam.isEmpty()){
        // Atualizar o produto existente
        produto.setCodProduto(Integer.parseInt(codProdutoParam));
        preencherProduto(request,produto);
        boolean updateProduto=ProdutoDAO.AtualizarProduto(produto);

        if(updateProduto){
        request.setAttribute("mensagem","Produto alterado com sucesso!");

        request.setAttribute("produto",produto);


        RequestDispatcher dispatcher=request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request,response);
        }else{
        request.setAttribute("mensagem","Falha ao alterar produto!");

        request.setAttribute("produto",produto);


        RequestDispatcher dispatcher=request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request,response);
        }
        }else{
        // Adicionar um novo produto
        preencherProduto(request,produto);
        int novoProdutoId=ProdutoDAO.AdicionarProdutoRetornandoCodigo(produto);

        if(novoProdutoId>0){
        carregarProduto(request,response,String.valueOf(novoProdutoId));
        request.setAttribute("mensagem","Produto adicionado com sucesso!");
        }else{
        request.setAttribute("mensagem","Falha ao adicionar produto!");
        }
        return;
        }
        }

        }

private void preencherProduto(HttpServletRequest request,Produto produto){
        produto.setNomeProduto(request.getParameter("nomeProduto"));
        produto.setDscDetalhadaProduto(request.getParameter("dscDetalhadaProduto"));
        produto.setAvaliacaoProduto(Double.parseDouble(request.getParameter("avaliacaoProduto")));
        produto.setVlrVendaProduto(new BigDecimal(request.getParameter("vlrVendaProduto")));
        produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));
        produto.setSituacaoProduto(Boolean.parseBoolean(request.getParameter("situacao")));
        }

private boolean processarImagens(HttpServletRequest request,int produtoId)throws IOException,ServletException{
        List<Part> fileParts=request.getParts().stream()
        .filter(part->"selImagem".equals(part.getName()))
        .collect(Collectors.toList());

        String diretorio="imagens";
        String diretorioAbsoluto=getServletContext().getRealPath("/"+diretorio);
        File diretorioImagens=new File(diretorioAbsoluto);
        if(!diretorioImagens.exists()){
        diretorioImagens.mkdirs();
        }

        List<Imagem> nomesImagensExistentes=ProdutoDAO.obterImagensPorProdutoId(produtoId);
        boolean imagemAdicionada=false;

        for(Part filePart:fileParts){
        String fileName=extractFileName(filePart);
        if(!fileName.isEmpty()){
        String novoNome="imagem_"+System.currentTimeMillis()+"_"+fileName;
        String filePath=diretorioAbsoluto+File.separator+novoNome;
        filePart.write(filePath);

        // Verificar se o nome da imagem já existe no banco de dados
        boolean imagemJaExiste=false;
        for(Imagem img:nomesImagensExistentes){
        if(img.getNome().equals(novoNome)){
        imagemJaExiste=true;
        break;
        }
        }

        // Se a imagem não existir no banco de dados, salve-a
        if(!imagemJaExiste){
        // Salvar informações da imagem no banco de dados
        Imagem imagem=new Imagem();
        imagem.setProdutoId(produtoId);
        imagem.setDiretorio(diretorio);
        imagem.setNome(novoNome);
        imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".")+1));
        imagem.setQualificacao(false); // Definir como false por padrão

        ProdutoDAO.AdicionarImagem(imagem);

        imagemAdicionada=true;
        }
        }
        }

        return imagemAdicionada;

        }

    public List<Imagem> processarImagensTemp(HttpServletRequest request, int qtdImagens) throws IOException, ServletException {
        List<Part> fileParts = request.getParts().stream()
                .filter(part -> "selImagem".equals(part.getName()))
                .limit(qtdImagens) // Limita a quantidade de FileParts conforme qtdImagens
                .collect(Collectors.toList());

        String diretorio = "imagens-temp";
        String diretorioAbsoluto = getServletContext().getRealPath("/" + diretorio);
        File diretorioImagens = new File(diretorioAbsoluto);
        if (!diretorioImagens.exists()) {
            diretorioImagens.mkdirs();
        }

        List<Imagem> listImages = new ArrayList<>();

        for (Part filePart : fileParts) {
            String fileName = extractFileName(filePart);
            if (!fileName.isEmpty()) {
                String novoNome = "imagem_" + System.currentTimeMillis() + "_" + fileName;
                String filePath = diretorioAbsoluto + File.separator + novoNome;
                filePart.write(filePath);

                Imagem imagem = new Imagem();
                //imagem.setProdutoId(produtoId);
                imagem.setDiretorio(diretorio);
                imagem.setNome(novoNome);
                imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".") + 1));
                imagem.setQualificacao(false);

                listImages.add(imagem);
            }
        }

        return listImages;
    }


private String extractFileName(Part part){
        String contentDisp=part.getHeader("content-disposition");
        String[]tokens=contentDisp.split(";");
        for(String token:tokens){
        if(token.trim().startsWith("filename")){
        return token.substring(token.indexOf("=")+2,token.length()-1);
        }
        }
        return"";
        }


private void carregarProduto(HttpServletRequest request,HttpServletResponse response,String codProdutoParam)throws ServletException,IOException{
        Produto produto=ProdutoDAO.ObterProdutoPorId(Integer.parseInt(codProdutoParam));

        if(produto!=null){
        // Passa as imagens e o produto para o JSP
        request.setAttribute("produto",produto);
        // Encaminha para a página de cadastro/edição de produtos

        RequestDispatcher dispatcher=request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request,response);
        }else{
        // Se o produto não foi encontrado, define uma mensagem de erro e encaminha para a página principal
        request.setAttribute("mensagem","Produto não encontrado!");
        RequestDispatcher dispatcher=request.getRequestDispatcher("/listarProdutos");
        dispatcher.forward(request,response);
        }
        }

private void setarImagemPrincipal(HttpServletRequest request,int codProduto,String nomeImagemPrincipal)throws IOException,ServletException{
        File file=new File(nomeImagemPrincipal);
        String nomeImagem=file.getName();
        if(!nomeImagem.isEmpty()){
        String fileName=extractFileName((Part)file);
        Imagem imagem=new Imagem();
        String novoNome="imagem_"+System.currentTimeMillis()+"_"+fileName;
        String diretorio="imagens";
        String diretorioAbsoluto=getServletContext().getRealPath("/"+diretorio);
        String filePath=diretorioAbsoluto+File.separator+novoNome;

        // Salvar o arquivo no servidor


        // Montar o objeto Imagem
        imagem.setProdutoId(codProduto);
        imagem.setDiretorio(diretorio);
        imagem.setNome(novoNome);
        imagem.setQualificacao(true);
        imagem.setExtensao(nomeImagem.substring(nomeImagem.lastIndexOf(".")+1));

        // Salvar informações da imagem no banco de dados
        boolean sucesso=ProdutoDAO.atualizarQualificacaoImagem(imagem);

        }
        }
        }


