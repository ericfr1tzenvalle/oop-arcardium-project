/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.TipoCartaTarot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Luísa
 */
public class EventoTarot extends Evento {

    public EventoTarot() {
        super();
    }

    

    

    //TODO: podemos implementar mais cartas de tarot e repensar um pouco nos efeitos dessas.
    public void executar(Jogador jogador, MagiaFactory magiaFactory) {
        view.mostrarEvento("TAROT");
        int escolha = sc.nextInt();
        if (escolha == 1) {
            List<TipoCartaTarot> baralho = new ArrayList<>(Arrays.asList(TipoCartaTarot.values()));

            Collections.shuffle(baralho);

            view.mostrarOpcoesTarot();
            int escolherCarta = sc.nextInt();

            Heroi heroi = jogador.getHeroi();

            TipoCartaTarot cartaSorteada = baralho.get(escolherCarta - 1);

            switch (cartaSorteada) {
                case SOL:
                    view.revelarCarta("O Sol", "Sua vitalidade é restaurada por completo!");
                    heroi.setHp(heroi.getMaxHp());
                    heroi.setMp(heroi.getMaxMp());
                    break;
                case TORRE:
                    view.revelarCarta("A Torre", "Um mau presságio! Voce está DOENTE");
                    int reducao = (int) (heroi.getMaxHp() * 0.3);
                    heroi.setHp(heroi.getHp() - (reducao));
                    break;
                case MAGO:
                    view.revelarCarta("O Mago", "O cosmo te presenteia com um novo conhecimento ARCANO!\n"
                            + "Você ganhou [+1] espaço de [MAGIA]");
                    if (heroi instanceof Mago) {
                        Mago mago = (Mago) heroi;
                        mago.setTamanho_max_grimorio(mago.getTamanho_max_grimorio() + 1);
                    }
                    break;
                case ENFORCADO:
                    view.revelarCarta("Enforcado", "");

            }
        } else {
            view.pularEvento();
        }

    }

}
