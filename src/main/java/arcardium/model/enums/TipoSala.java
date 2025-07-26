/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model.enums;

/**
 *
 * @author Éric
 */
public enum TipoSala {
    COMBATE("Combate"),
    EVENTO("Evento"),
    LOJA("Loja"),
    CHEFE("Chefe"),
    DESCANSO("Descanso");

    private String nome;

    TipoSala(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    @Override
    public String toString() {
        return "[" + nome + "]";
    }
    
}
