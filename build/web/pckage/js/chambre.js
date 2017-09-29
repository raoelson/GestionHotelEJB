$(document).ready(function() {
    inites();
});

inites = function() {
    $("#main-table tbody").html("");
    $.getJSON("ChambreController?actions=listeschambre", {
        }, initCallBack);
};
initCallBack= function(json){
    var tbody = "";
    var nbr = json.listeschambres;     
    var i=0; 
    $.each(nbr,function(i,elt){        
        tbody += "<tr id='tr_"+ elt.idchambre+ "'>";
        tbody += '<td>'+" N° " + elt.idchambre+ '</td>';
        tbody += '<td>'+ elt.surfacechambre+'</td>';
        tbody += '<td>' + elt.qualitechambre + '</td>';
        tbody += '<td>' +elt.loyerchambre + " Ar " +'</td>';
        tbody += '<td>' + elt.dispochambre + '</td>';
        tbody += '<td> <button onclick=" edites('+ elt.idchambre+ ')" class="btn btn-default btn-warning">Edit</button></td>';
        tbody += '<td> <a href="#" onclick=" supprimer('+ elt.idchambre+ ')" class="btn btn-default btn-danger"> Delete</a></td>';
        tbody += '</tr>';
        i++;
    //console.log(elt.id,elt.surface);
    });    
    $("#main-table tbody").append(tbody); 
};

supprimer = function(id){    
    if(!confirm("Supprimez cette chambre "))         
        return;
    $.getJSON("ChambreController?actions=delete&id="+id, supprCallBack);    
};
supprCallBack = function(json){
    var id = json.id;
    //console.log(json.id);
    $("#tr_"+id).remove();
};
show = function(){
    effacer();
    $("#insert_edit").show(); 
};
effacer=function(){
    $("#id").val("");
    $("#surface").val("");
    $("#qualite").val("");   
    $("#loyer").val("");
};
edites = function(id){
    $.getJSON("ChambreController?actions=recherches&id="+id,{        
        },editcallback);
};
editcallback=function(json){
    var chambre =  json.findchambre;    
    $("#id").val(chambre.idchambre);
    $("#surface").val(chambre.surfacechambre);
    $("#qualite").val(chambre.qualitechambre);
    $("#loyer").val(chambre.loyerchambre);
    $("#occupation").val(chambre.dispochambre);  
    $("#insert_edit").show();
};
cancel=function(){
    $("#insert_edit").hide();
};
ajouter=function(){
    var erreur = "";
    if (!$("#surface").val() || !$("#qualite").val() || !$("#loyer").val()) {
        erreur = "Vérifier bien le formulaire";
    }
    else if(erreur == "")       
        $.getJSON("ChambreController?actions=create", {
            "id" : $("#id").val(),
            "surface" : $("#surface").val(),
            "qualite" : $("#qualite").val(),
            "loyer" : $("#loyer").val(),
            "occupation" : $("#occupation").val()          
        },saveCallBack);
    if(erreur){
        $("#alert p").html(erreur);
        $("#alert").show();
        $("#alert").focus();
        setTimeout(function() {
            $("#alert").hide();
        }, 2000);
    }
};
saveCallBack=function(json){
    var success = json.success;
    console.log(success);
    if(json.success="true"){
        cancel();
        inites(); 
    }
};
