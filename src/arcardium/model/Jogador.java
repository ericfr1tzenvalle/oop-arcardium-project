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

    public void ganharXP(int quantidade) {
        this.xpAtual += quantidade;
        //Testes
        System.out.println(this.heroi.getNome() + " ganhou " + quantidade + " de XP!");
        if (this.xpAtual >= this.getXpParaProximoNivel()) {
            subirDeNivel();
        }
    }

    private void subirDeNivel() {
        this.nivel++;
        this.xpAtual = this.xpAtual - this.xpParaProximoNivel;
        this.xpParaProximoNivel *= 1.5;
        System.out.println("------------------");
        System.out.println("LEVEL UP! Voce alcançou o nivel " + this.nivel + " !");

        this.heroi.setHp(this.heroi.getHp() + 20);
        this.heroi.setMp(this.heroi.getMp() + 10);
        this.heroi.setAtk(this.heroi.getAtk() + 2);
        this.heroi.setDef(this.heroi.getDef() + 1);

        System.out.println("Seus atributos foram fortalecidos!");
        System.out.println("---------------------------------");

    }

}
