/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arcardium;

import arcardium.model.*;
import arcardium.controller.BatalhaController;
import arcardium.controller.MapaController;
import arcardium.model.enums.TipoSala;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Eric
 */
public class Arcardium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mago mago = new Mago("Gandalf, o Cinzento", 100, 50, 10, 5, 15);
        Inimigo monstro = new Inimigo("Orc de Moria", 40, 0, 12, 4, 8);

        Magia m1 = new Magia("Faisca", "Pequeno raio (1) Alvo", 10, 20);
        Magia m2 = new Magia("Restauração", "Restaura MP", 30, 0);
        Magia raioDeGelo = new Magia("Raio de Gelo", "Um raio que causa dano e pode deixar lento.", 12, 18);
        Magia bolaDeFogo = new Magia("Bola de Fogo", "Uma bola de fogo que queima os inimigos.", 10, 20);

        mago.aprenderMagia(m1);
        mago.aprenderMagia(m2);
        mago.aprenderMagia(raioDeGelo);
        mago.aprenderMagia(bolaDeFogo);

        Jogador jogador = new Jogador(mago);
        BatalhaController bc = new BatalhaController();
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- TESTE DE GERAÇÃO DE MAPA ---");
        MapaController mapaController = new MapaController();
        List<List<Sala>> mapaGerado = mapaController.gerarMapa(5);

        for (int i = 0; i < mapaGerado.size(); i++) {
            List<Sala> andarAtual = mapaGerado.get(i);
            System.out.println("Andar " + (i + 1) + ": " + andarAtual);
            System.out.print("Qual sala deseja entrar: ");
            //Preciso de algo que verifica a sala pra ver se é de combate se for entra na batalha
            int escolha = sc.nextInt();
            Sala salaEscolhida = andarAtual.get(escolha - 1);
            if (salaEscolhida.getTipo() == TipoSala.COMBATE) {
                System.out.println("Você entrou em uma sala de combate!");
                Inimigo novoInimigo = new Inimigo("Goblin Batedor", 50, 0, 15, 5, 10);
                bc.iniciarBatalha(jogador, novoInimigo);

                if (jogador.getHeroi().getHp() <= 0) {
                    System.out.println("Fim de jogo");
                    break;
                }
            } else if (salaEscolhida.getTipo() == TipoSala.CHEFE) {
                System.out.println("Voce entrou na sala do CHEFE!");
                System.out.println("Cuidado!");
                Inimigo chefe = new Inimigo("O Grande Orc", 200, 0, 25, 10, 12);
                bc.iniciarBatalha(jogador, chefe);

            }

        }

    }

}
