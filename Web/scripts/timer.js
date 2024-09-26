const timer = document.querySelector('.timer');

if (timer) {
    var todayThreeHoursLater = new Date().getTime() + 3 * 60 * 60 * 1000;
    var x = setInterval(function () {

        var now = new Date().getTime();
        var distance = todayThreeHoursLater - now;
        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);
        timer.innerHTML = "Usted tiene " + days + "d " + hours + "h "
            + minutes + "m " + seconds + "s para abrir estas heladeras:";
        if (distance < 0) {
            clearInterval(x);
            timer.innerHTML = "EXPIRED";
        }
    }, 1000);
}