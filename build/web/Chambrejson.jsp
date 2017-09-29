<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<c:choose>
    <c:when test="${!empty sessionScope.session.login}">
        <jsp:include page="pckage/header.jsp" /> 
        <jsp:include page="pckage/nav.jsp" />
        <script src="pckage/js/chambre.js"></script>
        <br/><br/>
        <div class="box-content">    
            <table class="table table-striped table-bordered bootstrap-datatable datatable" id="main-table" style="width: 700px;margin-left: -80px">
                <thead>  
                    <tr >
                        <td colspan="10" >
                            <a href="#" onclick="show()" class="btn btn-primary"><i class=""></i>Nouveau</a>                    
                        </td>
                    </tr>
                </thead>  
                <thead>  
                    <tr>       
                        <td width="90" align="center">Numero Chambre</td>
                        <td width="80" align="center">Surface/Personne(s)</td>
                        <td width="80" align="center">Qualité/Classe</td>
                        <td width="90" align="center">Loyer</td>    
                        <td width="90" align="center">Occupation</td>                             
                        <td width="50" align="center" colspan="2">Action</td>
                    </tr>            
                </thead> 
                <tbody>
                </tbody>            			                               
            </table>
        </div>
        <div  id="insert_edit" style="display: none">
            <fieldset style="border: #DDD solid;width: 700px;margin-left: -80px">
                <div class="alert" id="alert" style="display: none;">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <p></p>
                </div>
                <table border="0" align="center">                    
                    <tr style="display: none">
                        <td>Id : </td>
                        <td><input type="text" id="id" name="id" value="" ></input></td>
                    </tr>
                    <tr>
                        <td>Surface: </td>
                        <td><input type="text" name="surface" id="surface" value="" onKeypress="return autorisePoint(event);"></input></td>
                    </tr>
                    <tr>
                        <td>Qualité: </td>
                        <td><input type="text" name="qualite" id="qualite" value="" onKeypress="return autorisePoint(event);"></input></td>
                    </tr>                                                   
                    <td>Loyer : </td>
                    <td><input type="text" name="loyer"  value="" id="loyer" onKeypress="return autorisePoint(event);"></input></td>
                    </tr>
                    <tr>
                        <td>Occupation: </td>
                        <td>                                
                            <select id="occupation" name="occupation">
                                <option>DISPONIBLE</option>
                                <option>NON</option>
                            </select>
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

