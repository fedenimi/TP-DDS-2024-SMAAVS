

const suscripcionesContainer = document.querySelector('.heladeras-dashboard-scrollable');
const gridPuntos = document.querySelector('.grid-puntos');

if (categoriasTotales.length > 0) {
    agregarCategorias();
    iniciarGrid()
}


function agregarCategorias() {
    const suscripcionTitleDiv = document.createElement('div');
    suscripcionTitleDiv.classList.add('heladeras-fallas-dashboard');
    const suscripcionTitle = document.createElement('div');
    suscripcionTitle.classList.add('suscripciones-title');
    const heladera = document.createElement('h2');
    heladera.innerHTML = "Categoría";
    const topic = document.createElement('h2');
    topic.innerHTML = "Productos disponibles";
    suscripcionTitle.appendChild(heladera);
    suscripcionTitle.appendChild(topic);
    suscripcionTitleDiv.appendChild(suscripcionTitle);
    suscripcionesContainer.appendChild(suscripcionTitleDiv);
    categoriasTotales.forEach(categoria => {
        agregarCategoria(categoria, suscripcionTitleDiv);
    })
}

function agregarCategoria(categoria, divPrincipal) {
    const divHeladeraDropdownHeladera = document.createElement('div');

    const divHeladera = document.createElement('div');
    divHeladera.classList.add('heladera');
    const pHeladera = document.createElement('p');
    pHeladera.innerHTML = categoria.nombre;
    const hr = document.createElement('hr');
    const pViandas = document.createElement('p');
    pViandas.innerHTML = categoria.productosDisponibles.length + (categoria.productosDisponibles.length > 1 ? ' productos' : ' producto');

    divHeladera.appendChild(pHeladera);
    divHeladera.appendChild(hr);
    divHeladera.appendChild(pViandas);
    divHeladeraDropdownHeladera.appendChild(divHeladera);

    divPrincipal.appendChild(divHeladeraDropdownHeladera);
    heladeraBtns = document.querySelectorAll('.heladera');
    listenerHeladeraBtn(divHeladera);
}

function iniciarGrid() {
    categoriasTotales.forEach(categoria => {
        categoria.productosDisponibles.forEach(producto => {
            agregarProducto(producto);
        })
    })
}

function agregarProducto(producto) {
    const divProducto = document.createElement('div');
    divProducto.classList.add('box-producto');
    const img = document.createElement('img');
    img.src = producto.foto;
    const divProductoInfo = document.createElement('div');
    divProductoInfo.classList.add('producto-info');
    const h3 = document.createElement('h3');
    h3.innerHTML = producto.nombre;
    const p = document.createElement('p');
    p.innerHTML = producto.precio + ' puntos';

    divProductoInfo.appendChild(h3);
    divProductoInfo.appendChild(p);
    divProducto.appendChild(img);
    divProducto.appendChild(divProductoInfo);
    gridPuntos.appendChild(divProducto);
}

const heladeras = document.querySelectorAll('.heladera');
let boxProductos = document.querySelectorAll('.box-producto');

heladeras.forEach(heladera => {
    heladera.addEventListener('click', () => {
        if (!heladera.classList.contains('open')) {
            gridPuntos.innerHTML = '';
            iniciarGrid()
        } else {
            const categoria = categoriasTotales.find(c => c.nombre === heladera.children[0].innerHTML);
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