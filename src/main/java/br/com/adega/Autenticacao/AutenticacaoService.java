package br.com.adega.Autenticacao;

import br.com.adega.DAO.ClienteDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Cliente;
import br.com.adega.Model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AutenticacaoService {
    private BCryptPasswordEncoder encoder;

    public AutenticacaoService() {
        this.encoder = new BCryptPasswordEncoder();
    }

    public Usuario autenticarUsuario(String email, String senha) {
        Usuario usuario = UsuarioDAO.VerificarCredenciais(email);
        if (!usuario.isSituacao()) {
            return usuario;
        } else if (usuario != null && encoder.matches(senha, usuario.getSenha())) {
            return usuario;
        } else {
            return null;
        }
    }


    public Cliente autenticarCliente(String email, String senha) {

            Cliente cliente = ClienteDAO.VerificarCredenciais(email);
            if (cliente != null && encoder.matches(senha, cliente.getSenha())) {
                return cliente;
            } else {
                return null;
            }

        }
}