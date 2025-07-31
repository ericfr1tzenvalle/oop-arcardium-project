/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model.enums;

/**
 *
 * @author Luísa
 */
public enum NomeEfeito {
    // Nenhum
    NENHUM(""),
    
    // Efeitos de dano ou status negativos
    VENENO("Veneno"),
    FOGO("Queimadura"),
    SANGRAMENTO("Sangrando"),
    MALDICAO("Maldição"),
    INFECTADO("Infectado"),
    CEGUEIRA("Cegueira"),
    ATORDOADO("Atordoado"),
    PARALIZAR("Paralisado"),
    CONGELADO("Congelado"),
    SOFREUCRITICO("Tomou dano crítico"),
    FUGA("Fugindo"),
    
    // Efeitos de buff ou status positivos
    CURA_REGENERATIVA("Regeneração"),
    BANQUETE("Banquete"), // Drenagem do vampiro.
    FORCA_DO_URSO("Força do Urso"),
    PELE_DE_PEDRA("Pele de Pedra"),
    RESISTENTE("Resistência"),
    AGIL("Ágil"),
    ACAO_EXTRA("Celeridade"),
    FURIA_GLACIAL("Fúria Glacial"),
    
    // Outros/Especiais
    VINGANCA("Vingança"),
    ABRACO_SOMBRIO("Abraço Sombrio"),
    AMENDONTRAR("Amendontrado"),
    DEMENTAR("Dementação"),
    FRACO("Fraco"),
    ESQUIVOU("Esquivou"),
    ASSOMBRAR("Assombração da Deusa Luísa"),
    ENFURECIDO("Enfurecido"),
    GELEIRA("Geleira"),
    LENTIDAO("Lentidão");
    
    private final String nome;

    NomeEfeito(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}

