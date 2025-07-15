/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;
import java.util.Random;

/**
 *
 * @author Luísa
 */
public class MagiaFactory {
    
    public Magia criarMagiaAleatoria(){
        Random rand = new Random();
        int magia = rand.nextInt(3) + 1;
        
        switch(magia){
            case 1: 
                return new Magia("Corrente de Raios", "Atinge um inimigo e salta para outro", 18, 25);
            case 2:
                return new Magia("Barreira Mística", "Cria um escudo que absorve dano", 15, 0);
            case 3:
                return new Magia("Drenar Vida", "Causa dano e cura parte da sua vida", 20, 15);
            
        }
        return null;
        
    }
    
}
