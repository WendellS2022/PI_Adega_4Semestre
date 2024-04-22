function cancelarLogin(isCliente) {
        if (isCliente === 'true') {
            window.location.href = '/TelaProdutos';
        } else {
            document.querySelector('input[name="email"]').value = '';
            document.querySelector('input[name="password"]').value = '';
        }
    }
