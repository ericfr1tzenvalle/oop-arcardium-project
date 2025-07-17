/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.view;

import arcardium.model.Magia;
import java.util.List;

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
            case "DESCANSO":
                System.out.println("[FOGUEIRA SAGRADA]");
                System.out.println("1. DESCANSAR [Recupere 30% de sua vida máxima]");
                System.out.println("2. APRIMORAR MÁGIA");
                break;
        }

    }
    public void mostrarMensagemDescanso(int escolha){
        switch(escolha){
            case 1:
                System.out.println("Descansou bem e recuperou 30% da vida máxima");
                break;
            case 2:
                System.out.println("Escolha uma de suas magias para receber um UPGRADE!");
                break;
        }
    }
    public void mostrarOpcoesMagias(List<Magia> magias){
        for(Magia m: magias){
            System.out.println(m.getNome() + " | " + m.getDescricao());
        }
    }
    
    public void mostrarMagiaAprimorada(Magia magia){
        if(magia.getNivel() < 3){
        System.out.println("A magia [" + magia.getNome() + "]" + " para o NIVEL [" + magia.getNivel() + "]");  
        }else{
        System.out.println("A magia [" + magia.getNome() + "]" + " chegou ao [NIVEL MAXIMO] !");  
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
