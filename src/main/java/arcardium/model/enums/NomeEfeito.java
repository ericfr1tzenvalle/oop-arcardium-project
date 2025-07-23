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
    //CRITICO("CRITICO"),
    VENENO("Veneno"),
    FOGO("Queimadura"),
    SANGRAMENTO("Sangrando"),
    CURA_REGENERATIVA("Regeneração"),
    FORCA_DO_URSO("Força do Urso"),
    MALDICAO("Maldição"),
    AMENDONTRAR("Amendontrado"),
    DEMENTAR("Dementação"),
    ESQUIVOU("ESQUIVOU"),
    ASSOMBRAR("ASSOMBRAÇÃO DA DEUSA LUISA"),
    ENFURECIDO("Enfurecido"),
    PELE_DE_PEDRA("Pele de Pedra"),
    RESISTENTE("Resistencia"),
    AGIL("Agil"),
    INFECTADO("Infectado");
    
    private final String nome;

    NomeEfeito(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return nome;
    }
    
    
    
    
}
