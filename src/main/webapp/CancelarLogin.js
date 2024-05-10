document.getElementById("btn-cancelar").addEventListener("click", function(event) {
    event.preventDefault(); // Evita o envio do formulário padrão

    // Captura o valor da variável "clienteLogado"
    var clienteLogado = document.querySelector('input[name="clienteLogado"]').value;

    // Exibe o valor da variável "clienteLogado" no console
    console.log("Valor de clienteLogado:", clienteLogado);

    // Tratamento de clienteLogado para evitar comparações sensíveis a maiúsculas/minúsculas
    clienteLogado = clienteLogado.toLowerCase();

    // Verifica se o cliente está logado
    if (clienteLogado !== '') {
        // Redirecionar para a página de produtos se o cliente estiver logado
        window.location.href = '/TelaProdutos';
    } else {
        // Limpar campos de email e senha se o cliente não estiver logado
        document.querySelector('input[name="email"]').value = '';
        document.querySelector('input[name="password"]').value = '';
    }
});
