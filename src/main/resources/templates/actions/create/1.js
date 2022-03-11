function addInput() {
    var flatsPerFloor = document.getElementById("flatsPerFloor").value;
    document.getElementById('vvod').style.visibility = 'hidden';
    document.getElementById("maininfo").style.visibility = 'visible';
    var input = document.getElementById("input1");
    for (var i = 1; i < flatsPerFloor + 1; i++) {
        input.insertAdjacentHTML("beforebegin",
            '<label htmlFor="roomsCount" + i>Введите количество комнат <span id="i"></span> квартиры: </label>' +
            '<input type="text" id="roomsCount" + i name="flats_per_floor"> <br>')
    }
    var flatsSquare = [];
    for (var i = 1; i <= flatsPerFloor; i++) {
        var teg_input = document.createElement('<input>');
        teg_input.name = 'flat' + i;
        teg_input
        flatsSquare[i] =
    }
    document.forms["flat1"];

//вариант 1 - добавит в конец тела документа
    document.body.appendChild(teg_input);

}