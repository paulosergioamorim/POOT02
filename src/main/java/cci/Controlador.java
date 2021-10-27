package cci;

import ciu.GUI;

import javax.swing.*;

/**
 * @author Paulo Sergio
 * @author Nycolas Monjardim
 */

public class Controlador {
    GUI frame;
    Logica logica;

    public Controlador() {
        frame = new GUI(this);
        logica = new Logica();
        this.comecar();
    }

    public void jogar(JTextField fieldLance) {
        logica.jogar(fieldLance);
        this.atualizar();
    }

    public void comecar() {
        frame.setVisible(true);
        logica.comecar();
        this.atualizar();
    }

    public void atualizar() {
        frame.getLabelErros().setText("Erros: " + logica.getErros().size() + " de 6");
        frame.getLabelLances().setText(logica.getLances());
        frame.getFieldPalavra().setText(logica.getPalavraEscondida());

        if (logica.getErros().size() == 6
        || !logica.getPalavraEscondida().contains("_"))
            this.comecar();
    }

    public static void main(String[] args) {
        new Controlador();
    }
}
