
    document.getElementById("btn-salvar").addEventListener("click", function(event) {
        event.preventDefault(); // Evita o envio do formulário padrão

        // Captura o valor da variável "adicionar" e "clienteLogado"
        var adicionar = document.querySelector('input[name="adicionar"]').value;
        var clienteLogado = document.querySelector('input[name="clienteLogado"]').value;

        console.log("Valor de adicionar:", adicionar);
        console.log("Valor de clienteLogado:", clienteLogado);

        // Verifica se adicionar é false
        if (adicionar === 'false') {
            // Altera a action do formulário para apontar para a servlet /CadastrarEndereco
            document.getElementById("enderecoForm").action = "/CadastrarEndereco";


            document.getElementById("enderecoForm").submit();
        } else {
            document.getElementById("enderecoForm").action = "/alterarEndereco";

            document.getElementById("enderecoForm").submit();
        }
    });

