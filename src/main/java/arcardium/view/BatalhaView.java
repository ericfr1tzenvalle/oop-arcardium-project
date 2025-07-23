package arcardium.view;

import arcardium.model.Mago;
import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.enums.TipoDeEfeito;
import java.util.List;
import arcadium.utils.AnsiColors;
import arcadium.utils.ConsoleUtils;
import arcadium.utils.MathUtils;
import arcardium.model.EfeitoAtivo;
import arcardium.model.Jogador;
import arcardium.model.Personagem;
import arcardium.model.enums.NomeEfeito;

/**
 * Responsável por exibir todas as informações da batalha no terminal. Não
 * contém nenhuma lógica de jogo.
 */
public class BatalhaView {

    public void exibirInicioBatalha(String nomeMago, int quantidade, String nomeInimigo) {
        System.out.println("Uma NOVA BATALHA SE INICIA: " + nomeMago + " VS " + quantidade + "X " + nomeInimigo);
    }

    public void exibirStatusTurno(Jogador jogador , Mago mago, List<Inimigo> grupoInimigos) {
        System.out.println("-----------Jogador----------------");
        System.out.println(mago.getNome() + "  [lvl [" + jogador.getNivel() + "]] " + "[Ouro: [" + jogador.getOuro() + "]]" +"\nHP: " + mago.getHp() + "/" + mago.getMaxHp() + " ||" + " MP: " + mago.getMp() + "/" + mago.getMaxMp());
        System.out.println(mago.toStringStatus());
        exibirEfeitosDePersonagem(mago);
        System.out.println("-----------Inimigo(s)-------------");
        for (Inimigo inimigo : grupoInimigos) {
            System.out.println("[" + inimigo.getRank() + "] " + inimigo.getNome() + " HP: " + inimigo.getHp() + "/" + inimigo.getMaxHp());
            System.out.println(inimigo.toStringStatus());
            exibirEfeitosDePersonagem(inimigo);
        }
        System.out.println("<---------------------------------");
    }

    private void exibirEfeitosDePersonagem(Personagem personagem) {
        if (!personagem.getEfeitosAtivos().isEmpty()) {
            System.out.print("Efeitos [ATIVOS]: ");
            for (EfeitoAtivo efeito : personagem.getEfeitosAtivos()) {
                String nomeEfeito = efeito.getNomeEfeito().toString();
                int valor = efeito.getValor();
                int duracao = efeito.getDuracao();
                if (nomeEfeito.startsWith("BUFF")) {
                    System.out.print(AnsiColors.green("[" + nomeEfeito + " +" + valor + " | " + duracao + "T] "));
                } else if (nomeEfeito.startsWith("DEBUFF") || nomeEfeito.startsWith("DANO")) {
                    System.out.print(AnsiColors.red("[" + nomeEfeito + " -" + valor + " | " + duracao + "T] "));
                } else { 
                    System.out.print(AnsiColors.yellow("[" + nomeEfeito + " +" + valor + " | " + duracao + "T] "));
                }
            }
            System.out.println();
        }
    }

    public void exibirOpcoesAlvos(List<Inimigo> grupoInimigos) {
        System.out.println("Deseja atacar: ");
        int cont = 1;
        for (Inimigo i : grupoInimigos) {
            System.out.println("[" + cont++ + "]: " + i.getNome());
        }
    }

    public void exibirMenuJogador(String nomeMago) {
        System.out.println("\n Turno de [" + nomeMago + "]");
        System.out.println("Escolha o que deseja fazer: ");
        System.out.println("1. Lançar Magias");
        System.out.println("2. Inventário (Implementar)");
        System.out.println("3. Defender [Reduz o dano 50%]");
        System.out.println("0. Fugir");
        System.out.print("Digite a opção desejada: ");
    }

    public void exibirMagias(Mago mago) {
        System.out.println("Escolha sua [MAGIA]:");
        for (int i = 0; i < mago.getMagias().size(); i++) {
            Magia magiaAtual = mago.getMagias().get(i);
            System.out.println((i + 1) + ": " + magiaAtual.toString());
        }
    }

    public void exibirMagiasTroca(List<Magia> magias, Magia magiaEscolhida) {
        System.out.println("[MAGIA] a ser ADICIONADA: ");
        System.out.println(magiaEscolhida.toString());
        System.out.println("Magias atuais:");
        int i = 1;
        for (Magia magia : magias) {
            System.out.println(i++ + ": " + magia.toString());
        }
        System.out.println(i++ + ": Pular");
    }

