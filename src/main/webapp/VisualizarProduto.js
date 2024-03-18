setInterval( function(){
    nextImage()
}, 3000)

function nextImage(){
    const controleImagem = document.querySelector(".controle-imagem")
    const imagemAtiva = controleImagem.querySelector("[data-active]")
    let indiceImagemAtiva = Array.from(controleImagem.children).indexOf(imagemAtiva)

    indiceImagemAtiva++

    console.log (indiceImagemAtiva)

    if(indiceImagemAtiva >= controleImagem.children.length) {
        indiceImagemAtiva = 0
    }

    imagemAtiva.removeAttribute("data-active")
    controleImagem.children[indiceImagemAtiva].dataset.active = true
}

const changeSlideButton = document.querySelectorAll("[data-change-slide-button]");

changeSlideButton.forEach(button => {
    button.addEventListener("click", () => {
        const controleImagem = document.querySelector(".controle-imagem")
        const imagemAtiva = controleImagem.querySelector("[data-active]")
        let indiceImagemAtiva = Array.from(controleImagem.children).indexOf(imagemAtiva)

        indiceImagemAtiva = button.dataset.changeSlideButton === "next" ? (
            indiceImagemAtiva + 1
        ) : (
            indiceImagemAtiva -1
        )

        if(indiceImagemAtiva >= controleImagem.children.length) {
            indiceImagemAtiva = 0
        }

        if(indiceImagemAtiva < 0) {
            indiceImagemAtiva = controleImagem.children.length - 1
        }

        imagemAtiva.removeAttribute("data-active")
        controleImagem.children[indiceImagemAtiva].dataset.active = true
    })
})