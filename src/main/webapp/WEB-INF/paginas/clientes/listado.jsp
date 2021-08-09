<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US"/>

<!--<ul>
<c:forEach var="cliente" items="${clientes}">
    <li> ${cliente.idCliente} ${cliente.nombre} ${cliente.apellido} ${cliente.email} ${cliente.telefono} ${cliente.saldo} </li>
</c:forEach>
</ul>-->

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h2>Listado de clientes</h2>
                    </div>
                    <table class="table table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Saldo</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cliente" items="${clientes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${cliente.nombre}</td>
                                    <td>${cliente.apellido}</td>
                                    <td> <fmt:formatNumber value="${cliente.saldo}" type="currency"/></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/Controlador?accion=editar&idCliente=${cliente.idCliente}" class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right">Editar</i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Saldo total</h3>
                        <h4 class="display-4">
                            <fmt:formatNumber value="${saldoTotal}" type="currency"/>
                        </h4>
                    </div>
                </div>
                <!--</div>-->
                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Total clientes</h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i>
                            <spam>${totalClientes}</spam>
                        </h4>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</section>
       <!--Agregar clientes con modal-->
<jsp:include page="/WEB-INF/paginas/clientes/agregarCliente.jsp"/>