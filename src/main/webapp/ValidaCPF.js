 function formatarCPF(cpf) {
            cpf = cpf.replace(/\D/g, ''); // Remove caracteres não numéricos
            cpf = cpf.replace(/^(\d{3})(\d)/, '$1.$2'); // Insere ponto após o terceiro dígito
            cpf = cpf.replace(/^(\d{3})\.(\d{3})(\d)/, '$1.$2.$3'); // Insere ponto após o sexto dígito
            cpf = cpf.replace(/^(\d{3})\.(\d{3})\.(\d{3})(\d)/, '$1.$2.$3-$4'); // Insere hífen após o nono dígito
            return cpf;
        }

        function validarCPF(cpf) {
            cpf = cpf.replace(/\D/g, ''); // Remove caracteres não numéricos

            // Verifica se a quantidade de dígitos é igual a 11
            if (cpf.length !== 11) {
                return false;
            }

            // Calcula o primeiro dígito verificador
            var soma = 0;
            for (var i = 0; i < 9; i++) {
                soma += parseInt(cpf.charAt(i)) * (10 - i);
            }
            var resto = (soma * 10) % 11;
            var digitoVerificador1 = (resto === 10 || resto === 11) ? 0 : resto;

            // Calcula o segundo dígito verificador
            soma = 0;
            for (var i = 0; i < 10; i++) {
                soma += parseInt(cpf.charAt(i)) * (11 - i);
            }
            resto = (soma * 10) % 11;
            var digitoVerificador2 = (resto === 10 || resto === 11) ? 0 : resto;

            // Verifica se os dígitos verificadores calculados são iguais aos informados
            if (parseInt(cpf.charAt(9)) !== digitoVerificador1 || parseInt(cpf.charAt(10)) !== digitoVerificador2) {
                return false;
            }

            return true;
        }

        // Adiciona a máscara de CPF e valida o CPF quando o usuário digita no campo
        document.getElementById('cpf-usuario').addEventListener('input', function (e) {
            var input = e.target;
            var valor = input.value;
            input.value = formatarCPF(valor);

            if (!validarCPF(valor)) {
                input.setCustomValidity('CPF inválido');
            } else {
                input.setCustomValidity('');
            }
        });


