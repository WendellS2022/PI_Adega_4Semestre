package br.com.adega.Autenticacao;

import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AutenticacaoService {
    private BCryptPasswordEncoder encoder;

    public AutenticacaoService() {
        this.encoder = new BCryptPasswordEncoder();
    }

    public User autenticarUsuario(String email, String senha) {
        User usuario = UsuarioDAO.verificarCredenciais(email);
        if (usuario != null && encoder.matches(senha, usuario.getSenha())) {
            return usuario;
        } else {
            return null;
        }
    }
}