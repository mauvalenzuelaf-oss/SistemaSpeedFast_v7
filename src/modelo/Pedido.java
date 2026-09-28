package modelo;

/**
 * Representa un pedido del sistema SpeedFast.
 */
public class Pedido {

    // Atributos
    private int id;
    private String direccionEntrega;
    private String tipo;
    private EstadoPedido estado;

    /**
     * Constructor para registrar un pedido nuevo.
     */
    public Pedido(String direccionEntrega, String tipo, EstadoPedido estado) {
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estado = estado;
    }

    /**
     * Constructor para recuperar un pedido desde la base de datos.
     */
    public Pedido(int id, String direccionEntrega, String tipo, EstadoPedido estado) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "#" + id
                + " - " + direccionEntrega
                + " - " + tipo;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
}