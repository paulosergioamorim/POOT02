package ciu;

import cci.Controlador;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.LIGHT_GRAY;

/**
 * @author Paulo Sergio
 * @author Nycolas Monjardim
 * @see <a href="https://github.com/paulosergioamorim/">GitHub</a>
 */

public class GUI extends JFrame {
    private final JLabel labelErros;
    private final JLabel labelLances;
    private final JTextField fieldPalavra;
    private final JTextField fieldLance;

    public GUI(Controlador controlador) {
        super("Jogo da Forca");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(600,600);
        super.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(DARK_GRAY);
        super.setContentPane(panel);

        final Font font = new Font("Arial",Font.BOLD,25);
        final Dimension dimension = new Dimension(500,50);

        labelErros = new JLabel();
        labelErros.setPreferredSize(dimension);
        labelErros.setForeground(LIGHT_GRAY);
        labelErros.setFont(font);

        labelLances = new JLabel();
        labelLances.setPreferredSize(dimension);
        labelLances.setForeground(LIGHT_GRAY);
        labelLances.setFont(font);

        fieldPalavra = new JTextField(25);
        fieldPalavra.setPreferredSize(dimension);
        fieldPalavra.setFont(font);
        fieldPalavra.setEnabled(false);

        fieldLance = new JTextField(5);
        fieldLance.setPreferredSize(new Dimension(50,30));

        JButton buttonJogar = new JButton("Jogar");
        buttonJogar.setBackground(LIGHT_GRAY);
        buttonJogar.setBorderPainted(false);
        buttonJogar.setFocusPainted(false);
        buttonJogar.addActionListener(e -> controlador.jogar(fieldLance));

        JButton buttonNovaTentativa = new JButton("Nova Tentativa");
        buttonNovaTentativa.setBackground(LIGHT_GRAY);
        buttonNovaTentativa.setBorderPainted(false);
        buttonNovaTentativa.setFocusPainted(false);
        buttonNovaTentativa.addActionListener(e -> controlador.comecar());

        JLabel lanceLabel = new JLabel("Lance: ");
        lanceLabel.setForeground(LIGHT_GRAY);
        lanceLabel.setFont(new Font("Arial",Font.BOLD,20));

        add(labelErros);
        add(labelLances);
        add(fieldPalavra);
        add(lanceLabel);
        add(fieldLance);
        add(buttonJogar);
        add(buttonNovaTentativa);
    }

    public void vitoria() { JOptionPane.showMessageDialog(this,"Você venceu!"); }

    public void derrota() { JOptionPane.showMessageDialog(this,"Você perdeu!"); }

    public JLabel getLabelErros() { return labelErros; }

    public JLabel getLabelLances() { return labelLances; }

    public JTextField getFieldPalavra() { return fieldPalavra; }
}
