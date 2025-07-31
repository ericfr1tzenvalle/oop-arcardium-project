package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TagMagia;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;

import java.util.List;

public class MagoFactory {

    private Mago mago;

    public MagoFactory() {
    }

    // ===================== ARQUÉTIPOS ORIGINAIS (ajustados) =====================
    public Mago criarMagoDeBatalha(String nome) {
        // Tank/linha de frente com área barata, mas dano moderado
        mago = new Mago(nome, 120, 70, 4, 8, 10, 12, 10, 5);
        mago.aprenderMagia(new Magia(
                "Força do Urso", "Aumentando o ataque com vigor primal",
                15,
                List.of(new Efeito(TipoDeEfeito.BUFF_ATAQUE, 10, 2, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO)),
                List.of(TagMagia.NATUREZA, TagMagia.BUFF)));

        mago.aprenderMagia(new Magia(
                "Impacto Sísmico", "Batendo o cajado no chão e abalando o campo",
                22,
                List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 15, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM)),
                List.of(TagMagia.NATUREZA, TagMagia.DANO, TagMagia.AREA)));

        mago.setChanceDeCritico(5);
        mago.setDanoCritico(1.25);
        return mago;
    }

    public Mago criarMagoArcano(String nome) {
        // Glass cannon focado em single target + escudo utilitário
        mago = new Mago(nome, 85, 100, 8, 12, 5, 15, 10, 8);
        mago.aprenderMagia(new Magia(
                "Orbe Arcano", "Lançando um projétil arcano no inimigo",
                15,
                List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 26, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
                List.of(TagMagia.ARCANA, TagMagia.DANO, TagMagia.ALVO_UNICO)));

        mago.aprenderMagia(new Magia(
                "Escudo Arcano", "Protegendo o aliado com escudo mágico",
                15,
                List.of(new Efeito(TipoDeEfeito.BUFF_DEFESA, 18, 2, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)),
                List.of(TagMagia.ARCANA, TagMagia.BUFF, TagMagia.ALVO_UNICO)));
        return mago;
    }

    public Mago criarEscolhido(String nome) {
        // Sorte/evasão + golpe consistente
        mago = new Mago(nome, 100, 80, 5, 10, 7, 12, 12, 10);
        mago.aprenderMagia(new Magia(
                "Golpe de Sorte", "Golpeando o inimigo em um ponto crítico",
                12,
                List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 24, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
                List.of(TagMagia.SORTE, TagMagia.DANO, TagMagia.ALVO_UNICO)));

        mago.aprenderMagia(new Magia(
                "Sorte Sorte Sorte!", "Aumentando temporariamente a chance de esquiva",
                15,
                List.of(new Efeito(TipoDeEfeito.BUFF_EVASAO, 18, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.AGIL)),
                List.of(TagMagia.SORTE, TagMagia.BUFF, TagMagia.ALVO_UNICO)));

        mago.setChanceDeCritico(15);
        mago.setDanoCritico(1.3);
        return mago;
    }

    public Mago criarPadre(String nome) {
        // Suporte/ofensivo de Luz: smite + HOT
        mago = new Mago(nome, 90, 90, 6, 5, 8, 10, 12, 12);
        mago.aprenderMagia(new Magia(
                "Divine Smite", "Golpeando o inimigo com poder divino",
                15,
                List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 30, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
                List.of(TagMagia.LUZ, TagMagia.DANO, TagMagia.ALVO_UNICO)));

        mago.aprenderMagia(new Magia(
                "Benção do Pai", "Curando lentamente um aliado",
                20,
                List.of(new Efeito(TipoDeEfeito.CURA, 30, 3, TipoAlvo.ALIADO, NomeEfeito.CURA_REGENERATIVA)),
                List.of(TagMagia.LUZ, TagMagia.CURA, TagMagia.ALVO_UNICO)));
        return mago;
    }
    
    public Mago criarVampiro(String nome){
        mago = new Mago(nome, 80, 60, 10, 10, 2, 12, 10, 8);
        mago.aprenderMagia(new Magia("Drenar Sangue", "Drenando o sangue do alvo temporariamete", 12, List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 20,1,TipoAlvo.ALVO_UNICO,NomeEfeito.NENHUM), new Efeito(TipoDeEfeito.CURA, 10, 2, TipoAlvo.ALIADO, NomeEfeito.BANQUETE)),List.of(TagMagia.SOMBRA, TagMagia.DANO, TagMagia.CURA, TagMagia.ALVO_UNICO, TagMagia.SANGRAMENTO)));
