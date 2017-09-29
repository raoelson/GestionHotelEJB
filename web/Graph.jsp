<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<c:choose>
    <c:when test="${!empty sessionScope.session.login}">
        <script src="pckage/jquery/jquery-1.6.2.js" type="text/javascript"></script>
        <script src="pckage/js/lib/highcharts/js/highcharts.js" type="text/javascript"></script>        
        <script src="pckage/js/lib/highcharts/js/modules/exporting.js" type="text/javascript"></script> 
        <script src="pckage/js/charts.js" type="text/javascript"></script>
        <jsp:include page="pckage/header.jsp" /> 
        <jsp:include page="pckage/nav.jsp" />  
        <br/><br/>
        <div style="margin-left:-150px" id="container" style="height: 400px">            
        </div>
        <jsp:include page="pckage/footer.jsp" />
    </c:when>
    <c:otherwise>
        <c:redirect url="/"></c:redirect>
    </c:otherwise>
</c:choose>