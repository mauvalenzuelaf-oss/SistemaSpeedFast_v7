package vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal del sistema SpeedFast.
 */
public class VentanaPrincipal extends JFrame {

    // Botones
    private JButton btnRegistrarPedido;
    private JButton btnRegistrarRepartidor;
    private JButton btnListarPedidos;
    private JButton btnIniciarEntrega;

    /**
     * Construye la ventana principal.
     */
    public VentanaPrincipal() {

        setTitle(
                "SpeedFast - Gestión de Pedidos"
        );

        setSize(
                480,
                360
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                EXIT_ON_CLOSE
        );

        setResizable(false);

        setLayout(
                new BorderLayout()
        );

        JLabel lblTitulo =
                new JLabel(
                        "Sistema SpeedFast",
                        SwingConstants.CENTER
                );

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        btnRegistrarPedido =
                new JButton(
                        "Registrar pedido"
                );

        btnRegistrarRepartidor =
                new JButton(
                        "Registrar repartidor"
                );

        btnListarPedidos =
                new JButton(
                        "Listar pedidos"
                );

        btnIniciarEntrega =
                new JButton(
                        "Asignar repartidor / Iniciar entrega"
                );

        JPanel panelBotones =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                10,
                                10
                        )
                );

        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        30,
                        50
                )
        );

        panelBotones.add(
                btnRegistrarPedido
        );

        panelBotones.add(
                btnRegistrarRepartidor
        );

        panelBotones.add(
                btnListarPedidos
        );

        panelBotones.add(
                btnIniciarEntrega
        );

        add(
                lblTitulo,
                BorderLayout.NORTH
        );

        add(
                panelBotones,
                BorderLayout.CENTER
        );

        btnRegistrarPedido.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        VentanaRegistroPedido ventana =
                                new VentanaRegistroPedido();

                        ventana.setVisible(true);
                    }
                }
        );

        btnRegistrarRepartidor.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        VentanaRegistroRepartidor ventana =
                                new VentanaRegistroRepartidor();

                        ventana.setVisible(true);
                    }
                }
        );

        btnListarPedidos.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        VentanaListaPedidos ventana =
                                new VentanaListaPedidos();

                        ventana.setVisible(true);
                    }
                }
        );

        btnIniciarEntrega.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        VentanaRegistroEntrega ventana =
                                new VentanaRegistroEntrega();

                        ventana.setVisible(true);
                    }
                }
        );
    }
}
