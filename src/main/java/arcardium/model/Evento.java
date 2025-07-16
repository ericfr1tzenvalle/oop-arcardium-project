/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.view.EventoView;
import java.util.Scanner;

/**
 *
 * @author Éric
 */
public abstract class Evento {
    protected EventoView view;
    protected Scanner sc;

    public Evento() {
        this.view =  new EventoView();
        this.sc = new Scanner(System.in);
    }
    
    
    public abstract void executar(Jogador jogador, MagiaFactory magiaFactory);
    
}
