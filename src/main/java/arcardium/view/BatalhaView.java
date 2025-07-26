package arcardium.view;

import arcardium.model.Mago;
import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.enums.TipoDeEfeito;
import java.util.List;
import arcardium.utils.AnsiColors;
import arcardium.utils.ConsoleUtils;
import arcardium.utils.MathUtils;
import arcardium.model.EfeitoAtivo;
import arcardium.model.Jogador;
import arcardium.model.Personagem;
import arcardium.model.enums.NomeEfeito;

/**
 * Responsável por exibir todas as informações da batalha no terminal. Não
 * contém nenhuma lógica de jogo.
 */
public class BatalhaView {

    public void exibirStatusTurno(Jogador jogador, Mago mago, List<Inimigo> grupoInimigos) {

        System.out.println("============[ JOGADOR ]=============");
        String header = String.format("[Nvl.%d] %s   \nHP:[%s] %d/%d \nMP:[%s] %d/%d", jogador.getNivel(), mago.getNome(), criarBarra(mago.getHp(), mago.getMaxHp(), 14), mago.getHp(), mago.getMaxHp(),
                criarBarra(mago.getMp(), mago.getMaxMp(), 14), mago.getMp(), mago.getMaxMp());
        System.out.println(header);

        System.out.println("============[ STATUS ]==============");
        System.out.println("ATK: " + mago.getAtk() + "   DEF: " + mago.getDef() + "   SOR: " + mago.getDef());
        System.out.println("AGI: " + mago.getAgi() + "  PRE: " + mago.getPrecisao() + "  EVA: " + mago.getEvasao());

        exibirEfeitosDePersonagem(mago);

        System.out.println("===========[ INIMIGO(S) ]===========");
        for (Inimigo inimigo : grupoInimigos) {
            String headerInimigo = String.format("[Rank.%s] %s   \nHP:[%s] %d/%d", inimigo.getRank(), inimigo.getNome(), criarBarra(inimigo.getHp(), inimigo.getMaxHp(), 12), inimigo.getHp(), inimigo.getMaxHp());
            System.out.println(headerInimigo);

            exibirEfeitosDePersonagem(inimigo);
        }

    }

    private String criarBarra(int atual, int maximo, int tamanho) {
        if (maximo <= 0) {
            return ".".repeat(tamanho);
        }
        int preenchido = (int) (((double) Math.max(0, atual) / maximo) * tamanho);
        String barraPreenchida = "■".repeat(preenchido);
        String barraVazia = ".".repeat(tamanho - preenchido);
        return barraPreenchida + barraVazia;
    }

    private void exibirEfeitosDePersonagem(Personagem personagem) {
        if (!personagem.getEfeitosAtivos().isEmpty()) {
            System.out.println("============ [EFEITOS] =============");
            System.out.print("[ATIVOS]: ");
            for (EfeitoAtivo efeito : personagem.getEfeitosAtivos()) {
                String nomeEfeito = efeito.getNomeEfeito().toString();
                int valor = efeito.getValor();
                int duracao = efeito.getDuracao();
                if (nomeEfeito.startsWith("BUFF")) {
                    System.out.print("[" + nomeEfeito + " +" + valor + " | " + duracao + "T] ");
                } else if (nomeEfeito.startsWith("DEBUFF") || nomeEfeito.startsWith("DANO")) {
                    System.out.print("[" + nomeEfeito + " -" + valor + " | " + duracao + "T] ");
                } else {
                    System.out.print("[" + nomeEfeito + " +" + valor + " | " + duracao + "T] ");
                }
            }
            System.out.println();
        }
    }

    public void exibirOpcoesAlvos(List<Inimigo> grupoInimigos) {
        System.out.println("Deseja atacar: ");
        int cont = 1;
        for (Inimigo inimigo : grupoInimigos) {
            System.out.println("[" + cont++ + "]: " + inimigo.getNome() + " [HP: " + inimigo.getHp() + "/" + inimigo.getMaxHp() + "]");
        }
        System.out.print(">");
    }

    public void exibirMenuJogador(String nomeMago) {
        System.out.println("Escolha o que deseja fazer: ");
        System.out.println("[1] Lançar Magias" + " [2] Defender" + " [0] Fugir");
        System.out.print("> ");
    }

    public void exibirMagias(Mago mago) {
        System.out.println("Escolha sua [MAGIA]:");
        for (int i = 0; i < mago.getMagias().size(); i++) {
            Magia magiaAtual = mago.getMagias().get(i);
            System.out.println((i + 1) + ": " + magiaAtual.toString());
        }
        System.out.println("< Voltar [0]");
        System.out.print("> ");
    }

    public void exibirMagiasTroca(List<Magia> magias, Magia magiaEscolhida) {
        System.out.println("========== [TROCAR MAGIA] ==========");
        System.out.println("[MAGIA] a ser adicionada: ");
        System.out.println(magiaEscolhida.toString());
        System.out.println("Suas magias: ");
        int i = 1;
        for (Magia magia : magias) {
            System.out.println(i++ + ": " + magia.toString());
        }
        System.out.println("========== [TROCAR MAGIA] ==========");
        System.out.print("> ");

    }

    public void exibirTurnoInimigo(String nomeInimigo) {
        System.out.println("\n-> Turno de [" + nomeInimigo + "]");
    }

