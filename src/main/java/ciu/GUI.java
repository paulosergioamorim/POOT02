package ciu;

import cci.Controlador;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.LIGHT_GRAY;

public class GUI extends JFrame {
    private final JLabel labelErros;
    private final JLabel labelLances;
    private final JTextField fieldPalavra;
    private final JTextField fieldLance;

    public GUI(Controlador controlador) {
        super("Jogo da Forca");

        JPanel panel = new JPanel();
        panel.setBackground(DARK_GRAY);

        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setContentPane(panel);
        super.setSize(600,600);
        super.setResizable(false);

        labelErros = new JLabel();
        labelErros.setPreferredSize(new Dimension(500,80));
        labelErros.setForeground(LIGHT_GRAY);
        labelErros.setFont(new Font("Arial",Font.BOLD,25));

        labelLances = new JLabel();
        labelLances.setPreferredSize(new Dimension(500,80));
        labelLances.setForeground(LIGHT_GRAY);
        labelLances.setFont(new Font("Arial",Font.BOLD,25));

        fieldPalavra = new JTextField(30);
        fieldPalavra.setPreferredSize(new Dimension(500,30));
        fieldPalavra.setFont(new Font("Arial", Font.BOLD, 20));
        fieldPalavra.setEnabled(false);

        fieldLance = new JTextField(10);

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

    public JLabel getLabelErros() { return labelErros; }

    public JLabel getLabelLances() { return labelLances; }

    public JTextField getFieldPalavra() { return fieldPalavra; }
}