//        mago.aprenderMagia(new Magia("Trasformar em Morcego", "Se transforma em morcego aumentando muito a evasão mas em custo de [DEFESA]", 15, List.of(new Efeito(TipoDeEfeito.BUFF_EVASAO, 30,6,TipoAlvo.ALIADO)) ))
        return mago;
    }

    // ===================== NOVOS ARQUÉTIPOS PARA TESTE =====================
    public Mago criarPiromante(String nome) {
        // Alto MP, foco em fogo + DOT/aoe
        mago = new Mago(nome, 85, 95, 6, 8, 6, 10, 12, 8);
        mago.aprenderMagia(new Magia(
                "Chama Infernal", "Lançando uma bola de fogo explosiva",
                18,
                List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 32, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
                List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.ALVO_UNICO)));

        mago.aprenderMagia(new Magia(
                "Labaredas Vivas", "Atingindo inimigos com chamas contínuas",
                22,
                List.of(new Efeito(TipoDeEfeito.DANO_POR_TURNO, 24, 3, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.FOGO)),
                List.of(TagMagia.FOGO, TagMagia.DANO, TagMagia.AREA)));
        return mago;
    }

    public Mago criarCriomante(String nome) {
        // Controle/defesa: lança + congela/alivia pressão
        mago = new Mago(nome, 90, 90, 6, 7, 10, 10, 12, 8);
        mago.aprenderMagia(new Magia(
                "Lança de Gelo", "Disparando uma lança de gelo perfurante",
                17,
                List.of(new Efeito(TipoDeEfeito.DANO_DIRETO, 26, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM)),
                List.of(TagMagia.GELO, TagMagia.DANO, TagMagia.ALVO_UNICO)));

        mago.aprenderMagia(new Magia(
                "Torrão Congelado", "Congelando o inimigo em um bloco de gelo",
                22,
                List.of(new Efeito(TipoDeEfeito.CONTROLE, 0, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.CONGELADO)),
                List.of(TagMagia.GELO, TagMagia.CONTROLE)));
        return mago;
    }

    public Mago criarUmbramante(String nome) {
        // DOT forte single + cegueira em área
        mago = new Mago(nome, 80, 100, 7, 7, 7, 12, 14, 10);
        mago.aprenderMagia(new Magia(
                "Toque Sombrio", "Drenando a vida do inimigo lentamente",
                18,
                List.of(new Efeito(TipoDeEfeito.DANO_POR_TURNO, 24, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.SANGRAMENTO)),
                List.of(TagMagia.SOMBRA, TagMagia.DANO, TagMagia.ALVO_UNICO)));

        mago.aprenderMagia(new Magia(
                "Escuridão Total", "Cegando os inimigos em escuridão",
                20,
                List.of(new Efeito(TipoDeEfeito.DEBUFF_PRECISAO, 20, 2, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.CEGUEIRA)),
                List.of(TagMagia.SOMBRA, TagMagia.DEBUFF, TagMagia.AREA)));
        return mago;
    }

    public Mago criarDruida(String nome) {
        // Suporte/controle natureza: HOT + debuff de agilidade em área
        mago = new Mago(nome, 100, 85, 6, 7, 9, 9, 11, 11);
        mago.aprenderMagia(new Magia(
                "Toque da Mãe Terra", "Curando ferimentos com energia da terra",
                18,
                List.of(new Efeito(TipoDeEfeito.CURA, 28, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM)),
                List.of(TagMagia.NATUREZA, TagMagia.CURA, TagMagia.ALVO_UNICO)));

        mago.aprenderMagia(new Magia(
                "Raízes Aprisionadoras", "Reduzindo a agilidade dos inimigos",
                22,
                List.of(new Efeito(TipoDeEfeito.DEBUFF_AGILIDADE, 18, 2, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.AMENDONTRAR)),
                List.of(TagMagia.NATUREZA, TagMagia.DEBUFF, TagMagia.AREA)));
        return mago;
    }

    public Mago criarGuardiaoRunico(String nome) {
        // Suporte defensivo/escudos + buffs
        mago = new Mago(nome, 110, 80, 5, 8, 12, 8, 10, 8);
        mago.aprenderMagia(new Magia(
                "Escudo Arcano", "Protegendo o aliado com escudo mágico",
                15,
                List.of(new Efeito(TipoDeEfeito.BUFF_DEFESA, 18, 2, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)),
                List.of(TagMagia.ARCANA, TagMagia.BUFF, TagMagia.ALVO_UNICO)));

        mago.aprenderMagia(new Magia(
                "Vigor da Floresta", "Aumentando a defesa dos aliados",
                18,
                List.of(new Efeito(TipoDeEfeito.BUFF_DEFESA, 12, 3, TipoAlvo.ALIADO, NomeEfeito.RESISTENTE)),
                List.of(TagMagia.NATUREZA, TagMagia.BUFF)));
        return mago;
    }
}
// ===================== (Opcional) ARQUÉTIPOS EXTRAS DE VARIAÇÃO =====================
// Se quiser mais variedade rápida, você pode criar:
// - Tempestário (área/controle leve): Tempestade de Gelo + Rugido da Terra
// - Inquisidor (luz ofensiva): Divine Smite + Aura Curativa
// - Cartomante (sorte/arcana): Sorte Sorte Sorte! + Orbe Arcano

