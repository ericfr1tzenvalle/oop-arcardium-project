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
    NENHUM("Nenhum"),
    VINGANCA("Vingança"),
    ABRAÇO_SOMBRIO("Abraço Sombrio"),
    //CRITICO("CRITICO"),
    VENENO("Veneno"),
    FOGO("Queimadura"),
    SANGRAMENTO("Sangrando"),
    CURA_REGENERATIVA("Regeneração"),
    FORCA_DO_URSO("Força do Urso"),
    MALDICAO("Maldição"),
    AMENDONTRAR("Amendontrado"),
    DEMENTAR("Dementação"), FRACO("Fraco"),
    ESQUIVOU("ESQUIVOU"),
    ASSOMBRAR("ASSOMBRAÇÃO DA DEUSA LUISA"),
    ENFURECIDO("Enfurecido"),
    PELE_DE_PEDRA("Pele de Pedra"),
    RESISTENTE("Resistencia"), GELEIRA("Geleira"),
    AGIL("Agil"),
    INFECTADO("Infectado"), 
    FUGA("Fugindo"), 
    ATORDOADO("Atordoado"), ACAO_EXTRA("Celeridade"), PARALIZAR("Paralizado"), CONGELADO("Congelado"),
    CEGUEIRA("CEGUEIRA"),
    FURIA_GLACIAL("Furia Glacial");
    
    private final String nome;

    NomeEfeito(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return nome;
    }
    
    
    
    
}
