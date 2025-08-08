/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.view;

import arcardium.model.Jogador;
import arcardium.model.Magia;
import arcardium.model.MagiaFactory;
import arcardium.utils.AnsiColors;
import arcardium.utils.ConsoleUtils;
import java.util.List;

/**
 *
 * @author ´Éric
 */
public class EventoView {

    public void mostrarEvento(String evento) {
        switch (evento) {
            case "TAROT":
                System.out.println("============ [ TAROT ] =============");
                System.out.println("Você quer tirar sua [SORTE]");
                System.out.println("1. SIM");
                System.out.println("2. NAO");
                System.out.println("============ [ TAROT ] =============");
                System.out.print("> ");
                break;
            case "BLACKJACK":
                System.out.println("========== [ BLACKJACK ] ===========");
                System.out.println("Que tal um jogar um [JOGUINHO]");
                System.out.println("1. SIM");
                System.out.println("2. NAO");
                System.out.println("========== [ BLACKJACK ] ===========");
                System.out.print("> ");
                break;
            case "DESCANSO":
                System.out.println("=========== [ FOGUEIRA ] ===========");
                System.out.println("[FOGUEIRA SAGRADA]");
                System.out.println("1. DESCANSAR [Recupere 30% de sua vida máxima]");
                System.out.println("2. APRIMORAR MÁGIA");
                System.out.println("============ [ SAGRADA ] ===========");
                System.out.print("> ");
                break;
            case "LOJA":
                System.out.println("[LOJA]");
                break;
            case "SANTUARIO":
                System.out.println("========== [ SANTUARIO ] ===========");
                System.out.println(">  Uma sala silenciosa com um altar antigo no centro. \n"
                        + "> Você pode fazer uma oferenda em troca de uma bênção.");
                System.out.println("========== [ SANTUARIO ] ===========");
                ConsoleUtils.aguardarEnter();
                break;

        }

    }

    public void mostrarOpcoesMagias(List<Magia> magias) {
        System.out.println("======= [ APRIMORAR MAGIA ] ========");
        int i = 1;
        System.out.println("Suas magias:");
        for (Magia m : magias) {
            System.out.println(i++ + ": " + m.toString());
        }
        System.out.println("=========== [ ESCOLHA ] ============");
        System.out.print("> ");

    }

    public void mostrarMagiaAprimorada(Magia magia) {
        if (magia.getNivel() < 3) {
            System.out.println("======== [ SUBIU DE NIVEL ] =========");
            System.out.println("> Magia [" + magia.getNome() + "]" + " foi aprimorada " + AnsiColors.yellow("[" + magia.getNivel() + "]"));
            ConsoleUtils.pausar(2000);
        } else {
            System.out.println("> Magia [" + magia.getNome() + "]" + " chegou ao " + AnsiColors.yellow("[NIVEL MAXIMO]"));
            ConsoleUtils.pausar(2000);
        }

    }

    public void mostrarOpcoesTarot() {
        System.out.println("============ [ TAROT ] =============");
        System.out.println("\nSobre a mesa, três cartas emanam uma aura misteriosa.");
        System.out.println("1. A Carta da Esquerda");
        System.out.println("2. A Carta do Meio");
        System.out.println("3. A Carta da Direita");
        System.out.print("Qual você puxa? ");
    }

    public void revelarCarta(String nomeCarta, String descricao) {
        System.out.println("\nVocê vira a carta... É **" + nomeCarta.toUpperCase() + "**!");
        System.out.println(descricao);
        ConsoleUtils.pausar(2000);
    }

    public void exibirMagiaAprendida(Magia m) {
        System.out.println("MAGIA [" + m.getNome().toUpperCase() + "] APRENDIDA");
        ConsoleUtils.pausar(2000);

    }

    public void pularEvento() {
        System.out.println("X==== [ ABANDONOU O EVENTO ] ======X");

    }

    public void mostrarOpcoesSantuario(Jogador jogador, MagiaFactory magiaFactory) {
        System.out.println("========== [ SANTUARIO ] ===========");
        System.out.println("[1] Perder 10% de vida > +10 ATK");
        System.out.println("[2] Gastar ouro para aprimoramento [ALEATORIO]");
        System.out.println("[3] Esquecer MAGIA em troca de [XP]");
        System.out.println("========== [ SANTUARIO ] ===========");
        System.out.print("> ");
    }
}
