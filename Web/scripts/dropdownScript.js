//HEADER DROPDOWN
const toggleBtn = document.querySelector('.toggle-btn');
const toggleBtnIcon = document.querySelector('.toggle-btn i');
const dropdownMenu = document.querySelector('.dropdown-menu');
if (toggleBtn) {
    toggleBtn.onclick = function () {
        dropdownMenu.classList.toggle('open');
        const isOpen = dropdownMenu.classList.contains('open');

        toggleBtnIcon.classList = isOpen ? 'fa-solid fa-xmark' : 'fa-solid fa-bars';
    }
}


//SELECTION DROPDOWN
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



    function infoAgregarVianda() {
        let viandaInfo = document.querySelector('.donar-viandas');
        viandaInfo.children[1].children[1].value = '';
        viandaInfo.children[2].children[1].value = '';
        viandaInfo.children[3].children[1].value = '';
        viandaInfo.children[4].children[1].value = '';
        viandaInfo.children[5].children[1].value = '';
    }

    function formatDate(date) {
        let dateArray = date.split('/');
        return `${dateArray[2]}-${dateArray[1]}-${dateArray[0]}`;
    }

    const divForm = document.querySelector('.form-element-dropdown');
    if (divForm.classList.contains('form-categoria')) {
        categoriasTotales.forEach(categoria => {
            console.log(divForm.classList);
            let htmlUl = 
            `<li class="dropdown-item-only closable">
                <p></p>
                <p>${categoria.nombre}</p>
            </li>`;
            const dropdownItemList = document.querySelector('.dropdown-item-list');
            dropdownItemList.innerHTML += htmlUl;
        });
        itemOnly = document.querySelectorAll('.dropdown-item-only');
        itemOnly.forEach(item => {
            addListenerDropdown(item);
        });
    }
}



//HELADERA DROPDOWN
let heladeraBtns = document.querySelectorAll('.heladera');
let dropdownsHeladera = document.querySelectorAll('.dropdown-heladera');
let heladeraOpened = "none";

if (heladeraBtns.length > 0) {
    heladeraBtns.forEach((btn, index) => {
        listenerHeladeraBtn(btn);
    });
}

function listenerHeladeraBtn(heladera) {
    let index = Array.from(heladeraBtns).indexOf(heladera);
    heladera.addEventListener('click', () => {
        heladera.classList.toggle('open');
        heladeraBtns.forEach((btn, i) => {
            if (i !== index) {
                btn.classList.remove('open');
            }
        });
        const modalDonarViandas = document.querySelector('.modal-info-heladera-viandas');
        if (modalDonarViandas) {
            const heladeraObject = Object.values(heladerasTotales).find(h => h.nombre === heladera.children[0].innerHTML);
            if (heladeraOpened === heladeraObject.nombre) {
                modalDonarViandas.classList.remove('open');
                closeBtns();
                closeDropdowns();
                console.log("asdas");
                heladeraOpened = "none";
            } else {
                openInfoHeladera(heladeraObject)();
            }
        }
    });
}

function handleDropdown(heladera) {
    if (dropdownsHeladera.length > 0) {
        dropdownsHeladera.forEach((dropdown, i) => {
            if (dropdownsHeladera[i].previousElementSibling.children[0].innerHTML === heladera) {
                dropdownsHeladera[i].classList.toggle('open');
            } else {
                dropdownsHeladera[i].classList.remove('open');
            }
        })
    }
}

