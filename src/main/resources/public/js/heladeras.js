var heladerasTotales = [];

function definirHeladeras() {
    document.querySelectorAll('#heladeras-en-dashboard').forEach((heladera, i) => {
        heladerasTotales[i] = {
            latitud: heladera.children[3].innerText,
            longitud: heladera.children[4].innerText,
            nombre: heladera.children[0].innerText,
            direccion: heladera.children[5].innerText
        }
    })
    console.log(heladerasTotales)
}