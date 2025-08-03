package arcardium.view;

import arcardium.model.Efeito;
import arcardium.model.Mago;
import arcardium.model.Inimigo;
import arcardium.model.Magia;
import arcardium.model.enums.TipoDeEfeito;
import arcardium.model.EfeitoAtivo;
import arcardium.model.Jogador;
import arcardium.model.Personagem;
import arcardium.model.enums.NomeEfeito;
import arcardium.utils.AnsiColors;
import arcardium.utils.ConsoleUtils;
import arcardium.utils.MathUtils;
import java.util.List;

/**
 * Responsável por exibir todas as informações da batalha no terminal. Não
 * contém nenhuma lógica de jogo.
 */
public class BatalhaView {

    public void exibirStatusTurno(Jogador jogador, Mago mago, List<Inimigo> grupoInimigos) {
        System.out.println("============[ JOGADOR ]=============");
        System.out.println(gerarHeaderJogador(jogador, mago));
        System.out.println("============[ STATUS ]==============");
        // Corrigido para pegar atributo sorte, assumindo que exista getSor()
        System.out.println("ATK: " + mago.getAtk() + "   DEF: " + mago.getDef() + "   SOR: " + mago.getDef());
        System.out.println("AGI: " + mago.getAgi() + "  PRE: " + mago.getPrecisao() + "  EVA: " + mago.getEvasao());
        exibirEfeitosDePersonagem(mago);
        exibirAliados(mago);
        System.out.println("===========[ INIMIGO(S) ]===========");
        for (Inimigo inimigo : grupoInimigos) {
            System.out.println(gerarHeaderInimigo(inimigo));
            exibirEfeitosDePersonagem(inimigo);
        }
    }

    public void exibirHeaderJogador(Jogador jogador, Mago mago) {
        System.out.println("========= [ JOGADOR ] ==========");
        System.out.println(gerarHeaderJogador(jogador, mago));
        exibirEfeitosDePersonagem(mago);
        exibirAliados(mago);
    }

    public void exibirHeaderAmbosSemStatus(Jogador jogador, Mago mago, List<Inimigo> grupoInimigos) {
        System.out.println("============[ JOGADOR ]=============");
        System.out.println(gerarHeaderJogador(jogador, mago));
        System.out.println("===========[ INIMIGO(S) ]===========");
        for (Inimigo inimigo : grupoInimigos) {
            System.out.println(gerarHeaderInimigo(inimigo));
            exibirEfeitosDePersonagem(inimigo);
        }
        System.out.println("============ [ TURNO ] =============");
    }

    private String gerarHeaderJogador(Jogador jogador, Mago mago) {
        return String.format("[Nvl.%d] %s   \nHP:[%s] %d/%d \nMP:[%s] %d/%d",
                jogador.getNivel(),
                mago.getNome(),
                criarBarra(mago.getHp(), mago.getMaxHp(), 14),
                mago.getHp(),
                mago.getMaxHp(),
                criarBarra(mago.getMp(), mago.getMaxMp(), 14),
                mago.getMp(),
                mago.getMaxMp());
    }

