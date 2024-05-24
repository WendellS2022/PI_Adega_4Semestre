// Captura o evento de envio do formulário de atualização de quantidade
$('.update-quantity-form').submit(function(event) {
    // Evita que o formulário seja enviado normalmente
    event.preventDefault();

    // Obtém o valor do campo CEP
    var cepValue = $('#CEP').val();

    // Obtém o valor do campo action
    var actionValue = $(this).attr('action');

    // Obtém o valor do campo produtoId dentro do formulário atual
    var produtoIdValue = $(this).find('input[name="produtoId"]').val();

    // Obtém o botão clicado (decrease ou increase)
    var buttonClicked = $(this).find('button[type="submit"]:focus').val();

    // Cria um objeto de dados para enviar via AJAX
    var data = {
        cep: cepValue,
        action: actionValue,
        produtoId: produtoIdValue,
        buttonClicked: buttonClicked
    };

    // Envia a requisição AJAX
    $.ajax({
        type: 'POST',
        url: actionValue,
        data: data,
        success: function(response) {
            // Lida com a resposta da servlet, se necessário
           setTimeout(function() {
                   location.reload(); // Recarrega a página
               }, 0100); // Tempo em milissegundo
        },
        error: function(xhr, status, error) {
            // Lida com erros de requisição
            console.error('Erro na requisição:', status, error);
        }
    });
});
