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
const selectionBtn = document.querySelector('.dropdown-select-btn'),
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
        item.addEventListener('click', () => {
            if (!item.classList.contains('checked')) {
                itemOnly.forEach(i => {
                    i.classList.remove('checked');
                });
                item.classList.add('checked');
                let btnText = document.querySelector('.dropdown-select-btn p');
                btnText.innerHTML = item.children[1].innerHTML;
            } else {
                item.classList.remove('checked');
                let btnText = document.querySelector('.dropdown-select-btn p');
                btnText.innerHTML = 'Seleccionar';
            }
            
        });
    });
}



//HELADERA DROPDOWN
const heladeraBtns = document.querySelectorAll('.heladera');
const dropdownsHeladera = document.querySelectorAll('.dropdown-heladera');

if (heladeraBtns.length > 0) {
    heladeraBtns.forEach((btn, index) => {
        btn.addEventListener('click', () => {
            heladeraBtns[index].classList.toggle('open');
            heladeraBtns.forEach((btn, i) => {
                if (i !== index) {
                    btn.classList.remove('open');
                }
            });
            dropdownsHeladera[index].classList.toggle('open');
            dropdownsHeladera.forEach((dropdown, i) => {
                if (i !== index) {
                    dropdown.classList.remove('open');
                }
            });
        });
    });
}