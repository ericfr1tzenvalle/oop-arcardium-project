package arcardium.controller;

import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.Mago;
import java.util.Scanner;

/**
 * Gerencia toda a lógica de uma batalha em turnos entre um Mago e
 * um Inimigo.
 * Esta classe é o "motor" do combate.
 *
 * @author Éric
 */
public class BatalhaController {

    /**
     * Inicia e executa o loop principal de uma batalha.
     * O método só termina quando um dos combatentes é derrotado.
     *
     * @param mago O herói controlado pelo jogador.
     * @param inimigo O adversário a ser enfrentado.
     */
    public void iniciarBatalha(Mago mago, Inimigo inimigo) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Uma batalha entre " + mago.getNome() + " e " + inimigo.getNome() + " Iniciou!");

        // Loop principal da batalha, continua enquanto ambos estiverem vivos.
        while (mago.getHp() > 0 && inimigo.getHp() > 0) {
            System.out.println("\n-- Novo turno -- ");
            System.out.println("Vida do " + mago.getNome() + ": " + mago.getHp() + " | MP: " + mago.getMp());
            System.out.println("Vida do " + inimigo.getNome() + ": " + inimigo.getHp());
            System.out.println("----------------------------------");

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
        System.out.println("\n--- A BATALHA TERMINOU ---");
        if (mago.getHp() > 0) {
            System.out.println(mago.getNome() + " é o vencedor!");
        } else {
            System.out.println(inimigo.getNome() + " venceu. Você foi derrotado.");
        }
    }

    /**
     * Gerencia a lógica de ataque do inimigo durante seu turno.
     *
     * @param inimigo O inimigo que está atacando.
     * @param mago O alvo do ataque do inimigo.
     */
    private void turnoDoInimigo(Inimigo inimigo, Mago mago) {
        System.out.println("\n-> Turno de " + inimigo.getNome() + " <-");
        System.out.println(inimigo.getNome() + " ataca!");
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
        System.out.println("-> Turno de " + mago.getNome() + " <-");
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("Escolha o que deseja fazer: ");
            System.out.println("1. Lançar Magias");
            System.out.println("2. Inventário (Implementar)");
            System.out.println("0. Fugir");
            System.out.print("Digite a opção desejada: ");

            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    for (int i = 0; i < mago.getMagias().size(); i++) {
                        Magia magiaAtual = mago.getMagias().get(i);
                        System.out.println((i + 1) + ": " + magiaAtual.getNome() + " (Custo: " + magiaAtual.getCustoMana() + " MP)");
                    }
                    
                    int escolha = sc.nextInt();
                    Magia magiaEscolhida = mago.getMagias().get(escolha - 1);
                    mago.lancarMagia(magiaEscolhida, inimigo);
                    opcao = 0;
                    break;
                case 2:
                    System.out.println("Não implementado");
                    break;
                case 0:
                    System.out.println("Voce fugiu!");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
    }
}