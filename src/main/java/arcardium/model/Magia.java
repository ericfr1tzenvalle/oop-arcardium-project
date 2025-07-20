package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;

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

    public Magia(String nome, String descricao, int custoMana, TipoDeEfeito tipoEfeito, int valorEfeito, int duracaoEfeito, TipoAlvo tipoAlvo, NomeEfeito efeito) {
        this.nome = nome;
        this.nivel = 1;
        this.descricao = descricao;
        this.custoMana = custoMana;
        this.tipoEfeito = tipoEfeito;
        this.valorEfeito = valorEfeito;
        this.duracaoEfeito = duracaoEfeito;
        this.tipoAlvo = tipoAlvo;
        this.efeito = efeito;
    }

    public String getNome() {
        return nome;
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
        this.custoMana -= 10;
        this.valorEfeito += 10;

        if (this.custoMana < 0) {
            this.custoMana = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("[Lv.%d] %s | Custo: %d | Efeito: %s (%s %d por %d turnos) | Alvo: %s\n"
                + "[%s]",
                nivel, nome, custoMana, efeito, tipoEfeito, valorEfeito, duracaoEfeito, tipoAlvo,descricao);
    }

}
