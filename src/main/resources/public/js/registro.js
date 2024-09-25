
document.querySelectorAll('.btn-title').forEach(btn => {
    btn.addEventListener('click', () => {
        cambiarTipoColab(btn);
    });
})

function cambiarTipoColab(btn) {
    document.querySelectorAll('.btn-title').forEach(btn => {
        btn.classList.remove('pressed-btn');
    });
    btn.classList.add('pressed-btn');
}

Handlebars.registerHelper('funcionPrueba', function (valor) {
    console.log(valor)
    return valor == 1;
});