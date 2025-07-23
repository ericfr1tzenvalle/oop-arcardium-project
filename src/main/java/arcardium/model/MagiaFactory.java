package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class MagiaFactory {

    private final Random rand = new Random();

    public Magia criarMagiaUnica(List<Magia> magiasExistentes) {
        Magia magiaGerada = criarMagiaBase("Nenhuma", "Não faz nada", 999, TipoDeEfeito.CURA, 0, 0, TipoAlvo.ALIADO, NomeEfeito.MALDICAO, List.of(TagMagia.CURA));

        while (true) {
            int magiaNum = rand.nextInt(10) + 1;

            switch (magiaNum) {
                case 1:
                    magiaGerada = criarMagiaBase("Sugada Nervosa", "Suga vitalidade dos inimigos a cada turno", 10, TipoDeEfeito.DANO_POR_TURNO, 5, 4, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.SANGRAMENTO,
                            Arrays.asList(TagMagia.SOMBRA, TagMagia.DANO, TagMagia.SANGRAMENTO, TagMagia.AREA));
                    break;
                case 2:
                    magiaGerada = criarMagiaBase("Força do URSO", "Voce reune a FORÇA da natureza pra si", 10, TipoDeEfeito.BUFF_ATAQUE, 10, 2, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO,
                            Arrays.asList(TagMagia.NATUREZA, TagMagia.BUFF));
                    break;
                case 3:
                    magiaGerada = criarMagiaBase("Pele de PEDRA", "Voce reune a DEFESA da natureza pra si", 10, TipoDeEfeito.BUFF_DEFESA, 5, 3, TipoAlvo.ALIADO, NomeEfeito.PELE_DE_PEDRA,
                            Arrays.asList(TagMagia.NATUREZA, TagMagia.BUFF));
                    break;
                case 4:
                    magiaGerada = criarMagiaBase("Seta de Gelo", "Lança uma farpa de gelo em um único alvo.", 10, TipoDeEfeito.DANO_DIRETO, 25, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM,
                            Arrays.asList(TagMagia.GELO, TagMagia.DANO, TagMagia.ALVO_UNICO));
                    break;
                case 5:
                    magiaGerada = criarMagiaBase("Chuva de Meteoros", "Atinge todos os inimigos com fragmentos cósmicos.", 25, TipoDeEfeito.DANO_DIRETO, 15, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM,
                            Arrays.asList(TagMagia.FOGO, TagMagia.DANO, TagMagia.AREA));
                    break;
                case 6:
                    magiaGerada = criarMagiaBase("Toque Nocivo", "Aplica VENENO em um único alvo por 3 turnos.", 15, TipoDeEfeito.DANO_POR_TURNO, 8, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.VENENO,
                            Arrays.asList(TagMagia.SOMBRA, TagMagia.VENENO, TagMagia.CONTROLE, TagMagia.ALVO_UNICO));
                    break;
                case 7:
                    magiaGerada = criarMagiaBase("Imagem Espelhada", "Aumenta sua DEFESA por 2 turnos.", 12, TipoDeEfeito.BUFF_DEFESA, 10, 2, TipoAlvo.ALIADO, NomeEfeito.PELE_DE_PEDRA,
                            Arrays.asList(TagMagia.ARCANA, TagMagia.BUFF));
                    break;
                case 8:
                    magiaGerada = criarMagiaBase("Maldição da Fraqueza", "Reduz o ATAQUE de um inimigo por 2 turnos.", 18, TipoDeEfeito.DEBUFF_ATAQUE, 5, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.MALDICAO,
                            Arrays.asList(TagMagia.SOMBRA, TagMagia.DEBUFF, TagMagia.CONTROLE));
                    break;
                case 9:
                    magiaGerada = criarMagiaBase("Toque Restaurador", "Recupera uma quantidade significativa de VIDA.", 20, TipoDeEfeito.CURA, 40, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM,
                            Arrays.asList(TagMagia.LUZ, TagMagia.CURA));
                    break;
                case 10:
                    magiaGerada = criarMagiaBase("Assombrar", "Todos os seres ficam assombrados devido a AURA AMEDONTRADORA fornecida PELA DEUSA LUISA", 40, TipoDeEfeito.DEBUFF_DEFESA, 40, 5, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.ASSOMBRAR,
                            Arrays.asList(TagMagia.SOMBRA, TagMagia.DEBUFF, TagMagia.CONTROLE, TagMagia.AREA));
                    break;
            }

            if (verificaMagiaGerada(magiasExistentes, magiaGerada)) {
                return magiaGerada;
            }
        }
    }

    private Magia criarMagiaBase(String nome, String descricao, int custoMana, TipoDeEfeito tipoEfeito,
            int valorEfeito, int duracao, TipoAlvo tipoAlvo, NomeEfeito efeito,
            List<TagMagia> tags) {
        Magia magia = new Magia(nome, descricao, custoMana, tipoEfeito, valorEfeito, duracao, tipoAlvo, efeito, null);
        magia.getTags().addAll(tags);
        return magia;
    }

    public boolean verificaMagiaGerada(List<Magia> magias, Magia magiaGerada) {
        for (Magia magia : magias) {
            if (magia.getNome().equals(magiaGerada.getNome())) {
                return false;
            }
        }
        return true;
    }
}
