package arcardium.model;

/**
 * Representa uma magia que pode ser usada por um personagem.
 * Contém todas as informações essenciais sobre um feitiço.
 *
 * @author Éric
 */
public class Magia {

    private String nome;
    private String descricao;
    private int custoMana;
    private int danoBase;

    /**
     * Construtor para criar uma nova magia.
     *
     * @param nome O nome da magia (ex: "Bola de Fogo").
     * @param descricao Uma breve explicação do que a magia faz.
     * @param custoMana A quantidade de mana necessária para lançá-la.
     * @param danoBase O dano que a magia causa antes de considerar a defesa do alvo.
     */
    public Magia(String nome, String descricao, int custoMana, int danoBase) {
        this.nome = nome;
        this.descricao = descricao;
        this.custoMana = custoMana;
        this.danoBase = danoBase;
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

    public int getDanoBase() {
        return danoBase;
    }
}