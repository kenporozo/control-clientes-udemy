<section id="action" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <a href="index.jsp" class="btn btn-ligth btn-block">
                    <i class="fas fa-arrow-left"> Inicio</i>
                </a>
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-success btn-block">
                    <i class="fas fa-check"> Guardar</i>
                </button>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/Controlador?accion=eliminar&idCliente=${cliente.idCliente}" class="btn btn-danger btn-block">
                    <i class="fas fa-trash"> Eliminar cliente</i>
                </a>
            </div>
        </div>
    </div>
</section>