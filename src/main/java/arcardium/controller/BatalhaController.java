package arcardium.controller;

import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.Mago;
import arcardium.model.Jogador;
import arcardium.model.MagiaFactory;
import arcardium.model.Personagem;
import arcardium.view.BatalhaView;
import java.util.ArrayList;
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
        Mago mago = (Mago) jogador.getHeroi();
        
        Scanner sc = new Scanner(System.in);
        view.exibirInicioBatalha(mago.getNome(),grupoInimigos.size(), "Inimigos");
        // Loop principal da batalha, continua enquanto ambos estiverem vivos.
        while (mago.getHp() > 0 && !grupoInimigos.isEmpty()) {
            
            //Verificamos os efeitos, VENENO, SANGRAMENTO, CURA ETC.
            for(Inimigo inimigos: grupoInimigos){
                inimigos.processarEfeitosPorTurno();
            }
            mago.processarEfeitosPorTurno();
            //Verificamos se algum inimigo morreu no processo dos EFEITOS
            removerInimigosDerrotados(grupoInimigos);
            if(grupoInimigos.isEmpty() || mago.getHp() <= 0){
                break;
            }
            
            view.exibirStatusTurno(mago, grupoInimigos);

            // Executa o turno do jogador
            turnoDoJogador(mago, grupoInimigos, sc);

           removerInimigosDerrotados(grupoInimigos);
           
           if(!grupoInimigos.isEmpty()){
               turnoDosInimigos(grupoInimigos,mago);
           }
            

            
        }

        if (mago.getHp() > 0) {
            view.exibirFimDeBatalha(mago.getNome(), true);
            jogador.ganharXP(120);
            List<Magia> magiasParaEvitar = new ArrayList<>(mago.getMagias());
            List<Magia> recompensaMagica = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Magia novaMagiaUnica = magiaFactory.criarMagiaUnica(magiasParaEvitar);
                recompensaMagica.add(novaMagiaUnica);
                magiasParaEvitar.add(novaMagiaUnica);
            }
            view.exibirRecompensaMagias(recompensaMagica);
            int magiaEscolhida = sc.nextInt();
            if(mago.aprenderMagia(recompensaMagica.get(magiaEscolhida - 1))){
                view.exibirMagiaAprendida(recompensaMagica.get(magiaEscolhida - 1));
            }else{
                view.exibirMagiasTroca(mago.getMagias(), recompensaMagica.get(magiaEscolhida - 1));
                int magiaTroca = sc.nextInt();
                mago.trocarMagia(magiaTroca, recompensaMagica.get(magiaEscolhida - 1));
                view.exibirMagiaAprendida(recompensaMagica.get(magiaEscolhida - 1));
            }
            

        } else {
            for(Inimigo i: grupoInimigos){
                view.exibirFimDeBatalha(i.getNome(), true);
            }
        }
    }

    
    private void turnoDosInimigos(List<Inimigo> grupoInimigos, Mago mago) {
        for (Inimigo inimigo : grupoInimigos) {
            view.exibirTurnoInimigo(inimigo.getNome());
            inimigo.atacar(mago);
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
                    switch(magiaEscolhida.getTipoAlvo()){
                        case ALVO_UNICO:
                        if(grupoInimigos.size() > 1){
                        view.exibirOpcoesAlvos(grupoInimigos);
                        escolha = sc.nextInt();
                        Inimigo inimigo = grupoInimigos.get(escolha - 1);
                        if (mago.lancarMagia(magiaEscolhida, List.of(inimigo))) {
                        view.exibirAtaque(magiaEscolhida, mago, inimigo);
                        }
                    }else{
                        if (mago.lancarMagia(magiaEscolhida, List.of(grupoInimigos.getFirst()))) {
                        view.exibirAtaque(magiaEscolhida, mago, grupoInimigos.getFirst());
                        } 
                    }
                        break;
                        case TODOS_INIMIGOS:
                        List<Personagem> grupoAlvos = new ArrayList<>(grupoInimigos);
                        if(mago.lancarMagia(magiaEscolhida, grupoAlvos)){
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
