
const gridPuntos = document.querySelector('.grid-puntos');
const heladeras = document.querySelectorAll('.heladera');
let boxProductos = document.querySelectorAll('.box-producto');

function cargarCategorias() {
    document.querySelectorAll('#categoria-producto').forEach(categoria => {
        const nombre = categoria.children[0].innerHTML;
        categoriasTotales.push(nombre)
    })
}
cargarCategorias();

heladeras.forEach(heladera => {
    heladera.addEventListener('click', () => {
        if (!heladera.classList.contains('open')) {
            gridPuntos.innerHTML = '';
            iniciarGrid()
        } else {
            const categoria = categoriasTotales.find(c => c === heladera.children[0].innerHTML);
            updateGrid(categoria);
        }
        boxProductos = document.querySelectorAll('.box-producto');
        boxProductos.forEach(producto => {
            productoListener(producto);
        })
    })
})

function updateGrid(categoria) {
    gridPuntos.innerHTML = '';
    categoria.productosDisponibles.forEach(producto => {
        agregarProducto(producto);
    })
}

boxProductos.forEach(producto => {
    productoListener(producto);
})

function productoListener(producto) {
    producto.addEventListener('click', () => {
        console.log(producto);
        const wasPressed = producto.classList.contains('selected');
        boxProductos.forEach(producto => {
            producto.classList.remove('selected');
        })
        if (!wasPressed) {
            producto.classList.toggle('selected');
        }
    })
}