    private String gerarHeaderInimigo(Inimigo inimigo) {
        return String.format("[Rank.%s] %s   \nHP:[%s] %d/%d",
                inimigo.getRank(),
                inimigo.getNome(),
                criarBarra(inimigo.getHp(), inimigo.getMaxHp(), 12),
                inimigo.getHp(),
                inimigo.getMaxHp());
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
        List<EfeitoAtivo> efeitos = personagem.getEfeitosAtivos();
        if (efeitos != null && !efeitos.isEmpty()) {
            System.out.println("============ [EFEITOS] =============");
            System.out.print("[ATIVOS]: ");
            for (EfeitoAtivo efeito : efeitos) {
                // Usando os métodos do enum para verificar o tipo de efeito, se disponível
                if (efeito.getTipoEfeito().isBuff()) {
                    System.out.print("[" + efeito.getNomeEfeito() + " +" + efeito.getValor() + "  " + efeito.getDuracao() + " TURNOS] ");
                } else if (efeito.getTipoEfeito().isDebuff() || efeito.getTipoEfeito().isDanoContinuo()) {
                    System.out.print("[" + efeito.getNomeEfeito() + " -" + efeito.getValor() + "  " + efeito.getDuracao() + " TURNOS] ");
                } else {
                    System.out.print("[" + efeito.getNomeEfeito() + " +" + efeito.getValor() + "  " + efeito.getDuracao() + " TURNOS] ");
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
        System.out.println("[3] Ataque Básico" + " [4] Inventario" + " [5] Analisar");
        System.out.print("> ");
    }

    public void exibirMagias(Mago mago) {
        System.out.println("Escolha sua [MAGIA]:");
        List<Magia> magias = mago.getMagias();
        for (int i = 0; i < magias.size(); i++) {
            System.out.println((i + 1) + ": " + magias.get(i).toString());
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
        System.out.println("-> Turno de [" + nomeInimigo + "]");
    }

    public void exibirOrdemDosTurnos(List<Personagem> fila) {
        System.out.println("============= [ORDEM] ==============");
        int c = 1;
        for (Personagem f : fila) {
            System.out.println(c++ + ": [" + f.getNome() + "]");
        }
    }

    public void exibirAtaqueInimigo(Magia magiaEscolhida, Inimigo personagem, List<Personagem> inimigos) {
        System.out.println(" [" + personagem.getNome() + "] usa sua habilidade "
                + "[" + AnsiColors.magenta(magiaEscolhida.getNome().toUpperCase()) + "] "
                + magiaEscolhida.getDescricao());

        for (Personagem alvo : inimigos) {
            if (alvo.verificaEfeitoAtivo(NomeEfeito.ESQUIVOU)) {
                System.out.println("> [" + alvo.getNome() + "] SE ESQUIVOU!");
                alvo.removerEfeito(NomeEfeito.ESQUIVOU);
                continue;
            }
            for (Efeito efeito : magiaEscolhida.getEfeitos()) {
                TipoDeEfeito tipo = efeito.getTipoEfeito();
                int valorBase = efeito.getValor();
                if (tipo == TipoDeEfeito.DANO_DIRETO) {
                    int dano = alvo.verificaEfeitoAtivo(NomeEfeito.SOFREUCRITICO)
                            ? MathUtils.calculaDano(alvo, (int) (valorBase * personagem.getDanoCritico()))
                            : MathUtils.calculaDano(alvo, valorBase);

                    if (alvo.verificaEfeitoAtivo(NomeEfeito.SOFREUCRITICO)) {
                        alvo.removerEfeito(NomeEfeito.SOFREUCRITICO);
                        System.out.println(AnsiColors.yellow(">>> CRÍTICO! <<<"));
                    }

                    System.out.println("> Causando [" + AnsiColors.red(dano) + "] de DANO no ["
                            + AnsiColors.red(alvo.getNome()) + "]");
                } else if (tipo.isBuff() || tipo.isDebuff() || tipo.isDanoContinuo()) {
                    System.out.println("> [" + alvo.getNome() + "] afetado por " + efeito.getNomeEfeito()
                            + " [" + efeito.getValor() + "] por " + efeito.getDuracao() + " turno(s).");
                } else if (tipo == TipoDeEfeito.CURA) {
                    System.out.println("> [" + personagem.getNome() + "] recupera ["
                            + AnsiColors.green(valorBase) + "] de HP.");
                } else if (tipo == TipoDeEfeito.CONTROLE || tipo == TipoDeEfeito.PARALIZANTE) {
                    System.out.println("> [" + alvo.getNome() + "] está sob efeito de "
                            + efeito.getNomeEfeito() + " por " + efeito.getDuracao() + " turno(s).");
                } else {
                    // Futuramente: outros tipos de efeitos
                }
            }

        }
    }

    public void exibirAtaque(Magia magiaEscolhida, Mago mago, List<Inimigo> inimigos, Jogador jogador) {
        System.out.println("-> " + mago.getNome() + " LANÇA SUA MAGIA "
                + "[" + AnsiColors.magenta(magiaEscolhida.getNome().toUpperCase()) + "]\n "
                + magiaEscolhida.getDescricao());

        for (Inimigo alvo : inimigos) {
            if (alvo.verificaEfeitoAtivo(NomeEfeito.ESQUIVOU)) {
                System.out.println("> [" + alvo.getNome() + "] SE ESQUIVOU!");
                alvo.removerEfeito(NomeEfeito.ESQUIVOU);
                continue;
            }

            for (Efeito efeito : magiaEscolhida.getEfeitos()) {
                TipoDeEfeito tipo = efeito.getTipoEfeito();
                int valorBase = efeito.getValor();

                if (tipo == TipoDeEfeito.DANO_DIRETO) {
                    int dano = alvo.verificaEfeitoAtivo(NomeEfeito.SOFREUCRITICO)
                            ? MathUtils.calculaDano(alvo, (int) (valorBase * mago.getDanoCritico()))
                            : MathUtils.calculaDano(alvo, valorBase);

                    if (alvo.verificaEfeitoAtivo(NomeEfeito.SOFREUCRITICO)) {
                        alvo.removerEfeito(NomeEfeito.SOFREUCRITICO);
                        System.out.println(AnsiColors.yellow(">>> CRÍTICO! <<<"));
                    }

                    jogador.registrarMaiorDanoCausadoEmUmGolpe(dano);
                    System.out.println("> Causando [" + AnsiColors.red(dano) + "] de DANO no ["
                            + AnsiColors.red(alvo.getNome()) + "]");
                } else if (tipo.isBuff() || tipo.isDebuff() || tipo.isDanoContinuo()) {
                    System.out.println("> [" + alvo.getNome() + "] afetado por " + efeito.getNomeEfeito()
                            + " [" + efeito.getValor() + "] por " + efeito.getDuracao() + " turno(s).");
                } else if (tipo == TipoDeEfeito.CURA) {
                    System.out.println("> [" + mago.getNome() + "] recupera ["
                            + AnsiColors.green(valorBase) + "] de HP.");
                } else if (tipo == TipoDeEfeito.CONTROLE || tipo == TipoDeEfeito.PARALIZANTE) {
                    System.out.println("> [" + alvo.getNome() + "] está sob efeito de "
                            + efeito.getNomeEfeito() + " por " + efeito.getDuracao() + " turno(s).");
                } else {
                    // Futuramente: outros tipos de efeitos
                }
            }
        }
    }

    // Corrigido typo no nome do método exibirHeaderCombate
    public void exibirHeaderCombate(int turno, Jogador jogador) {
        System.out.println("============ [" + AnsiColors.red("COMBATE") + "] =============");
        System.out.println("[TURNO " + turno + "]                  [OURO:" + jogador.getOuro() + "]");
    }

    public void exibirMagiaAliado(Magia magiaEscolhida, Mago mago) {
        System.out.print("-> " + mago.getNome() + " LANÇA SUA MAGIA ");
        System.out.print("[" + AnsiColors.magenta(magiaEscolhida.getNome().toUpperCase()) + "]\n");

        for (Efeito efeitos : magiaEscolhida.getEfeitos()) {
            if (efeitos.getTipoEfeito() == TipoDeEfeito.INVOCACAO) {
                System.out.println("> " + efeitos.getTipoEfeito() + " " + magiaEscolhida.getDescricao());
            } else {
                System.out.println("> " + efeitos.getTipoEfeito() + " de [" + efeitos.getValor() + "] por [" + efeitos.getDuracao() + "] TURNOS");

            }
        }

    }

    public void exibirInimigoDerrotado(String nome) {
        System.out.println("[" + nome + "] foi DERROTADO!");
    }

    public void exibirFimDeBatalha(String nomeVencedor, boolean heroiVenceu) {
        System.out.println("======== [ FIM DO COMBATE ] ========");
        if (heroiVenceu) {
            System.out.println("> [" + nomeVencedor + "] é o vencedor!");
        } else {
            System.out.println(nomeVencedor + " venceu. Você foi derrotado.");
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

    public void exibirEspolios(int xp, int ouro, Mago mago) {
        System.out.println("=========== [ESPÓLIOS] =============");
        System.out.println("> " + mago.getNome() + " ganhou " + xp + " XP!");
        System.out.println("> " + mago.getNome() + " ganhou " + ouro + " OURO!");
        System.out.println("====================================");
    }

    public void exibirFuga() {
        System.out.println("======== [ FIM DO COMBATE ] ========");
        System.out.println("> Sem recompensas.");
        System.out.println("======== [      FUGIU     ] ========");
    }

    public void exibirInimigosCombate(List<Inimigo> grupoInimigos, int gold, int xp) {
        System.out.println("============ [INICIO DO COMBATE] =============");
        System.out.println("> " + (grupoInimigos.size() > 1 ? "Inimigos!" : "Inimigo!"));

        for (Inimigo inimigo : grupoInimigos) {
            System.out.println("> [" + inimigo.getRank() + "] " + inimigo.getNome());
        }
        System.out.println("=============X [ RECOMPENSAS ] X==============");
        System.out.println("            GOLD: [" + gold + "] " + " [" + xp + "] :XP");
        System.out.println("             [VENÇA PARA GANHAR]              ");
    }
    
   

    private void exibirAliados(Mago mago) {
        if(!mago.getAliadosInvocados().isEmpty()){
            System.out.println("=========== [ALIADOS] =============");
            for (Personagem invocacao : mago.getAliadosInvocados()) {
            Inimigo aliado = (Inimigo) invocacao;
            System.out.println(gerarHeaderInimigo(aliado));
            exibirEfeitosDePersonagem(aliado);
            }
            
        }
    }
}
