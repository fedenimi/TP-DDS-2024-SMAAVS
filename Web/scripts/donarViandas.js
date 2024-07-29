let viandas = {
    vianda1: {
        name: 'Vianda 1',
        comida: 'Milanesa con puré',
        fechaCaducidad: '2021-10-10',
        peso: '500g',
        calorias: '500kcal',
        heladera: 'Heladera 1',
    },
    vianda2: {
        name: 'Vianda 2',
        comida: 'Pollo con arroz',
        fechaCaducidad: '2021-10-10',
        peso: '500g',
        calorias: '500kcal',
        heladera: 'Heladera 1',
    },
    vianda3: {
        name: 'Vianda 3',
        comida: 'Fideos con tuco',
        fechaCaducidad: '2021-10-10',
        peso: '500g',
        calorias: '500kcal',
        heladera: 'Heladera 1',
    },
}

const dropdownItemList = document.getElementsByClassName('dropdown-item-list');
const eliminarViandaBtn = document.querySelector('.eliminar-vianda');
const guardarViadnaBtn = document.querySelector('.guardar-vianda');

Object.values(viandas).forEach(vianda => {
    agregarViandaAlDropdown(vianda);
});

eliminarViandaBtn.addEventListener('click', () => {
        itemOnly.forEach(item => {
            if (item.classList.contains('checked') && !item.classList.contains('agregar-vianda')) {
                eliminarVianda(item);
                item.remove()
                viandas = Object.fromEntries(Object.entries(viandas).filter(([key, value]) => value.name !== item.children[1].innerHTML));
            }
        });
        const btnAgregar = document.getElementsByClassName('agregar-vianda')[0];
        btnAgregar.classList.toggle('checked');
        infoAgregarVianda();
});

function eliminarVianda(item) {
    item.classList.remove('checked');
    let btnText = document.querySelector('.dropdown-select-btn p');
    btnText.innerHTML = 'Agregar vianda';
}

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
    infoAgregarVianda();
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