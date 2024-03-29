function excluirImagemNoServidor(caminhoImagem) {
    fetch('/sua_servlet_url?caminho=' + caminhoImagem, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            console.log('Imagem excluída com sucesso!');
            // Faça qualquer ação adicional que desejar após a exclusão bem-sucedida
        } else {
            console.error('Erro ao excluir imagem:', response.statusText);
            // Lide com erros de exclusão aqui
        }
    })
    .catch(error => {
        console.error('Erro ao excluir imagem:', error);
    });
}