$(document).ready(function() {   
    inites();
});
inites = function(){
    $.getJSON("AdminController?actions=charts", {
        }, initCallBack);  
};
initCallBack = function(json){     
    var processed_json = new Array();   
    $.each(json.student_data,function(i,elt){                   
        processed_json.push([parseInt(elt.value)]);        
    });
     //console.log(processed_json);
    var chart;
    chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            type: 'line',
            marginRight: 130,
            marginBottom: 25
        },
        title: {
            text: 'Stat & Graphe',
            x: -20 //center
        },
        subtitle: {
            text: 'Source: Statistiques de nbr locataires pour L\'Annee en cours',
            x: -20
        },
        xAxis: {
            categories: [ 'Jan', 'Feb', 'Mar', 'Apr', 'May','Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: 'Nbrs du Locataire'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function() {
                return '<b>'+'Locataire'+'</b><br/>'+
                this.x +': '+ this.y +' Nbrs';
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -10,
            y: 100,
            borderWidth: 0
        },                                        
        series: [{ data: processed_json}]
    });      
};


        //alert(datos);