function cancelarLogin() {
            // Captura o valor do botão
            var isCliente = document.getElementById("btn-cancelar").value === 'true';
            console.log("Valor de isCliente:", isCliente);

            // Verifica se o cliente está logado
            if (isCliente) {
                console.log("Cliente está logado, redirecionando para /TelaProdutos");
                // Redirecionar para a página de produtos se o cliente estiver logado
                window.location.href = '/TelaProdutos';
            } else {
                console.log("Cliente não está logado, limpando campos de email e senha");
                // Limpar campos de email e senha se o cliente não estiver logado
                document.querySelector('input[name="email"]').value = '';
                document.querySelector('input[name="password"]').value = '';
            }
        }