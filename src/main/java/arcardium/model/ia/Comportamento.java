/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arcardium.model.ia;

import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.Personagem;
import java.util.List;

/**
 *
 * @author Luísa
 */
public interface Comportamento {
    public Magia escolherAcao(Inimigo inimigo, List<Personagem> alvos);
    
}
