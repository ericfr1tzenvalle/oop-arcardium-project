/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.controller;

import arcardium.model.Inimigo;
import arcardium.model.Jogador;
import arcardium.model.Mago;
import arcardium.model.Sala;
import arcardium.model.enums.TipoSala;
import arcardium.view.GameView;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Éric
 */
public class GameController {

    Scanner sc = new Scanner(System.in);
    private final GameView view;

    public GameController() {
        this.view = new GameView();
    }

    public void iniciarJogo() {
        menuPrincipal();

    }

    public void menuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            view.mostrarMenu();
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    view.mostrarTelaNome();
                    String nome = sc.nextLine();
                    view.mostrarTelaArquetipos();
                    int arquetipo = sc.nextInt();
                    Mago magoEscolhido = criarMagoPorArquetipo(nome, arquetipo);
                    Jogador jogador = new Jogador(magoEscolhido);
                    executarRun(jogador);
                    break;

            }

        }

    }

    public Mago criarMagoPorArquetipo(String nomeMago, int arquetipo) {
        Mago mago = new Mago(nomeMago, 1, 1, 1, 1, 1);
        switch (arquetipo) {
            case 1:
                mago = new Mago(nomeMago, 120, 30, 8, 10, 12);
                break;
            case 2:
                mago = new Mago(nomeMago, 80, 80, 12, 4, 15);
                break;
            case 3:
                mago = new Mago(nomeMago, 100, 50, 10, 5, 15);
                break;
            default:
                System.out.println("Opção Inválida");

        }
        return mago;

    }

    public void executarRun(Jogador jogador) {
        BatalhaController bc = new BatalhaController();
        MapaController mapaController = new MapaController();

        System.out.println("\n--- A JORNADA DE " + jogador.getHeroi().getNome() + " COMEÇA ---");
        List<List<Sala>> mapaGerado = mapaController.gerarMapa(5);
        
        for (int i = 0; i < mapaGerado.size(); i++) {
            List<Sala> andarAtual = mapaGerado.get(i);
            System.out.println("\n--- ANDAR " + (i + 1) + " ---");
            System.out.println("Salas disponíveis: " + andarAtual);
            System.out.print("Qual sala deseja entrar: ");

            int escolha = sc.nextInt();
            Sala salaEscolhida = andarAtual.get(escolha - 1);

            if (salaEscolhida.getTipo() == TipoSala.COMBATE) {
                System.out.println("Você entrou em uma sala de combate!");
                Inimigo novoInimigo = new Inimigo("Goblin Batedor", 50, 0, 15, 5, 10);
                bc.iniciarBatalha(jogador, novoInimigo);

                // Verifica se o jogador morreu
                if (jogador.getHeroi().getHp() <= 0) {
                    System.out.println("Sua jornada termina aqui...");
                    break;
                }
            } else if (salaEscolhida.getTipo() == TipoSala.CHEFE) {
                System.out.println("Você entrou na sala do CHEFE! Cuidado!");
                Inimigo chefe = new Inimigo("O Grande Orc", 200, 0, 25, 10, 12);
                bc.iniciarBatalha(jogador, chefe);
            }
        }

       
        System.out.println("\n--- A run terminou. Retornando ao menu principal. ---\n");
    }

}
