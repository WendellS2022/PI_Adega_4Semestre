document.getElementById('selecao-imagem').addEventListener('change', function(event) {
    var imagens = event.target.files;
    var listaImagens = document.getElementById('imagens-produto');
    var imagensArray = []; // Array para armazenar os caminhos das imagens

    // Itera sobre as imagens selecionadas
    for (var i = 0; i < imagens.length; i++) {
        var imagem = imagens[i];
        var div = document.createElement('div');
        div.className = 'informacao-imagem';

        // Cria um novo nome para a imagem usando um timestamp para evitar duplicatas
        var novoNome = 'imagem_' + Date.now() + '_' + imagem.name;

        // Adiciona a imagem à lista de imagens
        var caminhoRelativo = 'imagens/' + novoNome;
        div.innerHTML = `
            <button type="button" class="btn-excluir">Excluir</button>
            <img src="${URL.createObjectURL(imagem)}" alt="Imagem do Produto" style="max-width: 100px; max-height: 100px;">
            <input type="radio" name="imagemPrincipal" class="imagem-principal" data-caminho="${caminhoRelativo}" ${i === 0 ? 'checked' : ''}>
        `;
        listaImagens.appendChild(div);

        // Adiciona o caminho da imagem ao array
        imagensArray.push(caminhoRelativo);

        // Adiciona o manipulador de eventos para o botão de exclusão
        var btnExcluir = div.querySelector('.btn-excluir');
        btnExcluir.addEventListener('click', function() {
            var caminhoExcluido = this.nextElementSibling.src; // Obtém o caminho da imagem excluída
            var parentDiv = this.parentNode; // Encontra o elemento pai da imagem
            parentDiv.remove(); // Remove o elemento pai que contém a imagem
            // Remove a imagem do servidor
            excluirImagemNoServidor(caminhoExcluido);
            // Remove o caminho da imagem excluída do array de caminhos de imagens
            var index = imagensArray.indexOf(caminhoExcluido);
            if (index !== -1) {
                imagensArray.splice(index, 1);
            }
            // Atualiza o total de imagens anexadas
            document.getElementById('total-imagens').textContent = 'Total de imagens anexadas: ' + imagensArray.length;
        });

        // Salva a imagem no diretório da aplicação (substitua '/caminho/para/diretorio' pelo caminho real)
        salvarImagemNoDiretorio(imagem, novoNome, '/imagens'); // Chamada corrigida
    }

    // Atualiza o total de imagens anexadas
    document.getElementById('total-imagens').textContent = 'Total de imagens anexadas: ' + imagensArray.length;

    // Aqui você pode fazer o envio do array de caminhos para o backend para posterior salvamento no banco de dados
    console.log(imagensArray); // Exemplo de como enviar o array para o backend
});

function salvarImagemNoDiretorio(imagem, nomeArquivo, diretorio) {
    var formData = new FormData();
    formData.append('selImagem', imagem);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/uploadImagem', true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log('Imagem enviada com sucesso!');
        } else {
            console.error('Erro ao enviar imagem:', xhr.statusText);
        }
    };
    xhr.send(formData);
}

