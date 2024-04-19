
    $(document).ready(function() {
        // Função para verificar se o nome do cliente atende aos requisitos
        function validarNome(nome) {
            var palavras = nome.split(" "); // Divide o nome em palavras
            if (palavras.length >= 2) { // Verifica se há pelo menos duas palavras
                // Verifica se cada palavra tem pelo menos 3 letras
                for (var i = 0; i < palavras.length; i++) {
                    if (palavras[i].length < 3) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }

        // Função para atualizar o estado do botão de salvar e mostrar a mensagem de erro
        function atualizarBotaoSalvar() {
            var nome = $("#nome").val(); // Obtém o valor do campo de nome
            var botaoSalvar = $("#btn-salvar"); // Obtém o botão de salvar
            var mensagemErro = $("#nome-error-message"); // Obtém o elemento para a mensagem de erro

            // Verifica se o nome atende aos requisitos
            if (validarNome(nome)) {
                botaoSalvar.prop("disabled", false); // Habilita o botão de salvar
                mensagemErro.text(""); // Limpa a mensagem de erro
            } else {
                botaoSalvar.prop("disabled", true); // Desabilita o botão de salvar
                mensagemErro.text("O nome deve conter pelo menos duas palavras com no mínimo três letras cada."); // Define a mensagem de erro
            }
        }

        // Ao digitar no campo de nome, verificar se o botão de salvar deve ser habilitado ou desabilitado
        $("#nome").keyup(function() {
            atualizarBotaoSalvar();
        });

        // Ao enviar o formulário, verificar novamente se o nome atende aos requisitos
        $("#cadastroForm").submit(function(event) {
            var nome = $("#nome").val(); // Obtém o valor do campo de nome

            // Se o nome não atender aos requisitos, impedir o envio do formulário
            if (!validarNome(nome)) {
                event.preventDefault(); // Impede o envio do formulário
                // Exibe uma mensagem de erro na tela
                $("#nome-error-message").text("O nome deve conter pelo menos duas palavras com no mínimo três letras cada.");
            }
        });
    });
