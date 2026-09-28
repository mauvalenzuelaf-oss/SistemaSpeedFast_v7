package vista;

import controlador.ControladorPedidos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Permite registrar pedidos.
 */
public class VentanaRegistroPedido extends JFrame {

    // Componentes
    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JButton btnGuardar;

    // Controlador
    private ControladorPedidos controlador;

    /**
     * Construye la ventana de registro de pedidos.
     */
    public VentanaRegistroPedido() {

        controlador =
                new ControladorPedidos();

        setTitle(
                "SpeedFast - Registrar Pedido"
        );

        setSize(
                400,
                220
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

        JLabel lblDireccion =
                new JLabel("Dirección:");

        JLabel lblTipo =
                new JLabel("Tipo:");

        txtDireccion =
                new JTextField();

        String[] tipos = {
                "COMIDA",
                "ENCOMIENDA",
                "EXPRESS"
        };

        cmbTipo =
                new JComboBox<>(tipos);

        btnGuardar =
                new JButton("Guardar");

        panel.add(lblDireccion);
        panel.add(txtDireccion);

        panel.add(lblTipo);
        panel.add(cmbTipo);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        guardarPedido();
                    }
                }
        );
    }

    /**
     * Valida y registra un pedido.
     */
    private void guardarPedido() {

        String direccion =
                txtDireccion
                        .getText()
                        .trim();

        if (direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar una dirección.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String tipo =
                cmbTipo
                        .getSelectedItem()
                        .toString();

        boolean guardado =
                controlador.registrarPedido(
                        direccion,
                        tipo
                );

        if (guardado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido guardado correctamente."
            );

            txtDireccion.setText("");

            cmbTipo.setSelectedIndex(0);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo guardar el pedido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
