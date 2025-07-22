/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcadium.utils;

public class ConsoleUtils {

    /**
     * Limpa a tela do console. Tenta usar o comando nativo do sistema
     * operacional e, se falhar, usa o código de escape ANSI.
     */
    public static void limparTela() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    /**
     * Pausa a execução do programa por um determinado número de milissegundos.
     * @param milissegundos O tempo da pausa.
     */
    public static void pausar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}