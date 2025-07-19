/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */

public class Mago extends Heroi {
    private int tamanho_max_grimorio = 4;
    private List<Magia> magias;

    public Mago(String nome, int hp, int mp, int atk, int def, int agi) {
        super(nome, hp, mp, atk, def, agi);
        this.magias = new ArrayList<>();

    }

    public List<Magia> getMagias() {
        return magias;
    }

    public List<Magia> getMagiasAprimoraveis() {
        List<Magia> magiasAprimoraveis = new ArrayList<>();
        for (Magia m : magias) {
            if (m.getNivel() < 3) {
                magiasAprimoraveis.add(m);
            }
        }
        return magiasAprimoraveis;
    }

    public void setMagias(List<Magia> magias) {
        this.magias = magias;
    }

    public boolean aprenderMagia(Magia m) {
        if(magias.size() < tamanho_max_grimorio){
            magias.add(m);
            return true;
        }
        return false;
    }
    
    
    public void trocarMagia(int indiceAtual, Magia nova){
        magias.remove(indiceAtual);
        magias.add(nova);
    }

    public void aprimorarMagia(Magia m) {
        m.aprimorarMagia();
    }
    public boolean lancarMagia(Magia magia, List<Personagem> alvos){
        return this.lancarHabilidade(magia, alvos);
    }

    
}
