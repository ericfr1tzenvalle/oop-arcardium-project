package arcardium.view;

import arcardium.model.Mago;
import arcardium.model.Inimigo;
import arcardium.model.Magia;

/**
 * Responsável por exibir todas as informações da batalha no terminal.
 * Não contém nenhuma lógica de jogo.
 */
public class BatalhaView {

    public void exibirInicioBatalha(String nomeMago, String nomeInimigo) {
        System.out.println("Uma batalha entre " + nomeMago + " e " + nomeInimigo + " Iniciou!");
    }

    public void exibirStatusTurno(Mago mago, Inimigo inimigo) {
        System.out.println("\n-- Novo turno -- ");
        System.out.println("Vida do " + mago.getNome() + ": " + mago.getHp() + " | MP: " + mago.getMp());
        System.out.println("Vida do " + inimigo.getNome() + ": " + inimigo.getHp());
        System.out.println("----------------------------------");
    }

    public void exibirMenuJogador(String nomeMago) {
        System.out.println("\n-> Turno de " + nomeMago + " <-");
        System.out.println("Escolha o que deseja fazer: ");
        System.out.println("1. Lançar Magias");
        System.out.println("2. Inventário (Implementar)");
        System.out.println("0. Fugir");
        System.out.print("Digite a opção desejada: ");
    }

    public void exibirMagias(Mago mago) {
        System.out.println("Escolha sua magia:");
        for (int i = 0; i < mago.getMagias().size(); i++) {
            Magia magiaAtual = mago.getMagias().get(i);
            System.out.println((i + 1) + ": " + magiaAtual.getNome() + " (Custo: " + magiaAtual.getCustoMana() + " MP)");
        }
    }
    
    public void exibirTurnoInimigo(String nomeInimigo) {
        System.out.println("\n-> Turno de " + nomeInimigo + " <-");
        System.out.println(nomeInimigo + " ataca!");
    }
    public void exibirAtaque(Magia magiaEscolhida, Mago mago, Inimigo inimigo){
        System.out.println(mago.getNome() + " [LANÇA SUA MAGIA] ");
        System.out.println("[" + magiaEscolhida.getNome().toUpperCase() + "]");
        int defesa = inimigo.getDef();
        int dano = magiaEscolhida.getDanoBase() - defesa;
        System.out.println("Causando " + "[" + dano + "]" + " de DANO no " + "[" + inimigo.getNome() + "]");
    }

    public void exibirFimDeBatalha(String nomeVencedor, boolean heroiVenceu) {
        System.out.println("\n--- A BATALHA TERMINOU ---");
        if (heroiVenceu) {
            System.out.println(nomeVencedor + " é o vencedor!");
        } else {
            System.out.println(nomeVencedor + " venceu. Você foi derrotado.");
        }
    }
    
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}