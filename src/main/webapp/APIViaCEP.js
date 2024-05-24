$(document).ready(function() {
    // Evento de clique no botão para buscar o CEP
    $('#btn-buscar-cep').click(function() {
        var cep = $('#cep').val();
        console.log("CEP buscado:", cep);

        // Realizar a busca do endereço a partir do CEP
        $.getJSON("https://viacep.com.br/ws/" + cep + "/json/", function(data) {
            console.log("Dados retornados pela API:", data);

            // Verificar se os dados foram encontrados
            if (!("erro" in data)) {
                // Preencher os campos com os dados do endereço
                $('#logradouro').val(data.logradouro);
                $('#bairro').val(data.bairro);
                $('#cidade').val(data.localidade);
                $('#estado').val(data.uf);
                console.log("Endereço preenchido com sucesso.");
            } else {
                alert("CEP não encontrado. Por favor, verifique e tente novamente.");
                console.log("Erro: CEP não encontrado.");
            }
        }).fail(function(jqxhr, textStatus, error) {
            var err = textStatus + ", " + error;
            console.log("Request Failed: " + err);
            alert("Erro ao buscar o CEP. Por favor, tente novamente mais tarde.");
        });
    });
});