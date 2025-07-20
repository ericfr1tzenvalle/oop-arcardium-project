/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import java.util.List;

/**
 *
 * @author Éric
 *
 */
public class Jogador {

    private Heroi heroi;
    private int nivel;
    private int xpAtual;
    private int xpParaProximoNivel;

    public Jogador(Heroi heroi) {
        this.heroi = heroi;
        this.nivel = 1;
        this.xpAtual = 0;
        this.xpParaProximoNivel = 100; // O primeiro nível requer 100 de XP
    }

    public Heroi getHeroi() {
        return heroi;
    }

    private void setHeroi(Heroi heroi) {
        this.heroi = heroi;
    }

    public int getNivel() {
        return nivel;
    }

    private void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getXpAtual() {
        return xpAtual;
    }

    private void setXpAtual(int xpAtual) {
        this.xpAtual = xpAtual;
    }

    public int getXpParaProximoNivel() {
        return xpParaProximoNivel;
    }

    private void setXpParaProximoNivel(int xpParaProximoNivel) {
        this.xpParaProximoNivel = xpParaProximoNivel;
    }

    public void ganharXP(int quantidade, Personagem alvo) {
        this.xpAtual += quantidade;
        //Testes
        System.out.println(this.heroi.getNome() + " ganhou " + quantidade + " de XP!");
        if (this.xpAtual >= this.getXpParaProximoNivel()) {
            subirDeNivel(alvo);
        }
    }

    private void subirDeNivel(Personagem alvo) {
        this.nivel++;
        this.xpAtual = this.xpAtual - this.xpParaProximoNivel;
        this.xpParaProximoNivel *= 1.5;
        System.out.println("------------------");
        System.out.println("LEVEL UP! Voce alcançou o nivel " + this.nivel + " !");
        alvo.setHp(alvo.getHp()+ 20);
        alvo.setMaxHp(alvo.getMaxHp() + 5);
        if(alvo.getHp() > alvo.getMaxHp()){
            alvo.setHp(alvo.getMaxHp());
        }
        alvo.setMp(alvo.getMp() + 30);
        alvo.setMaxMp(alvo.getMaxHp() + 10);
        if(alvo.getMp() > alvo.getMaxMp()){
            alvo.setMp(alvo.getMaxMp());
        }
        alvo.setDef(alvo.getDef() + 1);
        alvo.setAtk(alvo.getAtk() + 2);
        
        
       

        System.out.println("Seus atributos foram fortalecidos!");
        System.out.println("---------------------------------");

    }

}
