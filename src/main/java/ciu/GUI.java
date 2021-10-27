package ciu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static cdp.Palavras.getRandomPalavra;
import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.LIGHT_GRAY;

public class GUI extends JFrame {
    private final JLabel labelErros;
    private final JLabel labelLances;
    private final JTextField fieldPalavra;
    private final JTextField fieldLance;

    private String palavra;
    private String palavraEscondida;
    private String lances;
    private List<String> erros;

    public GUI() {
        super("Jogo da Forca");

        JPanel panel = new JPanel();
        panel.setBackground(DARK_GRAY);

        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setContentPane(panel);
        super.setSize(600,600);
        super.setResizable(false);

        labelErros = new JLabel();
        labelErros.setPreferredSize(new Dimension(500,30));
        labelErros.setForeground(LIGHT_GRAY);
        labelErros.setFont(new Font("Arial",Font.BOLD,25));

        labelLances = new JLabel();
        labelLances.setPreferredSize(new Dimension(500,30));
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
        buttonJogar.addActionListener(this::tentativa);

        JButton buttonNovaTentativa = new JButton("Nova Tentativa");
        buttonNovaTentativa.setBackground(LIGHT_GRAY);
        buttonNovaTentativa.setBorderPainted(false);
        buttonNovaTentativa.setFocusPainted(false);
        buttonNovaTentativa.addActionListener(e -> this.start());

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

        this.start();
    }

    private void tentativa(ActionEvent e) {
        String chute;
        String[] chars = palavra.split("");

        if (!fieldLance.getText().isEmpty())
            chute = fieldLance.getText().substring(0, 1).toUpperCase();
        else return;

        if (palavra.toUpperCase().contains(chute)) {
            StringBuilder s = new StringBuilder(palavraEscondida);
            for (int i = 0; i < chars.length; i++)
                if (chars[i].equalsIgnoreCase(chute)) {
                    s.setCharAt(i, chute.charAt(0));
                    if (!lances.contains(chute)) this.addLance(chute);
                }
            palavraEscondida = s.toString();
            fieldPalavra.setText(palavraEscondida);
        } else if (!erros.contains(chute)) erros.add(chute);

        labelErros.setText("Erros: " + erros.size() + " de 6");
        labelLances.setText(lances);
        fieldLance.setText(null);

        if (erros.size() == 6) {
            Object[] options = new Object[] {"Sair", "Recomeçar"};
            int i = JOptionPane.showOptionDialog(
                    this,"Você perdeu!",null,JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,null,options,null
            );
            switch (i) {
                case 0 : this.dispose(); break;
                case 1 : this.start(); break;
            }
        } // se perder

        if (palavraEscondida.replace("-", " ").equalsIgnoreCase(palavra)) {
            Object[] options = new Object[] {"Sair", "Recomeçar"};
            int i = JOptionPane.showOptionDialog(
                    this,"Você venceu!",null,JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,null,options,null
            );
            switch (i) {
                case 0 : this.dispose(); break;
                case 1 : this.start(); break;
            }
        } // se ganhar
    }

    private void start() {
        palavra = getRandomPalavra();
        erros = new ArrayList<>();

        lances = "Lances: ";
        labelLances.setText(lances);
        labelErros.setText("Erros: " + erros.size() + " de 6");

        palavraEscondida = palavra
                .replaceAll("\\p{javaLetter}","_")
                .replaceAll("\\s","-");
        fieldPalavra.setText(palavraEscondida);
    }

    private void addLance(String lance) { lances += lance + " "; }
}
