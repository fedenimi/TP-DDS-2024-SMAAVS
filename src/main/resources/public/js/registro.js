//CAMBIAR ENTRE JURÍDICA Y HUMANA
document.querySelectorAll('.btn-title').forEach(btn => {
    btn.addEventListener('click', () => {
        cambiarTipoColab(btn);
    });
})

function cambiarTipoColab(btn) {
    document.querySelectorAll('.btn-title').forEach(btn => {
        btn.classList.remove('pressed-btn');
    });
    btn.classList.add('pressed-btn');
    cambiarPrimerosTextosA(btn.innerHTML[0])
    sacarDocumentoYTipo()
}

function cambiarPrimerosTextosA(tipo) {
    switch (tipo) {
        case 'J':
            var nom = document.querySelector('#elemento-nombre')
            nom.children[0].innerHTML = 'Razón social';
            nom.children[1].name = 'social';
            var ape = document.querySelector('#elemento-apellido')
            ape.children[0].innerHTML = 'Tipo jurídico';
            ape.children[1].name = 'tipo';
            var fec_nac = document.querySelector('#elemento-fecha-nac')
            fec_nac.children[0].innerHTML = 'Rubro';
            fec_nac.children[1].name = 'rubro';
            var dropdown_items = document.querySelectorAll('.dropdown-item')
            dropdown_items[1].children[1].innerHTML = 'Administrar heladeras';
            dropdown_items[1].id = 'adm-hel';
            dropdown_items[2].classList.add('hidden');
            dropdown_items[2].id = '';
            break
        case 'H':
            var nom = document.querySelector('#elemento-nombre')
            nom.children[0].innerHTML = 'Nombre';
            nom.children[1].name = 'nombre';
            var ape = document.querySelector('#elemento-apellido')
            ape.children[0].innerHTML = 'Apellido';
            ape.children[1].name = 'apellido';
            var fec_nac = document.querySelector('#elemento-fecha-nac')
            fec_nac.children[0].innerHTML = 'Fecha de nacimiento';
            fec_nac.children[1].name = 'nacimiento';
            var dropdown_items = document.querySelectorAll('.dropdown-item')
            dropdown_items[1].children[1].innerHTML = 'Donar viandas';
            dropdown_items[1].id = 'don-via';
            dropdown_items[2].classList.remove('hidden');
            dropdown_items[2].id = 'dist-via';
            break
    }
}

function sacarDocumentoYTipo() {
    document.querySelector('.tipo-doc-input').classList.toggle('hidden');
    document.querySelector('.nro-doc-input').classList.toggle('hidden');
}

//CAMBIAR DE PÁGINA DE FORM
document.querySelector('#siguiente-registro-btn').addEventListener('click', () => {
    document.querySelector('#registro-persona').innerHTML =
       "Hola " + document.querySelector('#elemento-nombre').children[1].value +"!<br>Ya falta poco!"
    ;

    document.querySelectorAll('.registro-main')[0].classList.toggle('hidden');
    document.querySelectorAll('.registro2-main')[0].classList.toggle('hidden');
})

document.querySelector('#atras-registro-btn').addEventListener('click', () => {
    document.querySelectorAll('.registro-main')[0].classList.toggle('hidden');
    document.querySelectorAll('.registro2-main')[0].classList.toggle('hidden');
})

//SUBMIT FORM
document.querySelector('#submit-btn').addEventListener('click', () => {
    document.querySelector('#form-usuario').value = document.querySelector('#usuario-input').value;
    document.querySelector('#form-contrasenia').value = document.querySelector('#contrasenia-input').value;
    document.querySelector('#form-don-via').value = estaPresionado("don-via") ? "true" : null;
    document.querySelector('#form-adm-hel').value = estaPresionado("adm-hel") ? "true" : null;
    document.querySelector('#form-don-din').value = estaPresionado("don-din") ? "true" : null;
    document.querySelector('#form-dist-via').value = estaPresionado("dist-via") ? "true" : null;
    if (document.querySelector('#elemento-nombre').children[1].name === 'nombre') {
        document.querySelector('input[name="tipo-documento"]').value = document.querySelector('.item-tipo-doc.checked2').children[1].innerHTML.toUpperCase();
    }
    document.querySelector('#registro-form').submit();
})

function estaPresionado(id) {
    if (document.querySelector(`#${id}`) === null) {
        return false
    }
    return document.querySelector(`#${id}`).classList.contains('checked');
}