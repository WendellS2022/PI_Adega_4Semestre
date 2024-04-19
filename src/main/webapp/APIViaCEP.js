 $(document).ready(function() {
        // Evento de clique no botão para buscar o CEP
        $('#btn-buscar-cep').click(function() {
            var cep = $('#cep').val();
            // Realizar a busca do endereço a partir do CEP
            $.getJSON("https://viacep.com.br/ws/" + cep + "/json/", function(data) {
                // Verificar se os dados foram encontrados
                if (!("erro" in data)) {
                    // Preencher os campos com os dados do endereço
                    $('#logradouro').val(data.logradouro);
                    $('#bairro').val(data.bairro);
                    $('#cidade').val(data.localidade);
                    $('#estado').val(data.uf);
                } else {
                    alert("CEP não encontrado. Por favor, verifique e tente novamente.");
                }
            });
        });
    });