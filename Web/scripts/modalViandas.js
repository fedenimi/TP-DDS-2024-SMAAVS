const titleText = document.querySelector('.title-text');
const sigBtn = document.querySelector('.sig-btn');
const volverBtn = document.querySelector('.volver-btn');
const dist1 = document.querySelectorAll('.dist1');
const dist2 = document.querySelectorAll('.dist2');


if (volverBtn && titleText && sigBtn) {
    sigBtn.addEventListener('click', () => {
        dist1.forEach(dist => dist.classList.remove('dist1'));
        dist1.forEach(dist => dist.classList.add('dist2'));
        dist2.forEach(dist => dist.classList.remove('dist2'));
        dist2.forEach(dist => dist.classList.add('dist1'));
        heladeraBtns.forEach(btn => btn.classList.remove('open'));
    })

    volverBtn.addEventListener('click', () => {
        dist1.forEach(dist => dist.classList.remove('dist2'));
        dist1.forEach(dist => dist.classList.add('dist1'));
        dist2.forEach(dist => dist.classList.remove('dist1'));
        dist2.forEach(dist => dist.classList.add('dist2'));
    })
}