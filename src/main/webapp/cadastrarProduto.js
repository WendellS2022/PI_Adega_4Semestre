document.getElementById('selecao-imagem').addEventListener('change', function(event) {
    var imagens = event.target.files;
    console.log('Imagens selecionadas:', imagens);

    var listaImagens = document.getElementById('imagens-produto');
    var imagensArray = []; // Array para armazenar os caminhos das imagens

    // Limpa a pré-visualização existente
    listaImagens.innerHTML = '';

    // Itera sobre as imagens selecionadas
    for (var i = 0; i < imagens.length; i++) {
        var imagem = imagens[i];
        var div = document.createElement('div');
        div.className = 'informacao-imagem';

        // Cria um novo nome para a imagem usando um timestamp para evitar duplicatas
        var novoNome = 'imagem_' + Date.now() + '_' + imagem.name;

        // Adiciona a imagem à pré-visualização
        var caminhoRelativo = 'imagens/' + novoNome;
        div.innerHTML = `
            <button type="button" class="btn-excluir">Excluir</button>
            <img src="${URL.createObjectURL(imagem)}" alt="Imagem do Produto" style="max-width: 100px; max-height: 100px;">
            <input type="radio" name="imagemPrincipal" class="imagem-principal" data-caminho="${caminhoRelativo}" ${i === 0 ? 'checked' : ''}>
        `;
        listaImagens.appendChild(div);

        // Adiciona o caminho da imagem ao array
        imagensArray.push(caminhoRelativo);
    }

    // Adiciona um manipulador de eventos para os botões "Excluir"
    var btnsExcluir = document.querySelectorAll('.btn-excluir');
    btnsExcluir.forEach(function(btnExcluir) {
        btnExcluir.addEventListener('click', function() {
            var caminhoExcluido = this.nextElementSibling.src; // Obtém o caminho da imagem excluída
            console.log('Caminho da imagem excluída:', caminhoExcluido);

            var parentDiv = this.parentNode; // Encontra o elemento pai da imagem
            parentDiv.remove(); // Remove o elemento pai que contém a imagem

            // Remove a imagem do servidor
            excluirImagemNoServidor(caminhoExcluido);

            // Remove o caminho da imagem excluída do array de caminhos de imagens
            var index = imagensArray.indexOf(caminhoExcluido);
            if (index !== -1) {
                imagensArray.splice(index, 1);
                console.log('Array de caminhos atualizado:', imagensArray);
            }

            // Atualiza o total de imagens anexadas
            document.getElementById('total-imagens').textContent = 'Total de imagens anexadas: ' + imagensArray.length;
        });
    });

    // Adiciona um manipulador de eventos para os radiobuttons das imagens
    document.querySelectorAll('.imagem-principal').forEach(function(radioButton) {
        radioButton.addEventListener('change', function(event) {
            var caminhoImagemPrincipal = this.dataset.caminho;
            document.getElementById('caminho-imagem-principal').value = caminhoImagemPrincipal;
            console.log('Caminho da imagem principal:', caminhoImagemPrincipal);
        });
    });

    // Aqui você pode fazer o envio do array de caminhos para o backend para posterior salvamento no banco de dados
    console.log('Array de caminhos:', imagensArray); // Exemplo de como enviar o array para o backend
});

// Ouvinte de evento para o botão salvar
document.getElementById('btn-salvar').addEventListener('click', function(event) {
    event.preventDefault(); // Impede o envio do formulário padrão

    var imagens = document.getElementById('selecao-imagem').files;
    var formData = new FormData(); // Cria um novo objeto FormData para enviar os dados do formulário

    // Adiciona as imagens selecionadas ao objeto FormData
    for (var i = 0; i < imagens.length; i++) {
        formData.append('selImagem', imagens[i]);
    }

    // Adiciona o caminho da imagem principal ao objeto FormData
    var caminhoPrincipal = document.getElementById('caminho-imagem-principal').value;
    formData.append('caminhoImagemPrincipal', caminhoPrincipal);

    // Envia os dados para o backend
    enviarImagens(formData);
});

// Função para enviar as imagens para a servlet
function enviarImagens(formData) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/gerenciarImagens', true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log('Imagens enviadas com sucesso!');
            // Aqui você pode fazer algo após o envio bem-sucedido, como redirecionar para outra página
        } else {
            console.error('Erro ao enviar imagens:', xhr.statusText);
        }
    };
    xhr.send(formData);
}


function enviarImagens(formData) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/gerenciarImagens', true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log('Imagens enviadas com sucesso!');
            // Aqui você pode fazer algo após o envio bem-sucedido, como redirecionar para outra página
        } else {
            console.error('Erro ao enviar imagens:', xhr.statusText);
        }
    };
    xhr.send(formData);
}

function excluirImagemNoServidor(caminhoImagem) {
    // Implemente a lógica para excluir a imagem no servidor aqui
    console.log('Imagem excluída:', caminhoImagem);
}
