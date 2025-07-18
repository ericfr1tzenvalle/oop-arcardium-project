package arcardium.controller;

import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.Mago;
import arcardium.model.Jogador;
import arcardium.model.MagiaFactory;
import arcardium.model.enums.TipoAlvo;
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

    /**
     * Inicia e executa o loop principal de uma batalha. O método só termina
     * quando um dos combatentes é derrotado.
     *
     * @param jogador jogador que controla o mago.
     * @param inimigo O adversário a ser enfrentado.
     * @param magiaFactory o criador de magias.
     */
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

        // Seção executada após o fim do loop para determinar o vencedor.
        if (mago.getHp() > 0) {
            view.exibirFimDeBatalha(mago.getNome(), true);
            jogador.ganharXP(120);
            List<Magia> recompensaMagica = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                recompensaMagica.add(magiaFactory.criarMagiaAleatoria());
            }
            view.exibirRecompensaMagias(recompensaMagica);
            int escolhaMagia = sc.nextInt();
            mago.aprenderMagia(recompensaMagica.get(escolhaMagia - 1));
            view.exibirMagiaAprendida(recompensaMagica.get(escolhaMagia - 1));

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

    /**
     * Gerencia a lógica do turno do jogador, exibindo o menu de ações e
     * processando a escolha.
     *
     * @param mago O herói que está realizando a ação.
     * @param inimigo O alvo das ações do herói.
     * @param sc O objeto Scanner para ler a entrada do usuário.
     */
    private void turnoDoJogador(Mago mago, List<Inimigo> grupoInimigos, Scanner sc) {
        view.exibirMenuJogador(mago.getNome());
        int opcao = -1;

        // Loop do menu de ações... (a lógica interna não muda)
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
                        if(mago.lancarMagia(magiaEscolhida, grupoInimigos)){
                            for(Inimigo i: grupoInimigos){
                                view.exibirAtaque(magiaEscolhida,mago,i);
                            }
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
                    // Precisaremos de uma lógica para sair da batalha aqui no futuro
                    break;
                default:
                    view.exibirMensagem("Opção inválida, tente novamente.");
                    break;
            }
        }
    }
}
