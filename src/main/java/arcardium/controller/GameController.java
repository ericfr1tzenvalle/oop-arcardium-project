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
                    case 2: case 3: case 4:
                        System.out.println("> Não implementado.");
                        break;
                    case 0:
                        ConsoleUtils.digitar("Saindo de Arcardium...", 70);
                        break;
                    default:
                        view.exibirMensagem("> Opção inválida.");
                        break;
                }
            } catch (Exception e) {
                view.exibirMensagem("> Entrada inválida, por favor digite um número.");
                sc.nextLine(); 
            }
        }
    }

    public Mago criarMagoPorArquetipo(String nomeMago) {
        Mago mago;
        while (true) {
            view.mostrarTelaArquetipos();
            int arquetipo = sc.nextInt();
            sc.nextLine();

            switch (arquetipo) {
                case 1:
                    mago = new Mago(nomeMago, 120, 40, 8, 10, 12, 10, 5);
                    mago.aprenderMagia(new Magia("Força do Urso", "Aumenta o ATAQUE", 10, TipoDeEfeito.BUFF_ATAQUE, 10, 2, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO, List.of(TagMagia.NATUREZA, TagMagia.BUFF)));
                    mago.aprenderMagia(new Magia("Impacto Sísmico", "Dano em TODOS os inimigos", 15, TipoDeEfeito.DANO_DIRETO, 15, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM, List.of(TagMagia.DANO, TagMagia.AREA)));
                    return mago;
                case 2:
                    mago = new Mago(nomeMago, 80, 80, 12, 4, 15, 15, 15);
                    return mago;
                case 3:
                    mago = new Mago(nomeMago, 100, 50, 10, 5, 15, 10, 10);
                    return mago;
                default:
                    System.out.println("====404===== [ERRO] =====404====");
                    System.out.println("> ARQUÉTIPO INEXISTENTE. Escolha uma opção válida.");
                    ConsoleUtils.aguardarEnter();
                    
            }
        }
    }

    public void executarRun(Jogador jogador) {
        BatalhaController bc = new BatalhaController();
        MapaController mapaController = new MapaController();
        LojaController lojaController = new LojaController();
        MagiaFactory magiaFactory = new MagiaFactory();
        List<List<Sala>> mapaGerado = mapaController.gerarMapa(3);
        Mago mago = (Mago) jogador.getHeroi();
        String mensagem = "Os portões se fecharam atrás de você, " + mago.getNome() + ". O único caminho é em frente. Sobreviva.";
        ConsoleUtils.digitar(mensagem, 70);
        ConsoleUtils.pausar(1000);
        int rodada = 1;

        while(mago.getHp() > 0){
        for (int i = 0; i < mapaGerado.size(); i++) {
            List<Sala> andarAtual = mapaGerado.get(i);
            ConsoleUtils.limparTela();
            Sala salaAtual = andarAtual.get(0);
            if(salaAtual.getTipo() == TipoSala.CHEFE){
            System.out.println("======= CHEFE A CAMINHO ========");
            System.out.println("> Acesso a loja liberado.");
            ConsoleUtils.aguardarEnter();
            lojaController.executarFaseDeLoja(jogador, magiaFactory);    
            }
            
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
            rodada++;

        }
        
        }

        System.out.println("\n--- A run terminou. Retornando ao menu principal. ---\n");
    }

}
;