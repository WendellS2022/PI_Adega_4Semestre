document.querySelectorAll('input[type="radio"]').forEach(function(radioButton) {
    radioButton.addEventListener('change', function(event) {
        // Obtém o valor do input selecionado, que corresponde ao ID do endereço
        var enderecoId = this.value;

        // Envia o ID do endereço para a servlet usando AJAX
        enviarEnderecoParaServlet(enderecoId);
    });
});

function enviarEnderecoParaServlet(enderecoId) {
    var xhr = new XMLHttpRequest();
    var url = '/indicarEndereco?idEndereco=' + encodeURIComponent(enderecoId);
    xhr.open('GET', url, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log('ID do endereço enviado com sucesso para a servlet!');
        } else {
            console.error('Erro ao enviar ID do endereço para a servlet:', xhr.statusText);
        }
    };
    xhr.send();
}
