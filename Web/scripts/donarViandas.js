let viandas = {
    vianda1: {
        name: 'Vianda 1',
        comida: 'Milanesa con puré',
        fechaCaducidad: '10/10/2021',
        peso: '500g',
        calorias: '500kcal',
        heladera: 'Heladera 1',
    },
    vianda2: {
        name: 'Vianda 2',
        comida: 'Pollo con arroz',
        fechaCaducidad: '10/10/2021',
        peso: '500g',
        calorias: '500kcal',
        heladera: 'Heladera 1',
    },
    vianda3: {
        name: 'Vianda 3',
        comida: 'Fideos con tuco',
        fechaCaducidad: '10/10/2021',
        peso: '500g',
        calorias: '500kcal',
        heladera: 'Heladera 1',
    },
}

const dropdownItemList = document.getElementsByClassName('dropdown-item-list');


Object.values(viandas).forEach(vianda => {
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
});
