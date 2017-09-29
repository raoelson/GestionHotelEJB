<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<c:choose>
    <c:when test="${!empty sessionScope.session.login}">
        <jsp:include page="pckage/header.jsp" /> 
        <jsp:include page="pckage/nav.jsp" />
        <script src="pckage/js/Etat.js"></script> 
        <br/><br/>
        <div class="box-content">     
            <table class="table table-striped table-bordered bootstrap-datatable datatable" id="main-table" style="width: 890px;margin-left: -200px">          
                <font align="center" color="blue"><i>Entrez Numero Locataire  :</i> <input type="text" name="idnom" id="idnom" style="width: 100px; height: 20px" placeholder='ID '/>
                <a href="#" onclick="show()" class="btn btn-primary" >Valider</a>                    
                </font><br/>        
                <thead> 
                    <tr>
                        <td colspan="11"> <div class="alert" id="alert" style="display: none;">
                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                <p></p>
                            </div></td>
                    </tr>
                    <tr>       
                        <td>Numero Locataire</td>
                        <td width="80" align="center">Nom</td>
                        <td width="80" align="center">Prenom</td>
                        <td width="90" align="center">Numero Chambre</td>    
                        <td width="90" align="center">Date Arrivée</td> 
                        <td width="90" align="center">Montant du Loyer</td>
                        <td width="90" align="center">Paiement</td>
                        <td width="90" align="center">Reste</td>
                        <td width="90" align="center">Critique</td>
                        <td width="50" align="center">Imprimer</td>                        
                        <td width="50" align="center" colspan="2">Report</td>
                    </tr>            
                </thead> 
                <tbody>
                </tbody>
            </table>
        </div>
        <jsp:include page="pckage/footer.jsp" />
    </c:when>
    <c:otherwise>
        <c:redirect url="/"></c:redirect>
    </c:otherwise>
</c:choose>
