package arcardium.controller;

import arcardium.model.Curinga;
import arcardium.model.Jogador;
import arcardium.model.Magia;
import arcardium.model.MagiaFactory;
import arcardium.model.Mago;
import arcardium.model.PacoteDeMagia;
import arcardium.model.enums.TagMagia;
import arcardium.utils.ConsoleUtils;
import arcardium.view.BatalhaView; // Usaremos a BatalhaView por enquanto
import arcardium.view.LojaView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LojaController {

    private final LojaView view;
    private final Random rand = new Random();
    private final Scanner sc = new Scanner(System.in);

    public LojaController() {
        this.view = new LojaView();
    }

    public void executarFaseDeLoja(Jogador jogador, MagiaFactory magiaFactory) {
        int rolagem = 0;
        List<PacoteDeMagia> pacotesDisponiveis = gerarEstoqueDePacotes(rolagem);
        // List<Curinga> curingasDisponiveis = gerarEstoqueDeCuringas(rolagem); // Para o futuro

        while (true) {
            view.exibirLoja(jogador, pacotesDisponiveis);

            int escolha = sc.nextInt();
            sc.nextLine();

            if (escolha == 9) {
                ConsoleUtils.digitar("> Você se prepara para a próxima jornada...", 50);
                break;
            }

            if (escolha == 8) {
                rolagem++;
                int precoRolagem = 10 + (rolagem * 5);
                if (jogador.gastarOuro(precoRolagem)) {
                    ConsoleUtils.digitar("> O mercador troca suas mercadorias...\n", 50);
                    pacotesDisponiveis = gerarEstoqueDePacotes(rolagem);
                } else {
                    System.out.println("> Ouro insuficiente para rolar a loja!");
                    rolagem--;
                }
                continue;
            }

            if (escolha >= 1 && escolha <= pacotesDisponiveis.size()) {
                PacoteDeMagia pacoteEscolhido = pacotesDisponiveis.get(escolha - 1);
                if (comprarPacote(jogador, pacoteEscolhido, magiaFactory)) {
                    pacotesDisponiveis.remove(pacoteEscolhido);
                }
            } else {
                view.exibirMensagem("> Opção inválida");
            }
        }
    }

    private List<PacoteDeMagia> gerarEstoqueDePacotes(int rolagem) {
        List<PacoteDeMagia> pacotes = new ArrayList<>();

        pacotes.add(gerarTipoPacote(rolagem));
        pacotes.add(gerarTipoPacote(rolagem));
        pacotes.add(gerarTipoPacote(rolagem));
        return pacotes;
    }

    public PacoteDeMagia gerarTipoPacote(int rolagem) {
        int juros = rolagem * 2;
        int chance = rand.nextInt(100);

        if (chance < 40) {
            int numTematico = rand.nextInt(5);
            switch (numTematico) {
                case 0:
                    return new PacoteDeMagia("Pacote do Piromante", "Contém [3] magias de [FOGO]", 15 + juros, TagMagia.FOGO);
                case 1:
                    return new PacoteDeMagia("Pacote do Criomante", "Contém [3] magias de [GELO]", 15 + juros, TagMagia.GELO);
                case 2:
                    return new PacoteDeMagia("Pacote do Padre", "Contem [3] magias de [CURA]", 15 + juros, TagMagia.CURA);
                case 3:
                    return  new PacoteDeMagia("Pacote do Xamã", "Contem [3] magias da [NATUREZA]", 15 + juros, TagMagia.NATUREZA);
                default:
                    return new PacoteDeMagia("Pacote do Mago Sombrio", "Contem [3] magias de [SOMBRA]", 15 + juros, TagMagia.SOMBRA);

            }
        } else {
            return new PacoteDeMagia("Bolsa do Andarilho", "Contém [3] magias aleatórias", 10 + juros, null);
        }
    }

    private boolean comprarPacote(Jogador jogador, PacoteDeMagia pacote, MagiaFactory magiaFactory) {
        if (jogador.gastarOuro(pacote.getCusto())) {
            ConsoleUtils.digitar("> Abrindo pacote...", 50);
            Mago mago = (Mago) jogador.getHeroi();

            List<Magia> magiasParaEvitar = new ArrayList<>(mago.getMagias());
            List<Magia> opcoesPacote = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                Magia nova;
                if (pacote.getTagDoPacote() == null) {
                    nova = magiaFactory.criarMagiaUnica(magiasParaEvitar);
                } else {
                    nova = magiaFactory.criarMagiaUnicaPorTag(magiasParaEvitar, pacote.getTagDoPacote());
                }

                if (nova != null) {
                    opcoesPacote.add(nova);
                    magiasParaEvitar.add(nova);
                }
            }

            if (opcoesPacote.isEmpty()) {
                view.exibirMensagem("...mas parece que você já conhece todos os feitiços deste tipo. O mercador devolve seu ouro.");
                jogador.ganharOuro(pacote.getCusto());
                return false; 
            }

            BatalhaView batalhaView = new BatalhaView();
            batalhaView.exibirRecompensaMagias(opcoesPacote);
            try {
                int escolha = sc.nextInt();
                if (escolha >= 1 && escolha <= 3) {
                    Magia selecionada = opcoesPacote.get(escolha - 1);
                    if (mago.aprenderMagia(selecionada)) {
                        batalhaView.exibirMagiaAprendida(selecionada);
                    } else {
                        batalhaView.exibirMagiasTroca(mago.getMagias(), selecionada);
                        int indiceTroca = sc.nextInt();
                        if (indiceTroca >= 1 && indiceTroca <= mago.getMagias().size()) {
                            mago.trocarMagia(indiceTroca - 1, selecionada);
                            batalhaView.exibirMagiaAprendida(selecionada);
                        }
                    }

                } else if (escolha == 0) {
                    view.exibirMensagem("> Você optou por não aprender nenhuma magia.");

                }
            } catch (Exception e) {
                view.exibirMensagem("> Entrada inválida, tente novamente.");
                sc.nextLine();
            }

            return true; 
        } else {
            System.out.println("> Ouro insuficiente!");
            return false;
        }
    }
}
