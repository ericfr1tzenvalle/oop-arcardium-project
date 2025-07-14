/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

/**
 *
 * @author Éric
 */
public abstract class Personagem {
    private String nome;
    private int hp; // saude
    private int mp; // mana
    private int atk; // ataque
    private int def; // defesa
    private int agi; // agilidade

    public Personagem(String nome, int hp, int mp, int atk, int def, int agi) {
        this.nome = nome;
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.agi = agi;
    }
    
     public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getAgi() {
        return agi;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    protected void setHp(int hp) {
        this.hp = hp;
    }

    protected void setMp(int mp) {
        this.mp = mp;
    }

    protected void setAtk(int atk) {
        this.atk = atk;
    }

    protected void setDef(int def) {
        this.def = def;
    }

    protected void setAgi(int agi) {
        this.agi = agi;
    }
    
    public void atacar(Heroi h){
        h.tomarDano(atk);
    }
    
    public void tomarDano(int dano) {
        int danoReal = dano - this.def;
        if (danoReal > 0) {
            this.hp -= danoReal;
        }
    }
    
    

}
