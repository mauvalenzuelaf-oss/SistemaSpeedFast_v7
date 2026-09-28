package dao;

import modelo.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona el acceso a datos de los repartidores.
 */
public class RepartidorDAO {

    /**
     * Guarda un repartidor en la base de datos.
     *
     * @param repartidor repartidor que se desea registrar
     * @return true si fue guardado correctamente
     */
    public boolean guardar(Repartidor repartidor) {

        String sql =
                "INSERT INTO repartidor (nombre) VALUES (?)";

        try (
                Connection conn =
                        ConexionBD.obtenerConexion();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    repartidor.getNombre()
            );

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    /**
     * Obtiene todos los repartidores registrados.
     *
     * @return lista de repartidores
     */
    public List<Repartidor> listarTodos() {

        List<Repartidor> repartidores =
                new ArrayList<>();

        String sql =
                "SELECT * FROM repartidor ORDER BY id";

        try (
                Connection conn =
                        ConexionBD.obtenerConexion();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Repartidor repartidor =
                        new Repartidor(
                                rs.getInt("id"),
                                rs.getString("nombre")
                        );

                repartidores.add(repartidor);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return repartidores;
    }
}
