//ABRIR MAPA
if (document.querySelector('#abrir-mapa')){
document.querySelector('#abrir-mapa').addEventListener('click', () => {
    document.querySelector('.map-container').classList.toggle('transparent');
    document.querySelector('.donar-viandas-main').classList.toggle('hidden');
})}

//CERRAR MAPA
if (document.querySelector('#cerrar-mapa')){
document.querySelector('#cerrar-mapa').addEventListener('click', () => {
    document.querySelector('.map-container').classList.toggle('transparent');
    document.querySelector('.donar-viandas-main').classList.toggle('hidden');
})}

var blueIcon = L.icon({
    iconUrl: "../img/bluePin.png",
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

heladerasTotales.forEach(heladera => mostrarPunto(heladera, blueIcon));

function mostrarPunto(heladera, icon) {
    var marker = L.marker([heladera.latitud, heladera.longitud], { icon: icon }).addTo(map);
    marker.on('click', openInfoHeladera(heladera));
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


const modalDonarViandas = document.querySelector('.modal-info-heladera-viandas');

document.addEventListener('click', function (event) {
    if (!event.target.closest('.modal-info-heladera-viandas') &&
        !event.target.classList.contains('leaflet-marker-icon') &&
        !event.target.closest('.heladera') &&
        !event.target.closest('.arrow-opener') &&
        !event.target.closest('.sig-btn') &&
        !event.target.classList.contains('confirmar-btn-susc')
    ){
        console.log("aa");
        modalDonarViandas.classList.remove('open');
        closeBtns();
        closeDropdowns();
        heladeraOpened = "none";
    }
});

function openInfoHeladera(heladera) {
    return function () {
        if (document.querySelector('#form-heladera')) {
            document.querySelector('#form-heladera').value = heladera.nombre;
        }
        if (heladeraOpened === heladera.nombre) {
            modalDonarViandas.classList.remove('open');
            closeDropdowns();
            closeBtns();
            heladeraOpened = "none";
        } else {
            modalDonarViandas.classList.add('open');
            modalDonarViandas.children[0].innerHTML = heladera.nombre;
            modalDonarViandas.children[1].children[0].innerHTML = heladera.direccion;
            if (heladeraBtns.length > 0) {
                heladeraBtns.forEach(btn => {
                    if (btn.children[0].innerHTML === heladera.nombre) {
                        btn.classList.add('open');
                    } else {
                        btn.classList.remove('open');
                    }
                });
            }
            handleDropdown(heladera.nombre)
            heladeraOpened = heladera.nombre;
        }
    }
}

function closeDropdowns() {
    if (dropdownsHeladera.length > 0) {
        dropdownsHeladera.forEach(dropdown => dropdown.classList.remove('open'));
    }
}

function closeBtns() {
    if (heladeraBtns.length > 0) {
        heladeraBtns.forEach(btn => btn.classList.remove('open'));
    }
}