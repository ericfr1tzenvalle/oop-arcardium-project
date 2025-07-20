/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.RankInimigo;
import arcardium.model.ia.Comportamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Éric
 */
public class Inimigo extends Heroi {
    private List<Magia> habilidades;
    private final RankInimigo rank;
    private Comportamento comportamento;
    
    public Inimigo(String nome, int hp, int mp, int atk, int def, int agi, RankInimigo rank, Comportamento comportamento) {
        super(nome, hp, mp, atk, def, agi);
        this.habilidades = new ArrayList<>();
        this.rank = rank;
        this.comportamento = comportamento;
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
    public boolean possuiHabilidade(NomeEfeito efeito){
        for(Magia m: habilidades){
            if(m.getNomeEfeito() == efeito){
                return true;
            }
            
        }
        return false;
    }
    public Magia retornaMagiaEspecifica(NomeEfeito efeito){
        for(Magia m: habilidades){
            if(m.getNomeEfeito() == efeito){
                return m;
            }
            
        }
        return null;
    }
    
    public Magia escolherAcao(Inimigo inimigo, List<Personagem> alvo){
        return this.comportamento.escolherAcao(this, alvo);
    }
    
    
    
    
    
}
