/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.controller;

import arcardium.model.Evento;
import arcardium.model.EventoFactory;
import arcardium.model.Inimigo;
import arcardium.model.InimigoFactory;
import arcardium.model.Jogador;
import arcardium.model.Magia;
import arcardium.model.MagiaFactory;
import arcardium.model.Mago;
import arcardium.model.Sala;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
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
    InimigoFactory inimigoFactory = new InimigoFactory();
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
                //Mago de batalha: Alta defesa e durabilidade com dano moderado
                //Para testes
                mago = new Mago(nomeMago, 120, 3000, 8, 10, 12);
                Magia mantoDePedra = new Magia("Manto da força",
                        "Envolve o personagem com um manto que AUMENTA o ATAQUE",
                        20, TipoDeEfeito.BUFF_ATAQUE, 10, 2, TipoAlvo.ALIADO);
                Magia impactoSismico = new Magia("Impacto Sismico",
                        "Golpeia o chão com um soco estrondoso DA DANO EM TODOS", 0, TipoDeEfeito.DANO_DIRETO, 20, 0, TipoAlvo.TODOS_INIMIGOS);
                mago.aprenderMagia(mantoDePedra);
                mago.aprenderMagia(impactoSismico);
                break;
            case 2:
                //Mago Arcano: Alto dano e mana porém fragil
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
        MagiaFactory magiaFactory = new MagiaFactory();

        System.out.println("\n--- A JORNADA DE " + jogador.getHeroi().getNome() + " COMEÇA ---");
        List<List<Sala>> mapaGerado = mapaController.gerarMapa(5);

        for (int i = 0; i < mapaGerado.size(); i++) {
            List<Sala> andarAtual = mapaGerado.get(i);
            System.out.println("\n--- ANDAR " + (i + 1) + " ---");
            System.out.println("Salas disponíveis: " + andarAtual);
            System.out.print("Qual sala deseja entrar: ");
            jogador.getHeroi().resetarEfeitos();

            int escolha = sc.nextInt();
            Sala salaEscolhida = andarAtual.get(escolha - 1);
            
            if (salaEscolhida.getTipo() == TipoSala.COMBATE) {
                System.out.println("Você entrou em uma sala de combate!");
                 List<Inimigo> grupoInimigos = inimigoFactory.criarGrupoDeInimigos(i);
                 bc.iniciarBatalha(jogador, grupoInimigos, magiaFactory);

                // Verifica se o jogador morreu
                if (jogador.getHeroi().getHp() <= 0) {
                    System.out.println("Sua jornada termina aqui...");
                    break;
                }
            } else if (salaEscolhida.getTipo() == TipoSala.CHEFE) {
                System.out.println("Você entrou na sala do CHEFE! Cuidado!");
                Inimigo chefe = new Inimigo("O Grande Orc", 200, 0, 25, 10, 12);
                //bc.iniciarBatalha(jogador, chefe, magiaFactory);
            } else if (salaEscolhida.getTipo() == TipoSala.EVENTO) {
                System.out.println("Encontrou um EVENTO!!!");
                EventoFactory fc = new EventoFactory();
                Evento e = fc.criarEventoAleatorio();
                e.executar(jogador, magiaFactory);
            }

        }

        System.out.println("\n--- A run terminou. Retornando ao menu principal. ---\n");
    }

}
