
var heladeras = [
    { latitud: -34.61315, longitud: -58.37723, nombre: "Heladera A", direccion: "Av. Rivadavia 7200" },
    { latitud: -34.590075, longitud: -58.386510, nombre: "Heladera B", direccion: "Av. Corrientes 3200" },
    { latitud: -34.579320, longitud: -58.413611, nombre: "Heladera C", direccion: "Av. Córdoba 2000" },
    { latitud: -34.601305, longitud: -58.415235, nombre: "Heladera D", direccion: "Av. Santa Fe 3000" }
];
const modalOpenerBtn = document.querySelector('.puntos-recom');
const modalRecomendacion = document.querySelector('.modal-recomendacion');
const enviar = document.querySelector('.enviar');
let isOpen = false;
var blueIcon = L.icon({
    iconUrl: "images/bluePin.png",
    shadowUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png',
    iconSize: [30, 40],
    shadowSize: [50, 60],
    iconAnchor: [15, 40],
    shadowAnchor: [15, 60],
    popupAnchor: [0, -45]
});
var redIcon = L.icon({
    iconUrl: "images/redPin.png",
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
    var marker = L.marker([heladera.latitud, heladera.longitud], { icon: icon }).addTo(map);
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
            <button class="enviar">
                Enviar
            </button>
        </form>
        `;
        modalRecomendacion.classList.add('open');
        isOpen = true;
    }
});

enviar.addEventListener('click', () => {
    modalRecomendacion.classList.remove('open');
    isOpen = false;
    showRecommendedPoints();
});

function showRecommendedPoints() {
    let heladera = { latitud: -34.583808, longitud: -58.452976, nombre: "Heladera E", direccion: "Av. Cabildo 2000" };
    heladeras.push(heladera);
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
