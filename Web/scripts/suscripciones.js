var suscripciones = [
    { topic: "Viandas restantes", heladera: heladerasTotales[0], cantidad: 10 },
    { topic: "Viandas restantes", heladera: heladerasTotales[1], cantidad: 5 },
    { topic: "Viandas restantes", heladera: heladerasTotales[2], cantidad: 3 },
    { topic: "Viandas restantes", heladera: heladerasTotales[3], cantidad: 0 },
    { topic: "Viandas faltantes", heladera: heladerasTotales[0], cantidad: 10 },
    { topic: "Viandas faltantes", heladera: heladerasTotales[1], cantidad: 5 },
    { topic: "Viandas faltantes", heladera: heladerasTotales[2], cantidad: 3 },
    { topic: "Viandas faltantes", heladera: heladerasTotales[3], cantidad: 0 },
]

var viandasRestantes = suscripciones.filter(suscripcion => suscripcion.topic === "Viandas restantes");
var viandasFaltantes = suscripciones.filter(suscripcion => suscripcion.topic === "Viandas faltantes");
var desperfectos = suscripciones.filter(suscripcion => suscripcion.topic === "Desperfecto");

viandasRestantes.forEach(suscripcion => mostrarSuscripcion(suscripcion));
viandasFaltantes.forEach(suscripcion => mostrarSuscripcion(suscripcion));
desperfectos.forEach(suscripcion => mostrarSuscripcion(suscripcion));

const suscripcionesContainer = document.querySelector('.heladeras-dashboard-scrollable');

if (viandasRestantes.length > 0) {
    agregarTopic(viandasRestantes);
    document.querySelector('.grey').classList.remove('open');
}

if (viandasFaltantes.length > 0) {
    agregarTopic(viandasFaltantes);
    document.querySelector('.grey').classList.remove('open');
}

if (desperfectos.length > 0) {
    agregarTopic(desperfectos);
    document.querySelector('.grey').classList.remove('open');
}

function agregarTopic(title) {
    const suscripcionTitleDiv = document.createElement('div');
    suscripcionTitleDiv.classList.add('heladeras-fallas-dashboard');
    const suscripcionTitle = document.createElement('div');
    suscripcionTitle.classList.add('suscripciones-title');
    const heladera = document.createElement('h2');
    heladera.innerHTML = "Heladera";
    const topic = document.createElement('h2');
    topic.innerHTML = title[0].topic;
    suscripcionTitle.appendChild(heladera);
    suscripcionTitle.appendChild(topic);
    suscripcionTitleDiv.appendChild(suscripcionTitle);
    suscripcionesContainer.appendChild(suscripcionTitleDiv);

    title.forEach(suscripcion => {
        agregarSuscripcion(suscripcion, suscripcionTitleDiv);
    })
}

function agregarSuscripcion(suscripcion, divPrincipal) {
    const divHeladeraDropdownHeladera = document.createElement('div');
    
    const divHeladera = document.createElement('div');
    divHeladera.classList.add('heladera');
    const pHeladera = document.createElement('p');
    pHeladera.innerHTML = suscripcion.heladera.nombre;
    const hr = document.createElement('hr');
    const pViandas = document.createElement('p');
    pViandas.innerHTML = `Faltan ${suscripcion.cantidad} viandas`;
    divHeladera.appendChild(pHeladera);
    divHeladera.appendChild(hr);
    divHeladera.appendChild(pViandas);
    divHeladeraDropdownHeladera.appendChild(divHeladera);

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
    divHeladeraDropdownHeladera.appendChild(dropdownHeladera);

    divPrincipal.appendChild(divHeladeraDropdownHeladera);
    dropdownsHeladera = document.querySelectorAll('.dropdown-heladera');
    heladeraBtns = document.querySelectorAll('.heladera');
    listenerHeladeraBtn(divHeladera);
}

function mostrarSuscripcion(suscripcion) {
    const suscripcionDiv = document.createElement('div');
    const heladeraDiv = document.createElement('div');
    heladeraDiv.classList.add('heladera');
    const heladeraP = document.createElement('p');
    heladeraP.innerHTML = suscripcion.heladera;
    const hr = document.createElement('hr');
    const viandasP = document.createElement('p');
    viandasP.innerHTML = `Faltan ${suscripcion.cantidad} viandas`;
    heladeraDiv.appendChild(heladeraP);
    heladeraDiv.appendChild(hr);
    heladeraDiv.appendChild(viandasP);
    suscripcionDiv.appendChild(heladeraDiv);

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
    suscripcionDiv.appendChild(dropdownHeladera);


}

const btnTopic = document.querySelectorAll('.btn-topic');

if (btnTopic.length > 0) {
    btnTopic.forEach(btn => {
        btn.addEventListener('click', () => {
            btn.classList.toggle('pressed');
        });
    });
}