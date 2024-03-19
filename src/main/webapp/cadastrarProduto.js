/*// Função principal para lidar com a seleção de imagens
function handleImageSelection(event) {
    var imagens = event.target.files;
    var listaImagens = document.getElementById('lista-imagens');

    // Adiciona as novas imagens à lista existente
    for (var i = 0; i < imagens.length; i++) {
        addImage(imagens[i], listaImagens, i === 0); // Define a imagem padrão se for a primeira imagem
    }

    // Atualiza o total de imagens anexadas
    updateTotalImages();

    // Adiciona o evento de clique para excluir a imagem
    document.addEventListener('click', function(event) {
        if (event.target.classList.contains('btn-excluir')) {
            event.preventDefault(); // Previne o comportamento padrão do botão
            deleteImage(event.target); // Exclui a imagem associada ao botão clicado
        }
    });

    // Adiciona o evento de clique para marcar apenas uma imagem como principal
    document.querySelectorAll('.qualificacao-produto').forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
            if (this.checked) {
                uncheckOtherImages(this); // Desmarca as outras imagens se esta for marcada como principal
            }
        });
    });
}

// Função para adicionar uma imagem à lista
function addImage(imagemInfo, listaImagens, definirPadrao) {
    var tr = document.createElement('tr');
    var td = document.createElement('td');
    var div = document.createElement('div');
    div.className = 'informacao-imagem';
    div.innerHTML = `
        <form action="/excluir-imagem" method="DELETE">
            <button type="submit" class="btn-excluir">X</button>
        </form>
        <img src="${imagemInfo.caminho}" alt="Imagem do Produto">
        <input type="checkbox" name="codQualificacao" class="qualificacao-produto" placeholder="Nome do produto" ${definirPadrao ? 'checked' : ''} required>
    `;
    td.appendChild(div);
    tr.appendChild(td);
    listaImagens.appendChild(tr);
}

// Função para excluir uma imagem da lista
function deleteImage(deleteButton) {
    var tr = deleteButton.closest('tr'); // Obtém a linha da tabela que contém a imagem
    tr.remove(); // Remove a linha da tabela
    updateTotalImages(); // Atualiza o total de imagens anexadas
}

// Função para desmarcar as outras imagens se uma for marcada como principal
function uncheckOtherImages(checkedCheckbox) {
    document.querySelectorAll('.qualificacao-produto').forEach(function(checkbox) {
        if (checkbox !== checkedCheckbox) {
            checkbox.checked = false; // Desmarca os outros checkboxes
        }
    });

}

// Função para atualizar o total de imagens anexadas
function updateTotalImages() {
    var totalImages = document.querySelectorAll('#lista-imagens tr').length; // Conta o número de linhas na tabela
    document.getElementById('total-imagens').textContent = 'Total de imagens anexadas: ' + totalImages;
}

// Adiciona o evento de seleção de imagem ao elemento correspondente
document.getElementById('selecao-imagem').addEventListener('change', handleImageSelection);*/



Esse trecho é o contido no formulario CadastrarAlterarProduto.jsp

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
        var caminhoRelativo = '/caminho/para/diretorio/' + novoNome; // Caminho relativo ao diretório da aplicação
        salvarImagemNoDiretorio(imagem, caminhoRelativo);

        // Adiciona o caminho da imagem ao array
        imagensArray.push(caminhoRelativo);

        div.innerHTML = `
            <form action="/excluir-imagem" method="DELETE">
                <button type="submit" class="btn-excluir">X</button>
            </form>
            <img src="${URL.createObjectURL(imagem)}" alt="Imagem do Produto">
            <input type="checkbox" name="codQualificacao" class="qualificacao-produto" placeholder="Nome do produto" required>
        `;
        td.appendChild(div);
        tr.appendChild(td);
        listaImagens.appendChild(tr);
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


document.getElementById('btn-salvar').addEventListener('click', function(event) {
    event.preventDefault(); // Impede o comportamento padrão do botão (enviar o formulário)

    // Reúne os dados do formulário
    var formData = new FormData();

    // Adiciona as imagens selecionadas ao FormData
    var imagensSelecionadas = document.querySelectorAll('.informacao-imagem img');
    imagensSelecionadas.forEach(function(imagem) {
        formData.append('imagens', imagem.src);
    });

    // Obtém outros dados do formulário, se necessário, e adiciona ao FormData

    // Envia os dados para a servlet via requisição AJAX
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/cadastrarProduto');
    xhr.onload = function() {
        // Lida com a resposta da servlet, se necessário
        if (xhr.status === 200) {
            console.log('Produto cadastrado com sucesso!');
            // Faça algo com a resposta, se necessário
        } else {
            console.error('Erro ao cadastrar produto.');
            // Lida com o erro, se necessário
        }
    };
    xhr.send(formData);
});


