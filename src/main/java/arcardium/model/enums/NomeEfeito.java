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
    NENHUM, // Magias que não deixam efeito
    VENENO,
    FOGO,
    SANGRAMENTO,
    MALDICAO,
    CURA_REGENERATIVA, // poderia ser para curas por turno
    FORCA_DO_URSO, // poderia ser para buffs de ataque
    PELE_DE_PEDRA, // poderia ser para buffs de defesa
    BENÇAO; // vamos implementar a bençao que sera uma magia multipla.
    
}