    public void exibirTurnoInimigo(String nomeInimigo) {
        System.out.println("\n-> Turno de [" + nomeInimigo + "]");
    }

  

    public void exibirOrdemDosTurnos(List<Personagem> fila) {
        System.out.println("[Ordem do Combate]");
        for (Personagem f : fila) {
            System.out.println("[" + f.getNome() + "]");
        }
    }
     public void exibirAtaqueInimigo(Magia magiaEscolhida, Inimigo personagem, List<Personagem> inimigos) {
        System.out.print(" [" + personagem.getNome() + "] USA SUA HABILIDADE ");
        System.out.print("[" + magiaEscolhida.getNome().toUpperCase() + "]");
        System.out.print(" " + magiaEscolhida.getDescricao() + " ");
        System.out.println("");
        
        for (Personagem alvo : inimigos) {
            if (alvo.verificaEfeitoAtivo(NomeEfeito.ESQUIVOU)) {
            System.out.println("PORÉM [" + alvo.getNome() + "] SE ESQUIVOU!");

            }else{
             if(magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_DIRETO || magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_POR_TURNO){
             int dano = MathUtils.calculaDano(alvo,magiaEscolhida);
             System.out.print(" Causando " + "[" + dano + "]" + " de DANO no " + "[" + alvo.getNome() + "]\n");
            }else{
             System.out.print(" [" + magiaEscolhida.getTipoEfeito() + "] de " + magiaEscolhida.getValorEfeito());    
             }
             
        }
            
    }
    }
   
    public void exibirAtaque(Magia magiaEscolhida, Mago personagem, List<Inimigo> inimigos) {
        System.out.println(personagem.getNome() + " [LANÇA SUA MAGIA] ");
        System.out.println("[" + magiaEscolhida.getNome().toUpperCase() + "]");
        System.out.println(magiaEscolhida.getDescricao());
        for (Inimigo alvo : inimigos) {
            if (alvo.verificaEfeitoAtivo(NomeEfeito.ESQUIVOU)) {
            System.out.println(alvo.getNome() + " ESQUIVOU!");

            }else{
             if(magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_DIRETO || magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_POR_TURNO){
             int dano = MathUtils.calculaDano(alvo,magiaEscolhida);
             System.out.println("Causando " + "[" + dano + "]" + " de DANO no " + "[" + alvo.getNome() + "]");
            }else{
             System.out.println("[" + magiaEscolhida.getTipoEfeito() + "] de " + magiaEscolhida.getValorEfeito());    
             }
        }
            
    }
    }
    
    public void exibirMagiaAliado(Magia magiaEscolhida, Mago mago) {
        System.out.println(mago.getNome() + " [LANÇA SUA MAGIA] ");
        System.out.println("[" + magiaEscolhida.getNome().toUpperCase() + "]");
        System.out.println(magiaEscolhida.getTipoEfeito() + "|" + magiaEscolhida.getValorEfeito() + "|" + magiaEscolhida.getDuracaoEfeito() + " TURNOS");
    }

    public void exibirInimigoDerrotado(String nome) {
        System.out.println("[" + nome + "] foi DERROTADO!");
    }

    public void exibirFimDeBatalha(String nomeVencedor, boolean heroiVenceu) {
        System.out.println("\n--- A BATALHA TERMINOU ---");
        if (heroiVenceu) {
            System.out.println(nomeVencedor + " é o vencedor!");
        } else {
            System.out.println(nomeVencedor + " venceu. Você foi derrotado.");
        }
    }

    public void exibirRecompensaMagias(List<Magia> magias) {
        System.out.println("\nEscolha sua [MAGIA]");
        int contador = 1;
        for (Magia m : magias) {
            System.out.println(contador++ + ": " + m.toString());

        }
        System.out.println("");
    }

    public void exibirMagiaAprendida(Magia m) {
        System.out.println("VOCE APRENDEU A MAGIA [" + m.getNome() + "]");
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirEsquiva(Personagem atacante, Personagem alvo) {
        System.out.println(AnsiColors.cyan(alvo.getNome()) + " se esquivou do ataque de " + AnsiColors.red(atacante.getNome()) + "!");

    }
}
