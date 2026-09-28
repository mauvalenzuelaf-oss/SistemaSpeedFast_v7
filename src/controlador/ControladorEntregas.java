package controlador;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;

import modelo.Entrega;
import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.Repartidor;

import java.sql.Date;
import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordina las operaciones relacionadas con
 * repartidores y entregas.
 */
public class ControladorEntregas {

    // DAO
    private RepartidorDAO repartidorDAO;
    private PedidoDAO pedidoDAO;
    private EntregaDAO entregaDAO;

    /**
     * Construye el controlador de entregas.
     */
    public ControladorEntregas() {

        repartidorDAO =
                new RepartidorDAO();

        pedidoDAO =
                new PedidoDAO();

        entregaDAO =
                new EntregaDAO();
    }

    /**
     * Registra un nuevo repartidor.
     *
     * @param nombre nombre del repartidor
     * @return true si fue registrado correctamente
     */
    public boolean registrarRepartidor(
            String nombre
    ) {

        Repartidor repartidor =
                new Repartidor(nombre);

        return repartidorDAO.guardar(
                repartidor
        );
    }

    /**
     * Obtiene todos los repartidores registrados.
     *
     * @return lista de repartidores
     */
    public List<Repartidor> listarRepartidores() {

        return repartidorDAO.listarTodos();
    }

    /**
     * Obtiene solamente los pedidos pendientes.
     *
     * @return lista de pedidos pendientes
     */
    public List<Pedido> listarPedidosPendientes() {

        List<Pedido> pendientes =
                new ArrayList<>();

        List<Pedido> pedidos =
                pedidoDAO.listarTodos();

        for (Pedido pedido : pedidos) {

            if (pedido.getEstado()
                    == EstadoPedido.PENDIENTE) {

                pendientes.add(pedido);
            }
        }

        return pendientes;
    }

    /**
     * Registra una entrega y cambia el estado
     * del pedido a EN_REPARTO.
     *
     * @param pedido pedido seleccionado
     * @param repartidor repartidor seleccionado
     * @return true si la operación fue realizada correctamente
     */
    public boolean iniciarEntrega(
            Pedido pedido,
            Repartidor repartidor
    ) {

        long ahora =
                System.currentTimeMillis();

        Entrega entrega =
                new Entrega(
                        pedido.getId(),
                        repartidor.getId(),
                        new Date(ahora),
                        new Time(ahora)
                );

        boolean entregaGuardada =
                entregaDAO.guardar(entrega);

        if (!entregaGuardada) {
            return false;
        }

        return pedidoDAO.actualizarEstado(
                pedido.getId(),
                EstadoPedido.EN_REPARTO
        );
    }
}