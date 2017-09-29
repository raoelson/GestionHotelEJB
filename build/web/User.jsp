<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<c:choose>
    <c:when test="${!empty sessionScope.session.login}">
<jsp:include page="pckage/header.jsp" /> 
<jsp:include page="pckage/navs.jsp" />
<script src="pckage/js/User.js"></script> 
<br/><br/>

<div class="box-content">    
    <table class="table table-striped table-bordered bootstrap-datatable datatable" id="main-table" style="width: 600px;margin-left: 50px">
        <thead>  
            <tr >
                <td colspan="10" >
                    <a href="#" onclick="show()" class="btn btn-primary"><i class=""></i>Nouveau</a>                    
                </td>
            </tr>
        </thead>  
        <thead>  
            <tr>       
                <td width="90" align="center">id</td>
                <td width="80" align="center">Login</td>
                <td width="80" align="center">Type</td>                                           
                <td  style="width: 30px" align="center" colspan="2">Action</td>
            </tr>            
        </thead> 
        <tbody>
        </tbody>
    </table>
</div>
<div  id="insert_edit" style="display: none">
    <fieldset style="border: #DDD solid;width: 600px;margin-left: 55px">
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
                <td>Login: </td>
                <td><input type="text" name="login" id="login" value="" ></input></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password" id="password" value=""></input></td>
            </tr> 
            <tr>
                <td>Confirm Password: </td>
                <td><input type="password" name="confirm" id="confirm" value=""></input></td>
            </tr>           
            <tr>
                <td>Type : </td>
                <td>                                
                    <select id="type" name="type">
                        <option value="admin">Administrateur</option>
                        <option value="simple">Simple</option>
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