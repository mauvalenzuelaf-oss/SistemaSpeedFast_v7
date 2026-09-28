package vista;

import controlador.ControladorEntregas;

import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

/**
 * Permite asignar un repartidor
 * e iniciar una entrega.
 */
public class VentanaRegistroEntrega extends JFrame {

    // Componentes
    private JComboBox<Pedido> cmbPedido;
    private JComboBox<Repartidor> cmbRepartidor;
    private JButton btnIniciar;

    // Controlador
    private ControladorEntregas controlador;

    /**
     * Construye la ventana de registro de entregas.
     */
    public VentanaRegistroEntrega() {

        controlador =
                new ControladorEntregas();

        setTitle(
                "SpeedFast - Iniciar Entrega"
        );

        setSize(
                450,
                200
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                DISPOSE_ON_CLOSE
        );

        setResizable(false);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel lblPedido =
                new JLabel("Pedido:");

        JLabel lblRepartidor =
                new JLabel("Repartidor:");

        cmbPedido =
                new JComboBox<>();

        cmbRepartidor =
                new JComboBox<>();

        btnIniciar =
                new JButton("Iniciar entrega");

        panel.add(lblPedido);
        panel.add(cmbPedido);

        panel.add(lblRepartidor);
        panel.add(cmbRepartidor);

        panel.add(new JLabel(""));
        panel.add(btnIniciar);

        add(panel);

        cargarDatos();

        btnIniciar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        iniciarEntrega();
                    }
                }
        );
    }

    /**
     * Carga los pedidos pendientes
     * y los repartidores registrados.
     */
    private void cargarDatos() {

        List<Pedido> pedidos =
                controlador.listarPedidosPendientes();

        List<Repartidor> repartidores =
                controlador.listarRepartidores();

        for (Pedido pedido : pedidos) {

            cmbPedido.addItem(pedido);
        }

        for (Repartidor repartidor : repartidores) {

            cmbRepartidor.addItem(repartidor);
        }
    }

    /**
     * Registra la entrega seleccionada.
     */
    private void iniciarEntrega() {

        Pedido pedido =
                (Pedido)
                        cmbPedido.getSelectedItem();

        Repartidor repartidor =
                (Repartidor)
                        cmbRepartidor.getSelectedItem();

        if (pedido == null
                || repartidor == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe existir un pedido pendiente "
                            + "y un repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        boolean iniciado =
                controlador.iniciarEntrega(
                        pedido,
                        repartidor
                );

        if (iniciado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Entrega registrada correctamente."
            );

            cmbPedido.removeItem(pedido);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar la entrega.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
