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
            //GRANDE PARTE DESSAS MAGIAS FORAM GERADAS, MUITAS NAO VAO FICAR NA VERSAO FINAL.

            //FOGO
            new Magia("Chama Infernal", "Lançando uma bola de fogo que explode ao atingir o alvo", 20, TipoDeEfeito.DANO_DIRETO, 40, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.ALVO_UNICO)),
            new Magia("Chuva de Fogo", "Chovendo bolas de fogo que atingem todos os inimigos", 25, TipoDeEfeito.DANO_DIRETO, 18, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.AREA)),
            new Magia("Explosão Solar", "Liberando uma explosão de energia solar que incinera o alvo", 30, TipoDeEfeito.DANO_DIRETO, 50, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.ALVO_UNICO)),
            new Magia("Labaredas Vivas", "Gerando labaredas de fogo que aumentam de intensidade com o tempo", 18, TipoDeEfeito.DANO_POR_TURNO, 10, 3, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.FOGO, List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.SANGRAMENTO)),
            new Magia("Raio de Fogo", "Disparando um raio de fogo que causa grande dano direto", 22, TipoDeEfeito.DANO_DIRETO, 35, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.ALVO_UNICO)),
            new Magia("Magma Eruptivo", "Erupcionando magma que atinge todos os inimigos ao redor", 28, TipoDeEfeito.DANO_DIRETO, 45, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.AREA)),
            new Magia("Círculo Flamejante", "Criando um círculo de fogo ao redor do alvo", 20, TipoDeEfeito.DANO_POR_TURNO, 12, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.FOGO, List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.ALVO_UNICO)),
            new Magia("Explosão Combustiva", "Liberando uma grande explosão de fogo que atinge todos os inimigos", 35, TipoDeEfeito.DANO_DIRETO, 40, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.AREA)),
            new Magia("Fogo Celestial", "Liberando chamas divinas que reduzem a defesa do inimigo", 22, TipoDeEfeito.DEBUFF_DEFESA, 8, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.FOGO, TagMagia.DEBUFF, TagMagia.ALVO_UNICO)),
            new Magia("Força do Fogo", "Aumentando o poder de ataque com as chamas do fogo", 15, TipoDeEfeito.BUFF_ATAQUE, 10, 1, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO, List.of(TagMagia.FOGO, TagMagia.BUFF)),

            //GELO
            new Magia("Congelamento Mortal", "Congelando o inimigo, causando dano direto e profundo", 18, TipoDeEfeito.DANO_DIRETO, 25, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.GELO, TagMagia.DANO, TagMagia.ALVO_UNICO)),
            new Magia("Tempestade de Gelo", "Gerando uma tempestade de gelo que atinge todos os inimigos", 22, TipoDeEfeito.DANO_DIRETO, 30, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.GELO, TagMagia.DANO, TagMagia.AREA)),
            new Magia("Manto Gelado", "Envolvendo-se em um manto gelado que aumenta sua defesa", 18, TipoDeEfeito.BUFF_DEFESA, 15, 3, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE, List.of(TagMagia.GELO, TagMagia.BUFF)),
            new Magia("Espinhos de Gelo", "Lançando espinhos de gelo que perfuram o alvo", 16, TipoDeEfeito.DANO_DIRETO, 20, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.GELO, TagMagia.DANO, TagMagia.ALVO_UNICO)),
            new Magia("Geleira", "Criando uma barreira de gelo ao redor de um aliado para protegê-lo", 20, TipoDeEfeito.BUFF_DEFESA, 25, 3, TipoAlvo.ALIADO, NomeEfeito.GELEIRA, List.of(TagMagia.GELO, TagMagia.BUFF)),
            new Magia("Congelamento Instantâneo", "Congelando instantaneamente o inimigo", 18, TipoDeEfeito.DEBUFF_AGILIDADE, 30, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.LENTIDAO, List.of(TagMagia.GELO, TagMagia.DEBUFF)),
            new Magia("Cúpula de Gelo", "Criando uma cúpula de gelo para proteger todos os aliados ao redor", 25, TipoDeEfeito.BUFF_DEFESA, 30, 3, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE, List.of(TagMagia.GELO, TagMagia.BUFF)),
            new Magia("Torrão Congelado", "Transformando o inimigo em um bloco de gelo", 20, TipoDeEfeito.CONTROLE, 0, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.CONGELADO, List.of(TagMagia.GELO, TagMagia.DEBUFF)),
            new Magia("Fúria Glacial", "Aumentando o poder de ataque com o poder gelado", 15, TipoDeEfeito.BUFF_ATAQUE, 10, 2, TipoAlvo.ALIADO, NomeEfeito.FURIA_GLACIAL, List.of(TagMagia.GELO, TagMagia.BUFF)),
            new Magia("Lança de Gelo", "Disparando uma lança de gelo afiada contra o inimigo", 17, TipoDeEfeito.DANO_DIRETO, 30, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.GELO, TagMagia.DANO, TagMagia.ALVO_UNICO)),

            //SOMBRA
            new Magia("Toque Sombrio", "Drenando a vida do inimigo a cada toque", 20, TipoDeEfeito.DANO_POR_TURNO, 10, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.SANGRAMENTO, List.of(TagMagia.SOMBRA, TagMagia.DANO, TagMagia.VENENO)),
            new Magia("Escuridão Total", "Cobertura de escuridão cegando todos os inimigos ao redor", 18, TipoDeEfeito.DEBUFF_PRECISAO, 30, 2, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.CEGUEIRA, List.of(TagMagia.SOMBRA, TagMagia.DEBUFF)),
            new Magia("Corte Sombrio", "Desferindo um corte sombrio que enfraquece a defesa do alvo", 22, TipoDeEfeito.DEBUFF_DEFESA, 30, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.FRACO, List.of(TagMagia.SOMBRA, TagMagia.DEBUFF)),
            new Magia("Sombra Etérea", "Invocando uma sombra etérea que ataca todos os inimigos ao redor", 24, TipoDeEfeito.DANO_DIRETO, 40, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.SOMBRA, TagMagia.DANO, TagMagia.AREA)),
            new Magia("Manto Sombrio", "Envolvendo-se em sombras que aumentam a evasão do alvo", 20, TipoDeEfeito.BUFF_EVASAO, 25, 2, TipoAlvo.ALIADO, NomeEfeito.AGIL, List.of(TagMagia.SOMBRA, TagMagia.BUFF)),
            new Magia("Alma Sombria", "Atraindo as almas perdidas para causar dano aos inimigos próximos", 26, TipoDeEfeito.DANO_POR_TURNO, 15, 3, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.VENENO, List.of(TagMagia.SOMBRA, TagMagia.DANO, TagMagia.AREA)),
            new Magia("Espectro Vingativo", "Liberando um espectro sombrio para atacar diretamente o inimigo", 25, TipoDeEfeito.DANO_DIRETO, 50, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.VINGANCA, List.of(TagMagia.SOMBRA, TagMagia.DANO, TagMagia.ALVO_UNICO)),
            new Magia("Morte Súbita", "Infligindo um golpe fatal com a energia das sombras, causando grande dano", 30, TipoDeEfeito.DANO_DIRETO, 60, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.SOMBRA, TagMagia.DANO, TagMagia.ALVO_UNICO)),
            new Magia("Visão Sombria", "Mostrando ao aliado uma visão de sombras, aumentando sua agilidade", 18, TipoDeEfeito.BUFF_AGILIDADE, 30, 2, TipoAlvo.ALIADO, NomeEfeito.AGIL, List.of(TagMagia.SOMBRA, TagMagia.BUFF)),
            new Magia("Abraço das Sombras", "Abraçando o alvo com sombras que restauram sua vida aos poucos", 22, TipoDeEfeito.CURA, 20, 3, TipoAlvo.ALIADO, NomeEfeito.ABRAÇO_SOMBRIO, List.of(TagMagia.SOMBRA, TagMagia.CURA)),

            //CURA
            new Magia("Cura Rápida", "Recuperando uma pequena quantidade de vida ao toque", 15, TipoDeEfeito.CURA, 30, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM, List.of(TagMagia.LUZ, TagMagia.CURA)),
            new Magia("Regeneração Vital", "Curando aos poucos, regenerando uma quantidade significativa de vida por alguns turnos", 20, TipoDeEfeito.CURA, 25, 3, TipoAlvo.ALIADO, NomeEfeito.CURA_REGENERATIVA, List.of(TagMagia.LUZ, TagMagia.CURA)),
            new Magia("Toque Restaurador", "Curando uma grande quantidade de vida com um toque suave", 25, TipoDeEfeito.CURA, 50, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM, List.of(TagMagia.LUZ, TagMagia.CURA)),
            new Magia("Aura Curativa", "Criando uma aura de luz que cura todos os aliados ao redor", 30, TipoDeEfeito.CURA, 20, 2, TipoAlvo.ALIADO, NomeEfeito.CURA_REGENERATIVA, List.of(TagMagia.LUZ, TagMagia.CURA, TagMagia.AREA)),
            new Magia("Renovação Divina", "Restaurando completamente a saúde do aliado com uma energia divina", 40, TipoDeEfeito.CURA, 100, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM, List.of(TagMagia.LUZ, TagMagia.CURA)),
            //NATUREZA
            new Magia("Toque da Mãe Terra", "Curando ferimentos com a energia primordial da terra", 20, TipoDeEfeito.CURA, 35, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM, List.of(TagMagia.NATUREZA, TagMagia.CURA)),
            new Magia("Chuva Curativa", "Invocando uma chuva suave que restaura a vida dos aliados", 25, TipoDeEfeito.CURA, 40, 2, TipoAlvo.ALIADO, NomeEfeito.CURA_REGENERATIVA, List.of(TagMagia.NATUREZA, TagMagia.CURA, TagMagia.AREA)),
            new Magia("Vigor da Floresta", "Imbuindo a energia das árvores para aumentar a resistência dos aliados", 18, TipoDeEfeito.BUFF_DEFESA, 20, 3, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE, List.of(TagMagia.NATUREZA, TagMagia.BUFF)),
            new Magia("Rugido da Terra", "Liberando uma onda de força natural que atordoa os inimigos próximos", 15, TipoDeEfeito.CONTROLE, 0, 2, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.ATORDOADO, List.of(TagMagia.NATUREZA, TagMagia.DEBUFF, TagMagia.AREA)),
            new Magia("Raízes Aprisionadoras", "Crescendo raízes que imobilizam inimigos, reduzindo sua agilidade", 22, TipoDeEfeito.DEBUFF_AGILIDADE, 25, 2, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.AMENDONTRAR, List.of(TagMagia.NATUREZA, TagMagia.DEBUFF, TagMagia.AREA)),


            //Essas abaixo vao ficar.
        new Magia("Sorte Sorte Sorte!", "Ganha temporariamente maior chance de esquivar", 15, TipoDeEfeito.BUFF_EVASAO, 30, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.AGIL, List.of(TagMagia.SORTE, TagMagia.BUFF, TagMagia.ALVO_UNICO)),
        new Magia("Golpe de Sorte", "Golpeando o alvo em um ponto critico", 10, TipoDeEfeito.DANO_DIRETO, 28, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.SORTE, TagMagia.ALVO_UNICO)),
        new Magia("Orbe Arcano", "Lança uma bola de magia no alvo", 15, TipoDeEfeito.DANO_DIRETO, 30, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.ARCANA, TagMagia.DANO, TagMagia.ALVO_UNICO)),
        new Magia("Escudo Arcano", "Aprimorando a sua capacidade de defesa", 15,TipoDeEfeito.DEBUFF_DEFESA, 30, 2, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE, List.of(TagMagia.ARCANA, TagMagia.BUFF, TagMagia.ALVO_UNICO)),
        new Magia("Divine Smite", "Lança um golpe divino no alvo", 10, TipoDeEfeito.DANO_DIRETO, 35, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.LUZ, TagMagia.DANO, TagMagia.ALVO_UNICO)),
        new Magia("Benção do pai", "Faz uma prece para o pai", 20, TipoDeEfeito.CURA, 20, 3, TipoAlvo.ALIADO, NomeEfeito.CURA_REGENERATIVA, List.of(TagMagia.LUZ,TagMagia.CURA, TagMagia.ALVO_UNICO))
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