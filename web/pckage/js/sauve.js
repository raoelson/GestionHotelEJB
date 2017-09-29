sauve = function(){
    // alert($('#sauve').val());
    $.getJSON("AdminController?actions=save", {
        "id":$('#sauve').val()
    }, saveCallBack);
};
saveCallBack = function(json){   
    //console.log(json.message);  
    $("#alert p").html(json.message);
    $("#alert").show();
    $("#alert").focus();
    setTimeout(function() {
        $("#alert").hide();
    }, 2000);
};
restore = function(){
    //alert($('#file').val());
    $("#loading-table").show();
    $.getJSON("AdminController?actions=restaure", {
        "id":$('#file').val()
    }, restoreCallBack);     
};
restoreCallBack = function(json){
    $("#alert p").html(json.message);
    $("#alert").show();
    $("#alert").focus();
    setTimeout(function() {
        $("#alert").hide();
    }, 2000);
    $("#loading-table").hide();
};
