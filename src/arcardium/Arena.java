/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arcardium;

import arcardium.model.*;
import arcardium.controller.BatalhaController;

/**
 *
 * @author Eric
 */
public class Arena {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Mago nossoHeroi = new Mago("Gandalf, o Cinzento", 100, 50, 10, 5, 15);
       Inimigo monstro = new Inimigo("Orc de Moria", 40, 0, 12, 4, 8);
       
       Magia m1 = new Magia("Faisca", "Pequeno raio (1) Alvo", 10, 20);
       Magia m2 = new Magia("Restauração", "Restaura MP", 30, 0);
       Magia raioDeGelo = new Magia("Raio de Gelo", "Um raio que causa dano e pode deixar lento.", 12, 18);
       Magia bolaDeFogo = new Magia("Bola de Fogo", "Uma bola de fogo que queima os inimigos.", 10, 20);


       nossoHeroi.aprenderMagia(m1);
       nossoHeroi.aprenderMagia(m2);
       nossoHeroi.aprenderMagia(raioDeGelo);
       nossoHeroi.aprenderMagia(bolaDeFogo);
       
       BatalhaController bc = new BatalhaController();
       
       bc.iniciarBatalha(nossoHeroi, monstro);
       
       
    }
    
}
