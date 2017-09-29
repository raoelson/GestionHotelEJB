$(document).ready(function() {
    inites();  
});
inites = function() {
    $("#main-table tbody").html("");       
    $.getJSON("LocataireController?actions=listeslocataire", {
        "date1" : $('#datepicker1').val(),
        "date2" :$('#datepicker2').val()
    },initCallBack);
};
initCallBack= function(json){
    var tbody = "";
    var nbr = json.listeslocataire;  
    if(nbr==""){
        alert('Aucun résultat !');
        $('#datepicker1').val("");
        $('#datepicker2').val("");
    } 
    var i=0;
    $.each(nbr,function(i,elt){        
        tbody += "<tr id='tr_"+ elt.idlocataire+ "'>";
        tbody += '<td>' + elt.idlocataire+ '</td>';
        tbody += '<td>' + elt.idchambre.idchambre+ '</td>';
        tbody += '<td>' + elt.nomlocataire + '</td>';
        tbody += '<td>' + elt.prenomlocataire + '</td>';
        tbody += '<td>' + elt.professionlocataire + '</td>';
        tbody += '<td>' + elt.datearrivee + '</td>';
        tbody += '<td>' + elt.nbjrslocataire + '</td>';
        tbody += '<td>' + elt.adresselocataire + '</td>';        
        tbody += '<td> <button onclick=" edites('+ elt.idlocataire+ ');appel('+ elt.idchambre.idchambre+ ')" class="btn btn-default btn-warning">Edit</button></td>';
        tbody += '<td> <a href="#" onclick=" supprimer('+ elt.idlocataire+ ')" class="btn btn-default btn-danger"> Delete</a></td>';
        tbody += '</tr>';
        i++; 
    // console.log(elt.id,elt.surface);
    });
    $("#main-table tbody").append(tbody); 
};
show = function(){   
    $("#insert_edit").show();   
    initescombo();    
    effacer();  
};
effacer = function(){
    $("#id").val(0);
    $("#nomlocataire").val("");
    $("#prenom").val("");   
    $("#profes").val("");
    $("#datepicker").val("");
    $("#nbr").val("");
    $("#adresse").val("");
    $("#avance").val(0);
    $("#datepicker1").val("");
    $("#datepicker2").val("");
};
cancel = function(){
    $("#numchambre").val("").text("");
    $("#insert_edit").hide();
};
supprimer = function(id){
    if(!confirm("Supprimez cet Locataire "))         
        return;
    $.getJSON("LocataireController?actions=delete&id="+id, supprCallBack);
};
supprCallBack = function(json){
    var id = json.id;
    //console.log(json.id);
    $("#tr_"+id).remove();
    cancel();
};
initescombo = function(){    
    $.getJSON("LocataireController?actions=chargmnt", {
        }, initcomboCallBack);
};
initcomboCallBack= function(json){        
    var $select = $('#numchambre');
    var nbr = json.listeschambres; 
    console.log(json.listeschambres);    
    $.each(nbr,function(i,elt){
        $('<option>').val(elt.idchambre).text("N°"+elt.idchambre +" - "+elt.surfacechambre+" Pers"+" - "+elt.loyerchambre+" Ar").appendTo($select);
        //console.log(elt.idchambre," ",elt.surfacechambre," ",elt.loyerchambre);
});
};
ajouter=function(){
    var erreur = "";
    if (!$("#nomlocataire").val() || !$("#prenom").val() || !$("#datepicker").val()) {
        erreur = "Vérifier bien le formulaire";
    }
    else if(erreur == "")       
        $.getJSON("LocataireController?actions=create", {
            "id" : $("#id").val(),
            "numchambre" : $("#numchambre").val(),
            "nomlocataire" : $("#nomlocataire").val(),
            "prenom" : $("#prenom").val(),
            "prof" : $("#profes").val(),
            "datepicker" : $("#datepicker").val(),
            "nbr" : $("#nbr").val(),
            "adresse" : $("#adresse").val(),
            "payement" : $("#payement").val(),
            "avance" : $("#avance").val()
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
affiche=function(){
    var id =  $("#payement").val();
    if(id == "tous"){
        $("#avance").val(0);  
        $("#avance").hide();
    }
    else{
        $("#avance").show();
        $("#avance").val(0); 
    }
}
edites = function(id){
    $("#numchambre").val("").text(""); 
    $.getJSON("LocataireController?actions=recherches&id="+id,{        
        },editcallback);
};
editcallback=function(json){
    var locataire =  json.findlocataire;      
    //console.log(locataire.idchambre.idchambre);
    $("#id").val(locataire.idlocataire);
    //$("#numchambre").val(locataire.idchambre.idchambre);
    $("#nomlocataire").val(locataire.nomlocataire);
    $("#prenom").val(locataire.prenomlocataire);
    $("#profes").val(locataire.professionlocataire);  
    $("#datepicker").val(locataire.datearrivee);
    $("#nbr").val(locataire.nbjrslocataire);
    $("#adresse").val(locataire.adresselocataire);
    $("#payement").val(locataire.payement);
    if(( $("#avance").val(locataire.avance))== "0"){
        $("#avance").hide();
        $("#avance").val(locataire.avance)
    }
    else{
        $("#avance").show();       
    }
    $("#insert_edit").show();
};
appel = function(id){      
    $.getJSON("LocataireController?actions=chargmnts&id="+id, {
        }, initcombosCallBack);
};
initcombosCallBack= function(json){    
    var $select = $('#numchambre');
    var nbr = json.listeschambres;        
    $.each(nbr,function(i,elt){           
        $('<option>').val(elt.idchambre).text("N°"+elt.idchambre +" - "+elt.surfacechambre+" Pers"+" - "+elt.loyerchambre+" Ar").appendTo($select);
        //console.log(elt.idchambre+' '+elt.surfacechambre);
});    
};
appelle = function(){
    $("#numchambre").val("").text("");
};
entredates = function(){    
    if(($('#datepicker1').val()) == "" || ($('#datepicker2').val()) == ""){
        alert('Veuillez Remplir le(s) Date(s) Min et Max !');
    }
    else{
        inites();              
    }
};    
 