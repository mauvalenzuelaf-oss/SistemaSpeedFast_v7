package vista;

import controlador.ControladorPedidos;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

/**
 * Permite visualizar los pedidos almacenados
 * en la base de datos.
 */
public class VentanaListaPedidos extends JFrame {

    // Componentes
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;

    // Controlador
    private ControladorPedidos controlador;

    /**
     * Construye la ventana de listado de pedidos.
     */
    public VentanaListaPedidos() {

        controlador =
                new ControladorPedidos();

        setTitle(
                "SpeedFast - Lista de Pedidos"
        );

        setSize(
                650,
                350
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        String[] columnas = {
                "ID",
                "Dirección",
                "Tipo",
                "Estado"
        };

        modeloTabla =
                new DefaultTableModel(
                        columnas,
                        0
                );

        tablaPedidos =
                new JTable(modeloTabla);

        JScrollPane scrollPane =
                new JScrollPane(tablaPedidos);

        btnActualizar =
                new JButton("Actualizar");

        JPanel panelBoton =
                new JPanel();

        panelBoton.add(btnActualizar);

        add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                panelBoton,
                BorderLayout.SOUTH
        );

        cargarPedidos();

        btnActualizar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        cargarPedidos();
                    }
                }
        );
    }

    /**
     * Consulta los pedidos y actualiza la tabla.
     */
    private void cargarPedidos() {

        modeloTabla.setRowCount(0);

        List<Pedido> pedidos =
                controlador.listarPedidos();

        for (Pedido pedido : pedidos) {

            Object[] fila = {
                    pedido.getId(),
                    pedido.getDireccionEntrega(),
                    pedido.getTipo(),
                    pedido.getEstado()
            };

            modeloTabla.addRow(fila);
        }
    }
}
