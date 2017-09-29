<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<c:choose>
    <c:when test="${!empty sessionScope.session.login}">
        <jsp:include page="pckage/header.jsp" /> 
        <jsp:include page="pckage/navs.jsp" />
        <script src="pckage/js/sauve.js"></script> 
        <br/><br/>
        <div class="box-content">    
            <br/><br/>       
            <table border="1" id="main-table" class="table table-striped table-bordered bootstrap-datatable datatable" style="width: 350px;margin-left:120px;">                 
                <tr>
                <span style= "margin-left:60px"> Votre base sera sauvegardée dans la partition D:\Backup\ de votre disque dur!</span>
                <td colspan="3"><div class="alert" id="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <p></p>
                    </div>
                </td>
                </tr>
                <tr>                 
                    <td align="center">Nom de la Base</td>
                    <td><input type="text" value="gestionhotel"  name="sauve" id="sauve" readonly style="width: 90px"/></td>                 
                    <td><button type="button" onclick="sauve()" class="btn btn-primary"><i class=""></i>Backup</button></td>
                </tr>                           
            </table>    
        </div>
        <jsp:include page="pckage/footer.jsp" />
    </c:when>
    <c:otherwise>
        <c:redirect url="/"></c:redirect>
    </c:otherwise>
</c:choose>
