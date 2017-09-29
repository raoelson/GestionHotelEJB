show = function(){  
    $("#main-table tbody").html("");
    id = $("#idnom").val();    
    $.getJSON("EtatController?actions=etats&id="+id, {        
        }, etatcallback)
};
etatcallback = function(json){
    var tbody = "";
    var nbr = json.etatpaie; 
    //console.log(nbr);    
    var i=0; 
    if(nbr==""){
        alert('Aucun résultat !');
        $("#idnom").val("")
    }    
    $.each(nbr,function(i,elt){         
        check = "";
        tbody += "<tr id='tr_"+ elt.idlocataire+ "'>";
        tbody += '<td>' + elt.idlocataire+ '</td>';       
        tbody += '<td>' + elt.nomlocataire + '</td>';
        tbody += '<td>' + elt.prenomlocataire + '</td>';
        tbody += '<td>' + elt.idchambre.idchambre+ '</td>';        
        tbody += '<td>' + elt.datearrivee + '</td>';
        tbody += '<td>' + elt.idchambre.loyerchambre + '</td>';
        tbody += '<td>' + elt.payement + '</td>'; 
        if(elt.payement == "avance"){
            tbody += '<td>' + (elt.idchambre.loyerchambre - elt.avance) + '</td>';             
            tbody += '<td><img src="pckage/img/fermer-croix-supprimer-erreurs-sortie-icone-4368-96.png" align="absmiddle" width="28" height="28"/></td>';   
        }
        else{
            tbody += '<td>' + 0.0 + '</td>'; 
            tbody += '<td><img src="pckage/images/verif1.jpg" align="absmiddle" width="28" height="28"/></td>';
        }         
        tbody += '<td><a href="#" onclick=" imprimer()" ><img src="pckage/images/print.png" width="28" height="28"><span style="font-size: 11px">Imprimer</span></a></td>';        
        tbody += '<td> <a href="#" onclick="pdf('+elt.idlocataire+')"  ><img src="pckage/images/pdf.jpg" width="28" height="28">PDF</a></td>';
        tbody += '<td> <a href="#" onclick="csv('+elt.idlocataire+')" ><img src="pckage/images/CSV.png" width="28" height="28">CSV</a></td>';
        tbody += '</tr>';
        i++;
    //console.log(elt.reste);
    });    
    $("#main-table tbody").append(tbody);
};
imprimer = function(){
    window.print();
}
csv = function(id){   
    $.getJSON("EtatController?actions=csv&id="+id, {        
        }, csvCallback)
};
csvCallback = function(json){
    if(json.success="true"){        
        $("#alert p").html("Report CSV Avec Success");
        $("#alert").show();
        $("#alert").focus();
        setTimeout(function() {
            $("#alert").hide();
        }, 3500);
    }
};
pdf = function(id){   
    $.getJSON("EtatController?actions=pdf&id="+id, {        
        }, pdfCallback)
};
pdfCallback = function(json){
    if(json.success="true"){        
        $("#alert p").html("Report PDF Avec Success");
        $("#alert").show();
        $("#alert").focus();
        setTimeout(function() {
            $("#alert").hide();
        }, 3500);
    }
};
authfy = function(){
    if(($("#login").val()== "" && $("#password").val()== "")){
          $("#alert p").html("Login ou Password required");
        $("#alert").show();
        $("#alert").focus();
        setTimeout(function() {
            $("#alert").hide();
        }, 3500);
    }else{
        
    $.getJSON("UserController?actions=authentification", { 
        "login" : $("#login").val(),
        "password" : $("#password").val()
    }, authcallback)
    
    }
};
authcallback = function(json){
    var verf = json.verify;    
    if(verf == ""){
        $("#alert p").html("Login ou Password Invalid");
        $("#alert").show();
        $("#alert").focus();
        setTimeout(function() {
            $("#alert").hide();
        }, 3500);        
    }else{
       document.location='/GestionHotelEJB/ChambreController?actions=wlcm';
    }
    };

 
