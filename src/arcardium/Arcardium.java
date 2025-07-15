/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arcardium;

import arcardium.model.*;
import arcardium.controller.BatalhaController;
import arcardium.controller.GameController;
import arcardium.controller.MapaController;
import arcardium.model.enums.TipoSala;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Eric
 */
public class Arcardium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       GameController game = new GameController();
       game.iniciarJogo();

    }

}
