package br.com.adega.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUploadHelper {

    public static boolean saveFile(InputStream inputStream, String nomeArquivo, String diretorio) {
        // Caminho completo do arquivo no diretório da aplicação
        String caminhoCompleto = diretorio + File.separator + nomeArquivo;

        try {
            // Cria o diretório se ele não existir
            File directory = new File(diretorio);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Cria o arquivo
            File arquivo = new File(caminhoCompleto);

            // Copia os dados do InputStream para o arquivo no disco
            FileOutputStream outputStream = new FileOutputStream(arquivo);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();

            System.out.println("Imagem salva com sucesso: " + caminhoCompleto);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar a imagem: " + e.getMessage());
            return false;
        }
    }
}
