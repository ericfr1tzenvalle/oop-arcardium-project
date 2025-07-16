/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.view;

import arcardium.model.Magia;

/**
 *
 * @author ´Éric
 */
public class EventoView {

    public void mostrarEvento(String evento) {
        switch (evento) {
            case "TAROT":
                System.out.println("Você quer tirar sua [SORTE]");
                System.out.println("1. SIM");
                System.out.println("2. NAO");
                break;

            //TODO: Implementar BLACKJACK simples ou alguma outra ideia pensando...
            case "BLACKJACK":
                System.out.println("Que tal um jogar um [JOGUINHO]");
                System.out.println("1. SIM");
                System.out.println("2. NAO");
                break;
        }

    }

    public void mostrarOpcoesTarot() {
        System.out.println("\nSobre a mesa, três cartas emanam uma aura misteriosa.");
        System.out.println("1. A Carta da Esquerda");
        System.out.println("2. A Carta do Meio");
        System.out.println("3. A Carta da Direita");
        System.out.print("Qual você puxa? ");
    }

    public void revelarCarta(String nomeCarta, String descricao) {
        System.out.println("\nVocê vira a carta... É **" + nomeCarta.toUpperCase() + "**!");
        System.out.println(descricao);
    }

    public void exibirMagiaAprendida(Magia m) {
        System.out.println("VOCÊ APRENDEU A MAGIA [" + m.getNome().toUpperCase() + "]");

    }

    public void pularEvento() {
        System.out.println("[ABANDONOU O EVENTO]");
    }
}
