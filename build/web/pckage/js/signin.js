$(function() {

	jQuery.support.placeholder = false;
	test = document.createElement('input');
	if ('placeholder' in test)
		jQuery.support.placeholder = true;

	if (!$.support.placeholder) {

		$('.field').find('label').show();

	}
	$.fn.enterKey = function(fnc) {
		return this.each(function() {
			$(this).keypress(function(ev) {
				var keycode = (ev.keyCode ? ev.keyCode : ev.which);
				if (keycode == '13') {
					fnc.call(this, ev);
				}
			})
		})
	};
	$("#login").focus(function() {
		$(".alert").hide();
	});
	$.postJSON = function(url, data, callback) {
		$.post(url, data, callback, "json");
	};
	$("#login, #password").enterKey(function() {
		auth.login();
	});
});
var erreur_global = 0;
auth = {};

auth.login = function() {
	$.postJSON(BASE_URL + "admin.php/auth/verify", {
		"login" : $("#login").val(),
		"password" : $("#password").val(),
		"remember" : $("#remember").val()
	}, auth.loginCallBack);
};
auth.loginCallBack = function(json) {
	if (!json.message) {
		document.location = BASE_URL + "admin.php";
		return;
	}
	$(".alert>span").html("").append(json.message);
	$(".alert").fadeIn(1000);
	$(".alert").fadeOut(10000, "linear");
};
auth.signup = function() {
	var erreur = 0;
	var msg = "Erreur dans le formulaire";
	$("input[type=text], input[type=password], select").each(function() {
		if ($(this).val() == "") {
			erreur = 1;
			msg = "Veuiller remplir toutes les informations";
		}
	});
	if (erreur) {alert(msg);return;}
	if ($("#password").val() != $("#confirm_password").val()) {
		erreur = 1;
		msg="Les mots de passes ne correspondent pas!";
	}
	if (erreur) {alert(msg);return;}
	$("#signup-form").submit();
};