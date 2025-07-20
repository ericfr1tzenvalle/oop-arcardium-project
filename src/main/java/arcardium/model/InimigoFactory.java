/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.RankInimigo;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
import arcardium.model.ia.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Éric
 */
public class InimigoFactory {

    public Inimigo criarInimigoAleatorio(int andar) {
        Comportamento CPA = new ComportamentoAleatorio();
        Comportamento CPS = new ComportamentoSequencial();
        Comportamento CPB = new ComportamentoBerserker();
        Inimigo inimigo;

        Random rand = new Random();

        int numIni = rand.nextInt(3) + 1;
        switch (numIni) {
            case 1:
                inimigo = new Inimigo("Slime Ácido", 30, 0, 10, 8, 5, RankInimigo.D, CPA);
                Magia guspeAcido = new Magia("Guspe acido","Gospe um acido que corroi",0,TipoDeEfeito.DANO_DIRETO,20,1,TipoAlvo.ALVO_UNICO,NomeEfeito.NENHUM);
                inimigo.aprenderHabilidade(guspeAcido);
                return inimigo;
                

            case 2:
                inimigo =  new Inimigo("Morcego da Caverna", 25, 0, 12, 3, 18, RankInimigo.D, CPA);
                Magia mordida = new Magia("Mordida", "Mordida que aplica dano", 0, TipoDeEfeito.DANO_DIRETO, 10, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM);
                inimigo.aprenderHabilidade(mordida);
                return inimigo;

            case 3:
                inimigo =  new Inimigo("Lobo das Sombras", 40, 0, 15, 4, 15, RankInimigo.C, CPB);
                Magia garraDupla = new Magia("Garra Dupla", "Duplo golpe com as garras",0,TipoDeEfeito.DANO_POR_TURNO,10,4,TipoAlvo.ALVO_UNICO,NomeEfeito.SANGRAMENTO);
                Magia enfurecer = new Magia("Enfurecer", "Se enfurece e aumenta o ATK", 0, TipoDeEfeito.BUFF_ATAQUE, 15, 2, TipoAlvo.ALIADO,NomeEfeito.ENFURECIDO);
                inimigo.aprenderHabilidade(garraDupla);
                inimigo.aprenderHabilidade(enfurecer);
                return inimigo;
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
