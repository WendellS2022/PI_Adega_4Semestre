// Captura o evento de entrada no campo CEP
document.getElementById('CEP').addEventListener('input', function() {
    var cepValue = this.value.trim();
    var pagamentoContainer = document.getElementById('pagamento-container');

    if (cepValue === "") {
        pagamentoContainer.style.display = 'none';
    } else {
        pagamentoContainer.style.display = 'block';
    }
});

// Inicialmente oculta o campo de forma de pagamento se o campo CEP estiver vazio ao carregar a página
document.addEventListener('DOMContentLoaded', function() {
    var cepValue = document.getElementById('CEP').value.trim();
    var pagamentoContainer = document.getElementById('pagamento-container');

    if (cepValue === "") {
        pagamentoContainer.style.display = 'none';
    }
});
