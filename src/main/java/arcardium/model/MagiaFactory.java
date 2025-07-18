/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;
import arcardium.model.enums.NomeEfeito;
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
                        "Suga vitalidade dos inimigos a cada turno", 
                        10, TipoDeEfeito.DANO_POR_TURNO, 5, 4, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.SANGRAMENTO );    
            case 2:
                return new Magia("Força do URSO", "Voce reune a FORÇA da natureza pra si", 
                        10, 
                        TipoDeEfeito.BUFF_ATAQUE, 10, 
                        2, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO);
            case 3:
                 return new Magia("Pele de PEDRA", "Voce reune a DEFESA da natureza pra si",
                 10,
                 TipoDeEfeito.BUFF_DEFESA,5,3,TipoAlvo.ALIADO,NomeEfeito.PELE_DE_PEDRA);
            
        }
        return null;
        
    }
    
}
