<jsp:include page="pckage/header.jsp" /> 
<jsp:include page="pckage/nav.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
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
        <c:forEach items="${listeschambre}" var="listes">
            <tr id="tr_<c:out value="${listes.idchambre}" />">
                <td>  
                    <c:out value="${listes.idchambre}" />                          
                </td>
                <td> 
                    <c:out value="${listes.surfacechambre}" />  
                </td>
                <td> 
                    <c:out value="${listes.qualitechambre}" />  
                </td>
                <td> 
                    <c:out value="${listes.loyerchambre}" />  
                </td>
                <td> 
                    <c:out value="${listes.dispochambre}" />  
                </td>               
                <td>
                    <a class="btn btn-danger" href="#" onclick="client.supprimer(<c:out value="${listes.idchambre}" />)">Delete</a>                                                                        
                </td>
                <td>                                                                                                                                         
                    <a  href="#" onclick=" client.edit(<c:out value="${listes.idchambre}" />) " class="btn btn-default btn-warning">Edit</a> 
                </td>                    
            </tr>
        </c:forEach>                        
    </table>
</form> 
</div>
