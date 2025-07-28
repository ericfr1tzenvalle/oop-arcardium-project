/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model.enums;

/**
 *
 * @author Éric
 */
public enum TipoDeEfeito {
    DANO_DIRETO("Dano"),    // O que já temos: causa dano puro.
    CURA("Cura"),           // Recupera o HP do alvo.
    BUFF_ATAQUE("Buff de Ataque"),    // Aumenta o ataque do alvo por alguns turnos.
    BUFF_DEFESA("Buff de Defesa"),  
    BUFF_EVASAO("Buff de Evasao"), // Aumenta a defesa do alvo por alguns turnos.
    BUFF_AGILIDADE("Buff de Agilidade"), // Aumenta a agilidade do alvo
    DEBUFF_ATAQUE("Debuff de ataque"),  // Diminui o ataque do alvo por alguns turnos.
    DEBUFF_DEFESA("Debuff de defesa"),  // Diminui a defesa do alvo por alguns turnos.
    DEBUFF_AGILIDADE("Debuff de agilidade"),
    DEBUFF_PRECISAO("Debuff precisao"),
    DEBUFF_EVASAO("Debuff evasao"),
    DANO_POR_TURNO("Dano por turno"),
    PARALIZANTE("Paralizante");
    // Causa dano contínuo (veneno/queimadura).
    
    private final String nome;
    
    TipoDeEfeito(String nome){
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return nome;
    }
    
}