<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar cliente</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
        <!--</div>-->
        <form action="${pageContext.request.contextPath}/Controlador?accion=insertar" method="POST" class="was-validated">
            <div class="modal-body">
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" name="nombre" class="form-control" required="">
                </div>
                 <div class="form-group">
                    <label for="apellido">Apellido</label>
                    <input type="text" name="apellido" class="form-control" required="">
                </div>
                 <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" name="email" class="form-control" required="">
                </div>
                 <div class="form-group">
                    <label for="telefono">Telefono</label>
                    <input type="tel" name="telefono" class="form-control" required="">
                </div>
                 <div class="form-group">
                    <label for="saldo">Saldo</label>
                    <input type="number" name="saldo" class="form-control" required="" step="any">
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn-primary">Guardar</button>                
            </div>
        </form>
    </div>
</div> 