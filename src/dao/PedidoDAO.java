package dao;

import modelo.EstadoPedido;
import modelo.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona el acceso a datos de los pedidos.
 */
public class PedidoDAO {

    /**
     * Guarda un pedido en la base de datos.
     *
     * @param pedido pedido que se desea registrar
     * @return true si fue guardado correctamente
     */
    public boolean guardar(Pedido pedido) {

        String sql =
                "INSERT INTO pedido (direccion, tipo, estado) "
                        + "VALUES (?, ?, ?)";

        try (
                Connection conn =
                        ConexionBD.obtenerConexion();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    pedido.getDireccionEntrega()
            );

            stmt.setString(
                    2,
                    pedido.getTipo()
            );

            stmt.setString(
                    3,
                    pedido.getEstado().name()
            );

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    /**
     * Obtiene todos los pedidos registrados.
     *
     * @return lista de pedidos
     */
    public List<Pedido> listarTodos() {

        List<Pedido> pedidos =
                new ArrayList<>();

        String sql =
                "SELECT * FROM pedido ORDER BY id";

        try (
                Connection conn =
                        ConexionBD.obtenerConexion();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Pedido pedido =
                        new Pedido(
                                rs.getInt("id"),
                                rs.getString("direccion"),
                                rs.getString("tipo"),
                                EstadoPedido.valueOf(
                                        rs.getString("estado")
                                )
                        );

                pedidos.add(pedido);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return pedidos;
    }

    /**
     * Actualiza el estado de un pedido.
     *
     * @param idPedido identificador del pedido
     * @param estado nuevo estado
     * @return true si el pedido fue actualizado
     */
    public boolean actualizarEstado(
            int idPedido,
            EstadoPedido estado
    ) {

        String sql =
                "UPDATE pedido "
                        + "SET estado = ? "
                        + "WHERE id = ?";

        try (
                Connection conn =
                        ConexionBD.obtenerConexion();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    estado.name()
            );

            stmt.setInt(
                    2,
                    idPedido
            );

            int filasActualizadas =
                    stmt.executeUpdate();

            return filasActualizadas > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}