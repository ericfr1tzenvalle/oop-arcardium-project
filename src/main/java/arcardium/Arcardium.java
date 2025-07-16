/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arcardium;


import arcardium.controller.GameController;


/**
 *
 * @author Eric
 */
public class Arcardium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       org.fusesource.jansi.AnsiConsole.systemInstall(); // Iniciando o Jansi.
       GameController game = new GameController();
       game.iniciarJogo();

    }

}
