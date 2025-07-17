/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.TipoDeEfeito;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
//Implementar o Mago que vai ter metodos diferentes para o ataque.
public class Mago extends Heroi {

    private List<Magia> magias;

    public Mago(String nome, int hp, int mp, int atk, int def, int agi) {
        super(nome, hp, mp, atk, def, agi);
        this.magias = new ArrayList<>();

    }

    public List<Magia> getMagias() {
        return magias;
    }
    
    public List<Magia> getMagiasAprimoraveis(){
        List<Magia> magiasAprimoraveis = new ArrayList<>();
        for(Magia m: magias){
            if(m.getNivel() < 3){
                magiasAprimoraveis.add(m);
            }
        }
        return magiasAprimoraveis;
    }

    public void setMagias(List<Magia> magias) {
        this.magias = magias;
    }

    public void aprenderMagia(Magia m) {
        magias.add(m);
    }
    
    public void aprimorarMagia(Magia m){
        m.aprimorarMagia();
    }

    public boolean lancarMagia(Magia magia, Inimigo alvo) {
        int custo = magia.getCustoMana();
        int mpAtual = super.getMp();
        int valor = magia.getValorEfeito();
        int duracao = magia.getDuracaoEfeito();

        if (mpAtual >= custo) {
            TipoDeEfeito efeito = magia.getTipoEfeito();
            switch (efeito) {
                case BUFF_ATAQUE:
                    this.aplicarEfeito(efeito, valor, duracao);
                    break;
                case CURA:
                    this.receberCura(valor);
                    break;
                case BUFF_DEFESA:
                    this.aplicarEfeito(efeito, valor, duracao);
                    break;
                case DEBUFF_ATAQUE:
                    alvo.aplicarEfeito(efeito, valor, duracao);
                    break;
                case DEBUFF_DEFESA:
                    alvo.aplicarEfeito(efeito, valor, duracao);
                    break;
                case DANO_POR_TURNO:
                    alvo.aplicarEfeito(efeito, valor, duracao);
                    break;
                case DANO_DIRETO:
                    alvo.tomarDano(valor);

            }
            super.setMp(mpAtual - custo);
            return true;
        } else {
            System.out.println("MP insuficiente para lançar " + magia.getNome());
            return false;
        }
    }

}
