/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model.enums;

/**
 *
 * @author Luísa
 */
public enum TipoDeEfeito {
    DANO_DIRETO,    // O que já temos: causa dano puro.
    CURA,           // Recupera o HP do alvo.
    BUFF_ATAQUE,    // Aumenta o ataque do alvo por alguns turnos.
    BUFF_DEFESA,    // Aumenta a defesa do alvo por alguns turnos.
    DEBUFF_ATAQUE,  // Diminui o ataque do alvo por alguns turnos.
    DEBUFF_DEFESA,  // Diminui a defesa do alvo por alguns turnos.
    DANO_POR_TURNO  // Causa dano contínuo (veneno/queimadura).
}