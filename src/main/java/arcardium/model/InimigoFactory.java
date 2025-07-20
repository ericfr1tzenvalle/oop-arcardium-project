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
 * Responsável por criar e configurar todos os inimigos do jogo. Age como um
 * "painel de controle" central para o balanceamento dos monstros.
 */
public class InimigoFactory {

    private final Comportamento compAleatorio = new ComportamentoAleatorio();
    private final Comportamento compBerserker = new ComportamentoBerserker();
    private final Comportamento compSequencial = new ComportamentoSequencial();
    private final Random rand = new Random();

    public Inimigo criarInimigoAleatorio(int andar) {
        String nome;
        int hpBase, mpBase, atkBase, defBase, agiBase;
        int recompensaXp, recompensaOuro;
        RankInimigo rank;
        Comportamento comportamento;
        List<Magia> habilidades = new ArrayList<>();

        int numIni = rand.nextInt(3) + 1;
        switch (numIni) {
            case 1: 
                nome = "Slime Ácido";
                hpBase = 30;
                mpBase = 0;
                atkBase = 10;
                defBase = 8;
                agiBase = 5;
                rank = RankInimigo.D;
                comportamento = compAleatorio;
                recompensaXp = 50;
                recompensaOuro = 10;
                habilidades.add(new Magia("Cuspe Ácido", "Cospe um ácido que corrói", 0, TipoDeEfeito.DANO_DIRETO, 10, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM));
                break;

            case 2: 
                nome = "Morcego da Caverna";
                hpBase = 25;
                mpBase = 0;
                atkBase = 12;
                defBase = 3;
                agiBase = 18;
                rank = RankInimigo.D;
                comportamento = compAleatorio;
                recompensaXp = 60;
                recompensaOuro = 12;
                habilidades.add(new Magia("Mordida", "Mordida que aplica dano", 0, TipoDeEfeito.DANO_DIRETO, 12, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM));
                break;

            case 3: 
            default: 
                nome = "Lobo das Sombras";
                hpBase = 40;
                mpBase = 0;
                atkBase = 15;
                defBase = 4;
                agiBase = 15;
                rank = RankInimigo.C;
                comportamento = compBerserker;
                recompensaXp = 75;
                recompensaOuro = 20;
                habilidades.add(new Magia("Garra Dilacerante", "Golpe com as garras que causa sangramento", 0, TipoDeEfeito.DANO_POR_TURNO, 5, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.SANGRAMENTO));
                habilidades.add(new Magia("Enfurecer", "Se enfurece e aumenta o ATK", 0, TipoDeEfeito.BUFF_ATAQUE, 10, 3, TipoAlvo.ALIADO, NomeEfeito.ENFURECIDO));
                break;
        }

        int hpFinal = hpBase + (andar * 5); // Cada andar adiciona +5 HP
        int atkFinal = atkBase + (andar * 2); // Cada andar adiciona +2 ATK
        int xpFinal = recompensaXp + (andar * 10); // Recompensa escala com o andar
        int ouroFinal = recompensaOuro + (andar * 3);

        Inimigo inimigo = new Inimigo(nome, hpFinal, mpBase, atkFinal, defBase, agiBase, rank, comportamento, xpFinal, ouroFinal);

        for (Magia habilidade : habilidades) {
            inimigo.aprenderHabilidade(habilidade);
        }

        return inimigo;
    }

    public List<Inimigo> criarGrupoDeInimigos(int andar) {
        List<Inimigo> grupo = new ArrayList<>();
        int numeroDeInimigos = rand.nextInt(2) + 1;
        for (int i = 0; i < numeroDeInimigos; i++) {
            grupo.add(criarInimigoAleatorio(andar));
        }
        return grupo;
    }
}
