let results = [];
let canvas = null;

main = function (array, startRadius) {
    canvas = new CanvasView(document.getElementById("canvas"));
    canvas.render(startRadius);
    array.forEach(res => {
        canvas.drawDot(res.coordinates, res.success ? '#00FF00' : '#FF0000');
    });
    results = array;

    canvas.onClickCanvas = (coord) => {
        processFromCanvas([
            {name: 'x', value: coord.x},
            {name: 'y', value: coord.y}
        ]);
    };
}

updateCanvas = function (newRadius, array) {
    canvas.render(newRadius);
    array.forEach(res => {
        canvas.drawDot(res.coordinates, res.success ? '#00FF00' : '#FF0000');
    });
}