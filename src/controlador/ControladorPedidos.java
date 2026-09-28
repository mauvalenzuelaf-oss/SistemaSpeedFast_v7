package controlador;

import dao.PedidoDAO;
import modelo.EstadoPedido;
import modelo.Pedido;

import java.util.List;

/**
 * Coordina las operaciones relacionadas con los pedidos.
 */
public class ControladorPedidos {

    // DAO
    private PedidoDAO pedidoDAO;

    /**
     * Construye el controlador de pedidos.
     */
    public ControladorPedidos() {
        pedidoDAO = new PedidoDAO();
    }

    /**
     * Registra un nuevo pedido.
     *
     * @param direccion dirección de entrega
     * @param tipo tipo de pedido
     * @return true si fue registrado correctamente
     */
    public boolean registrarPedido(
            String direccion,
            String tipo
    ) {

        Pedido pedido =
                new Pedido(
                        direccion,
                        tipo,
                        EstadoPedido.PENDIENTE
                );

        return pedidoDAO.guardar(pedido);
    }

    /**
     * Obtiene todos los pedidos registrados.
     *
     * @return lista de pedidos
     */
    public List<Pedido> listarPedidos() {

        return pedidoDAO.listarTodos();
    }
}
