/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.RankInimigo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Éric
 */
public class Inimigo extends Heroi {
    private List<Magia> habilidades;
    private final RankInimigo rank;
    
    public Inimigo(String nome, int hp, int mp, int atk, int def, int agi, RankInimigo rank) {
        super(nome, hp, mp, atk, def, agi);
        this.habilidades = new ArrayList<>();
        this.rank = rank;
    }
    
    public void aprenderHabilidade(Magia habilidade){
        habilidades.add(habilidade);
    }
    
    public void usarHabilidade(Magia habilidade, List<Personagem> alvos){
        this.lancarHabilidade(habilidade, alvos);
        
    }

    public List<Magia> getHabilidades() {
        return habilidades;
    }

    public RankInimigo getRank() {
        return rank;
    }
    
    public void escolherAcao(){
        
        switch(this.rank){
            case A:
                //Inimigos mais inteligentes, usam debuffs precisos e buffs tambem
                break;
                
                
        }
    }
    
    
    
    
    
}
