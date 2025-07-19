/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.RankInimigo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Éric
 */
public class InimigoFactory {

    public Inimigo criarInimigoAleatorio(int andar) {

        Random rand = new Random();

        int inimigo = rand.nextInt(3) + 1;
        // Posteriormente estaremos usando o andar como base também para definir inimigos mais fortes ou fracos
        // Ou o level do mago, pensando ainda...
        switch (inimigo) {
            case 1:
                return new Inimigo("[D] Slime Ácido", 30, 0, 10, 8, 5, RankInimigo.D);

            case 2:
                return new Inimigo("[D] Morcego da Caverna", 25, 0, 12, 3, 18, RankInimigo.D);

            case 3:
                return new Inimigo("[C] Lobo das Sombras", 40, 0, 15, 4, 15, RankInimigo.C);
        }

        return null;

    }

    public List<Inimigo> criarGrupoDeInimigos(int andar) {
        List<Inimigo> grupo = new ArrayList<>();
        Random rand = new Random();

        // O número de inimigos pode aumentar com o andar
        int numeroDeInimigos = rand.nextInt(2) + 1; // Gera 1 ou 2 inimigos

        for (int i = 0; i < numeroDeInimigos; i++) {
            grupo.add(criarInimigoAleatorio(andar));
        }

        return grupo;

    }
}
