function validarSenhas() {
    var senhaOriginal = document.getElementById('senha-usuario').value;
    var senhaConfirmacao = document.getElementById('senha-usuario-confirmacao').value;

    var mensagemErroSenha = document.getElementById('mensagem-erro-senha');

    if (senhaOriginal !== senhaConfirmacao) {
        mensagemErroSenha.innerText = 'As senhas não coincidem';
    } else {
        mensagemErroSenha.innerText = '';
    }
}

document.getElementById('senha-usuario').addEventListener('input', validarSenhas);
document.getElementById('senha-usuario-confirmacao').addEventListener('input', validarSenhas);
