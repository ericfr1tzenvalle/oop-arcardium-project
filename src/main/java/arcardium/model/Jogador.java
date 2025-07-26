/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.utils.ConsoleUtils;

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
    private int ouro;
    private int pontuacao;

    public Jogador(Heroi heroi) {
        this.heroi = heroi;
        this.nivel = 1;
        this.xpAtual = 0;
        this.ouro = 0;
        this.pontuacao = 0;
        this.xpParaProximoNivel = 100; // O primeiro nível requer 100 de XP
    }

    public Heroi getHeroi() {
        return heroi;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    private void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    public void addPontuacao(int valor){
        this.pontuacao += valor;
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
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
        if (this.xpAtual >= this.getXpParaProximoNivel()) {
            subirDeNivel(this.heroi);
        }
    }
    
    public void ganharOuro(int quantidade){
        this.ouro += quantidade;

    }
    
    public boolean gastarOuro(int quantidade){
        if(quantidade <= this.ouro){
            this.ouro -= quantidade;
            return true;
        }
        return false;
    }

    private void subirDeNivel(Personagem alvo) {
        this.nivel++;
        this.xpAtual -= this.xpParaProximoNivel;
        this.xpParaProximoNivel *= 1.2;

        // Ganhos base + um pequeno bônus que escala com o nível atual
        int ganhoMaxHp = 10 + (this.nivel * 2); // Ex: No Nv 2 ganha 14, no Nv 10 ganha 30
        int ganhoMaxMp = 5 + this.nivel;      // Ex: No Nv 2 ganha 7, no Nv 10 ganha 15
        int ganhoAtk = 2;
        int ganhoDef = 1;

        // Bonus ao mago a cada 5 níveis.
        if (this.nivel % 5 == 0) {
            ganhoAtk += 3;
        }
        System.out.println("<<<<<<<<<<< [LEVEL UP! >>>>>>>>>>>>");
        System.out.println("Você alcançou o Nível [" +  this.nivel + "]!");

        System.out.printf("HP Máximo: %d > %d (+%d)%n", alvo.getMaxHp(), alvo.getMaxHp() + ganhoMaxHp, ganhoMaxHp);
        System.out.printf("MP Máximo: %d > %d (+%d)%n", alvo.getMaxMp(), alvo.getMaxMp() + ganhoMaxMp, ganhoMaxMp);
        System.out.printf("ATK: %d > %d (+%d)%n", alvo.getAtk(), alvo.getAtk() + ganhoAtk, ganhoAtk);
        System.out.printf("DEF: %d > %d (+%d)%n", alvo.getDef(), alvo.getDef() + ganhoDef, ganhoDef);
        System.out.println("====================================");
        ConsoleUtils.aguardarEnter();

        
        alvo.setMaxHp(alvo.getMaxHp() + ganhoMaxHp);
        alvo.setMaxMp(alvo.getMaxMp() + ganhoMaxMp);

        alvo.setHp(alvo.getMaxHp());
        alvo.setMp(alvo.getMaxMp());

        alvo.setAtk(alvo.getAtk() + ganhoAtk);
        alvo.setDef(alvo.getDef() + ganhoDef);
    }

}
