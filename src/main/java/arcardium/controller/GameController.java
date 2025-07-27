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
import arcardium.model.enums.TagMagia;
import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.RankInimigo;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
import arcardium.model.enums.TipoSala;
import arcardium.model.ia.Comportamento;
import arcardium.model.ia.ComportamentoAleatorio;
import arcardium.model.ia.ComportamentoSequencial;
import arcardium.utils.ConsoleUtils;
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
                case 2:
                case 3:
                case 4:
                    System.out.println("> Não implementado.");
                    break;
                case 0:
                    ConsoleUtils.digitar("Saindo...", 100);
                    
                    

            }

        }

    }

    public Mago criarMagoPorArquetipo(String nomeMago, int arquetipo) {
        Mago mago = new Mago(nomeMago, 1, 1, 1, 1, 1,10,10);
        switch (arquetipo) {
            case 1:
                mago = new Mago(nomeMago, 120, 3000, 8, 10, 12,10,8);
                Magia agilidadeZero = new Magia("Agilidade zero", "Deixa o alvo sem nada de agilidade", 20, TipoDeEfeito.DEBUFF_AGILIDADE,1000,2,TipoAlvo.ALVO_UNICO, NomeEfeito.AMENDONTRAR, List.of(TagMagia.CONTROLE));
                Magia mantoDePedra = new Magia("Força do URSO",
                        "Envolve o personagem com um manto que AUMENTA o ATAQUE",
                        20, TipoDeEfeito.BUFF_ATAQUE, 10, 2, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO, List.of(TagMagia.NATUREZA, TagMagia.BUFF, TagMagia.ALVO_UNICO));
                Magia impactoSismico = new Magia("Impacto Sismico",
                        "Golpeia o chão com um soco estrondoso DA DANO EM TODOS",
                        0, TipoDeEfeito.DANO_DIRETO, 20, 0, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.DANO,TagMagia.AREA));
                mago.aprenderMagia(mantoDePedra);
                mago.aprenderMagia(impactoSismico);
                break;
            case 2:
                mago = new Mago(nomeMago, 80, 80, 12, 4, 15,15,12);
                break;
            case 3:
                mago = new Mago(nomeMago, 100, 50, 10, 5, 15,20,20);
                break;
            default:
                 System.out.println("====404===== [ERRO] =====404====");
                 System.out.println("> ARQUÉTIPO INEXISTENTE");
                 ConsoleUtils.aguardarEnter();
                 view.mostrarTelaArquetipos();
                 int arquetipoErro = sc.nextInt();
                 criarMagoPorArquetipo(nomeMago,arquetipoErro);

        }
        return mago;

    }

    public void executarRun(Jogador jogador) {
        BatalhaController bc = new BatalhaController();
        MapaController mapaController = new MapaController();
        MagiaFactory magiaFactory = new MagiaFactory();
        List<List<Sala>> mapaGerado = mapaController.gerarMapa(20);
        Mago mago = (Mago) jogador.getHeroi();
        String mensagem = "Os portões se fecharam atrás de você, " + mago.getNome() + ". O único caminho é em frente. Sobreviva.";
        ConsoleUtils.digitar(mensagem, 70);
        ConsoleUtils.pausar(1000);


        for (int i = 0; i < mapaGerado.size(); i++) {
            List<Sala> andarAtual = mapaGerado.get(i);
            ConsoleUtils.limparTela();
            System.out.println("=========Arcardium[RPG]=========");
            System.out.println("            ANDAR [" + (i + 1) + "]");
            System.out.println("==========  MASMORRA  ==========");
            System.out.println("        Salas disponíveis");
            System.out.println("  >" + andarAtual + "<");
            System.out.println("===== 1 ======= 2 ====== 3 =====");
            System.out.print("> ");
            jogador.getHeroi().resetarEfeitos();

            int escolha = sc.nextInt();
            Sala salaEscolhida = andarAtual.get(escolha - 1);
            
            if (salaEscolhida.getTipo() == TipoSala.COMBATE) {
                 List<Inimigo> grupoInimigos = inimigoFactory.criarGrupoDeInimigos(i);
                 bc.iniciarBatalha(jogador, grupoInimigos, magiaFactory);
                if (jogador.getHeroi().getHp() <= 0) {
                    String msgDerrota = "Sua visão escurece... Seu grimório cai no chão, aberto, esperando pelo próximo tolo corajoso o suficiente para tentar.";
                    ConsoleUtils.digitar(msgDerrota, 120);
                    System.out.println("X===========[MORTE]==========X");
                    
                    break;
                }
            } else if (salaEscolhida.getTipo() == TipoSala.CHEFE) {
                Comportamento a = new ComportamentoAleatorio();
                Inimigo chefe = new Inimigo("O Grande Orc", 200, 0, 25, 10, 12,20,0, RankInimigo.B,new ComportamentoSequencial());
                bc.iniciarBatalha(jogador, List.of(chefe), magiaFactory);
            } else if (salaEscolhida.getTipo() == TipoSala.EVENTO) {
                EventoFactory fc = new EventoFactory();
                Evento e = fc.criarEventoAleatorio();
                e.executar(jogador, magiaFactory);
            }

        }

        System.out.println("\n--- A run terminou. Retornando ao menu principal. ---\n");
    }

}
