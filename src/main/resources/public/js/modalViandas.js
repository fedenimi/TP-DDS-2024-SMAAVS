const titleText = document.querySelector('.title-text');
const sigBtn = document.querySelector('.sig-btn');
const confirmarBtn = document.querySelector('.confirmar-btn');
const volverBtn = document.querySelector('.volver-btn');

const confirmarBtnSusc1 = document.querySelectorAll('.confirmar-btn-susc')[0];
const volverBtnSusc1 = document.querySelectorAll('.volver-btn-susc')[0];
const confirmarBtnSusc2 = document.querySelectorAll('.confirmar-btn-susc')[2];
const volverBtnSusc2 = document.querySelectorAll('.volver-btn-susc')[3];
let quedanN = false;
let faltanN = false;

console.log(volverBtnSusc2);

const dist1 = document.querySelectorAll('.dist1');
const dist2 = document.querySelectorAll('.dist2');

if (titleText) {
    if (sigBtn) {
        sigBtn.addEventListener('click', () => {
            if (heladeraSelected() != null) {
                try {
                    completarHeladerasInput("origen", heladeraSelected())
                    document.querySelector('.form-mapa-distribuir').submit()
                } catch (e) {}
                try {document.querySelector('.modal-info-heladera-viandas').classList.remove('open')} catch (e) {
                    console.log(e)}
                dist1.forEach(dist => dist.classList.remove('dist1'));
                dist1.forEach(dist => dist.classList.add('dist2'));
                dist2.forEach(dist => dist.classList.remove('dist2'));
                dist2.forEach(dist => dist.classList.add('dist1'));
                heladeraBtns.forEach(btn => btn.classList.remove('open'));

            }
        })
    }
    if (confirmarBtn) {
        confirmarBtn.addEventListener('click', () => {
            try {completarHeladerasInput("destino", heladeraSelected())} catch (e) {}
            dist1.forEach(dist => dist.classList.remove('dist1'));
            dist1.forEach(dist => dist.classList.add('dist2'));
            dist2.forEach(dist => dist.classList.remove('dist2'));
            dist2.forEach(dist => dist.classList.add('dist1'));
            heladeraBtns.forEach(btn => btn.classList.remove('open'));
        })
    }

    function heladeraSelected() {
        let heladera = null
        heladeraBtns.forEach(btn => {
            if (btn.classList.contains('open')) {
                heladera = btn.children[0].innerText;
            }
        })
        return heladera
    }

    if (volverBtnSusc1) {
        volverBtnSusc1.addEventListener('click', () => {
            dist1.forEach(dist => dist.classList.remove('dist2'));
            dist1.forEach(dist => dist.classList.add('dist1'));
            dist2.forEach(dist => dist.classList.remove('dist1'));
            dist2.forEach(dist => dist.classList.add('dist2'));
            quedanN = false;
            faltanN = false;
        })
    }

    if (confirmarBtnSusc1) {
        confirmarBtnSusc1.addEventListener('click', () => {
            const btns = document.querySelectorAll('.btn-topic');
            if (btns[0].classList.contains('pressed')) quedanN = true;
            if (btns[1].classList.contains('pressed')) faltanN = true;
            dist1.forEach(dist => {
                dist.classList.remove('dist1')
                dist.classList.add('dist2')
            });
            dist2.forEach(dist => {
                if (!dist.classList.contains('form-element')) {
                    dist.classList.remove('dist2')
                    dist.classList.add('dist1')
                } else {
                    if (quedanN && dist.children[1].name === 'restantes') {
                        console.log("DistAA: " + dist);
                        dist.classList.remove('dist2')
                        dist.classList.add('dist1')
                    }
                    if (faltanN && dist.children[1].name === 'faltantes') {
                        console.log("DistBB: " + dist);
                        dist.classList.remove('dist2')
                        dist.classList.add('dist1')
                    }
                }
            });

        })
    }

    if (volverBtnSusc2) {
        volverBtnSusc2.addEventListener('click', () => {
            dist1.forEach(dist => dist.classList.remove('dist2'));
            dist1.forEach(dist => dist.classList.add('dist1'));
            dist2.forEach(dist => dist.classList.remove('dist1'));
            dist2.forEach(dist => dist.classList.add('dist2'));
            quedanN = false;
            faltanN = false;
        })
    }

    if (confirmarBtnSusc2) {
        console.log(confirmarBtnSusc2);
        confirmarBtnSusc2.addEventListener('click', () => {
            const btns = document.querySelectorAll('.btn-topic');
            if (btns[0].classList.contains('pressed')) quedanN = true;
            if (btns[1].classList.contains('pressed')) faltanN = true;
            dist1.forEach(dist => {
                dist.classList.remove('dist1')
                dist.classList.add('dist2')
            });
            dist2.forEach(dist => {
                if (!dist.classList.contains('form-element')) {
                    dist.classList.remove('dist2')
                    dist.classList.add('dist1')
                } else {
                    if (quedanN && dist.children[1].name === 'restantes') {
                        console.log("DistAA: " + dist);
                        dist.classList.remove('dist2')
                        dist.classList.add('dist1')
                    }
                    if (faltanN && dist.children[1].name === 'faltantes') {
                        console.log("DistBB: " + dist);
                        dist.classList.remove('dist2')
                        dist.classList.add('dist1')
                    }
                }
            });
        })
    }

    if (volverBtn) {
        volverBtn.addEventListener('click', () => {
            dist1.forEach(dist => dist.classList.remove('dist2'));
            dist1.forEach(dist => dist.classList.add('dist1'));
            dist2.forEach(dist => dist.classList.remove('dist1'));
            dist2.forEach(dist => dist.classList.add('dist2'));
        })
    }
}

const btnOpenerSolicitudesApertura = document.querySelector('.btn-opener-solicitudes-apertura');
const distribuirViandas3 = document.querySelectorAll('.distribuir-viandas-3');

if (btnOpenerSolicitudesApertura && distribuirViandas3) {
    btnOpenerSolicitudesApertura.addEventListener('click', () => {
        distribuirViandas3.forEach(dist => {
            dist.classList.add('opacity-low');
        })
        const modal = document.querySelector('.modal-solicitudes-apertura');
        modal.classList.contains('hidden') ? modal.classList.remove('hidden') : modal.classList.add('hidden'); 
        modal.classList.add('show-modal');
    })
}

