/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model.ia;

import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.Personagem;
import arcardium.model.enums.NomeEfeito;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Luísa
 */
public class ComportamentoBerserker implements Comportamento {
    

    @Override
    public Magia escolherAcao(Inimigo inimigo, List<Personagem> alvos) {
        Random rand = new Random();
        List<Magia> habilidades = inimigo.getHabilidades();
        List<Magia> habilidadesUsadas = inimigo.getHabilidadesUsadasNestaBatalha();
        if(inimigo.getHp() < inimigo.getMaxHp() / 2 && !inimigo.verificaEfeitoAtivo(NomeEfeito.ENFURECIDO)){
            for(Magia habilidade: habilidades){
                if(habilidade.getNomeEfeito() == NomeEfeito.ENFURECIDO){
                    return habilidade;
                }
            }
        }else{
            int hab = rand.nextInt(habilidades.size());
            while(habilidades.get(hab).getNomeEfeito() == NomeEfeito.ENFURECIDO){
                hab = rand.nextInt(habilidades.size());
            }
            return habilidades.get(hab);
        }
        
        return null;
    }


}
