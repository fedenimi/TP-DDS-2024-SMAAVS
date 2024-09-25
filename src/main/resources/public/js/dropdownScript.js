let selectionBtn = document.querySelector('.dropdown-select-btn'),
    items = document.querySelectorAll('.dropdown-item'),
    itemOnly = document.querySelectorAll('.dropdown-item-only');

if (selectionBtn) {
    selectionBtn.addEventListener('click', () => {
        selectionBtn.classList.toggle('open');
    });


    items.forEach(item => {
        item.addEventListener('click', () => {
            item.classList.toggle('checked');
            let checkedItems = document.querySelectorAll('.checked');
            let btnText = document.querySelector('.dropdown-select-btn p');
            btnText.innerHTML = checkedItems.length === 1 ? '1 seleccionada' : `${checkedItems.length} seleccionadas`;

        });
    });

    itemOnly.forEach(item => {
        addListenerDropdown(item);
    });

    function addListenerDropdown(item) {
        item.addEventListener('click', () => {
            console.log(item.classList);
            if (!item.classList.contains('checked')) {
                itemOnly.forEach(i => {
                    i.classList.remove('checked');
                });
                item.classList.add('checked');
                let btnText = document.querySelector('.dropdown-select-btn p');
                btnText.innerHTML = item.children[1].innerHTML;
            }
            if (item.classList.contains('closable')) {
                selectionBtn.classList.toggle('open');
            }
            if (item.classList.contains('es-vianda')) {
                let vianda = Object.values(viandas).find(vianda => vianda.name === item.children[1].innerHTML);
                let viandaInfo = document.querySelector('.donar-viandas');
                viandaInfo.children[1].children[1].value = vianda.comida;
                viandaInfo.children[2].children[1].value = vianda.fechaCaducidad;
                viandaInfo.children[3].children[1].value = vianda.peso;
                viandaInfo.children[4].children[1].value = vianda.calorias;
                viandaInfo.children[5].children[1].value = vianda.heladera;
            }
            if (item.classList.contains('agregar-vianda')) {
                infoAgregarVianda();
            }
        });
    }
}