document.querySelectorAll('.status-dropdown').forEach(function(dropdown) {
    dropdown.addEventListener('change', function(event) {
        // Obtém o valor do status selecionado
        var status = this.value;

        // Obtém o pedidoId do atributo data-pedido-id
        var pedidoId = this.getAttribute('data-pedido-id');

        // Envia o pedidoId e o status para a servlet usando AJAX
        enviarStatusParaServlet(pedidoId, status);
    });
});

function enviarStatusParaServlet(pedidoId, status) {
    var xhr = new XMLHttpRequest();
    var url = '/atualizarStatusPedido?pedidoId=' + encodeURIComponent(pedidoId) + '&status=' + encodeURIComponent(status);
    xhr.open('GET', url, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log('Status do pedido enviado com sucesso para a servlet!');
        } else {
            console.error('Erro ao enviar status do pedido para a servlet:', xhr.statusText);
        }
    };
    xhr.send();
}