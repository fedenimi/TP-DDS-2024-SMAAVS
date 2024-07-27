
var heladeras = [
    { latitud: -34.61315, longitud: -58.37723, nombre: "Heladera A", direccion: "Av. Rivadavia 7200" },
    { latitud: -34.590075, longitud: -58.386510, nombre: "Heladera B", direccion: "Av. Corrientes 3200" },
    { latitud: -34.579320, longitud: -58.413611, nombre: "Heladera C", direccion: "Av. Córdoba 2000" },
    { latitud: -34.601305, longitud: -58.415235, nombre: "Heladera D", direccion: "Av. Santa Fe 3000" }
];

var blueIcon = L.icon({
    iconUrl: "images/bluePin.png",
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
}

const arrowOpener = document.querySelector('.arrow-opener');
const heladerasDashboard = document.querySelector('.heladeras-dashboard');
let isArrowOpen = true;

if (arrowOpener) {
    arrowOpener.addEventListener('click', () => {
        if (isArrowOpen) {
            arrowOpener.children[0].classList.remove('fa-chevron-left');
            arrowOpener.children[0].classList.add('fa-chevron-right');
            isArrowOpen = false;
        } else {
            arrowOpener.children[0].classList.remove('fa-chevron-right');
            arrowOpener.children[0].classList.add('fa-chevron-left');
            isArrowOpen = true;
        }
        heladerasDashboard.classList.toggle('open');
    });
}
