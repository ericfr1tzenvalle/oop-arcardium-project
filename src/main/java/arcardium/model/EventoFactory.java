/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import static java.lang.Math.random;
import java.util.Random;



/**
 *
 * @author Luísa
 */
public class EventoFactory {
   
    
    public Evento criarEventoAleatorio(){
        
        Random random = new Random();
        
        int chance = random.nextInt(100);
        
        if(chance < 70){
            return new EventoSantuario();
        }else{
            return new EventoTarot();
        }
        
    }
}
