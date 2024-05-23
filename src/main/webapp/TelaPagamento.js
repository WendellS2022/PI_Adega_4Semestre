document.addEventListener('DOMContentLoaded', function() {
    var continuarBtn = document.getElementById('continuar-btn');
    var form = document.querySelector('#pagamento-container form');

    continuarBtn.addEventListener('click', function(event) {
        event.preventDefault();

        var formData = new FormData(form);
        var serializedData = new URLSearchParams(formData).toString();

        fetch(form.action, {
            method: form.method,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: serializedData
        })
        .then(function(response) {
            return response.text();
        })
        .then(function(responseText) {
            console.log('Requisição enviada com sucesso.');
            console.log('Resposta da servlet:', responseText);

            return fetch('/pagamento', {
                method: 'GET'
            });
        })
        .then(function(response) {
            return response.text();
        })
        .then(function(responseText) {
            console.log('Chamada para /pagamento enviada com sucesso.');
            console.log('Resposta da servlet /pagamento:', responseText);
        })
        .catch(function(error) {
            console.error('Erro:', error);
        });
    });
});
document.addEventListener('DOMContentLoaded', function() {
    var continuarBtn = document.getElementById('continuar-btn');

    continuarBtn.addEventListener('click', function(event) {
        event.preventDefault();

        var cep = document.getElementById('CEP').value;
        var totalItensComprados = document.querySelector('input[name="totalItensComprados"]').value;
        var subtotal = document.querySelector('input[name="subtotal"]').value;

        console.log('CEP:', cep);
        console.log('Total de Itens Comprados:', totalItensComprados);
        console.log('Subtotal:', subtotal);

        // Construir a URL com os parâmetros
        var url = new URL('/pagamento', window.location.origin);
        var params = {
            cep: cep,
            totalItensComprados: totalItensComprados,
            subtotal: subtotal
        };

        Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));

        // Fazer a requisição fetch
        fetch(url, {
            method: 'GET',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(responseText => {
            console.log('Chamada para /pagamento enviada com sucesso.');
            console.log('Resposta da servlet /pagamento:', responseText);
            // Aqui você pode fazer algo com a resposta da servlet se necessário
        })
        .catch(error => {
            console.error('Erro:', error);
        });
    });
});
