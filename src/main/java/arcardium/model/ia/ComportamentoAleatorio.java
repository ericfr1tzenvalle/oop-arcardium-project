/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model.ia;

import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.Personagem;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Luísa
 */
public class ComportamentoAleatorio implements Comportamento{

    @Override
    public Magia escolherAcao(Inimigo inimigo, List<Personagem> alvos) {
        List<Magia> habilidades = inimigo.getHabilidades();
        Random rand = new Random();
        int hab = rand.nextInt(habilidades.size());
        return habilidades.get(hab);
    }
    
}
