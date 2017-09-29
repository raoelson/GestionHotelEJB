<jsp:include page="pckage/header.jsp" />
<script src="pckage/js/Etat.js"></script> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes"> 
<link href="pckage/css/bootstrap.css" rel="stylesheet">
<link href="pckage/css/bootstrap-responsive.css" rel="stylesheet">
<div class="account-container" style="background: url(./pckage/img/signin/body-bg.png); color:#838383; font: 13px/1.7em 'Open Sans';">

    <div class="content clearfix">

        <h1 style="color: #0099FF">Authentification</h1>		

        <div class="login-fields">
            <p>Identifiez-vous avec votre compte:</p>            
            <div class="field">
                <label for="username">Login:</label>
                <input type="text" id="login" name="login" value="" class="login username-field" required placeholder="Login"/>
            </div> <!-- /field -->

            <div class="field">
                <label for="password">Mot de passe :</label>
                <input type="password" id="password" name="password" value="" placeholder="Mot de passe" class="login password-field" required/>
            </div> <!-- /password -->							
        </div> <!-- /login-fields -->        
        <div id="alert" style="display: none">            
            <p class="alert alert-error" ></p> 
        </div>
        <div class="login-actions">
            <h3 style="font-size: 36px;float: left;margin: 15px 34px 0;display: none" id="load"><i class="icon-spinner icon-spin"></i></h3>					
            <button type="button" class=" button btn-default btn-info btn-large" value='Se connecter' id="connecter" onclick="authfy()">Se connecter</button>
        </div> <!-- .actions -->                                           
    </div> <!-- /content -->

</div>
<script src="pckage/js/bootstrap.js"></script>
<script src="pckage/js/signin.js"></script>
