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
    private String nome;
    private int xp;
    private static final int XP_BASE = 30;
    private int level;
    private int vitorias;
    private int derrotas;
    private List<Heroi> herois;

    public Jogador(String nome, int xp, int level, int vitorias, int derrotas, List herois) {
        this.nome = nome;
        this.xp = xp;
        this.level = level;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.herois = herois;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public int getXp() {
        return xp;
    }

    private void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public int getVitorias() {
        return vitorias;
    }

    private void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    private void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public List getHerois() {
        return herois;
    }

    private void setHerois(List herois) {
        this.herois = herois;
    }
    public int getXpParaProximoNivel(){
        return level * XP_BASE;
    }
    
    @Override
    public String toString(){
        return "Jogador: " + nome + " Level: " + level + " XP: " + xp + "/" + getXpParaProximoNivel() + " Vitorias: " + vitorias;
    }
    
    
    
    
    
    
}
