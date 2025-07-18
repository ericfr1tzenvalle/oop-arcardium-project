/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;
import arcardium.model.enums.TipoAlvo;
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
                return new Magia("Sugada Nervosa", 
                        "Suga todas as defesas dos inimigos", 
                        20, TipoDeEfeito.DEBUFF_DEFESA, 10, 2, TipoAlvo.TODOS_INIMIGOS);    
            case 2:
                return new Magia("Mordida", 
                        "Morde um alvo e se cura", 
                        20, TipoDeEfeito.CURA, 20, 1, TipoAlvo.ALVO_UNICO);    
            case 3:
                return new Magia("Manto de PIEDRA", 
                        "Envolve o personagem com um manto que AUMENTA a defesa", 
                        20, TipoDeEfeito.BUFF_DEFESA, 10, 2, TipoAlvo.ALIADO);    
            
        }
        return null;
        
    }
    
}
