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

let holdTimeout;
map.on('mousedown', function(e) {
    holdTimeout = setTimeout(() => {
        obtenerDireccion(e.latlng.lat, e.latlng.lng).then(direccion => {
            console.log(direccion);
            var heladera = {latitud: e.latlng.lat, longitud: e.latlng.lng, nombre: "", direccion: direccion};
            mostrarPunto(heladera, redIcon);
        });
    }, 2000);
});
map.on('mouseup', function() {
    clearTimeout(holdTimeout); // Cancela si sueltan antes de los 2 segundos
});

function obtenerDireccion(lat, lng) {
    return fetch(`https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${lat}&lon=${lng}`)
        .then(response => response.json())
        .then(data => {
            var calle = data.address.road == undefined ? "" : data.address.road;
            var numero = data.address.house_number == undefined ? "" : data.address.house_number;
            return `${calle} ${numero}`;
        })
        .catch(error => {
            console.error('Error al obtener la dirección:', error);
            return "";
        });
}

function mostrarPunto(heladera, icon) {
    var marker = L.marker([heladera.latitud, heladera.longitud], {icon: icon}).addTo(map);
    var popupContent =
        `Latitud: ${heladera.latitud} <br>
        Longitud: ${heladera.longitud}`
    
    marker.bindPopup(popupContent);

    if (icon === blueIcon) marker.on('click', showHeladera);
    if (icon === redIcon) marker.on('click', function(e) {
        showHeladeraForConfirmation(heladera)
    });
    marker.on('mouseover', function (e) {
        this.openPopup();
    })
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

function showHeladeraForConfirmation(heladera) {
    console.log(heladera);
    const modalAgregarHeladera = document.querySelector('.modal-agregar-heladera');
    modalAgregarHeladera.children[0].children[0].children[1].children[1].value = heladera.direccion;
    modalAgregarHeladera.children[0].children[0].children[2].children[1].value = heladera.latitud;
    modalAgregarHeladera.children[0].children[1].children[0].children[1].value = heladera.longitud;
    modalAgregarHeladera.classList.toggle('open');
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
    console.log('ShowHeladera');
    modalRecomendacion.classList.add('open');
}

function getDireccionBy(heladera) {
    return heladeras.find(h => h.nombre === heladera).direccion;
}
