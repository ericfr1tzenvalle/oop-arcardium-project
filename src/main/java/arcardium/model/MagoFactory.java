package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TagMagia;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;

import java.util.List;

public class MagoFactory {
    private Mago mago;
    public MagoFactory() {}

    public Mago criarMagoDeBatalha(String nomeMago){
        mago = new Mago(nomeMago, 120, 70, 4, 8, 10, 12, 10, 5);
        mago.aprenderMagia(new Magia("Força do Urso", "Aumenta o ATAQUE", 10, TipoDeEfeito.BUFF_ATAQUE, 10, 2, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO, List.of(TagMagia.NATUREZA, TagMagia.BUFF)));
        mago.aprenderMagia(new Magia("Impacto Sísmico", "Dano em TODOS os inimigos", 15, TipoDeEfeito.DANO_DIRETO, 15, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.DANO, TagMagia.AREA)));
        return mago;
    }

    public Mago criarMagoArcano(String nomeMago){
        mago = new Mago(nomeMago, 80, 100, 8,12, 5, 15, 10, 8);
        mago.aprenderMagia(new Magia("Orbe Arcano", "Lança uma bola de magia no alvo", 15, TipoDeEfeito.DANO_DIRETO, 30, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.ARCANA, TagMagia.DANO, TagMagia.ALVO_UNICO)));
        mago.aprenderMagia(new Magia("Escudo Arcano", "Aprimorando a sua capacidade de defesa", 15,TipoDeEfeito.DEBUFF_DEFESA, 30, 2, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE, List.of(TagMagia.ARCANA, TagMagia.BUFF, TagMagia.ALVO_UNICO)));
        return mago;
    }

    public Mago criarEscolhido(String nomeEscolhido){
        mago = new Mago(nomeEscolhido, 100, 80, 5,10, 7, 12, 20, 15);
        mago.aprenderMagia(new Magia("Golpe de Sorte", "Golpeando o alvo em um ponto critico", 10, TipoDeEfeito.DANO_DIRETO, 28, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM, List.of(TagMagia.SORTE, TagMagia.ALVO_UNICO)));
        mago.aprenderMagia(new Magia("Sorte Sorte Sorte!", "Ganha temporariamente maior chance de esquivar", 15, TipoDeEfeito.BUFF_EVASAO, 30, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.AGIL, List.of(TagMagia.SORTE, TagMagia.BUFF, TagMagia.ALVO_UNICO)));
        return mago;
    }

}
