<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<c:choose>
    <c:when test="${!empty sessionScope.session.login}">
        <jsp:include page="pckage/header.jsp" /> 
        <jsp:include page="pckage/navs.jsp" />
        <script src="pckage/js/sauve.js"></script> 
        <br/><br/>
        <div class="box-content" id="box-content" >    
            <br/><br/>       
            <table border="1" class="table table-striped table-bordered bootstrap-datatable datatable" id="main-table" style="width: 490px;margin-left:120px;">                 
                <tr>
                    <td colspan="3"> <div class="alert" id="alert" style="display: none;">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <p></p>
                        </div></td>
                </tr>
                <tr>                 
                    <td align="center">Nom de la Base</td>
                    <td><input type="file"  name="file" id="file" value="" style="width: 260px"/></td>                 
                    <td><button type="button" onclick="restore()" class="btn btn-primary">Restore</button></td>    
                </tr> 
                <tr>
                    <td colspan="3"> <div  id="loading-table" style="margin-left: 140px;display: none">
                            <img src="pckage/img/ajax-loader-5.gif"></img> loading
                            content...
                        </div></td>
                </tr>
            </table>    
        </div>
        <jsp:include page="pckage/footer.jsp" />
    </c:when>
    <c:otherwise>
        <c:redirect url="/"></c:redirect>
    </c:otherwise>
</c:choose>
