document.getElementById('senha-usuario-confirmacao').addEventListener('input', function (e) {
    var senhaOriginal = document.getElementById('senha-usuario').value;
    var senhaConfirmacao = e.target.value;

    if (senhaOriginal !== senhaConfirmacao) {
        document.getElementById('mensagem-erro-senha').innerText = 'As senhas não coincidem';
    } else {
        document.getElementById('mensagem-erro-senha').innerText = '';
    }
});
