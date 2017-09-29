<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<c:choose>
    <c:when test="${!empty sessionScope.session.login}">
        <jsp:include page="pckage/header.jsp" /> 
        <jsp:include page="pckage/nav.jsp" />
        <script src="pckage/js/Locataire.js"></script> 
        <link rel="stylesheet" href="pckage/jquery/themes/base/jquery.ui.all.css" > 	
        <script src="pckage/jquery/jquery-1.6.2.js"></script>		
        <script src="pckage/jquery/ui/jquery.ui.core.js"></script>	
        <script src="pckage/jquery/ui/jquery.ui.widget.js"></script>
        <script src="pckage/jquery/ui/jquery.ui.datepicker.js"></script>
        <link rel="stylesheet" href="pckage/jquery/css/demos.css"	>
        <script>
            $(function() {
                $( "#datepicker" ).datepicker();               
                $( "#datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );                    
            });
            $(function() {
                $( "#datepicker1" ).datepicker();               
                $( "#datepicker1" ).datepicker( "option", "dateFormat", "yy-mm-dd" );                    
            });
            $(function() {
                $( "#datepicker2" ).datepicker();               
                $( "#datepicker2" ).datepicker( "option", "dateFormat", "yy-mm-dd" );                    
            });
        </script>
        <style>
            .toggler { width: 100px; height: 90px; position: relative; }
            #button { padding: .5em 1em; text-decoration: none; }
            #effect {position: relative;  width: 220px; letter-spacing: 0; font-size: 1.2em; border: 1px solid #000; background: #eee; color: #333; }
            .newClass { text-indent: 40px; letter-spacing: .4em; width: 80px; height: 90px;   font-size: 1.6em; }
        </style>
        <br/><br/>  
        <div class="box-content">   
            <table class="table table-striped table-bordered bootstrap-datatable datatable" id="main-table" style="width: 700px;margin-left:-80px">
                <thead>  
                    <tr>
                        <td colspan="10">
                            <div>
                                <a href="#" onclick="appelle();show()" class="btn btn-primary">Nouveau</a>
                                <span style="margin-left: 80px">
                                    Recherche Entre Deux Dates : 
                                    <input type="text" name="datepicker1" id="datepicker1" placeholder="date Min" style="width:90px">
                                    <input type="text" name="datepicker2" id="datepicker2" placeholder="Date Max" style="width:90px">
                                    <a href="#" style="width: 90px" onclick="entredates()" class="btn btn-primary"><img src="pckage/img/Search-icon.png" align="absmiddle" width="13" height="13"/>&nbsp;Ok</a>
                                </span>
                            </div>
                        </td>                             
                    </tr>
                </thead>  
                <thead>  
                    <tr>       
                        <td width="90" align="center">ID Locataire</td>
                        <td width="80" align="center">Numero Chambre</td>
                        <td width="80" align="center">Nom</td>
                        <td width="90" align="center">Prenom</td>    
                        <td width="90" align="center">Profession</td>
                        <td width="90" align="center">Date Arrivée</td>
                        <td width="90" align="center">Nbr Jours</td>
                        <td width="90" align="center">Adresse</td>
                        <td width="50" align="center" colspan="2">Action</td>
                    </tr>            
                </thead> 
                <tbody>
                </tbody>
            </table>
        </div>
        <div  id="insert_edit" style="display: none">                
            <fieldset style="border: #DDD  ;width: 700px;margin-left: -180px">
                <div class="alert" id="alert" style="display: none;">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <p></p>
                </div>
                <table border="0" style="margin-left: 250px">                    
                    <tr style="display: none">
                        <td>Id : </td>
                        <td><input type="text" id="id" name="id" value="" ></td>
                    </tr>
                    <tr>
                        <td>N° Chambre Dispo: </td>
                        <td>                      
                            <select id="numchambre" name="numchambre" onchange="caractere()">   
                            </select>                    
                        </td>                  
                    </tr>
                    <tr>
                        <td>Nom </td>
                        <td><input type="text" name="nomlocataire" id="nomlocataire" value="" onKeypress="return special_caract(event);"></input></td>
                    </tr>
                    <tr>
                        <td>Prenom </td>
                        <td><input type="text" name="prenom" id="prenom" value="" onKeypress="return special_caract(event);"></input></td>
                    </tr>  
                    <tr>
                        <td>Profession : </td>
                        <td><input type="text" name="profes"  value="" id="profes" onKeypress="return special_caract(event);"></input></td>
                    </tr> 
                    <tr>
                        <td>Date Arrivée : </td>
                        <td><input type="text" name="datepicker"  value="" id="datepicker"></input></td>
                    </tr> 
                    <tr>
                        <td>Nbr Jour : </td>
                        <td><input type="text" name="nbr"  value="" id="nbr" onKeypress="return autorisePoint(event);"></input></td>
                    </tr>
                    <tr>
                        <td>Adresse : </td>
                        <td><input type="text" name="adresse"  value="" id="adresse" onKeypress="return special_caract(event);"></input></td>
                    </tr>
                    <tr>
                        <td>Payé : </td>
                        <td>
                            <select id="payement" name="payement" onclick="affiche()">
                                <option value="tous">Tous</option>
                                <option value="avance">Avance</option>
                            </select>
                        <td><input type="text" name="avance"  value="0" id="avance" placeholder="Montant en Ar" style="width: 90px;display: none"></input></td>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">

                            <button type="button" onclick="ajouter()" class="btn btn-primary">Save</button>                                                                                        
                            <a class="btn" href="" onclick="cancel()">Cancel</a>
                        </td>
                    </tr>
                </table>

            </fieldset>
        </div>
        <jsp:include page="pckage/footer.jsp" />
    </c:when>
    <c:otherwise>
        <c:redirect url="/"></c:redirect>
    </c:otherwise>
</c:choose>