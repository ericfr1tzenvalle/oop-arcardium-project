/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.view;

import arcardium.utils.AnsiColors;
import arcardium.utils.ConsoleUtils;
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
        ConsoleUtils.digitar("Iniciando run...", 100);
        System.out.println("\nDigite o nome do seu [MAGO]");
        System.out.print("> ");
    }

    public void mostrarTelaArquetipos() {
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

}
