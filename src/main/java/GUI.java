import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.awt.Color.*;

public class GUI extends JFrame {
    private final JPanel panel;
    private final JLabel labelErros;
    private final JLabel labelLances;
    private final JTextField fieldPalavra;
    private final JTextField fieldLance;
    private final JButton buttonJogar;
    private final JButton buttonNovaTentativa;

    private static final Dimension DIMENSION = new Dimension(600,600);

    private String palavraAtual;
    private String palavraEscondida;
    private StringBuilder lances;

    private List<String> erros;

    public GUI() {
        super("Jogo da Forca");

        panel = new JPanel();
        panel.setBackground(DARK_GRAY);

        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setContentPane(panel);
        super.setSize(DIMENSION);
        super.setResizable(false);

        labelErros = new JLabel();
        labelErros.setPreferredSize(new Dimension(500,50));
        labelErros.setForeground(LIGHT_GRAY);
        labelErros.setFont(new Font("Arial",Font.BOLD,25));

        labelLances = new JLabel();
        labelLances.setPreferredSize(new Dimension(500,50));
        labelLances.setForeground(LIGHT_GRAY);
        labelLances.setFont(new Font("Arial",Font.BOLD,25));

        fieldPalavra = new JTextField(30);
        fieldPalavra.setPreferredSize(new Dimension(500,30));
        fieldPalavra.setFont(new Font("Arial", Font.BOLD, 20));
        fieldPalavra.setEnabled(false);

        fieldLance = new JTextField(10);
        fieldLance.setPreferredSize(new Dimension(50,30));

        buttonJogar = new JButton("Jogar");
        buttonJogar.setBackground(LIGHT_GRAY);
        buttonJogar.setBorderPainted(false);
        buttonJogar.setFocusPainted(false);
        buttonJogar.addActionListener(this::tentativa);

        buttonNovaTentativa = new JButton("Nova Tentativa");

        var lanceLabel = new JLabel("Lance: ");
        lanceLabel.setForeground(LIGHT_GRAY);
        lanceLabel.setFont(new Font("Arial",Font.BOLD,20));

        add(labelErros);
        add(labelLances);
        add(fieldPalavra);
        add(lanceLabel);
        add(fieldLance);
        add(buttonJogar);

        this.startGame();
    }

    private void tentativa(ActionEvent e) {
        var chute = "";
        var chars = palavraAtual.split("");

        try {
            chute = fieldLance.getText().substring(0,1).toUpperCase();
        } catch (Exception ignored) { }

        if (palavraAtual
                .toUpperCase()
                .contains(chute)
        ) {
            var stringBuilder = new StringBuilder(palavraEscondida);

            for (int i = 0; i < chars.length; i++)
                if (chars[i].equalsIgnoreCase(chute)) {
                    stringBuilder.setCharAt(i, chute.charAt(0));
                    if (!lances.toString().contains(chute)) this.addToLances(chute);
                }

            palavraEscondida = stringBuilder.toString();
            fieldPalavra.setText(palavraEscondida);
        } else if (!erros.contains(chute)) erros.add(chute);

        labelErros.setText("Erros: " + erros.size() + " de 6");
        fieldLance.setText(null);

        if (erros.size() == 6) this.startGame();
    }

    private void startGame() {
        palavraAtual = this.getPalavra();
        erros = new ArrayList<>();

        lances = new StringBuilder("Lances: ");

        labelLances.setText(String.valueOf(lances));

        palavraEscondida = palavraAtual
                .replaceAll("\\p{javaLetter}","_")
                .replaceAll("\\s","-");

        labelErros.setText("Erros: " + erros.size() + "de 6");
        fieldPalavra.setText(palavraEscondida);
    }

    private String getPalavra() {
        var random = new Random();
        return Words.LIST.get(random.nextInt(9));
    }

    private void addToLances(String lance) {
        lances.append(lance).append(" ");
        labelLances.setText(lances.toString());
    }
}
