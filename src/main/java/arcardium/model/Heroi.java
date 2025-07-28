/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

/**
 *
 * @author Eric
 */
public class Heroi extends Personagem {
    public Heroi(String nome, int hp, int mp, int atk, int def, int agi, int pre, int eva) {
        super(nome,hp,mp,atk,def,agi,pre,eva);
    }
    public Heroi(String nome, int hp, int mp, int regMp, int atk, int def, int agi, int pre, int eva) {
        super(nome,hp,mp,regMp,atk,def,agi,pre,eva);
    }

}
