const element = document.getElementById("canvasON");
const chart = new ChartView(element, -6, 6, -6, 6);

chart.onClickChart = (coordinates) => {
    calculateClick([{name:'x', value:coordinates.x}, {name:'y', value:coordinates.y}]);
};
