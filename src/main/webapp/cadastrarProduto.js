document.getElementById('selecao-imagem').addEventListener('change', function(event) {
    var imagens = event.target.files;
    var listaImagens = document.getElementById('lista-imagens');

    // Adiciona as novas imagens à lista existente
    for (var i = 0; i < imagens.length; i++) {
        adicionarImagem(imagens[i], listaImagens, i === 0); // Define a imagem padrão se for a primeira imagem
    }

    // Atualiza o total de imagens anexadas
    updateTotalImagens();

    document.addEventListener('click', function(event) {
        if (event.target.classList.contains('btn-excluir')) {
            event.preventDefault(); // Previne o comportamento padrão do botão
            excluirImagem(event.target); // Exclui a imagem associada ao botão clicado
        }
    });
});

function adicionarImagem(imagem, listaImagens, definirPadrao) {
    var tr = document.createElement('tr');
    var td = document.createElement('td');
    var div = document.createElement('div');
    div.className = 'informacao-imagem';
    div.innerHTML = `
        <form action="/excluir-imagem" method="DELETE">
            <button type="submit" class="btn-excluir">X</button>
        </form>
        <img src="${URL.createObjectURL(imagem)}" alt="Imagem do Produto">
        <input type="checkbox" name="codQualificacao" class="qualificacao-produto" placeholder="Nome do produto" ${definirPadrao ? 'checked' : ''} required>
    `;
    td.appendChild(div);
    tr.appendChild(td);
    listaImagens.appendChild(tr);
}

function excluirImagem(botaoExcluir) {
    var tr = botaoExcluir.closest('tr'); // Obtém a linha da tabela que contém a imagem
    tr.remove(); // Remove a linha da tabela
    updateTotalImagens(); // Atualiza o total de imagens anexadas
}
//function definirImagemPadrao(codQualificacao) {
//    if (checkbox.checked) {
//        var checkboxes = document.querySelectorAll('.qualificacao-produto');
//        checkboxes.forEach(function(cb) {
//            if (cb !== checkbox) {
//                cb.checked = false; // Desmarca os outros checkboxes
//            }
//        });
//    }
//}
function updateTotalImagens() {
    var totalImagens = document.querySelectorAll('#lista-imagens tr').length; // Conta o número de linhas na tabela
    document.getElementById('total-imagens').textContent = 'Total de imagens anexadas: ' + totalImagens;
}

