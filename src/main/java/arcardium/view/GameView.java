/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.view;

import arcardium.model.Jogador;
import arcardium.model.Mago;
import arcardium.utils.AnsiColors;
import arcardium.utils.ConsoleUtils;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 *
 * @author Éric
 */
public class GameView {

    Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("=========Arcardium[RPG]=========");
        System.out.println("[1] Startar run");
        System.out.println("[2] Estatistica");
        System.out.println("[3] Colecao");
        System.out.println("[4] Opcoes");
        System.out.println("[0] Sair");
        System.out.println("=========Arcardium[RPG]=========");
        System.out.print("> ");

    }

    public void mostrarTelaNome() {
        ConsoleUtils.limparTela();
        System.out.println("=========Arcardium[RPG]=========");
        ConsoleUtils.digitar("Iniciando run...", 100);
        ConsoleUtils.digitar("\nDigite o nome do seu [MAGO]\n", 100);
        System.out.print("> ");
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void mostrarTelaArquetipos() {
        ConsoleUtils.limparTela();
        System.out.println("===========Arquétipo===========");
        System.out.println("[1] Mago de batalha");
        System.out.println("   x Resistente e bruto");
        System.out.println("[2] Mago Arcano");
        System.out.println("   x Magia pura");
        System.out.println("[3] O escolhido");
        System.out.println("   x Que a sorte lhe ajude");
        System.out.println("[4] O padre");
        System.out.println("   x Poder da fé");
        // Arquétipos extras bloqueados // Apenas testes
        System.out.println("[5] Vampiro [Desbloqueado]");
        System.out.println("   x Magias de sangue e drenagem");
        System.out.println("[6] Necromante [Desbloqueado]");
        System.out.println("   x Manipulando o gelo para controle e defesa");
        System.out.println("[7] Umbramante [Bloqueado]"); // Posteriormente pode ser vampiro.
        System.out.println("   x Conjurando magias sombrias e drenando inimigos");
        System.out.println("[8] Druida [Bloqueado]");
        System.out.println("   x Conectando-se à natureza para curar e fortalecer");
        System.out.println("[9] Guardião Rúnico [Bloqueado]");
        System.out.println("   x Protegendo-se com runas e encantamentos antigos");
        System.out.println("============Escolha===========");
        System.out.print("> ");
    }

    public void mostrarArquetipo(Mago mago, int arquetipo) {
        System.out.println("=========== [ARQUÉTIPO] ===========");
        System.out.println("Nome: " + mago.getNome());
        System.out.println("HP: " + mago.getHp() + " | MP: " + mago.getMp());
        System.out.println("ATK: " + mago.getAtk() + " | DEF: " + mago.getDef());
        System.out.println("AGI: " + mago.getAgi() + " | PRE: " + mago.getPrecisao() + " | EVA: " + mago.getEvasao());
        System.out.println("Recuperação MP por turno: " + mago.getRegeneraçaoDeMana());

        // Descrição do arquétipo
        System.out.println("\n>>> Descrição:");
        switch (arquetipo) {
            case 1 ->
                System.out.println("Um mago robusto, especialista em dano físico e resistência.");
            case 2 ->
                System.out.println("Um conjurador de pura energia arcana com alto poder mágico.");
            case 3 ->
                System.out.println("Um escolhido pelo destino, dependente da sorte em combate.");
            case 4 ->
                System.out.println("Um mago devoto que usa o poder da fé para curar e atacar.");
            case 5 ->
                System.out.println("O filho(a) das trevas, usa magias de sangue para matar seus inimigos.");
            case 6 ->
                System.out.println("Manipula os mortos, trazendo caos e terror.");
            case 7 ->
                System.out.println("Usa magias sombrias e roubo de vida.");
            case 8 ->
                System.out.println("Conectado à natureza, excelente em curar e buffar.");
            case 9 ->
                System.out.println("Tanque mágico com defesas elevadas.");
        }

        System.out.println("\n>>> Magias iniciais:");
        mago.getMagias().forEach(magia -> {
            System.out.println(" > " + magia.getNome() + ": " + magia.getDescricao());
        });
        
        //Posteriormente vai verificar as estatistica do jogador pra desbloquear.
        
        //if (arquetipo >= 5) {
            // 
           // System.out.println("\n Este arquétipo está bloqueado. Derrote um chefe para desbloquear.");
          //  System.out.println("[0] Voltar");
       // } else {
            System.out.println("\nDeseja escolher este arquétipo?");
            System.out.println("[1] Sim   [2] Não");
       // }
        System.out.print("> ");
    }

    public void exibirResumoDaRun(Jogador jogador, Mago mago) {
        System.out.println("======= [RESUMO DA RUN] =======");
        System.out.println("> PONTUAÇÃO");
        System.out.print(" [" + jogador.calcularPontuacaoFinal() + "]\n");
        System.out.println("> Mago: " + mago.getNome());
        System.out.println("> Andar: " + jogador.getAndarAtual());
        System.out.println("> Nivel: " + jogador.getNivel());
        System.out.println("> Morto por: " + jogador.getNemesis().getNome());
        System.out.println("> Inimigos derrotados:  " + jogador.getNumeroDeInimigosDerrotados());
        System.out.println("> Inimigo mais odiado: " + jogador.getNomeInimigoMaisDerrotado());
        System.out.println("> Magia preferida: " + jogador.getNomeMagiaMaisUsada());
        System.out.println("> Maior dano: " + jogador.getMaiorDanoCausadoEmUmGolpe());
        System.out.println("======= [ FIM DA RUN ] =======");

    }
}
