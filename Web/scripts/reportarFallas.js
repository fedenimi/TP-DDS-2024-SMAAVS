

const suscripcionesContainer = document.querySelector('.heladeras-dashboard-scrollable');

var estado = "estado"

if (heladerasTotales.length > 0) {
    agregarTopic(estado);
}


function agregarTopic(title) {
    const suscripcionTitleDiv = document.createElement('div');
    suscripcionTitleDiv.classList.add('heladeras-fallas-dashboard');
    const suscripcionTitle = document.createElement('div');
    suscripcionTitle.classList.add('suscripciones-title');
    const heladera = document.createElement('h2');
    heladera.innerHTML = "Heladera";
    const topic = document.createElement('h2');
    topic.innerHTML = title;
    suscripcionTitle.appendChild(heladera);
    suscripcionTitle.appendChild(topic);
    suscripcionTitleDiv.appendChild(suscripcionTitle);
    suscripcionesContainer.appendChild(suscripcionTitleDiv);
    heladerasTotales.forEach(heladera => {
        agregarHeladera(heladera, suscripcionTitleDiv);
    })
}

function agregarHeladera(heladera, divPrincipal) {
    const divHeladeraDropdownHeladera = document.createElement('div');
    
    const divHeladera = document.createElement('div');
    divHeladera.classList.add('heladera');
    const pHeladera = document.createElement('p');
    pHeladera.innerHTML = heladera.nombre;
    const hr = document.createElement('hr');
    const pViandas = document.createElement('p');
    pViandas.innerHTML = heladera.estado;
    divHeladera.appendChild(pHeladera);
    divHeladera.appendChild(hr);
    divHeladera.appendChild(pViandas);
    divHeladeraDropdownHeladera.appendChild(divHeladera);

    divPrincipal.appendChild(divHeladeraDropdownHeladera);
    heladeraBtns = document.querySelectorAll('.heladera');
    listenerHeladeraBtn(divHeladera);
}

const btnTopic = document.querySelectorAll('.btn-topic');

if (btnTopic.length > 0) {
    btnTopic.forEach(btn => {
        btn.addEventListener('click', () => {
            btn.classList.toggle('pressed');
        });
    });
}