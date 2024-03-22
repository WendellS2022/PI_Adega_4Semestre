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



        // Adiciona a imagem à lista de imagens
        var caminhoRelativo = 'imagens/' + novoNome;
        div.innerHTML = `
            <button type="button" class="btn-excluir">Excluir</button>
            <img src="${URL.createObjectURL(imagem)}" alt="Imagem do Produto" style="max-width: 100px; max-height: 100px;">
            <input type="radio" name="imagemPrincipal" class="imagem-principal" data-caminho="${caminhoRelativo}" ${i === 0 ? 'checked' : ''}>
        `;
        td.appendChild(div);
        tr.appendChild(td);
        listaImagens.appendChild(tr);

        // Adiciona o caminho da imagem ao array
        imagensArray.push(caminhoRelativo);

        // Adiciona o manipulador de eventos para o botão de exclusão
        var btnExcluir = div.querySelector('.btn-excluir');
        btnExcluir.addEventListener('click', function() {
            tr.remove(); // Remove a linha da tabela que contém a imagem
        });

        // Adiciona o manipulador de eventos para marcar a imagem principal
        var radioPrincipal = div.querySelector('.imagem-principal');
        radioPrincipal.addEventListener('change', function() {
            imagemPrincipalCaminho = caminhoRelativo;
            document.getElementById('caminho-imagem-principal').value = caminhoRelativo;
        });

        // Salva a imagem no diretório da aplicação (substitua '/caminho/para/diretorio' pelo caminho real)
        salvarImagemNoDiretorio(imagem, novoNome, '/imagens'); // Chamada corrigida
    }

    // Atualiza o total de imagens anexadas
    var totalAtual = parseInt(document.getElementById('total-imagens').textContent.split(' ')[4]); // Extrai o número atual de imagens anexadas
    var novoTotal = totalAtual + imagens.length;
    document.getElementById('total-imagens').textContent = 'Total de imagens anexadas: ' + novoTotal;

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

function excluirImagem(elementoTR) {
    elementoTR.remove(); // Remove a linha da tabela que contém a imagem
}

// Supondo que "listaDeImagens" seja a lista de imagens recebida da servlet
// Vamos iterar sobre essa lista e adicionar cada imagem à tabela
//var listaDeImagens = []; // Substitua por sua lista real de imagens
//
//var tabelaImagem = document.getElementById('tabela-imagem');
//var listaImagens = document.getElementById('lista-imagens');
//
//listaDeImagens.forEach(function(imagem) {
//    var linha = listaImagens.insertRow();
//    var coluna = linha.insertCell();
//    var img = document.createElement('img');
//    img.src = imagem; // Defina o src da imagem com o caminho recebido
//    coluna.appendChild(img);
//});
