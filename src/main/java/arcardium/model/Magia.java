package arcardium.model;

import arcardium.model.enums.TagMagia;
import arcardium.utils.AnsiColors;
import arcardium.model.Efeito;
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
    private List<Efeito> efeitos;
    private List<TagMagia> tags;

    public Magia(String nome, String descricao, int custoMana, List<Efeito> efeitos, List<TagMagia> tags) {
        this.nome = nome;
        this.nivel = 1;
        this.descricao = descricao;
        this.custoMana = custoMana;
        this.efeitos = efeitos;
        this.tags = tags;
    }

    public String getNome() {
        return nome;
    }

    public List<TagMagia> getTags() {
        return tags;
    }

    public List<Efeito> getEfeitos() {
        return efeitos;
    }

    public void setEfeitos(List<Efeito> efeitos) {
        this.efeitos = efeitos;
    }
    
    

    public String getDescricao() {
        return descricao;
    }

    public int getCustoMana() {
        return custoMana;
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

    public void aprimorarMagia() {
        this.nivel++;
        this.custoMana -= 2;

        if (this.custoMana < 0) {
            this.custoMana = 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        
        String nomeColorido = AnsiColors.cyan(String.format("[Lv.%d] %s", nivel, nome));
        String custoColorido = AnsiColors.cyan(String.format("[Custo: %d MP]", custoMana));
        sb.append(String.format("%-45s %s%n", nomeColorido, custoColorido));

        
        

        // --- Linha 2: Os Efeitos (A GRANDE MUDANÇA) ---
        // Agora, nós percorremos a lista de efeitos.
        if (efeitos != null && !efeitos.isEmpty()) {
            sb.append("\n > Efeitos:");
            for (Efeito efeito : efeitos) {
                // Para cada efeito na lista, chamamos o formatador.
                sb.append("\n   >> ").append(formatarEfeito(efeito));
            }
        }
        // --- Linha 3: A Descrição (Sabor) ---
        sb.append(" > " + (descricao));

        
        sb.append("\n > Tags: ");
        if (tags != null && !tags.isEmpty()) {
            for (TagMagia tag : tags) {
                sb.append(AnsiColors.yellow("[" + tag.name() + "] "));
            }
        } else {
            sb.append("Nenhuma");
        }

        return sb.toString();
    }

    /**
     * Método ajudante que agora recebe um objeto Efeito e formata sua
     * descrição.
     *
     * @param efeito O objeto Efeito a ser formatado.
     * @return Uma String com a descrição formatada do efeito.
     */
    private String formatarEfeito(Efeito efeito) {
        String valorColorido = String.valueOf(efeito.getValor());
        String nomeEfeitoColorido = efeito.getNomeEfeito().toString();
        String alvoColorido = AnsiColors.cyan(efeito.getTipoAlvo().toString());

        String base = String.format("[%s]: ", alvoColorido);

        switch (efeito.getTipoEfeito()) {
            case DANO_DIRETO:
                return base + "Causa " + valorColorido + " de dano " + nomeEfeitoColorido + ".";
            case DANO_POR_TURNO:
                return base + "Aplica " + valorColorido + " de dano de " + nomeEfeitoColorido + " por " + efeito.getDuracao() + " turnos.";
            case CURA:
                return base + "Recupera " + efeito.getValor() + " de VIDA" + ".";
            case BUFF_ATAQUE:
            case BUFF_DEFESA:
            case BUFF_AGILIDADE:
            case BUFF_EVASAO:
                return base + "Concede " + "+" + efeito.getValor() + " de " + nomeEfeitoColorido + " por " + efeito.getDuracao() + " turnos.";
            case DEBUFF_ATAQUE:
            case DEBUFF_DEFESA:
            case DEBUFF_AGILIDADE:
                return base + "Aplica " + AnsiColors.red("-" + efeito.getValor() + " de " + nomeEfeitoColorido) + " por " + efeito.getDuracao() + " turnos.";
            default:
                return base + AnsiColors.yellow("Especial.");
        }
    }
}
