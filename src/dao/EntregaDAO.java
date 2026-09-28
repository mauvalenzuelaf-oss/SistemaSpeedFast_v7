package dao;

import modelo.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Gestiona el acceso a datos de las entregas.
 */
public class EntregaDAO {

    /**
     * Guarda una entrega en la base de datos.
     *
     * @param entrega entrega que se desea registrar
     * @return true si fue guardada correctamente
     */
    public boolean guardar(Entrega entrega) {

        String sql =
                "INSERT INTO entrega "
                        + "(id_pedido, id_repartidor, fecha, hora) "
                        + "VALUES (?, ?, ?, ?)";

        try (
                Connection conn =
                        ConexionBD.obtenerConexion();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    entrega.getIdPedido()
            );

            stmt.setInt(
                    2,
                    entrega.getIdRepartidor()
            );

            stmt.setDate(
                    3,
                    entrega.getFecha()
            );

            stmt.setTime(
                    4,
                    entrega.getHora()
            );

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}