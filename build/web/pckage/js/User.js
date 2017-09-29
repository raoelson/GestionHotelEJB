$(document).ready(function() {
    inites();
});
inites = function() {
    $("#main-table tbody").html("");
    $.getJSON("UserController?actions=listesusers", {
        }, initCallBack);
};
initCallBack= function(json){
    var tbody = "";
    var nbr = json.listesusers;     
    var i=0; 
    $.each(nbr,function(i,elt){        
        tbody += "<tr id='tr_"+ elt.id+ "'>";
        tbody += '<td>' + elt.id+ '</td>';
        tbody += '<td>' + elt.login+ '</td>';
        tbody += '<td>' + elt.type + '</td>';
        tbody += '<td style="width:70px"> <a href="#" onclick=" edites('+ elt.id+ ')" class="btn btn-default btn-warning"> Edit</a></td>';
        tbody += '<td style="width:80px"> <a href="#" onclick=" supprimer('+ elt.id+ ')" class="btn btn-default btn-danger"> Delete</a></td>';
        tbody += '</tr>';
        i++;
    //console.log(elt.id,elt.surface);
    });    
    $("#main-table tbody").append(tbody); 
};
supprimer = function(id){
    if(!confirm("Supprimez cette chambre "))         
        return;
    $.getJSON("UserController?actions=delete", {
        "id":id
    }, supprCallBack); 
};
supprCallBack = function(json){
    var id = json.id;
    //console.log(json.id);
    $("#tr_"+id).remove();
    cancel();
    effacer();
};
show = function(){
    effacer();
    $("#insert_edit").show(); 
};
effacer=function(){
    $("#id").val(0);
    $("#login").val("");
    $("#password").val("");   
    $("#confirm").val("");    
};
edites = function(id){  
    $.getJSON("UserController?actions=recherches",{ 
        "id":id
    },editcallback);
};
editcallback=function(json){
    var users =  json.findusers;
    $("#id").val(users.id);    
    $("#login").val(users.login);   
    $("#type").val(users.type);      
    $("#insert_edit").show();
};
cancel=function(){
    $("#insert_edit").hide();
};
ajouter=function(){
    var erreur = "";
    if (!$("#login").val() || !$("#password").val() || !$("#confirm").val()) {
        erreur = "Vérifier bien le formulaire";
    }
    else if($("#password").val() != $("#confirm").val()){
        erreur = "Votre mot de passe ne correspond pas!";
    }
    else if(erreur == "")       
        $.getJSON("UserController?actions=create", {
            "id" : $("#id").val(),
            "login" : $("#login").val(),
            "password" : $("#password").val(),
            "type" : $("#type").val()                      
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
        effacer();
        inites();
    }
};
