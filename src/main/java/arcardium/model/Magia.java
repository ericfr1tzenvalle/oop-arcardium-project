package arcardium.model;

import arcardium.model.enums.TipoDeEfeito;

/**
 * Representa uma magia que pode ser usada por um personagem.
 * Contém todas as informações essenciais sobre um feitiço.
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

   

    public Magia(String nome, String descricao, int custoMana, TipoDeEfeito tipoEfeito, int valorEfeito, int duracaoEfeito) {
        this.nome = nome;
        this.nivel = 1;
        this.descricao = descricao;
        this.custoMana = custoMana;
        this.tipoEfeito = tipoEfeito;
        this.valorEfeito = valorEfeito;
        this.duracaoEfeito = duracaoEfeito;
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
    public void setValorEfeito(int valorEfeito){
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
    public int getNivel(){
        return nivel;
    }
    public void aprimorarMagia(Magia m){
        nivel++;
        setCustoMana(m.getCustoMana() - 10);
        setValorEfeito(m.getValorEfeito() + 10);
    }
    
    

    
}