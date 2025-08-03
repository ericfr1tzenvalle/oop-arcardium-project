package arcardium.controller;

import arcardium.model.*;
import arcardium.model.enums.TipoSala;
import arcardium.utils.ConsoleUtils;
import arcardium.view.BatalhaView;
import arcardium.view.GameView;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameController {

    Scanner sc = new Scanner(System.in);
    InimigoFactory inimigoFactory = new InimigoFactory();
    private final GameView view;
    private MagoFactory MagoFactory = new MagoFactory();

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
            try {
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 1:
                        view.mostrarTelaNome();
                        String nome = sc.nextLine();
                        Mago magoEscolhido = criarMagoPorArquetipo(nome);
                        Jogador jogador = new Jogador(magoEscolhido);
                        executarRun(jogador);
                        break;
                    case 2:
                    case 3:
                    case 4:
                        System.out.println("> Não implementado.");
                        ConsoleUtils.aguardarEnter();
                        break;
                    case 0:
                        ConsoleUtils.digitar("Saindo de Arcardium...", 70);
                        break;
                    default:
                        view.exibirMensagem("> Opção inválida.");
                        ConsoleUtils.aguardarEnter();
                        break;
                }
            } catch (InputMismatchException e) {
                view.exibirMensagem("> Entrada inválida! Digite apenas números.");
                sc.nextLine();
            }
        }
    }

    public Mago criarMagoPorArquetipo(String nomeMago) {
        Mago mago = new Mago("No one", 10, 10, 10, 10, 10, 10, 10, 10);

        while (true) {
            view.mostrarTelaArquetipos();
            int arquetipo = -1;

            try {
                arquetipo = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("> Entrada inválida! Digite apenas números.");
                sc.nextLine();
                continue;
            }

            switch (arquetipo) {
                case 1:
                    mago = MagoFactory.criarMagoDeBatalha(nomeMago);
                    break;
                case 2:
                    mago = MagoFactory.criarMagoArcano(nomeMago);
                    break;
                case 3:
                    mago = MagoFactory.criarEscolhido(nomeMago);
                    break;
                case 4:
                    mago = MagoFactory.criarPadre(nomeMago);
                    break;
                case 5:
                    mago = MagoFactory.criarVampiro(nomeMago);
                    break;
                case 6:
                    mago = MagoFactory.criarNecromante(nomeMago);
                    break;
                default:
                    System.out.println("====404===== [ERRO] =====404====");
                    System.out.println("> ARQUÉTIPO INEXISTENTE. Escolha uma opção válida.");
                    ConsoleUtils.aguardarEnter();
                    continue;
            }

            ConsoleUtils.limparTela();
            view.mostrarArquetipo(mago, arquetipo);

            try {
                int confirmar = sc.nextInt();
                sc.nextLine();
                if (confirmar == 1) {
                    return mago;
                }
            } catch (InputMismatchException e) {
                System.out.println("> Entrada inválida! Voltando ao menu de arquetipos...");
                sc.nextLine();
            }
        }
    }

    public void executarRun(Jogador jogador) {
        try {
            BatalhaController bc = new BatalhaController();
            BatalhaView bv = new BatalhaView();
            MapaController mapaController = new MapaController();
            LojaController lojaController = new LojaController();
            MagiaFactory magiaFactory = new MagiaFactory();

            int ato = 1;
            List<List<Sala>> mapaGerado = mapaController.gerarMapa(5 * ato);
            Mago mago = (Mago) jogador.getHeroi();
            String mensagem = "Os portões se fecharam atrás de você, " + mago.getNome()
                    + ". O único caminho é em frente. Sobreviva.\n";
            ConsoleUtils.digitar(mensagem, 70);
            ConsoleUtils.pausar(1000);

            int andar = 1;
            while (mago.getHp() > 0) {
                for (int i = 0; i < mapaGerado.size(); i++) {
                    List<Sala> andarAtual = mapaGerado.get(i);
                    ConsoleUtils.limparTela();
                    Sala salaAtual = andarAtual.get(0);
                    
                    if (salaAtual.getTipo() == TipoSala.CHEFE) {
                        System.out.println("======= CHEFE A CAMINHO ========");
                        System.out.println("> Acesso a loja liberado.");
                        lojaController.executarFaseDeLoja(jogador, magiaFactory);
                    }

                    bv.exibirHeaderJogador(jogador, (Mago) jogador.getHeroi());
                    System.out.println("=========Arcardium[RPG]=========");
                    System.out.println("            ANDAR [" + (i + 1) + "]");
                    System.out.println("==========  MASMORRA  ==========");
                    System.out.println("        Salas disponíveis");
                    System.out.println("  >" + andarAtual + "<");
                    System.out.println("===== 1 ======= 2 ====== 3 =====");
                    System.out.print("> ");
                    jogador.getHeroi().resetarEfeitos();

                    int escolha = -1;
                    while (true) {
                        try {
                            escolha = sc.nextInt();
                            sc.nextLine();

                            if (escolha < 1 || escolha > andarAtual.size()) {
                                System.out.println("> Sala inválida! Escolha entre 1 e " + andarAtual.size() + ":");
                                System.out.print("> ");
                            } else {
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("> Entrada inválida! Digite apenas números:");
                            System.out.print("> ");
                            sc.nextLine();
                        }
                    }

                    Sala salaEscolhida = andarAtual.get(escolha - 1);
                    
                    if (salaEscolhida.getTipo() == TipoSala.COMBATE) {
                        List<Inimigo> grupoInimigos = inimigoFactory.criarGrupoDeInimigos(andar);
                        ConsoleUtils.limparTela();
                        bc.iniciarBatalha(jogador, grupoInimigos, magiaFactory);

                        if (jogador.getHeroi().getHp() <= 0) {
                            String msgDerrota = "Sua visão escurece... Seu grimório cai no chão, aberto, esperando pelo próximo tolo corajoso o suficiente para tentar.\n";
                            ConsoleUtils.digitar(msgDerrota, 60);
                            System.out.println("-X===========[MORTE]==========X-");
                            ConsoleUtils.pausar(1000);
                            break;
                        }
                    } else if (salaEscolhida.getTipo() == TipoSala.CHEFE) {
                        List<Inimigo> chefe = inimigoFactory.criarChefe(ato, andar);
                        bc.iniciarBatalha(jogador, chefe, magiaFactory);
                    } else if (salaEscolhida.getTipo() == TipoSala.EVENTO) {
                        EventoFactory fc = new EventoFactory();
                        Evento e = fc.criarEventoAleatorio();
                        e.executar(jogador, magiaFactory);
                    }

                    andar++;
                    jogador.setAndarAtual(andar);
                }
            }
            ConsoleUtils.limparTela();
            view.exibirResumoDaRun(jogador, mago);
            ConsoleUtils.aguardarEnter();
            System.out.println("\nRetornando ao menu principal. ---\n");

        } catch (Exception e) {
            System.out.println("> ERRO inesperado na run: " + e.getMessage());
            System.out.println("> Voltando ao menu principal...");
            ConsoleUtils.aguardarEnter();
        }
    }
}
