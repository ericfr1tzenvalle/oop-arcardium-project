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

/**
 *
 * @author Éric
 */
public class Inimigo extends Heroi {

    private int recompensaXp;
    private int recompensaOuro;
    private List<Magia> habilidades;
    private final RankInimigo rank;
    private Comportamento comportamento;
    private List<Magia> habilidadesUsadasNestaBatalha;
    private int indiceHabilidadeSequencial;

    public Inimigo(String nome, int hp, int mp, int atk, int def, int agi, int pre, int eva, RankInimigo rank, Comportamento comportamento) {
        super(nome, hp, mp, atk, def, agi, pre, eva);
        this.habilidades = new ArrayList<>();
        this.rank = rank;
        this.comportamento = comportamento;
        this.habilidadesUsadasNestaBatalha = new ArrayList<>();
        this.indiceHabilidadeSequencial = 0;

    }

    public void setRecompensaXp(int valor) {
        this.recompensaXp = valor;

    }

    public void setRecompensaOuro(int valor) {
        this.recompensaOuro = valor;
    }

    public int getRecompensaXp() {
        return recompensaXp;
    }

    public int getRecompensaOuro() {
        return recompensaOuro;
    }

    public List<Magia> getHabilidadesUsadasNestaBatalha() {
        return habilidadesUsadasNestaBatalha;
    }

    public void setHabilidadesUsadasNestaBatalha(List<Magia> habilidadesUsadasNestaBatalha) {
        this.habilidadesUsadasNestaBatalha = habilidadesUsadasNestaBatalha;
    }

    public int getIndiceHabilidadeSequencial() {
        return indiceHabilidadeSequencial;
    }

    public void setIndiceHabilidadeSequencial(int indiceHabilidadeSequencial) {
        this.indiceHabilidadeSequencial = indiceHabilidadeSequencial;
    }

    public void aprenderHabilidade(Magia habilidade) {
        habilidades.add(habilidade);
    }

    public void usarHabilidade(Magia habilidade, List<Personagem> alvos) {
        this.lancarHabilidade(habilidade, alvos);

    }

    public List<Magia> getHabilidades() {
        return habilidades;
    }

    public RankInimigo getRank() {
        return rank;
    }

    public boolean verificaMagiaUsada(NomeEfeito nome) {
        for (Magia magia : habilidadesUsadasNestaBatalha) {
            if (magia.getNomeEfeito() == nome) {
                return true;
            }
        }
        return false;
    }

    public Magia escolherAcao(Inimigo inimigo, List<Personagem> alvo) {
        return this.comportamento.escolherAcao(this, alvo);
    }
    
    @Override
    public String toString(){
        return  "[" + super.getNome() + "]";
    }

}
