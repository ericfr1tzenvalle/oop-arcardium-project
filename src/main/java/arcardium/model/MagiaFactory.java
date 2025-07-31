package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TagMagia;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MagiaFactory {

    private final Random rand = new Random();

   private static final List<Magia> TODAS_AS_MAGIAS = List.of(

    // ==================== FOGO ====================
    new Magia("Chama Infernal", "Lançando uma bola de fogo explosiva", 18,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 32, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.ALVO_UNICO)
    ),

    new Magia("Chuva de Fogo", "Chovendo bolas de fogo em todos os inimigos", 28,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 16, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM)),
        List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.AREA)
    ),

    new Magia("Explosão Solar", "Explodindo em uma onda de energia solar devastadora", 35,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 45, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.ALVO_UNICO)
    ),

    new Magia("Labaredas Vivas", "Atingindo inimigos com chamas contínuas", 22,
        List.of(new Efeito(TipoDeEfeito.DANO_POR_TURNO, 24, 3, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.FOGO)),
        List.of(TagMagia.FOGO, TagMagia.DANO)
    ),

    new Magia("Raio de Fogo", "Disparando um feixe concentrado de fogo", 22,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 30, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.ALVO_UNICO)
    ),

    new Magia("Magma Eruptivo", "Explodindo magma em área", 30,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 38, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM)),
        List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.AREA)
    ),

    new Magia("Círculo Flamejante", "Queimando o alvo com chamas constantes", 20,
        List.of(new Efeito(TipoDeEfeito.DANO_POR_TURNO, 30, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.FOGO)),
        List.of(TagMagia.FOGO, TagMagia.DANO)
    ),

    new Magia("Explosão Combustiva", "Liberando uma explosão flamejante em área", 35,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 35, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM)),
        List.of(TagMagia.FOGO, TagMagia.DANO)
    ),

    new Magia("Fogo Celestial", "Reduzindo a defesa do inimigo com chamas divinas", 20,
        List.of(
            new Efeito(TipoDeEfeito.DANO_DIRETO, 12, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM),
            new Efeito(TipoDeEfeito.DEBUFF_DEFESA, 6, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)
        ),
        List.of(TagMagia.FOGO, TagMagia.DEBUFF)
    ),

    new Magia("Força do Fogo", "Aumentando o poder de ataque com chamas", 15,
        List.of(new Efeito(TipoDeEfeito.BUFF_ATAQUE, 8, 2, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO)),
        List.of(TagMagia.FOGO, TagMagia.BUFF)
    ),

    // ==================== GELO ====================
    new Magia("Congelamento Mortal", "Congelando o inimigo em um golpe mortal", 16,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 22, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.GELO, TagMagia.DANO)
    ),

    new Magia("Tempestade de Gelo", "Atingindo todos com uma tempestade gelada", 26,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 24, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM)),
        List.of(TagMagia.GELO, TagMagia.DANO)
    ),

    new Magia("Manto Gelado", "Protegendo o aliado com um manto gelado", 18,
        List.of(new Efeito(TipoDeEfeito.BUFF_DEFESA, 12, 3, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)),
        List.of(TagMagia.GELO, TagMagia.BUFF)
    ),

    new Magia("Espinhos de Gelo", "Perfurando o inimigo com espinhos afiados", 14,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 18, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.GELO, TagMagia.DANO)
    ),

    new Magia("Geleira", "Criando uma barreira protetora de gelo", 20,
        List.of(new Efeito(TipoDeEfeito.BUFF_DEFESA, 20, 3, TipoAlvo.ALIADO, NomeEfeito.GELEIRA)),
        List.of(TagMagia.GELO, TagMagia.BUFF)
    ),

    new Magia("Congelamento Instantâneo", "Diminuindo a agilidade do alvo com gelo", 18,
        List.of(new Efeito(TipoDeEfeito.DEBUFF_AGILIDADE, 20, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.LENTIDAO)),
        List.of(TagMagia.GELO, TagMagia.DEBUFF)
    ),

    new Magia("Cúpula de Gelo", "Protegendo todos os aliados com gelo", 25,
        List.of(new Efeito(TipoDeEfeito.BUFF_DEFESA, 20, 2, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)),
        List.of(TagMagia.GELO, TagMagia.BUFF)
    ),

    new Magia("Torrão Congelado", "Congelando o inimigo em um bloco de gelo", 22,
        List.of(new Efeito(TipoDeEfeito.CONTROLE, 0, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.CONGELADO)),
        List.of(TagMagia.GELO, TagMagia.CONTROLE)
    ),

    new Magia("Fúria Glacial", "Aumentando o ataque com energia glacial", 15,
        List.of(new Efeito(TipoDeEfeito.BUFF_ATAQUE, 8, 2, TipoAlvo.ALIADO, NomeEfeito.FURIA_GLACIAL)),
        List.of(TagMagia.GELO, TagMagia.BUFF)
    ),

    new Magia("Lança de Gelo", "Disparando uma lança de gelo perfurante", 17,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 26, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.GELO, TagMagia.DANO)
    ),

    // ==================== SOMBRA ====================
    new Magia("Toque Sombrio", "Drenando a vida do inimigo lentamente", 18,
        List.of(new Efeito(TipoDeEfeito.DANO_POR_TURNO, 24, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.SANGRAMENTO)),
        List.of(TagMagia.SOMBRA, TagMagia.DANO)
    ),

    new Magia("Escuridão Total", "Cegando os inimigos em escuridão", 20,
        List.of(new Efeito(TipoDeEfeito.DEBUFF_PRECISAO, 20, 2, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.CEGUEIRA)),
        List.of(TagMagia.SOMBRA, TagMagia.DEBUFF)
    ),

    new Magia("Corte Sombrio", "Enfraquecendo a defesa do inimigo", 20,
        List.of(new Efeito(TipoDeEfeito.DEBUFF_DEFESA, 15, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.FRACO)),
        List.of(TagMagia.SOMBRA, TagMagia.DEBUFF)
    ),

    new Magia("Sombra Etérea", "Atacando todos os inimigos com sombras", 24,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 28, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM)),
        List.of(TagMagia.SOMBRA, TagMagia.DANO)
    ),

    new Magia("Manto Sombrio", "Aumentando a evasão do aliado", 18,
        List.of(new Efeito(TipoDeEfeito.BUFF_EVASAO, 20, 2, TipoAlvo.ALIADO, NomeEfeito.AGIL)),
        List.of(TagMagia.SOMBRA, TagMagia.BUFF)
    ),

    new Magia("Alma Sombria", "Causando dano contínuo em área", 26,
        List.of(new Efeito(TipoDeEfeito.DANO_POR_TURNO, 36, 3, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.VENENO)),
        List.of(TagMagia.SOMBRA, TagMagia.DANO)
    ),

    new Magia("Espectro Vingativo", "Atingindo um alvo com um golpe sombrio", 25,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 40, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.VINGANCA)),
        List.of(TagMagia.SOMBRA, TagMagia.DANO)
    ),

    new Magia("Morte Súbita", "Executando um golpe sombrio fatal", 32,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 50, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.SOMBRA, TagMagia.DANO)
    ),

    new Magia("Visão Sombria", "Aumentando a agilidade do aliado", 18,
        List.of(new Efeito(TipoDeEfeito.BUFF_AGILIDADE, 15, 2, TipoAlvo.ALIADO, NomeEfeito.AGIL)),
        List.of(TagMagia.SOMBRA, TagMagia.BUFF)
    ),

    new Magia("Abraço das Sombras", "Curando lentamente o aliado", 20,
        List.of(new Efeito(TipoDeEfeito.CURA, 30, 3, TipoAlvo.ALIADO, NomeEfeito.ABRACO_SOMBRIO)),
        List.of(TagMagia.SOMBRA, TagMagia.CURA)
    ),

    // ==================== CURA ====================
    new Magia("Cura Rápida", "Curando um aliado rapidamente", 15,
        List.of(new Efeito(TipoDeEfeito.CURA, 25, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM)),
        List.of(TagMagia.LUZ, TagMagia.CURA)
    ),

    new Magia("Regeneração Vital", "Curando o aliado ao longo do tempo", 20,
        List.of(new Efeito(TipoDeEfeito.CURA, 36, 3, TipoAlvo.ALIADO, NomeEfeito.CURA_REGENERATIVA)),
        List.of(TagMagia.LUZ, TagMagia.CURA)
    ),

    new Magia("Toque Restaurador", "Restaurando grande quantidade de vida", 28,
        List.of(new Efeito(TipoDeEfeito.CURA, 40, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM)),
        List.of(TagMagia.LUZ, TagMagia.CURA)
    ),

    new Magia("Aura Curativa", "Curando todos os aliados em área", 30,
        List.of(new Efeito(TipoDeEfeito.CURA, 15, 2, TipoAlvo.ALIADO, NomeEfeito.CURA_REGENERATIVA)),
        List.of(TagMagia.LUZ, TagMagia.CURA)
    ),

    new Magia("Renovação Divina", "Restaurando totalmente a vida do aliado", 45,
        List.of(new Efeito(TipoDeEfeito.CURA, 80, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM)),
        List.of(TagMagia.LUZ, TagMagia.CURA)
    ),

    // ==================== NATUREZA ====================
    new Magia("Toque da Mãe Terra", "Curando ferimentos com energia da terra", 18,
        List.of(new Efeito(TipoDeEfeito.CURA, 28, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM)),
        List.of(TagMagia.NATUREZA, TagMagia.CURA)
    ),

    new Magia("Chuva Curativa", "Curando todos aliados com chuva mágica", 28,
        List.of(new Efeito(TipoDeEfeito.CURA, 20, 2, TipoAlvo.ALIADO, NomeEfeito.CURA_REGENERATIVA)),
        List.of(TagMagia.NATUREZA, TagMagia.CURA)
    ),

    new Magia("Vigor da Floresta", "Aumentando a defesa dos aliados", 18,
        List.of(new Efeito(TipoDeEfeito.BUFF_DEFESA, 12, 3, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)),
        List.of(TagMagia.NATUREZA, TagMagia.BUFF)
    ),

    new Magia("Rugido da Terra", "Atordoando inimigos com um rugido", 20,
        List.of(new Efeito(TipoDeEfeito.CONTROLE, 0, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.ATORDOADO)),
        List.of(TagMagia.NATUREZA, TagMagia.CONTROLE)
    ),

    new Magia("Raízes Aprisionadoras", "Reduzindo a agilidade dos inimigos", 22,
        List.of(new Efeito(TipoDeEfeito.DEBUFF_AGILIDADE, 18, 2, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.AMENDONTRAR)),
        List.of(TagMagia.NATUREZA, TagMagia.DEBUFF)
    ),

    // ==================== SORTE / ARCANO ====================
    new Magia("Sorte Sorte Sorte!", "Aumentando a chance de esquiva", 15,
        List.of(new Efeito(TipoDeEfeito.BUFF_EVASAO, 18, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.AGIL)),
        List.of(TagMagia.SORTE, TagMagia.BUFF)
    ),

    new Magia("Golpe de Sorte", "Golpeando o inimigo em um ponto crítico", 12,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 24, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.SORTE, TagMagia.DANO)
    ),

    new Magia("Orbe Arcano", "Lançando um projétil arcano no inimigo", 15,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 26, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.ARCANA, TagMagia.DANO)
    ),

    new Magia("Escudo Arcano", "Protegendo o aliado com escudo mágico", 15,
        List.of(new Efeito(TipoDeEfeito.BUFF_DEFESA, 18, 2, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)),
        List.of(TagMagia.ARCANA, TagMagia.BUFF)
    ),

    new Magia("Divine Smite", "Golpeando o inimigo com poder divino", 15,
        List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 30, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
        List.of(TagMagia.LUZ, TagMagia.DANO)
    ),

    new Magia("Benção do Pai", "Curando lentamente um aliado", 20,
        List.of(new Efeito(TipoDeEfeito.CURA, 30, 3, TipoAlvo.ALIADO, NomeEfeito.CURA_REGENERATIVA)),
        List.of(TagMagia.LUZ, TagMagia.CURA)
    )
);

    /**
     * Gera uma magia aleatória de uma tag específica que o jogador ainda não
     * possui.
     */
    public Magia criarMagiaUnicaPorTag(List<Magia> magiasExistentes, TagMagia tagDesejada) {

        // 1. Filtra o catálogo mestre para pegar apenas as magias da tag desejada.
        List<Magia> magiasComTag = TODAS_AS_MAGIAS.stream()
                .filter(m -> m.getTags().contains(tagDesejada))
                .collect(Collectors.toList());

        // 2. Remove dessa lista filtrada as magias que o jogador já possui
        magiasComTag.removeIf(magia -> !verificaMagiaGerada(magiasExistentes, magia));

        // 3. Se não sobrar nenhuma magia nova, retorna uma magia aleatória qualquer que o mago tem
        if (magiasComTag.isEmpty()) {
            return criarMagiaUnica(magiasExistentes);
        }

        // 4. Sorteia uma da lista final
        return magiasComTag.get(rand.nextInt(magiasComTag.size()));
    }

    /**
     * Gera uma magia aleatória de qualquer tipo que o jogador ainda não possui
     */
    public Magia criarMagiaUnica(List<Magia> magiasExistentes) {
        Magia magiaGerada;
        while (true) {
            // Sorteia uma magia aleatória do nosso catálogo mestre
            magiaGerada = TODAS_AS_MAGIAS.get(rand.nextInt(TODAS_AS_MAGIAS.size()));

            if (verificaMagiaGerada(magiasExistentes, magiaGerada)) {
                return magiaGerada; // Se for nova, entrega.
            }
            // Se não, o loop tenta de novo.
        }
    }

    /**
     * Verifica se uma magia gerada já existe em uma lista
     */
    public boolean verificaMagiaGerada(List<Magia> magias, Magia magiaGerada) {
        for (Magia magia : magias) {
            if (magia.getNome().equals(magiaGerada.getNome())) {
                return false;
            }
        }
        return true;
    }
}
