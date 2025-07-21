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
        RankInimigo rankSorteado = sortearRank(andar);
        Inimigo inimigo = null;
        switch (rankSorteado) {
            case D:
                inimigo = criarMonstroRankD();
                break;
            case C:
                inimigo = criarMonstroRankC();
                break;
            case B:
                inimigo = criarMonstroRankB();

        }
        definirRecompensar(inimigo);
        aplicarEscalamento(inimigo, andar);

        return inimigo;

    }

    private void definirRecompensar(Inimigo inimigo) {
        switch (inimigo.getRank()) {
            case S:
                inimigo.setRecompensaXp(200);
                inimigo.setRecompensaOuro(60);
                break;
            case A:
                inimigo.setRecompensaXp(140);
                inimigo.setRecompensaOuro(40);
                break;
            case B:
                inimigo.setRecompensaXp(100);
                inimigo.setRecompensaOuro(25);
                break;
            case C:
                inimigo.setRecompensaXp(75);
                inimigo.setRecompensaOuro(15);
                break;
            case D:
            default:
                inimigo.setRecompensaXp(50);
                inimigo.setRecompensaOuro(10);
                break;
        }
    }
    //TODO: rever esse codigo pois não foi bem feito.

    private void aplicarEscalamento(Inimigo inimigo, int andar) {
        double fatorEscalaHp;
        double fatorEscalaAtk;
        switch (inimigo.getRank()) {
            case C:
                fatorEscalaHp = 1.3;
                fatorEscalaAtk = 1.1;
                break;
            case D:
            default:
                fatorEscalaHp = 0.8;
                fatorEscalaAtk = 1.0;
                break;
        }

        int hpBonus = (int) (andar * 5 * fatorEscalaHp);
        int atkBonus = (int) (andar * 2 * fatorEscalaAtk);

        inimigo.setMaxHp(inimigo.getMaxHp() + hpBonus);
        inimigo.setHp(inimigo.getMaxHp());
        inimigo.setAtk(inimigo.getAtk() + atkBonus);
    }

    private RankInimigo sortearRank(int andar) {
        int chance = rand.nextInt(100);
        if (andar < 3) {
            return RankInimigo.D;
        } else if (andar < 7) {
            return (chance < 70) ? RankInimigo.D : RankInimigo.C;
        } else if (andar < 14) {
            return (chance < 40) ? RankInimigo.D : RankInimigo.C;
        } else if (andar < 20) {
            return (chance < 20) ? RankInimigo.D : RankInimigo.C;
        } else if (andar < 24) {
            return (chance < 70) ? RankInimigo.C : RankInimigo.B;
        } else if (andar < 27) {
            return (chance < 40) ? RankInimigo.C : RankInimigo.B;
        } else if (andar < 30) {
            return (chance < 20) ? RankInimigo.C : RankInimigo.B;
        } else {
            return (chance < 70) ? RankInimigo.B : RankInimigo.A;
        }

    }

    private Inimigo criarMonstroRankD() {
        int numMonstro = rand.nextInt(2) + 1;
        switch (numMonstro) {
            case 1:
                Inimigo slime = new Inimigo("Slime Ácido", 50, 0, 10, 8, 5, RankInimigo.D, compAleatorio);
                slime.aprenderHabilidade(new Magia("Cuspe Ácido", "Cospe um ácido que corrói", 0, TipoDeEfeito.DANO_DIRETO, 10, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM));
                return slime;
            case 2:
                Inimigo morcego = new Inimigo("Morcego da Caverna", 25, 0, 12, 3, 18, RankInimigo.D, compAleatorio);
                morcego.aprenderHabilidade(new Magia("Mordida", "Mordida que aplica dano", 0, TipoDeEfeito.DANO_DIRETO, 12, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM));
                return morcego;
        }

        return null;
    }

    private Inimigo criarMonstroRankC() {
        Inimigo lobo = new Inimigo("Lobo das Sombras", 40, 0, 15, 4, 15, RankInimigo.C, compBerserker);
        lobo.aprenderHabilidade(new Magia("Garra Dilacerante", "Golpe com as garras que causa sangramento", 0, TipoDeEfeito.DANO_POR_TURNO, 5, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.SANGRAMENTO));
        lobo.aprenderHabilidade(new Magia("Enfurecer", "Se enfurece e aumenta o ATK", 0, TipoDeEfeito.BUFF_ATAQUE, 10, 3, TipoAlvo.ALIADO, NomeEfeito.ENFURECIDO));
        return lobo;

    }

    private Inimigo criarMonstroRankB() {
        int numMonstro = rand.nextInt(2) + 1;
        switch (numMonstro) {
            case 1:
                Inimigo zumbi = new Inimigo("Zumbi", 70, 0, 24, 2, 10, RankInimigo.B, compSequencial);
                zumbi.aprenderHabilidade(new Magia("Mordida infecciosa", "Corre em direção ao alvo e morde, infectando-o", 0, TipoDeEfeito.DANO_POR_TURNO, 7, 5, TipoAlvo.ALVO_UNICO, NomeEfeito.INFECTADO));
                zumbi.aprenderHabilidade(new Magia("Corredor", "Aumenta a AGILIDADE", 0, TipoDeEfeito.BUFF_AGILIDADE, 10, 2, TipoAlvo.ALIADO, NomeEfeito.AGIL));
                zumbi.aprenderHabilidade(new Magia("Vomito acido", "Vomita a frente dando dano a todos os alvos", 0, TipoDeEfeito.DANO_DIRETO, 20, 0, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.INFECTADO));
                return zumbi;
            case 2:
                Inimigo cavaleiroZumbi = new Inimigo("Cavaleiro Fantasma", 120, 0, 20, 15, 5, RankInimigo.B, compSequencial); 
                cavaleiroZumbi.aprenderHabilidade(new Magia("Lâmina Espectral", "Um corte sombrio que causa dano e tem chance de aplicar medo", 0, TipoDeEfeito.DANO_DIRETO, 25, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.AMENDONTRAR));
                cavaleiroZumbi.aprenderHabilidade(new Magia("Aura Sombria", "Envolve-se em sombras, reduzindo o dano recebido por 2 turnos", 0, TipoDeEfeito.BUFF_DEFESA, 15, 2, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)); 
                cavaleiroZumbi.aprenderHabilidade(new Magia("Chamado dos Condenados", "Invoca almas perdidas que causam dano em todos os inimigos", 0, TipoDeEfeito.DANO_DIRETO, 20, 5, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM)); 
                return cavaleiroZumbi;

                
        }
        return null;
    }

    public List<Inimigo> criarGrupoDeInimigos(int andar) {
        List<Inimigo> grupo = new ArrayList<>();
        int numeroDeInimigos = rand.nextInt(2) + 1;
        for (int i
                = 0; i < numeroDeInimigos; i++) {
            grupo.add(criarInimigoAleatorio(andar));
        }
        return grupo;
    }
}