    public void exibirOrdemDosTurnos(List<Personagem> fila) {
        System.out.println("============= [ORDEM] ==============");

        int c = 1;
        for (Personagem f : fila) {
            System.out.println(c++ + ": [" + f.getNome() + "]");
        }
    }

    public void exibirAtaqueInimigo(Magia magiaEscolhida, Inimigo personagem, List<Personagem> inimigos) {
        System.out.print(" [" + personagem.getNome() + "] usa sua habilidade ");
        System.out.print("[" + AnsiColors.magenta(magiaEscolhida.getNome().toUpperCase()) + "]");
        System.out.print(" " + magiaEscolhida.getDescricao() + " \n");

        for (Personagem alvo : inimigos) {
            if (alvo.verificaEfeitoAtivo(NomeEfeito.ESQUIVOU)) {
                System.out.println("> [" + alvo.getNome() + "] SE ESQUIVOU!");
                alvo.removerEfeito(NomeEfeito.ESQUIVOU);
            } else {
                if (magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_DIRETO || magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_POR_TURNO) {
                    int dano = MathUtils.calculaDano(alvo, magiaEscolhida);
                    System.out.print("> Causando " + "[" + AnsiColors.red(dano) + "]" + " de DANO no " + "[" + AnsiColors.red(alvo.getNome()) + "]\n");
                } else {
                    System.out.print(" [" + magiaEscolhida.getTipoEfeito() + "] de " + magiaEscolhida.getValorEfeito());
                }

            }

        }
    }

    public void exibirAtaque(Magia magiaEscolhida, Mago personagem, List<Inimigo> inimigos) {
        System.out.print("-> " + personagem.getNome() + " LANÇA SUA MAGIA ");
        System.out.print("[" + AnsiColors.magenta(magiaEscolhida.getNome().toUpperCase()) + "]");

        System.out.println(magiaEscolhida.getDescricao());
        for (Inimigo alvo : inimigos) {
            if (alvo.verificaEfeitoAtivo(NomeEfeito.ESQUIVOU)) {
                System.out.println("> [" + alvo.getNome() + "] SE ESQUIVOU!");
                alvo.removerEfeito(NomeEfeito.ESQUIVOU);

            } else {
                if (magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_DIRETO || magiaEscolhida.getTipoEfeito() == TipoDeEfeito.DANO_POR_TURNO) {
                    int dano = MathUtils.calculaDano(alvo, magiaEscolhida);
                    System.out.print("> Causando " + "[" + AnsiColors.red(dano) + "]" + " de DANO no " + "[" + AnsiColors.red(alvo.getNome()) + "]\n");
                } else {
                    System.out.println("[" + magiaEscolhida.getTipoEfeito() + "] de " + magiaEscolhida.getValorEfeito());
                }
            }

        }
    }

    public void exibirMagiaAliado(Magia magiaEscolhida, Mago mago) {
        System.out.print("-> " + mago.getNome() + " LANÇA SUA MAGIA ");
        System.out.print("[" + AnsiColors.magenta(magiaEscolhida.getNome().toUpperCase()) + "]");

        System.out.println("> " + magiaEscolhida.getTipoEfeito() + " de [" + magiaEscolhida.getValorEfeito() + "] por [" + magiaEscolhida.getDuracaoEfeito() + "] TURNOS");
    }

    public void exibirInimigoDerrotado(String nome) {
        System.out.println("[" + nome + "] foi DERROTADO!");
        ConsoleUtils.aguardarEnter();
    }

    public void exibirFimDeBatalha(String nomeVencedor, boolean heroiVenceu) {
        System.out.println("======== [ FIM DO COMBATE ] ========");
        if (heroiVenceu) {
            System.out.println("> [" + nomeVencedor + "] é o vencedor!");
            ConsoleUtils.aguardarEnter();
        } else {
            System.out.println(nomeVencedor + " venceu. Você foi derrotado.");
            ConsoleUtils.aguardarEnter();
        }
    }

    public void exibirRecompensaMagias(List<Magia> magias) {
        ConsoleUtils.limparTela();
        System.out.println("====== [ RECOMPENSA MAGICA ] =======");
        System.out.println("Escolha sua [MAGIA]");
        int contador = 1;
        for (Magia m : magias) {
            System.out.println(contador++ + ": " + m.toString());

        }
        System.out.println("< Pular [0]");
        System.out.print("> ");
    }

    public void exibirMagiaAprendida(Magia m) {
        System.out.println("====== [ RECOMPENSA MAGICA ] =======");
        System.out.println("VOCE APRENDEU A MAGIA [" + m.getNome() + "]");
        System.out.println("====== [ RECOMPENSA MAGICA ] =======");
        ConsoleUtils.pausar(2000);
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exbirHeaderCombate(int turno, Jogador jogador) {
        System.out.println("============ ["+AnsiColors.red("COMBATE")+"] =============");
        System.out.println("[TURNO " + turno++ + "]                  [OURO:" + jogador.getOuro() + "]");

    }

    public void exibirEspolios(int xp, int ouro, Mago mago) {
        System.out.println("=========== [ESPÓLIOS] =============");
        System.out.println("> " + mago.getNome() + " ganhou " + xp + " XP!");
        System.out.println("> " + mago.getNome() + " ganhou " + ouro + " OURO!");
        System.out.println("====================================");
        ConsoleUtils.aguardarEnter();
    }
}
