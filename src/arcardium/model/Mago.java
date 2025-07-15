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

    public void setMagias(List<Magia> magias) {
        this.magias = magias;
    }

    public void aprenderMagia(Magia m) {
        magias.add(m);
    }

    public boolean lancarMagia(Magia magia, Inimigo alvo) {
        int custo = magia.getCustoMana();
        int mpAtual = super.getMp();
        int valor = magia.getValorEfeito();
        int duracaoEfeito = magia.getDuracaoEfeito();

        if (mpAtual >= custo) {
            TipoDeEfeito efeito = magia.getTipoEfeito();
            switch (efeito) {
                case BUFF_ATAQUE:
                    //implementar aumentar o ataque do this.mago no personagem
                    //implementar a duracao desse efeito
                break;
                case CURA:
                    //implementar restaurar vida do this.mago no personagem
                break;
                case BUFF_DEFESA:
                    //mesmo que ataque.
                break;
                case DEBUFF_ATAQUE:
                    // Diminui o ataque do alvo por alguns turnos.
                break;
                case DEBUFF_DEFESA:
                // Diminui a defesa do alvo por alguns turnos.
                break;
                case DANO_POR_TURNO:
                // Causa dano contínuo (veneno/queimadura).
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
