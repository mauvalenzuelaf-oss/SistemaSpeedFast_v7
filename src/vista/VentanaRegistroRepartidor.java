package vista;

import controlador.ControladorEntregas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Permite registrar repartidores.
 */
public class VentanaRegistroRepartidor extends JFrame {

    // Componentes
    private JTextField txtNombre;
    private JButton btnGuardar;

    // Controlador
    private ControladorEntregas controlador;

    /**
     * Construye la ventana de registro de repartidores.
     */
    public VentanaRegistroRepartidor() {

        controlador =
                new ControladorEntregas();

        setTitle(
                "SpeedFast - Registrar Repartidor"
        );

        setSize(
                380,
                160
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                DISPOSE_ON_CLOSE
        );

        setResizable(false);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
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

        JLabel lblNombre =
                new JLabel("Nombre:");

        txtNombre =
                new JTextField();

        btnGuardar =
                new JButton("Guardar");

        panel.add(lblNombre);
        panel.add(txtNombre);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        guardarRepartidor();
                    }
                }
        );
    }

    /**
     * Valida y registra un repartidor.
     */
    private void guardarRepartidor() {

        String nombre =
                txtNombre
                        .getText()
                        .trim();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un nombre.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        boolean guardado =
                controlador.registrarRepartidor(
                        nombre
                );

        if (guardado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor guardado correctamente."
            );

            txtNombre.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo guardar el repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
