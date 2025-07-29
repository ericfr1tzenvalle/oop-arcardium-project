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
        System.out.println("  x Resistente e bruto");
        System.out.println("[2] Mago Arcano");
        System.out.println("  x Magia pura");
        System.out.println("[3] O escolhido");
        System.out.println("  x Que a sorte lhe ajude");
        System.out.println("============Escolha===========");
        System.out.print("> ");
    }

    public void mostrarArquetipo(Mago mago, int arquetipo) {
        switch (arquetipo) {
            case 1:
                ConsoleUtils.limparTela();
                System.out.println("======= MAGO DE BATALHA =======");


        }
    }


    public void exibirResumoDaRun(Jogador jogador, Mago mago) {
        System.out.println("======= [RESUMO DA RUN] =======");
        System.out.println(" PONTUAÇÃO");
        System.out.println("[" + jogador.calcularPontuacaoFinal() + "]");
        System.out.println("> Mago: " + mago.getNome());
        System.out.println("> Andar: " + jogador.getAndarAtual());
        System.out.println("> Nivel: " + jogador.getNivel());
        System.out.println("> Inimigos derrotados:  " + jogador.getNumeroDeInimigosDerrotados());
        System.out.println("> Inimigo mais odiado: " + jogador.getNomeInimigoMaisDerrotado());
        System.out.println("> Magia preferida: " + jogador.getNomeMagiaMaisUsada());
        System.out.println("> Maior dano: " + jogador.getMaiorDanoCausadoEmUmGolpe());
        System.out.println("======= [ FIM DA RUN ] =======");



    }
}
