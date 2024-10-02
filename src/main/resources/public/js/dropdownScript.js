let selectionBtn = document.querySelector('.dropdown-select-btn'),
    items = document.querySelectorAll('.dropdown-item'),
    itemOnly = document.querySelectorAll('.dropdown-item-only');

function updateTextSeleccionadas() {
    let checkedItems = document.querySelectorAll('.checked');
    let btnText = document.querySelector('.dropdown-select-btn p');
    btnText.innerHTML = checkedItems.length === 1 ? '1 seleccionada' : `${checkedItems.length} seleccionadas`;
}

if (selectionBtn) {
    selectionBtn.addEventListener('click', () => {
        selectionBtn.classList.toggle('open');
    });


    items.forEach(item => {
        item.addEventListener('click', () => {
            item.classList.toggle('checked');
            updateTextSeleccionadas()
        });
    });

    itemOnly.forEach(item => {
        addListenerDropdown(item);
    });

    function addListenerDropdown(item) {
        item.addEventListener('click', () => {
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
                let nro_vianda = item.children[1].innerHTML.split(' ')[1] - 1;
                document.querySelector(`input[name="vianda[${nro_vianda}][comida]"]`).classList.remove('hidden');
                document.querySelector(`input[name="vianda[${nro_vianda}][fecha_caducidad]"]`).classList.remove('hidden');
                document.querySelector(`input[name="vianda[${nro_vianda}][peso]"]`).classList.remove('hidden');
                document.querySelector(`input[name="vianda[${nro_vianda}][calorias]"]`).classList.remove('hidden');
                for (let i = 0; i <= viandaCount; i++) {
                    if (i !== nro_vianda) {
                        console.log(i);
                        document.querySelector(`input[name="vianda[${i}][comida]"]`).classList.add('hidden');
                        document.querySelector(`input[name="vianda[${i}][fecha_caducidad]"]`).classList.add('hidden');
                        document.querySelector(`input[name="vianda[${i}][peso]"]`).classList.add('hidden');
                        document.querySelector(`input[name="vianda[${i}][calorias]"]`).classList.add('hidden');
                    }
                }
                let btnText = document.querySelector('.dropdown-select-btn p');
                btnText.innerHTML = item.children[1].innerHTML;
            }
            if (item.classList.contains('agregar-vianda')) {
                agregarVianda()
            }
        });
    }

    function agregarVianda() {
        document.querySelector(`input[name="vianda[${viandaCount}][comida]"]`).classList.remove('hidden');
        document.querySelector(`input[name="vianda[${viandaCount}][fecha_caducidad]"]`).classList.remove('hidden');
        document.querySelector(`input[name="vianda[${viandaCount}][peso]"]`).classList.remove('hidden');
        document.querySelector(`input[name="vianda[${viandaCount}][calorias]"]`).classList.remove('hidden');
        for (let i = 0; i < viandaCount; i++) {
            console.log(i);
            document.querySelector(`input[name="vianda[${i}][comida]"]`).classList.add('hidden');
            document.querySelector(`input[name="vianda[${i}][fecha_caducidad]"]`).classList.add('hidden');
            document.querySelector(`input[name="vianda[${i}][peso]"]`).classList.add('hidden');
            document.querySelector(`input[name="vianda[${i}][calorias]"]`).classList.add('hidden');
        }
    }
}

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