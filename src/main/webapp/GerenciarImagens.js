document.getElementById('selecao-imagem').addEventListener('change', function(event) {
    var imagens = event.target.files;
    salvarImagemNoDiretorio(imagens);
    // Mostrar elemento de carregamento
    document.getElementById('loading').style.display = 'block';
});

document.querySelectorAll('.imagem-principal').forEach(function(radioButton) {
    radioButton.addEventListener('change', function(event) {
        var caminhoImagemPrincipal = this.dataset.caminho;
        document.getElementById('caminho-imagem-principal').value = caminhoImagemPrincipal;

        // Descarrega a tela antes de enviar o caminho da imagem principal
        setTimeout(function() {
            enviarCaminhoImagemPrincipal(caminhoImagemPrincipal);
        }, 1000); // 1000 milissegundos = 1 segundo
    });
});

function enviarCaminhoImagemPrincipal(caminhoImagemPrincipal) {
    // Recarrega a tela após 1 segundo
    setTimeout(function() {
        window.location.reload();
    }, 800); // 1000 milissegundos = 1 segundo

    // Aqui você pode adicionar o código para enviar o caminho da imagem principal para o backend
    var formData = new FormData();
    formData.append('caminhoImagemPrincipal', caminhoImagemPrincipal);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/setarImagemPrincipal', true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log('Caminho da imagem principal enviado com sucesso!');
        } else {
            console.error('Erro ao enviar caminho da imagem principal:', xhr.statusText);
        }
    };
    xhr.send(formData);
}



function salvarImagemNoDiretorio(imagens) {
    // Adiciona o código do produto ao FormData
    var codProduto = document.getElementById('codProduto').value;

    // Itera sobre todas as imagens selecionadas
    for (var i = 0; i < imagens.length; i++) {
        var imagem = imagens[i];
        var formData = new FormData();
        formData.append('codProduto', codProduto);
        formData.append('selImagem', imagem);

        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/gerenciarImagens', true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log('Imagem enviada com sucesso!');
            } else {
                console.error('Erro ao enviar imagem:', xhr.statusText);
            }
        };
        xhr.send(formData);
    }
}

document.getElementById('selecao-imagem').addEventListener('change', function(event) {
    var imagens = event.target.files;
    recarregarTela(imagens);


    loading.style.display = 'block';
    // Recarregar a página após um segundo
    setTimeout(function() {
        location.reload();
    }, 800);
});


function recarregarTela(imagens) {
    // Adiciona o código do produto ao FormData
    var codProduto = document.getElementById('codProduto').value;

    // Itera sobre todas as imagens selecionadas
    for (var i = 0; i < imagens.length; i++) {
        var imagem = imagens[i];
        var formData = new FormData();
        formData.append('codProduto', codProduto);
        formData.append('selImagem', imagem);

        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/gerenciarImagens', true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log('Imagem enviada com sucesso!');
            } else {
                console.error('Erro ao enviar imagem:', xhr.statusText);
            }
        };
        xhr.send(formData);
    }
}