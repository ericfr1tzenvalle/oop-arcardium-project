/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class ComportamentoSequencial implements Comportamento{
   
    @Override
    public Magia escolherAcao(Inimigo inimigo, List<Personagem> alvos) {
        //Pegar as habilidades do inimigo
        List<Magia> habilidades = inimigo.getHabilidades();
        //Indice da magia 
        int indiceAtual = inimigo.getIndiceHabilidadeSequencial();
        //Pegamos a magia
        Magia habilidade = habilidades.get(indiceAtual);
        //Subimos o indice
        int proximoIndice = (indiceAtual + 1) % habilidades.size();
        //Setamos ele  
        inimigo.setIndiceHabilidadeSequencial(proximoIndice);
        //Retornamos a acao.
        return habilidade;
    }
    
}
