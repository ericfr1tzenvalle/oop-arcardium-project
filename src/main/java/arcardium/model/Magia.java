package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TagMagia;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
import arcardium.utils.AnsiColors;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma magia que pode ser usada por um personagem. Contém todas as
 * informações essenciais sobre um feitiço.
 *
 * @author Éric
 */
public class Magia {

    private String nome;
    private String descricao;
    private int nivel;
    private int custoMana;
    private final TipoDeEfeito tipoEfeito;
    private int valorEfeito;
    private int duracaoEfeito;
    private TipoAlvo tipoAlvo;
    private NomeEfeito efeito;
    private List<TagMagia> tags;

    public Magia(String nome, String descricao, int custoMana, TipoDeEfeito tipoEfeito, int valorEfeito, int duracaoEfeito, TipoAlvo tipoAlvo, NomeEfeito efeito, List<TagMagia> tags) {
        this.nome = nome;
        this.nivel = 1;
        this.descricao = descricao;
        this.custoMana = custoMana;
        this.tipoEfeito = tipoEfeito;
        this.valorEfeito = valorEfeito;
        this.duracaoEfeito = duracaoEfeito;
        this.tipoAlvo = tipoAlvo;
        this.efeito = efeito;
        this.tags = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<TagMagia> getTags() {
        return tags;
    }
    
    

    public String getDescricao() {
        return descricao;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public TipoDeEfeito getTipoEfeito() {
        return tipoEfeito;
    }

    public int getValorEfeito() {
        return valorEfeito;
    }

    public void setValorEfeito(int valorEfeito) {
        this.valorEfeito = valorEfeito;
    }

    public int getDuracaoEfeito() {
        return duracaoEfeito;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private void setCustoMana(int custoMana) {
        this.custoMana = custoMana;
    }

    public int getNivel() {
        return nivel;
    }

    public TipoAlvo getTipoAlvo() {
        return tipoAlvo;
    }

    public NomeEfeito getNomeEfeito() {
        return efeito;
    }

    public void aprimorarMagia() {
        this.nivel++;
        this.custoMana -= 2;
        this.valorEfeito += 5;

        if (this.custoMana < 0) {
            this.custoMana = 0;
        }
    }

    @Override
    public String toString() {
        // Usamos um StringBuilder para construir nosso texto de forma eficiente.
        StringBuilder sb = new StringBuilder();


        String nomeColorido = AnsiColors.red(String.format("[Lv.%d] %s", nivel, nome));
        String custoColorido = AnsiColors.red(String.format("[Custo: %d MP]", custoMana));
        sb.append(String.format("%-45s %s%n", nomeColorido, custoColorido)); // %-45s alinha à esquerda


        String alvoInfo = ("Alvo: " + this.tipoAlvo.toString());
        sb.append(String.format(" > %-30s", alvoInfo));


        sb.append(formatarEfeito());


        sb.append("\n > Tags: ");
        if (tags == null || tags.isEmpty()) {
            sb.append(AnsiColors.red("Nenhuma"));
        } else {
            for(TagMagia tag : tags) {
                sb.append(AnsiColors.red("[" + tag.name() + "] "));
            }
        }


        sb.append("\n > " + ('"' + descricao + '"'));

        return sb.toString();
    }

    /**
     * Método ajudante privado para formatar a linha de efeito da magia
     * de forma inteligente, mostrando apenas o que é relevante.
     */
    private String formatarEfeito() {
        String efeitoDesc = "Efeito: ";
        String valorColorido = (String.valueOf(this.valorEfeito));
        String nomeEfeitoColorido = (this.efeito.toString());

        switch (this.tipoEfeito) {
            case DANO_DIRETO:
                return efeitoDesc + "Causa " + valorColorido + " de dano " + nomeEfeitoColorido + ".";
            case DANO_POR_TURNO:
                return efeitoDesc + "Aplica " + valorColorido + " de dano de " + nomeEfeitoColorido + " por " + this.duracaoEfeito + " turnos.";
            case CURA:
                return efeitoDesc + "Recupera " + (valorEfeito + " de VIDA") + ".";
            case BUFF_ATAQUE:
            case BUFF_DEFESA:
            case BUFF_AGILIDADE:
            case BUFF_EVASAO:
                return efeitoDesc + "Concede " + ("+" + valorEfeito + " de " + nomeEfeitoColorido) + " por " + this.duracaoEfeito + " turnos.";
            case DEBUFF_ATAQUE:
            case DEBUFF_DEFESA:
            case DEBUFF_AGILIDADE:
                return efeitoDesc + "Aplica " + ("-" + valorEfeito + " de " + nomeEfeitoColorido) + " por " + this.duracaoEfeito + " turnos.";
            default:
                return efeitoDesc + AnsiColors.black("Especial.");
        }
    }

}
