package arcardium.model;

import arcardium.model.enums.*;
import arcardium.model.ia.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InimigoFactory {

    private final Comportamento compAleatorio = new ComportamentoAleatorio();
    private final Comportamento compBerserker = new ComportamentoBerserker();
    private final Comportamento compSequencial = new ComportamentoSequencial();
    private final Random rand = new Random();

    // Multiplicadores por rank (ajustados)
    private final double MULT_HP_S = 2.5, MULT_ATK_S = 2.0;
    private final double MULT_HP_A = 2.0, MULT_ATK_A = 1.7;
    private final double MULT_HP_B = 1.5, MULT_ATK_B = 1.3;
    private final double MULT_HP_C = 1.2, MULT_ATK_C = 1.1;
    private final double MULT_HP_D = 1.0, MULT_ATK_D = 1.0;

    public Inimigo criarInimigoAleatorio(int andar) {
        RankInimigo rankSorteado = sortearRank(andar);
        Inimigo inimigo;

        switch (rankSorteado) {
            case D ->
                inimigo = criarMonstroRankD();
            case C ->
                inimigo = criarMonstroRankC();
            case B ->
                inimigo = criarMonstroRankB();
            default ->
                inimigo = criarMonstroRankD();
        }

        aplicarEscalamento(inimigo, andar);
        definirRecompensas(inimigo, andar);

        return inimigo;
    }

    // XP, ouro e pontos agora são escalados
    private void definirRecompensas(Inimigo inimigo, int andar) {
        int baseXp;
        int baseGold;
        int basePontos;

        switch (inimigo.getRank()) {
            case S -> {
                baseXp = 200;
                baseGold = 60;
                basePontos = 1000;
            }
            case A -> {
                baseXp = 140;
                baseGold = 40;
                basePontos = 700;
            }
            case B -> {
                baseXp = 100;
                baseGold = 25;
                basePontos = 400;
            }
            case C -> {
                baseXp = 75;
                baseGold = 15;
                basePontos = 250;
            }
            default -> {
                baseXp = 50;
                baseGold = 10;
                basePontos = 120;
            }
        }

        int xpEscalado = baseXp + (int) (andar * baseXp * 0.15);
        int ouroEscalado = baseGold + (int) (andar * baseGold * 0.12);
        int pontosEscalados = basePontos
                + (int) (andar * basePontos * 0.20)
                + inimigo.getMaxHp() / 2
                + inimigo.getAtk() * 5;

        inimigo.setRecompensaXp(xpEscalado);
        inimigo.setRecompensaOuro(ouroEscalado);
        inimigo.setPontos(pontosEscalados);
    }

    private void aplicarEscalamento(Inimigo inimigo, int andar) {
        double multHp;
        double multAtk;

        switch (inimigo.getRank()) {
            case S -> {
                multHp = MULT_HP_S;
                multAtk = MULT_ATK_S;
            }
            case A -> {
                multHp = MULT_HP_A;
                multAtk = MULT_ATK_A;
            }
            case B -> {
                multHp = MULT_HP_B;
                multAtk = MULT_ATK_B;
            }
            case C -> {
                multHp = MULT_HP_C;
                multAtk = MULT_ATK_C;
            }
            default -> {
                multHp = MULT_HP_D;
                multAtk = MULT_ATK_D;
            }
        }

        // Identifica chefes para ajustar escalonamento
        boolean isChefe = inimigo.getNome().contains("Durak");

        double fatorAndar = isChefe ? 0.04 : 0.08;   // chefes escalam mais devagar
        double fatorAtk = isChefe ? 0.035 : 0.07;

        int hpEscalado = (int) (inimigo.getMaxHp() * (1 + (andar * fatorAndar)) * multHp);
        int atkEscalado = (int) (inimigo.getAtk() * (1 + (andar * fatorAtk)) * multAtk);

        // Limita chefes em andares baixos para não explodir status
        if (isChefe && hpEscalado > 350) {
            hpEscalado = 350;
        }

        inimigo.setMaxHp(hpEscalado);
        inimigo.setHp(hpEscalado);
        inimigo.setAtk(atkEscalado);
    }

    private RankInimigo sortearRank(int andar) {
        int chance = rand.nextInt(100);

        if (andar < 3) {
            return RankInimigo.D;
        }
        if (andar < 5) {
            return (chance < 70) ? RankInimigo.D : RankInimigo.C;
        }
        if (andar < 10) {
            return (chance < 40) ? RankInimigo.D : RankInimigo.C;
        }
        if (andar < 15) {
            return (chance < 70) ? RankInimigo.C : RankInimigo.B;
        }
        if (andar < 27) {
            return (chance < 50) ? RankInimigo.C : RankInimigo.B;
        }
        if (andar < 35) {
            return (chance < 20) ? RankInimigo.C : RankInimigo.B;
        }
        return (chance < 70) ? RankInimigo.B : RankInimigo.A;
    }

    // ===================== Inimigos Rank D =====================
    private Inimigo criarMonstroRankD() {
        int numMonstro = rand.nextInt(3);
        switch (numMonstro) {
            case 0 -> {
                Inimigo slime = new Inimigo("Slime Ácido", 50, 0, 10, 8, 5, 5, 0, RankInimigo.D, compAleatorio);
                slime.aprenderHabilidade(new Magia(
                        "Cuspe Ácido",
                        "cospe ácido corrosivo",
                        0,
                        List.of(
                                new Efeito(TipoDeEfeito.DANO_DIRETO, 10, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)
                        ),
                        List.of(TagMagia.DANO)
                ));
                return slime;
            }
            case 1 -> {
                Inimigo morcego = new Inimigo("Morcego da Caverna", 25, 0, 12, 3, 18, 10, 20, RankInimigo.D, compAleatorio);
                morcego.aprenderHabilidade(new Magia(
                        "Mordida",
                        "morde o alvo",
                        0,
                        List.of(
                                new Efeito(TipoDeEfeito.DANO_DIRETO, 12, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)
                        ),
                        List.of(TagMagia.DANO)
                ));
                return morcego;
            }
            default -> {
                Inimigo aranha = new Inimigo("Aranha da Cripta", 22, 0, 10, 4, 16, 10, 20, RankInimigo.D, compAleatorio);
                aranha.aprenderHabilidade(new Magia(
                        "Picada Venenosa",
                        "injeta veneno no alvo",
                        0,
                        List.of(
                                new Efeito(TipoDeEfeito.DANO_POR_TURNO, 4, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.VENENO)
                        ),
                        List.of(TagMagia.DANO, TagMagia.VENENO)
                ));
                return aranha;
            }

        }
    }

    // ===================== Inimigos Rank C =====================
    private Inimigo criarMonstroRankC() {
        Inimigo lobo = new Inimigo("Lobo das Sombras", 40, 0, 15, 4, 15, 15, 25, RankInimigo.C, compBerserker);
        lobo.aprenderHabilidade(new Magia("Garra Dilacerante", "golpeando com sangramento", 0,
                List.of(new Efeito(TipoDeEfeito.DANO_POR_TURNO, 5, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.SANGRAMENTO)),
                List.of(TagMagia.DANO)
        ));
        lobo.aprenderHabilidade(new Magia("Enfurecer", "aumentando o ATK", 0,
                List.of(new Efeito(TipoDeEfeito.BUFF_ATAQUE, 10, 3, TipoAlvo.ALIADO, NomeEfeito.ENFURECIDO)),
                List.of(TagMagia.BUFF)
        ));
        return lobo;
    }

// ===================== Inimigos Rank B =====================
    private Inimigo criarMonstroRankB() {
        int numMonstro = rand.nextInt(3);
        switch (numMonstro) {
            case 0 -> {
                Inimigo fantasma = new Inimigo("Fantasma", 80, 0, 22, 12, 8, 5, 20, RankInimigo.B, compSequencial);
                fantasma.aprenderHabilidade(new Magia("Susto Fantasmagórico", "diminuindo DEF dos inimigos", 0,
                        List.of(new Efeito(TipoDeEfeito.DEBUFF_DEFESA, 20, 3, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.AMENDONTRAR)),
                        List.of(TagMagia.DEBUFF)
                ));
                fantasma.aprenderHabilidade(new Magia("Toque Espectral", "drenando vida por turno", 0,
                        List.of(new Efeito(TipoDeEfeito.DANO_POR_TURNO, 10, 4, TipoAlvo.ALVO_UNICO, NomeEfeito.DEMENTAR)),
                        List.of(TagMagia.DANO)
                ));
                return fantasma;
            }
            case 1 -> {
                Inimigo zumbi = new Inimigo("Zumbi", 70, 0, 24, 2, 10, 0, 0, RankInimigo.B, compSequencial);
                zumbi.aprenderHabilidade(new Magia("Mordida Infecciosa", "causando veneno", 0,
                        List.of(new Efeito(TipoDeEfeito.DANO_POR_TURNO, 7, 5, TipoAlvo.ALVO_UNICO, NomeEfeito.INFECTADO)),
                        List.of(TagMagia.VENENO)
                ));
                zumbi.aprenderHabilidade(new Magia("Vômito Ácido", "atingindo todos com dano em área", 0,
                        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 20, 0, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM)),
                        List.of(TagMagia.AREA, TagMagia.DANO)
                ));
                return zumbi;
            }
            default -> {
                Inimigo cavaleiro = new Inimigo("Cavaleiro Fantasma", 120, 0, 20, 15, 5, 10, 5, RankInimigo.B, compSequencial);
                cavaleiro.aprenderHabilidade(new Magia("Lâmina Espectral", "cortando com energia sombria", 0,
                        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 25, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.AMENDONTRAR)),
                        List.of(TagMagia.DANO)
                ));
                cavaleiro.aprenderHabilidade(new Magia("Aura Sombria", "aumentando DEF", 0,
                        List.of(new Efeito(TipoDeEfeito.BUFF_DEFESA, 15, 2, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)),
                        List.of(TagMagia.BUFF)
                ));
                return cavaleiro;
            }
        }
    }

    // ===================== Chefe =====================
    public List<Inimigo> criarChefe(int ato, int andar) {
        List<Inimigo> inimigos = new ArrayList<>();

        if (ato == 1) {
            Inimigo chefe = new Inimigo("Durak, o Orc Superior", 200, 0, 25, 20, 12, 30, 0, RankInimigo.B, compSequencial);
            aplicarEscalamento(chefe, andar);
            definirRecompensas(chefe, andar);

            Magia fatiar = new Magia("Fatiar", "desferindo um ataque brutal", 0,
                    List.of(
                            new Efeito(TipoDeEfeito.DANO_DIRETO, 40, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)
                    ),
                    List.of(TagMagia.CHEFE, TagMagia.DANO)
            );
            chefe.aprenderHabilidade(fatiar);

            Magia cortar = new Magia("Cortar", "causando sangramento contínuo", 0,
                    List.of(
                            new Efeito(TipoDeEfeito.DANO_POR_TURNO, 20, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.SANGRAMENTO)
                    ),
                    List.of(TagMagia.CHEFE, TagMagia.DANO)
            );
            chefe.aprenderHabilidade(cortar);

            Magia esgotamento = new Magia("Esgotamento", "reforçando sua defesa", 0,
                    List.of(
                            new Efeito(TipoDeEfeito.BUFF_DEFESA, 0, 1, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)
                    ),
                    List.of(TagMagia.CHEFE, TagMagia.BUFF)
            );
            chefe.aprenderHabilidade(esgotamento);

            inimigos.add(chefe);
        }

        return inimigos;
    }

    // ===================== Grupo =====================
    public List<Inimigo> criarGrupoDeInimigos(int andar) {
        List<Inimigo> grupo = new ArrayList<>();
        int numeroDeInimigos = rand.nextInt(2) + 1;
        for (int i = 0; i < numeroDeInimigos; i++) {
            grupo.add(criarInimigoAleatorio(andar));
        }
        return grupo;
    }
}
