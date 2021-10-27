package cci;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static cdp.Palavras.getRandomPalavra;

public class Logica {
    private String palavra;
    private String palavraEscondida;
    private String lances;
    private List<String> erros;

    public void jogar(JTextField fieldLance) {
        String chute;
        String[] letras = palavra.split("");

        if (!fieldLance.getText().isEmpty()) {
            chute = fieldLance.getText().substring(0, 1).toUpperCase();
            if (palavra.toUpperCase().contains(chute)) {
                StringBuilder s = new StringBuilder(palavraEscondida);
                for (int i = 0; i < letras.length; i++)
                    if (letras[i].equalsIgnoreCase(chute)) {
                        s.setCharAt(i, chute.charAt(0));
                        if (!lances.contains(chute)) this.addLance(chute);
                    }
                palavraEscondida = s.toString();
            } else if (!erros.contains(chute)) erros.add(chute);
            fieldLance.setText(null);
        }

    }

    public void comecar() {
        palavra = getRandomPalavra();
        erros = new ArrayList<>();
        lances = "Lances: ";
        palavraEscondida = palavra
                .replaceAll("\\p{javaLetter}","_")
                .replaceAll("\\s","-");
    }

    public void addLance(String lance) { lances += lance + " "; }

    public String getPalavraEscondida() { return palavraEscondida; }

    public String getLances() { return lances; }

    public List<String> getErros() { return erros; }
}
