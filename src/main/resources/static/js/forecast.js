var tempHumLayout = {
      title: 'Temperature and humidity',
      yaxis: {title: 'Celsius'},
      yaxis2: {
        title: '%',
        titlefont: {color: 'rgb(148, 103, 189)'},
        tickfont: {color: 'rgb(148, 103, 189)'},
        overlaying: 'y',
        side: 'right'
      }
    };

 var pressureLayout = {
      title: 'Pressure',
      yaxis: {title: 'mmHg'},
      yaxis2: {
        title: 'mmHg',
        titlefont: {color: 'rgb(148, 103, 189)'},
        tickfont: {color: 'rgb(148, 103, 189)'},
        overlaying: 'y',
        side: 'right'
      }
    };

$( document ).ready(function() {
    $( "#London" ).click(function() {
      RestGet('London');
    });

    $( "#NY" ).click(function() {
      RestGet('New York');
    });

    $( "#Washington" ).click(function() {
      RestGet('Washington');
    });
});


var RestGet = function(location) {
        $.ajax({
        type: 'GET',
        url:  '/' + location + '/dataMap',
        dataType: 'json',
        async: true,
        success: function(result) {

                 var temp = {
                      x: result.weatherForecastDataSeries['TEMPERATURE'].x,
                      y: result.weatherForecastDataSeries['TEMPERATURE'].y,
                      type: 'scatter',
                      name: 'Temperature'
                    };

                var pressure = {
                  x: result.weatherForecastDataSeries['PRESSURE'].x,
                  y: result.weatherForecastDataSeries['PRESSURE'].y,
                  type: 'scatter',

                  name: 'Pressure'
                };

                var humidity = {
                  x: result.weatherForecastDataSeries['HUMIDITY'].x,
                  y: result.weatherForecastDataSeries['HUMIDITY'].y,
                  type: 'scatter',
                  yaxis: 'y2',
                  name: 'Humidity'
                };

                var tempHum = [temp,humidity];
                Plotly.newPlot('tempHumDiv', tempHum, tempHumLayout);
                var press = [pressure];
                Plotly.newPlot('pressDiv', press, pressureLayout);

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Error occur during processing request: ' + jqXHR.status + ' ' + jqXHR.responseText);
        }
   });
}