package arcardium.controller;

import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.Mago;
import arcardium.model.Jogador;
import arcardium.model.MagiaFactory;
import arcardium.model.Personagem;
import arcardium.view.BatalhaView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Gerencia toda a lógica de uma batalha em turnos entre um Mago e um Inimigo.
 * Esta classe é o "motor" do combate.
 *
 * @author Éric
 */
public class BatalhaController {

    private final BatalhaView view;

    public BatalhaController() {
        this.view = new BatalhaView();
    }

    public void iniciarBatalha(Jogador jogador, List<Inimigo> grupoInimigos, MagiaFactory magiaFactory) {
       
        List<Personagem> filaDeTurnos = new ArrayList<>();
        Mago mago = (Mago) jogador.getHeroi();
        filaDeTurnos.add(mago);
        for (Inimigo inimigo : grupoInimigos) {
            filaDeTurnos.add(inimigo);
        }
        filaDeTurnos.sort(Comparator.comparingInt(Personagem::getAgi).reversed());
        Scanner sc = new Scanner(System.in);
        view.exibirInicioBatalha(mago.getNome(), grupoInimigos.size(), "Inimigos");
        int recompensaGold = 0;
        int recompensaXp = 0;
        for (Inimigo i : grupoInimigos) {
            recompensaGold += i.getRecompensaOuro();
            recompensaXp += i.getRecompensaXp();
        }
        // Loop principal da batalha, continua enquanto ambos estiverem vivos.
        while (mago.getHp() > 0 && !grupoInimigos.isEmpty()) {
            
            
            System.out.println("<---Inicio da rodada--->");
            view.exibirOrdemDosTurnos(filaDeTurnos);
            
            for (Personagem personagemDaVez : filaDeTurnos) {
                
                if(personagemDaVez.getHp() <= 0){
                    continue;
                }
                personagemDaVez.processarEfeitosPorTurno();
                if(personagemDaVez.getHp() <= 0){
                  removerInimigosDerrotados(grupoInimigos);
                  continue;
                }
                view.exibirStatusTurno(mago, grupoInimigos);
                
                if (personagemDaVez instanceof Mago) {
                    turnoDoJogador(mago, grupoInimigos, sc);
                } else if(personagemDaVez instanceof Inimigo){
                    Inimigo inimigoAtivo = (Inimigo) personagemDaVez;
                    List<Personagem> alvo = new ArrayList<>();
                    alvo.add(mago);
                    
                    view.exibirTurnoInimigo(inimigoAtivo.getNome());
                    Magia magiaSelecionada = inimigoAtivo.escolherAcao(inimigoAtivo, alvo);
                    if (inimigoAtivo.lancarHabilidade(magiaSelecionada, alvo)) {
                        view.exibirAtaqueInimigo(magiaSelecionada, alvo, inimigoAtivo);
                    }
                }
              
            }
            removerInimigosDerrotados(grupoInimigos);  
            if (grupoInimigos.isEmpty() || mago.getHp() <= 0) {
                break;
            }
        }

        if (mago.getHp() > 0) {
            view.exibirFimDeBatalha(mago.getNome(), true);
            jogador.ganharXP(recompensaXp, mago);
            List<Magia> magiasParaEvitar = new ArrayList<>(mago.getMagias());
            List<Magia> recompensaMagica = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Magia novaMagiaUnica = magiaFactory.criarMagiaUnica(magiasParaEvitar);
                recompensaMagica.add(novaMagiaUnica);
                magiasParaEvitar.add(novaMagiaUnica);
            }
            view.exibirRecompensaMagias(recompensaMagica);
            int magiaEscolhida = sc.nextInt();
            if (magiaEscolhida < 4) {

                if (mago.aprenderMagia(recompensaMagica.get(magiaEscolhida - 1))) {
                    view.exibirMagiaAprendida(recompensaMagica.get(magiaEscolhida - 1));
                } else {
                    view.exibirMagiasTroca(mago.getMagias(), recompensaMagica.get(magiaEscolhida - 1));
                    int magiaTroca = sc.nextInt();
                    mago.trocarMagia(magiaTroca - 1, recompensaMagica.get(magiaEscolhida - 1));
                    view.exibirMagiaAprendida(recompensaMagica.get(magiaEscolhida - 1));
                }
            }else{
                System.out.println("Pulou");
            }

        } else {
            for (Inimigo i : grupoInimigos) {
                view.exibirFimDeBatalha(i.getNome(), true);
            }
        }
    }
    
    private void removerInimigosDerrotados(List<Inimigo> grupoInimigos) {
        Iterator<Inimigo> iterator = grupoInimigos.iterator();
        while (iterator.hasNext()) {
            Inimigo inimigo = iterator.next();
            if (inimigo.getHp() <= 0) {
                view.exibirInimigoDerrotado(inimigo.getNome());
                iterator.remove();
            }
        }
    }

    private void turnoDoJogador(Mago mago, List<Inimigo> grupoInimigos, Scanner sc) {
        view.exibirMenuJogador(mago.getNome());
        int opcao = -1;

        while (opcao != 0) {
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    view.exibirMagias(mago);
                    int escolha = sc.nextInt();
                    Magia magiaEscolhida = mago.getMagias().get(escolha - 1);
                    switch (magiaEscolhida.getTipoAlvo()) {
                        case ALVO_UNICO:
                            if (grupoInimigos.size() > 1) {
                                view.exibirOpcoesAlvos(grupoInimigos);
                                escolha = sc.nextInt();
                                Inimigo inimigo = grupoInimigos.get(escolha - 1);
                                if (mago.lancarMagia(magiaEscolhida, List.of(inimigo))) {
                                    view.exibirAtaque(magiaEscolhida, mago, inimigo);
                                }
                            } else {
                                if (mago.lancarMagia(magiaEscolhida, List.of(grupoInimigos.getFirst()))) {
                                    view.exibirAtaque(magiaEscolhida, mago, grupoInimigos.getFirst());
                                }
                            }
                            break;
                        case TODOS_INIMIGOS:
                            List<Personagem> grupoAlvos = new ArrayList<>(grupoInimigos);
                            if (mago.lancarMagia(magiaEscolhida, grupoAlvos)) {
                                view.exibirAtaqueTodos(magiaEscolhida, mago, grupoInimigos);
                            }
                            break;
                        case ALIADO:
                            if (mago.lancarMagia(magiaEscolhida, new ArrayList<>())) {
                                view.exibirMagiaAliado(magiaEscolhida, mago);
                            }
                            break;

                    }
                    opcao = 0;
                    break;
                case 2:
                    view.exibirMensagem("Não implementado");
                    break;
                case 0:
                    view.exibirMensagem("Voce fugiu!");

                    break;
                default:
                    view.exibirMensagem("Opção inválida, tente novamente.");
                    break;
            }
        }
    }

    
}
