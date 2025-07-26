/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.utils;


import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 *
 * @author Éric
 */

//Classe para facilitar a utilizacao das cores do terminal.
public class AnsiColors {
    
    
    // Transforma qualquer texto em texto VERMELHO
    public static String red(Object text){
        return ansi().fg(RED).a(text).reset().toString();
    }
    public static String black(Object text){
        return ansi().fg(BLACK).a(text).reset().toString();
    }
    // Transforma qualquer texto em texto VERDE
    public static String green(Object text) {
        return ansi().fg(GREEN).a(text).reset().toString();
    }

    // Transforma qualquer texto em texto AMARELO
    public static String yellow(Object text) {
        return ansi().fg(YELLOW).a(text).reset().toString();
    }

    // Transforma qualquer texto em texto AZUL
    public static String blue(Object text) {
        return ansi().fg(BLUE).a(text).reset().toString();
    }
    
    // Transforma qualquer texto em texto CIANO (um azul claro)
    public static String cyan(Object text) {
        return ansi().fg(CYAN).a(text).reset().toString();
    }
    
    // Transforma qualquer texto em texto MAGENTA (um roxo/rosa)
    public static String magenta(Object text) {
        return ansi().fg(MAGENTA).a(text).reset().toString();
    }
}
