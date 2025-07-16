/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

/**
 *
 * @author Luísa
 */
public class EventoFactory {
    
    public Evento criarEventoAleatorio(){
        return new EventoTarot();
    }
}
