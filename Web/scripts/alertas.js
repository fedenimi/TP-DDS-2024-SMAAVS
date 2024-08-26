var alertas = [
    { topic: "Falla de Temperatura", heladera: heladerasTotales[0], descripcion: "La temperatura es demasiado alta."},
    { topic: "Fraude", heladera: heladerasTotales[1], descripcion: "Actividad sospechosa detectada."},
    { topic: "Falla Técnica", heladera: heladerasTotales[2], descripcion: "Error en el sistema de refrigeración."},
];

// Filtrar las alertas por topic
var fallasTemperatura = alertas.filter(alerta => alerta.topic === "Falla de Temperatura");
var fraudes = alertas.filter(alerta => alerta.topic === "Fraude");
var fallasTecnicas = alertas.filter(alerta => alerta.topic === "Falla Técnica");

// Mostrar alertas en el panel
fallasTemperatura.forEach(alerta => mostrarAlerta(alerta));
fraudes.forEach(alerta => mostrarAlerta(alerta));
fallasTecnicas.forEach(alerta => mostrarAlerta(alerta));

const alertasContainer = document.querySelector('.heladeras-dashboard-scrollable');

if (fallasTemperatura.length > 0) {
    agregarTopic(fallasTemperatura);
    document.querySelector('.grey').classList.remove('open');
}

if (fraudes.length > 0) {
    agregarTopic(fraudes);
    document.querySelector('.grey').classList.remove('open');
}

if (fallasTecnicas.length > 0) {
    agregarTopic(fallasTecnicas);
    document.querySelector('.grey').classList.remove('open');
}

function agregarTopic(alertas) {
    const alertaTitleDiv = document.createElement('div');
    alertaTitleDiv.classList.add('heladeras-fallas-dashboard');
    const alertaTitle = document.createElement('div');
    alertaTitle.classList.add('alertas-title');
    const heladera = document.createElement('h2');
    const topic = document.createElement('h2');
    topic.innerHTML = alertas[0].topic;
    alertaTitle.appendChild(heladera);
    alertaTitle.appendChild(topic);
    alertaTitleDiv.appendChild(alertaTitle);
    alertasContainer.appendChild(alertaTitleDiv);

    alertas.forEach(alerta => {
        agregarAlerta(alerta, alertaTitleDiv);
    })
}

function agregarAlerta(alerta, divPrincipal) {
    const divHeladeraDropdownHeladera = document.createElement('div');
    
    const divHeladera = document.createElement('div');
    divHeladera.classList.add('heladera');
    const pHeladera = document.createElement('p');
    pHeladera.innerHTML = alerta.heladera.nombre;
    const hr = document.createElement('hr');
    const pDescripcion = document.createElement('p');
    pDescripcion.innerHTML = alerta.descripcion;
    divHeladera.appendChild(pHeladera);
    divHeladera.appendChild(hr);
    divHeladera.appendChild(pDescripcion);
    divHeladeraDropdownHeladera.appendChild(divHeladera);
    divPrincipal.appendChild(divHeladeraDropdownHeladera);
    dropdownsHeladera = document.querySelectorAll('.dropdown-heladera');
    heladeraBtns = document.querySelectorAll('.heladera');
    listenerHeladeraBtn(divHeladera);
}

function mostrarAlerta(alerta) {
    const alertaDiv = document.createElement('div');
    const heladeraDiv = document.createElement('div');
    heladeraDiv.classList.add('heladera');
    const heladeraP = document.createElement('p');
    heladeraP.innerHTML = alerta.heladera.nombre;
    const hr = document.createElement('hr');
    const descripcionP = document.createElement('p');
    descripcionP.innerHTML = alerta.descripcion;
    heladeraDiv.appendChild(heladeraP);
    heladeraDiv.appendChild(hr);
    heladeraDiv.appendChild(descripcionP);
    alertaDiv.appendChild(heladeraDiv);

    const dropdownHeladera = document.createElement('div');
    dropdownHeladera.classList.add('dropdown-heladera');
    const li1 = document.createElement('li');
    const a1 = document.createElement('a');
    a1.href = '';
    const i1 = document.createElement('i');
    i1.classList.add('fa-solid', 'fa-pencil');
    const p1 = document.createElement('p');
    p1.innerHTML = 'Modificar';
    a1.appendChild(i1);
    a1.appendChild(p1);
    li1.appendChild(a1);
    const li2 = document.createElement('li');
    const a2 = document.createElement('a');
    a2.href = '';
    const i2 = document.createElement('i');
    i2.classList.add('fa-solid', 'fa-trash');
    const p2 = document.createElement('p');
    p2.innerHTML = 'Eliminar';
    a2.appendChild(i2);
    a2.appendChild(p2);
    li2.appendChild(a2);
    dropdownHeladera.appendChild(li1);
    dropdownHeladera.appendChild(li2);
    alertaDiv.appendChild(dropdownHeladera);
}

// Manejando el mapa
function initMap() {
    const map = new google.maps.Map(document.getElementById('map'), {
        zoom: 12,
        center: { lat: -34.6037, lng: -58.3816 } // Coordenadas de Buenos Aires
    });

    alertas.forEach(alerta => {
        const marker = new google.maps.Marker({
            position: alerta.heladera.location,
            map: map,
            title: alerta.heladera.nombre
        });

        const infoWindowContent = `
            <div>
                <h2>${alerta.heladera.nombre}</h2>
                <p>${alerta.direccion}</p>
                <p>${alerta.topic}: ${alerta.descripcion}</p>
            </div>
        `;

        const infoWindow = new google.maps.InfoWindow({
            content: infoWindowContent
        });

        marker.addListener('click', () => {
            infoWindow.open(map, marker);
            document.getElementById('info-heladera').textContent = alerta.heladera.nombre;
            document.getElementById('info-direccion').textContent = alerta.direccion;
            document.getElementById('info-alerta').textContent = `${alerta.topic}: ${alerta.descripcion}`;
        });
    });
}
