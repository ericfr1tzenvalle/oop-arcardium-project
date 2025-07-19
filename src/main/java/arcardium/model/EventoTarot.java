/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TipoCartaTarot;
import arcardium.model.enums.TipoDeEfeito;
import arcardium.view.EventoView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
                    heroi.aplicarEfeito(TipoDeEfeito.DANO_DIRETO, 20, 2, NomeEfeito.SANGRAMENTO);
                    break;
                case MAGO:
                    view.revelarCarta("O Mago", "O cosmo te presenteia com um novo conhecimento arcano!");
                    if (heroi instanceof Mago) {
                        Mago mago = (Mago) heroi;
                        Magia novaMagia = magiaFactory.criarMagiaUnica(mago.getMagias());
                        mago.aprenderMagia(novaMagia);
                        view.exibirMagiaAprendida(novaMagia);
                    }
                    break;

            }
        } else {
            view.pularEvento();
        }

    }

}
