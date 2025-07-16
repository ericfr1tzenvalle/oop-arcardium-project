/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;
import arcardium.model.enums.TipoDeEfeito;
import java.util.Random;

/**
 *
 * @author Luísa
 */
public class MagiaFactory {
    
    public Magia criarMagiaAleatoria(){
        Random rand = new Random();
        int magia = rand.nextInt(3) + 1;
        //TODO: Adicionar novas magias pro criador de Magias.
        switch(magia){
            case 1: 
                return new Magia("Manto de Pedra", 
                        "Envolve o personagem com um manto que AUMENTA a defesa", 
                        20, TipoDeEfeito.BUFF_DEFESA, 10, 2);    
            case 2:
                return new Magia("Manto de Pedra", 
                        "Envolve o personagem com um manto que AUMENTA a defesa", 
                        20, TipoDeEfeito.BUFF_DEFESA, 10, 2);    
            case 3:
                return new Magia("Manto de Pedra", 
                        "Envolve o personagem com um manto que AUMENTA a defesa", 
                        20, TipoDeEfeito.BUFF_DEFESA, 10, 2);    
            
        }
        return null;
        
    }
    
}
