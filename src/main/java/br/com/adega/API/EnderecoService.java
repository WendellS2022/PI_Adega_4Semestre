package br.com.adega.API;
import br.com.adega.Model.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class EnderecoService {
    public static Endereco consultarCEP(String cep) {
        try {
            // Formata a URL da API de consulta de CEP
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            // Realiza a requisição GET para a API e mapeia o resultado para um objeto Endereco
            ObjectMapper mapper = new ObjectMapper();
            Endereco endereco = mapper.readValue(new URL(url), Endereco.class);

            return endereco;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Tratamento de erro: retorna null em caso de falha na consulta
        }
    }
}
