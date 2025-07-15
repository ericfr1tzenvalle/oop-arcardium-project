package arcardium.controller;

import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.Mago;
import arcardium.model.Jogador;
import arcardium.model.MagiaFactory;
import arcardium.view.BatalhaView;
import java.util.ArrayList;
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
    public void iniciarBatalha(Jogador jogador, Inimigo inimigo , MagiaFactory magiaFactory) {
        Mago mago = (Mago) jogador.getHeroi();
        
        Scanner sc = new Scanner(System.in);
        view.exibirInicioBatalha(mago.getNome(), inimigo.getNome());
        // Loop principal da batalha, continua enquanto ambos estiverem vivos.
        while (mago.getHp() > 0 && inimigo.getHp() > 0){
            view.exibirStatusTurno(mago, inimigo);
             

            // Executa o turno do jogador
            turnoDoJogador(mago, inimigo, sc);

            // Verifica se o inimigo foi derrotado antes de poder atacar
            if (inimigo.getHp() <= 0) {
                break;
            }

            // Executa o turno do inimigo
            turnoDoInimigo(inimigo, mago);
        }

        // Seção executada após o fim do loop para determinar o vencedor.
        if (mago.getHp() > 0) {
            view.exibirFimDeBatalha(mago.getNome(), true);
            jogador.ganharXP(120);
            List<Magia> recompensaMagica = new ArrayList<>();
            for(int i = 0; i < 3; i++){
                recompensaMagica.add(magiaFactory.criarMagiaAleatoria());
            }
            view.exibirRecompensaMagias(recompensaMagica);
            int escolhaMagia = sc.nextInt();
            mago.aprenderMagia(recompensaMagica.get(escolhaMagia - 1));
            view.exibirMagiaAprendida(recompensaMagica.get(escolhaMagia - 1));
            
            
        } else {
            view.exibirFimDeBatalha(inimigo.getNome(), false);
        }
    }

    /**
     * Gerencia a lógica de ataque do inimigo durante seu turno.
     *
     * @param inimigo O inimigo que está atacando.
     * @param mago O alvo do ataque do inimigo.
     */
    private void turnoDoInimigo(Inimigo inimigo, Mago mago) {
        view.exibirTurnoInimigo(inimigo.getNome());
        inimigo.atacar(mago);
    }

    /**
     * Gerencia a lógica do turno do jogador, exibindo o menu de ações e
     * processando a escolha.
     *
     * @param mago O herói que está realizando a ação.
     * @param inimigo O alvo das ações do herói.
     * @param sc O objeto Scanner para ler a entrada do usuário.
     */
private void turnoDoJogador(Mago mago, Inimigo inimigo, Scanner sc) {
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
                    if(mago.lancarMagia(magiaEscolhida, inimigo)){
                        view.exibirAtaque(magiaEscolhida, mago, inimigo);
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
