/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model.enums;


/**
 * Representa os tipos de alvo que uma magia pode atingir.
 * Agora com nome personalizado.
 * 
 * @author Luísa
 */
public enum TipoAlvo {
    ALVO_UNICO("Alvo Único"),
    TODOS_INIMIGOS("Todos os Inimigos"),
    ALEATORIO("Alvo Aleatório"),
    ALIADO("Aliado");

    private final String nome;

    TipoAlvo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
