$(document).ready(function(){
    inites();    
});


inites=function(){ 
    $("#main-table tbody").html("");
    $.getJSON("ClientController?actions=listesclients", {		
        }, initCallBack);   
}
initCallBack = function(json){ 
    var tbody = "";
    var nbr = json.listeclients;
    var i=0;
    $.each(nbr,function(i,elt){        
        tbody += "<tr id='tr_"+ elt.id+ "'>";
        tbody += '<td>' + elt.nomClient + '</td>';
        tbody += '<td>' + elt.prenomClient + '</td>';
        tbody += '<td>' + elt.numeroLit + '</td>';
        tbody += '<td>' + elt.situation + '</td>';
        tbody += '<td>' + elt.datenaissanceClient + '</td>';        
        tbody += '<td>' + elt.lieu + '</td>';        
        tbody += '<td>' + elt.nationalite + '</td>';      
        tbody += '<td>' + elt.adresseClient + '</td>';
        tbody += '<td> <a href="#" onclick=" edites('+ elt.id+ ')" class="btn btn-default btn-warning">Edit</a></td>';
        tbody += '<td> <a href="#" onclick=" supprimer('+ elt.id+ ')" class="btn btn-default btn-danger">Delete</a></td>';
        tbody += '</tr>';
        i++;
    //console.log(elt.nomClient,elt.prenomClient,elt.numeroLit,elt.datenaissanceClient,elt.adresseClient,elt.lieu,elt.situation);
    });
    $("#main-table tbody").append(tbody);  
};
edites = function(id){
    $.getJSON("ClientController?actions=recherches&id="+id,{
        
        },editcallback);
};
editcallback=function(json){
    var client =  json.findclients;
    $("#id").val(client.id);
    $("#nom").val(client.nomClient);
    $("#prenom").val(client.prenomClient);
    $("#lit").val(client.numeroLit);
    $("#situation").val(client.situation);
    $("#datepicker").val(client.datenaissanceClient);
    $("#lieu").val(client.lieu);
    $("#age").val(client.nationalite);
    $("#adresse").val(client.adresseClient);
    $("#insert_edit").show();
};
supprimer = function(id){
    
    if(!confirm("Supprimez cet client "))         
        return;
    $.getJSON("ClientController?actions=delete&id="+id, supprCallBack);
    
};
supprCallBack = function(json){
    var id = json.id;
    //console.log(json.id);
    $("#tr_"+id).remove();
};
cancel=function(){
    $("#insert_edit").hide();
};
show=function(){    
    effacer();
    $("#insert_edit").show();
}
ajouter=function(){
    var erreur = "";
    if (!$("#nom").val() || !$("#prenom").val() || !$("#lit").val()) {
        erreur = "Vérifier bien le formulaire";
    }
    else if(erreur == "")       
        $.getJSON("ClientController?actions=create", {
            "id" : $("#id").val(),
            "nom" : $("#nom").val(),
            "prenom" : $("#prenom").val(),
            "lit" : $("#lit").val(),
            "situation" : $("#situation").val(),
            "datepicker" : $("#datepicker").val(),
            "lieu" : $("#lieu").val(),			 
            "age" : $("#age").val(),
            "adresse" : $("#adresse").val()
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
    //var success = json.success;
    //console.log(success);
    if(json.success="true"){
        cancel();
        inites();
    }
};
effacer=function(){
    $("#id").val("");
    $("#nom").val("");
    $("#prenom").val("");
    $("#lit").val("");
    $("#situation").val("");
    $("#datepicker").val("");
    $("#lieu").val("");
    $("#age").val("");
    $("#adresse").val("");
};