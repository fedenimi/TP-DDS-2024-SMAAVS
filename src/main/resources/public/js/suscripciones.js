const btnTopic = document.querySelectorAll('.btn-topic');

if (btnTopic.length > 0) {
    btnTopic.forEach(btn => {
        btn.addEventListener('click', () => {
            btn.classList.toggle('pressed');
        });
    });
}