// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Task', 'Cantidad'],
  ['Obras', 8],
  ['Archivos', 2],
  ['Beacons', 4],
  ['Exhibiciones', 2]
]);

  // Optional; add a title and set the width and height of the chart
  var options = {'title':'Estadistica De Museo', 'width':550, 'height':400};

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.ColumnChart(document.getElementById('piechart'));
  chart.draw(data, options);
}