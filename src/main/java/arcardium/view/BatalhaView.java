package arcardium.view;

import arcardium.model.Mago;
import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.enums.TipoDeEfeito;
import java.util.List;
import arcadium.utils.AnsiColors;

/**
 * Responsável por exibir todas as informações da batalha no terminal.
 * Não contém nenhuma lógica de jogo.
 */
public class BatalhaView {

    public void exibirInicioBatalha(String nomeMago, int quantidade, String nomeInimigo) {
        System.out.println("Uma batalha COMEÇA: " + nomeMago + " VS " + quantidade + "X " + nomeInimigo);
    }
    
    public void exibirStatusTurno(Mago mago, List<Inimigo> grupoInimigos) {
        System.out.println("\n-- Novo turno -- ");
        System.out.println("Jogador: " + mago.getNome() + AnsiColors.green("\nHP: " + mago.getHp() + "/" + mago.getMaxHp()) + "|" + AnsiColors.blue(" MP: " + mago.getMp() + "/" + mago.getMaxMp()));
        for(Inimigo inimigo: grupoInimigos){
            System.out.println(inimigo.getNome() + " HP: " + inimigo.getHp() + "/" + inimigo.getMaxHp() + " ATK: " + inimigo.getAtk() + " DEF: " + inimigo.getDef());
        }
        System.out.println("----------------------------------");
    }
    public void exibirOpcoesAlvos(List<Inimigo> grupoInimigos){
        System.out.println("Deseja atacar: ");
        int cont = 0;
        for(Inimigo i: grupoInimigos){
            System.out.println(++cont + "->" + i.getNome());
        }
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
        System.out.println(magiaEscolhida.getDescricao());
        if(magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_DIRETO || magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_POR_TURNO){
        int defesa = inimigo.getDef();
        int dano = magiaEscolhida.getValorEfeito() - defesa;
        System.out.println("Causando " + "[" + dano + "]" + " de DANO no " + "[" + inimigo.getNome() + "]");
        }else{
        System.out.println("[" + magiaEscolhida.getTipoEfeito() + "] de " + magiaEscolhida.getValorEfeito());
        }
        
    }
    public void exibirMagiaAliado(Magia magiaEscolhida, Mago mago){
        System.out.println(mago.getNome() + " [LANÇA SUA MAGIA] ");
        System.out.println("[" + magiaEscolhida.getNome().toUpperCase() + "]");
        System.out.println(magiaEscolhida.getTipoEfeito() + "|" + magiaEscolhida.getValorEfeito() + "|" + magiaEscolhida.getDuracaoEfeito() + " TURNOS");
    }
    public void exibirInimigoDerrotado(String nome){
        System.out.println(nome + " foi DERROTADO!");
    }

    public void exibirFimDeBatalha(String nomeVencedor, boolean heroiVenceu) {
        System.out.println("\n--- A BATALHA TERMINOU ---");
        if (heroiVenceu) {
            System.out.println(nomeVencedor + " é o vencedor!");
        } else {
            System.out.println(nomeVencedor + " venceu. Você foi derrotado.");
        }
    }
    public void exibirRecompensaMagias(List<Magia> magias){
        System.out.println("Escolha sua magia");
        int contador = 1;
        for(Magia m: magias){
            System.out.println(contador++ + "[" + m.getNome() + "]" );
        }
    }
    public void exibirMagiaAprendida(Magia m){
        System.out.println("VOCE APRENDEU A MAGIA [" + m.getNome() + "]");
    }
    
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}