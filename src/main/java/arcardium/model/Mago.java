/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TipoDeEfeito;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Eric
 */
public class Mago extends Heroi {

    private int tamanho_max_grimorio = 4;
    private List<Magia> magias;

    public Mago(String nome, int hp, int mp, int regMp, int atk, int def, int agi, int pre, int eva) {
        super(nome, hp, mp, regMp, atk, def, agi, pre, eva);
        this.magias = new ArrayList<>();

    }

    public List<Magia> getMagias() {
        return magias;
    }

    public List<Magia> getMagiasAprimoraveis() {
        List<Magia> magiasAprimoraveis = new ArrayList<>();
        for (Magia m : magias) {
            if (m.getNivel() < 5) {
                magiasAprimoraveis.add(m);
            }
        }
        return magiasAprimoraveis;
    }

    public void setMagias(List<Magia> magias) {
        this.magias = magias;
    }

    public void setTamanho_max_grimorio(int tamanho_max_grimorio) {
        this.tamanho_max_grimorio = tamanho_max_grimorio;
    }

    public int getTamanho_max_grimorio() {
        return tamanho_max_grimorio;
    }

    public boolean aprenderMagia(Magia m) {
        if (magias.size() < tamanho_max_grimorio) {
            magias.add(m);
            return true;
        }
        return false;
    }

    public void trocarMagia(int indiceAtual, Magia nova) {
        magias.remove(indiceAtual);
        magias.add(indiceAtual, nova);
    }

    public boolean ataqueBasico(Inimigo alvo) {
        int chanceDeAcerto = Math.max(5, Math.min(95, 85 + (this.getPrecisao() - alvo.getEvasao())));
        if (new Random().nextInt(100) >= chanceDeAcerto) {
            alvo.aplicarEfeito(TipoDeEfeito.BUFF_DEFESA, 0, 0, NomeEfeito.ESQUIVOU);
            return false;
        }
        int dano = this.getAtk();
        alvo.tomarDano(dano);
        return true;
    }

    public boolean aprimorarMagia(Magia m) {
        if (m.verificaPodeAprimorar()) {
            m.aprimorarMagia();
            return true;
        }
        return false;

    }

    public void removerMagia(Magia m) {
        this.getMagias().removeIf(magia -> magia.getNome().equals(m.getNome()));
    }

    public boolean lancarMagia(Magia magia, List<Personagem> alvos) {
        return this.lancarHabilidade(magia, alvos);
    }

}
