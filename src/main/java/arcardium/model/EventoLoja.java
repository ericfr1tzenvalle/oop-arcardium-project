/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

/**
 *
 * @author Luísa
 */
public class EventoLoja extends Evento {

    public EventoLoja() {
        super();
    }
    

    @Override
    public void executar(Jogador jogador, MagiaFactory magiaFactory) {
        view.mostrarEvento("LOJA");
        
    }
    
}
