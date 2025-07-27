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
        new Magia("Sugada Nervosa", "Suga vitalidade dos inimigos a cada turno", 20, TipoDeEfeito.DANO_POR_TURNO, 7, 3, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.SANGRAMENTO, List.of(TagMagia.SOMBRA, TagMagia.DANO, TagMagia.SANGRAMENTO, TagMagia.AREA)),
        new Magia("Força do Urso", "Reúne a força da natureza para si", 15, TipoDeEfeito.BUFF_ATAQUE, 10, 3, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO, List.of(TagMagia.NATUREZA, TagMagia.BUFF)),
        new Magia("Pele de Pedra", "Enrijece toda a sua pele", 15, TipoDeEfeito.BUFF_DEFESA, 12, 3, TipoAlvo.ALIADO, NomeEfeito.PELE_DE_PEDRA, List.of(TagMagia.NATUREZA, TagMagia.BUFF)),
        new Magia("Seta de Gelo", "Lança um fragmento de seta de gelo", 10, TipoDeEfeito.DANO_DIRETO, 25, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.GELO, TagMagia.DANO, TagMagia.ALVO_UNICO)),
        new Magia("Chuva de Meteoros", "Uma chuva de meteoros de fogo", 25, TipoDeEfeito.DANO_DIRETO, 18, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.AREA)),
        new Magia("Toque Nocivo", "Aplica VENENO em um único alvo", 17, TipoDeEfeito.DANO_POR_TURNO, 8, 4, TipoAlvo.ALVO_UNICO, NomeEfeito.VENENO, List.of(TagMagia.SOMBRA, TagMagia.VENENO, TagMagia.CONTROLE)),
        new Magia("Imagem Espelhada", "Aumenta sua EVASÃO por 2 turnos.", 12, TipoDeEfeito.BUFF_EVASAO, 15, 2, TipoAlvo.ALIADO, NomeEfeito.AGIL, List.of(TagMagia.ARCANA, TagMagia.BUFF)),
        new Magia("Maldição da Fraqueza", "Reduz o ATAQUE de um inimigo.", 18, TipoDeEfeito.DEBUFF_ATAQUE, 8, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.MALDICAO, List.of(TagMagia.SOMBRA, TagMagia.DEBUFF, TagMagia.CONTROLE)),
        new Magia("Toque Restaurador", "Recupera uma quantidade significativa de VIDA.", 20, TipoDeEfeito.CURA, 50, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM, List.of(TagMagia.LUZ, TagMagia.CURA)),
        new Magia("Assombrar", "Todos os inimigos ficam assombrados, reduzindo sua DEFESA.", 30, TipoDeEfeito.DEBUFF_DEFESA, 10, 3, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.ASSOMBRAR, List.of(TagMagia.SOMBRA, TagMagia.DEBUFF, TagMagia.AREA)),
        new Magia("MAGIA FOGO 1", "Uma chuva de meteoros de fogo", 25, TipoDeEfeito.DANO_DIRETO, 18, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.FOGO)),
        new Magia("MAGIA FOGO 2", "Uma chuva de meteoros de fogo", 25, TipoDeEfeito.DANO_DIRETO, 18, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.FOGO)),
        new Magia("MAGIA FOGO 3", "Uma chuva de meteoros de fogo", 25, TipoDeEfeito.DANO_DIRETO, 18, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.FOGO))
        // Vou adicionar mais 40 magias aqui, e essas serão reformuladas provavelmente
    );

    /**
     * Gera uma magia aleatória de uma tag específica que o jogador ainda não possui.
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