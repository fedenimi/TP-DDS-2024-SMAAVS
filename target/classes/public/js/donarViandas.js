let viandas = {}

const dropdownItemList = document.getElementsByClassName('dropdown-item-list');
const guardarViadnaBtn = document.querySelector('.guardar-vianda');

Object.values(viandas).forEach(vianda => {
    agregarViandaAlDropdown(vianda);
});


guardarViadnaBtn.addEventListener('click', () => {
    itemOnly.forEach(item => {
        if (item.classList.contains('checked') && item.classList.contains('agregar-vianda')) {
            guardarVianda(item);
        }
    });
});

function guardarVianda(vianda) {
    let viandaInfo = document.querySelector('.donar-viandas');
    let newVianda = {
        name: 'Vianda ' + (Object.keys(viandas).length + 1),
        comida: viandaInfo.children[1].children[1].value,
        fechaCaducidad: viandaInfo.children[2].children[1].value,
        peso: viandaInfo.children[3].children[1].value,
        calorias: viandaInfo.children[4].children[1].value,
        heladera: viandaInfo.children[5].children[1].value,
    }
    viandas = {
        ...viandas,
        [newVianda.name]: newVianda,
    }
    agregarViandaAlDropdown(newVianda);
    console.log('Guardando vianda');
    agregarVianda();
}

function agregarViandaAlDropdown(vianda) {
    const item = document.createElement('li');
    item.classList.add('dropdown-item-only');
    item.classList.add('closable');
    item.classList.add('es-vianda');
    item.innerHTML = `
        <p></p>
        <p>${vianda.name}</p>
    `;
    dropdownItemList[0].prepend(item);
    addListenerDropdown(item);
    itemOnly = document.querySelectorAll('.dropdown-item-only');
}