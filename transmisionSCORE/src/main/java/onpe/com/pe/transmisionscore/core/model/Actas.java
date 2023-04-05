package onpe.com.pe.transmisionscore.core.model;

public class Actas {

    String _id;
    String id_acta;
    String acta;
    String departamento;
    String provincia;
    String distrito;
    String hora;
    String lista1;
    String votosBlanco;
    String votosNulos;
    String votosInpugnados;
    String votosEmitidos;
    String observaciones;
    String firma1;
    String firma2;
    String firma3;
    String estado;
    String fecha_registro;
    Imagenes imagen = new Imagenes();
    String accion;

    public Imagenes getImagen() {
        return imagen;
    }

    public void setImagen(Imagenes imagen) {
        this.imagen = imagen;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getId_acta() {
        return id_acta;
    }

    public void setId_acta(String id_acta) {
        this.id_acta = id_acta;
    }

    public String getActa() {
        return acta;
    }

    public void setActa(String acta) {
        this.acta = acta;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLista1() {
        return lista1;
    }

    public void setLista1(String lista1) {
        this.lista1 = lista1;
    }

    public String getVotosBlanco() {
        return votosBlanco;
    }

    public void setVotosBlanco(String votosBlanco) {
        this.votosBlanco = votosBlanco;
    }

    public String getVotosNulos() {
        return votosNulos;
    }

    public void setVotosNulos(String votosNulos) {
        this.votosNulos = votosNulos;
    }

    public String getVotosInpugnados() {
        return votosInpugnados;
    }

    public void setVotosInpugnados(String votosInpugnados) {
        this.votosInpugnados = votosInpugnados;
    }

    public String getVotosEmitidos() {
        return votosEmitidos;
    }

    public void setVotosEmitidos(String votosEmitidos) {
        this.votosEmitidos = votosEmitidos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFirma1() {
        return firma1;
    }

    public void setFirma1(String firma1) {
        this.firma1 = firma1;
    }

    public String getFirma2() {
        return firma2;
    }

    public void setFirma2(String firma2) {
        this.firma2 = firma2;
    }

    public String getFirma3() {
        return firma3;
    }

    public void setFirma3(String firma3) {
        this.firma3 = firma3;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
