/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.utils;

import java.util.Scanner;

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

            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    public static void digitar(String texto, long delayMs) {
    for (char c : texto.toCharArray()) {
        System.out.print(c);
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

    /**
     * Pausa a execução do programa por um determinado número de milissegundos.
     *
     * @param milissegundos O tempo da pausa.
     */
    public static void pausar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void aguardarEnter() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(Enter...)");
        sc.nextLine();
    }
    
     /**
     * Cria uma barra de progresso em texto, sem cores.
     * @param atual O valor atual (ex: HP atual).
     * @param maximo O valor máximo (ex: HP máximo).
     * @param tamanho O número de caracteres que a barra terá.
     * @return Uma String formatada como uma barra de progresso.
     */
    public static String criarBarraDeProgresso(int atual, int maximo, int tamanho) {
        // Segurança para evitar divisão por zero se o valor máximo for 0
        if (maximo <= 0) atual = 0;
        if (atual < 0) atual = 0;
        
        // 1. Calcula a proporção
        double proporcao = (double) atual / maximo;

        // 2. Calcula quantos blocos devem ser preenchidos
        int blocosCheios = (int) (proporcao * tamanho);
        if (blocosCheios > tamanho) blocosCheios = tamanho; // para que não ultrapasse o limite
        
        int blocosVazios = tamanho - blocosCheios;

        // 3. Constrói a String, passo a passo
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < blocosCheios; i++) {
            sb.append("█");
        }
        for (int i = 0; i < blocosVazios; i++) {
            sb.append("░"); 
        }
        sb.append("]");
        
        return sb.toString();
    }
}
    
   

