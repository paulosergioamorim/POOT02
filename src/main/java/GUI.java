import javax.swing.*;
import java.awt.*;

import static java.awt.Color.DARK_GRAY;

public class GUI extends JFrame {
    private final JPanel panel;
    private final Dimension dimension;

    public GUI() {
        super("Jogo da Forca");

        panel = new JPanel();
        panel.setBackground(DARK_GRAY);

        dimension = new Dimension(600,600);

        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setContentPane(panel);
        super.setSize(dimension);
        super.setResizable(false);
    }
}
