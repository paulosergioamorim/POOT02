package cdp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Paulo Sergio
 * @author Nycolas Monjardim
 * @see <a href="https://github.com/paulosergioamorim/">GitHub</a>
 */

public class Palavras {
    public final static List<String> LIST =
            new ArrayList<>(
                    List.of(
                            "Instituto Federal do Espírito Santo",
                            "Programação Orientada a Objetos",
                            "Banco de Dados",
                            "Análise e Projeto de Sistemas",
                            "Java",
                            "Python",
                            "Modelo de Entidade e Relacionamento",
                            "Modelo Relacional Normalizado",
                            "Diagrama de Classes",
                            "BrModelo",
                            "NetBeans",
                            "Framework"
                    )
            );

    public static String getRandomPalavra() {
        Random random = new Random();
        int index = random.nextInt(LIST.size()-1);
        return LIST.get(index);
    }
}
