var heladeras = [];

function definirHeladeras() {
    document.querySelectorAll('#heladera-map').forEach((heladera, i) => {
        heladeras[i] = {
            latitud: heladera.children[3].innerText,
            longitud: heladera.children[4].innerText,
            nombre: heladera.children[0].innerText,
            direccion: heladera.children[5].innerText
        }
    })
    console.log(heladeras)
}

definirHeladeras()

const modalOpenerBtn = document.querySelector('.puntos-recom');
const modalRecomendacion = document.querySelector('.modal-recomendacion');
const enviar = document.querySelector('.enviar');
let isOpen = false;
var blueIcon = L.icon({
    iconUrl: "/img/bluePin.png",
    shadowUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png',
    iconSize: [30, 40],
    shadowSize: [50, 60],
    iconAnchor: [15, 40],
    shadowAnchor: [15, 60],
    popupAnchor: [0, -45]
});
var redIcon = L.icon({
    iconUrl: "/img/redPin.png",
    shadowUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png',
    iconSize: [30, 40],
    shadowSize: [50, 60],
    iconAnchor: [15, 40],
    shadowAnchor: [15, 60],
    popupAnchor: [0, -45]
});
var mapContainer = document.getElementById('map');
var map = L.map(mapContainer).setView([-34.6037, -58.3816], 13);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);

heladeras.forEach(heladera => mostrarPunto(heladera, blueIcon));

function mostrarPunto(heladera, icon) {
    var marker = L.marker([heladera.latitud, heladera.longitud], {icon: icon}).addTo(map);
    marker.bindPopup(heladera.nombre);
    if (icon === blueIcon) marker.on('click', showHeladera);
    if (icon === redIcon) marker.on('click', showHeladeraForConfirmation);
}

modalOpenerBtn.addEventListener('click', () => {
    if (isOpen) {
        modalRecomendacion.classList.remove('open');
        isOpen = false;
        showRecommendedPoints();
    } else {
        modalRecomendacion.innerHTML = `
        <form class="form-modal-mapa">
            <div class="form-element-mapa">
                <label for="form-direccion">Dirección</label>
                <input type="text" name="direccion" required>
            </div>
            <div class="form-element-mapa">
                <label for="form-radio">Radio máximo en metros</label>
                <input type="text" name="radio" required>
            </div>
            <a class="enviar" id="mostrar-puntos">
                Enviar
            </a>
        </form>
        `;
        modalRecomendacion.classList.add('open');
        isOpen = true;
        document.querySelector("#mostrar-puntos").addEventListener('click', () => {
            modalRecomendacion.classList.remove('open');
            isOpen = false;
            showRecommendedPoints();

        })
    }
});

enviar.addEventListener('click', () => {
    modalRecomendacion.classList.remove('open');
    isOpen = false;
    showRecommendedPoints();
});

function showRecommendedPoints() {
    let heladera = {latitud: -34.583808, longitud: -58.452976, nombre: "Heladera E", direccion: "Av. Cabildo 2000"};
    heladeras.push(heladera);
    console.log(heladeras);
    mostrarPunto(heladera, redIcon);
}

function showHeladeraForConfirmation(e) {
    console.log(this);
    modalRecomendacion.innerHTML = `
        <form class="form-modal-mapa">
            <div class="form-element-mapa">
                <h2>${this._popup._content}</h2>
            </div>
            <div class="form-element-mapa">
                <h2>Dirección ${getDireccionBy(this._popup._content)}</h2>
            <button class="enviar">
                Confirmar
            </button>
        </form>
    `;
    modalRecomendacion.classList.add('open');
}

function showHeladera(e) {
    modalRecomendacion.innerHTML = `
        <form class="form-modal-mapa">
            <div class="form-element-mapa">
                <h2>${this._popup._content}</h2>
            </div>
            <div class="form-element-mapa">
                <h2>Dirección ${getDireccionBy(this._popup._content)}</h2>
            <button class="enviar">
                Cerrar
            </button>
        </form>
    `;
    modalRecomendacion.classList.add('open');
}

function getDireccionBy(heladera) {
    return heladeras.find(h => h.nombre === heladera).direccion;
}
