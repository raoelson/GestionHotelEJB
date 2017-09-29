autorisePoint = function(evt){
    var keyCode = evt.which ? evt.which : evt.keyCode;
    if (keyCode==9) return true;
    var interdit = 'AZERTYUIOPQSDFGHJKLMWXCVBNazertyuiopqsdfghjklmwxcvbnààâäãçéèêëìîïòôöõµùûüñ°&\?!:\;,\t#~"^¨%\$£?²¤§%\*[]{}=+<>|\\`\'';
    if (interdit.indexOf(String.fromCharCode(keyCode)) >= 0) {
        alert('Veuillez Entrer un Nombre !');
        return false;
    } 
};
special_caract = function(evt){
    var keyCode = evt.which ? evt.which : evt.keyCode;
    if (keyCode==9) return true;
    var interdit = '0123456789ààâäãçéèêëìîïòôöõµùûüñ°&!:;,\t#~"^¨²()[]{}=+<>|\\/`\'';
    if (interdit.indexOf(String.fromCharCode(keyCode)) >= 0) {
        alert('Veuillez Entrer une Lettre !');
        return false;
    }
};

