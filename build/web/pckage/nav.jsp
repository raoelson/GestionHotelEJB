<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmtx"%>
<div id="art-page-background-glare">
    <div id="art-page-background-glare-image">                                                               
    </div>
</div>
<div id="art-main">
    <div class="art-sheet">
        <div class="art-sheet-tl"></div>
        <div class="art-sheet-tr"></div>
        <div class="art-sheet-bl"></div>
        <div class="art-sheet-br"></div>
        <div class="art-sheet-tc"></div>
        <div class="art-sheet-bc"></div>
        <div class="art-sheet-cl"></div>
        <div class="art-sheet-cr"></div>
        <div class="art-sheet-cc"></div>
        <div class="art-sheet-body">
            <div class="art-header">
                <div class="art-header-clip">
                    <div class="art-header-center">
                        <div class="art-header-jpeg"></div>
                    </div>
                </div>
                <div class="art-logo">
                    <h1 class="art-logo-name"><span style="margin-left: 360px"><font color="white"></font></span></h1><br/>                                                       
                </div>
            </div>
            <div class="cleared reset-box"></div>
            <div class="art-nav">
                <div class="art-nav-l"></div>
                <div class="art-nav-r"></div>
                <div class="art-nav-outer">
                    <ul class="nav nav-tabs">
                        <li ><a href="ChambreController?actions=wlcm"  style="color: #000; border-radius: 20px; font-size: 14px;"><b>Chambre</b></a></li>
                        <li><a href="LocataireController?actions=wlcm" style="color: #000; border-radius: 20px"><b>Locataire</b></a></li>                                                            
                        <li><a href="EtatController?actions=wlcm" style="color: #000; border-radius: 20px"><b>Etat de Paie</b></a></li>
                        <li><a href="AdminController?actions=stat" style="color: #000; border-radius: 20px"><b>Stat&Graphe</b></a></li>
                        <li><a href="UserController?actions=wlcm" style="color: #000; border-radius: 20px"><b>Administration</b></a></li>                                                             
                        <li><a href="Deconnexion" style="color: #000; border-radius: 20px"><b>D&eacute;connexion</b></a></li>              
                    </ul>
                </div>
            </div>
            <div style="height: 480px; overflow: auto;">            
                <div class="cleared reset-box"></div>
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1">
                            <br/><b/><span style="margin-left:20px"><font color="black">Vous &ecirc;tes ici:</font></span><font color="#999966">&nbsp;<cx:out value="${connecte}"/></font>                                                                             
                        </div>
                        <div class="art-layout-cell art-sidebar1">                                                                                                        
                        </div>
                        <div class="art-layout-cell art-sidebar1">                                                                                                         
                        </div> 
                        <div class="art-layout-cell art-sidebar1">
                            <br/><span style="margin-left: -150px"><font color="black">Vous &ecirc;tes connectés autant que:</font></span><font color="#999966">&nbsp;                                                                                      
                            <cx:if test="${!empty sessionScope.session.login}">${sessionScope.session.login}</cx:if>                             
                            </font>                                                                           
                        </div>                           