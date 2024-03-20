document.getElementById('selecao-imagem').addEventListener('change', function(event) {
    var imagens = event.target.files;
    var listaImagens = document.getElementById('lista-imagens');
    var imagensArray = []; // Array para armazenar os caminhos das imagens

    // Itera sobre as imagens selecionadas
    for (var i = 0; i < imagens.length; i++) {
        var imagem = imagens[i];
        var tr = document.createElement('tr');
        var td = document.createElement('td');
        var div = document.createElement('div');
        div.className = 'informacao-imagem';

        // Cria um novo nome para a imagem usando um timestamp para evitar duplicatas
        var novoNome = 'imagem_' + Date.now() + '_' + imagem.name;

        // Salva a imagem no diretório da aplicação (substitua '/caminho/para/diretorio' pelo caminho real)
        var caminhoRelativo = '/imagens/' + novoNome; // Caminho relativo ao diretório da aplicação
        salvarImagemNoDiretorio(imagem, novoNome, '/imagens'); // Chamada corrigida

        // Adiciona o caminho da imagem ao array
        imagensArray.push(caminhoRelativo);

        div.innerHTML = `
            <button type="button" class="btn-excluir">X</button>
            <img src="${URL.createObjectURL(imagem)}" alt="Imagem do Produto">
            <input type="radio" name="imagemPrincipal" class="imagem-principal" data-caminho="${caminhoRelativo}" ${i === 0 ? 'checked' : ''}>
        `;
        td.appendChild(div);
        tr.appendChild(td);
        listaImagens.appendChild(tr);

        // Adiciona o manipulador de eventos para o botão de exclusão
        var btnExcluir = div.querySelector('.btn-excluir');
        btnExcluir.addEventListener('click', function() {
        excluirImagem(this.closest('tr')); // Chama a função para excluir a linha da tabela
        });
    }

    // Atualiza o total de imagens anexadas
    var totalAtual = parseInt(document.getElementById('total-imagens').textContent.split(' ')[4]); // Extrai o número atual de imagens anexadas
    var novoTotal = totalAtual + imagens.length;
    document.getElementById('total-imagens').textContent = 'Total de imagens anexadas: ' + novoTotal;

    // Aqui você pode fazer o envio do array de caminhos para o backend para posterior salvamento no banco de dados
    console.log(imagensArray); // Exemplo de como enviar o array para o backend
});

function salvarImagemNoDiretorio(imagem, caminhoRelativo) {
    // Aqui você pode implementar a lógica para salvar a imagem no diretório da aplicação
    // Isso depende da tecnologia do servidor backend que você está utilizando (Java, Node.js, PHP, etc.)
    // Por exemplo, se estiver usando Node.js com Express, pode usar a função fs.writeFile para salvar a imagem no disco
}

function excluirImagem(elementoTR) {
    elementoTR.remove(); // Remove a linha da tabela que contém a imagem
}
function salvarImagemNoDiretorio(imagem) {
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

