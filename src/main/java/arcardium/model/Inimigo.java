/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Éric
 */
public class Inimigo extends Heroi {
    private List<Magia> habilidades;
    
    public Inimigo(String nome, int hp, int mp, int atk, int def, int agi) {
        super(nome, hp, mp, atk, def, agi);
        this.habilidades = new ArrayList<>();
    }
    
    
}
