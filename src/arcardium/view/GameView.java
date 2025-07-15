/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.view;

import arcardium.model.Mago;
import java.util.Scanner;

/**
 *
 * @author Éric
 */
public class GameView {
    
    Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("1. Nova run");
        System.out.println("2. Estatistica");
        System.out.println("3. Coleção");
        System.out.println("4. Opcoes");
        System.out.println("0. Sair");
        
        

    }
    
    public void mostrarTelaNome(){
       System.out.println("Digite o nome do seu [MAGO]:  ");
    }  
    
    public void mostrarTelaArquetipos(){
        //Possivel ideia ter arquetipo inicial mas pensando ainda se é interresante.
        System.out.println("Escolha um arquétipo");
        //Vai ter os atributos com mais saúde focado em um mago que resiste mais.
        System.out.println("1. [Mago de batalha]");
        System.out.println("Duro e resistente");
        //Vai ter uma defesa baixa mas a mana e o atk bem altos
        System.out.println("2. [Mago Arcano]");
        System.out.println("Pura magia");
        //O escolhido vai ter uma sorte maior e dois feitiços iniciais mas atributos bem base.
        //Ele terá que se provar
        System.out.println("3. [O escolhido]");
        System.out.println("A sorte está ao seu lado");
        //Posteriormente pode existir mais arquetipos sendo alguns desbloqueaveis.
        
        
        //Implementar geração de opção 3 feitiços.
    }
    
    // Talvez implementar uma mensagem pra cada arquetipo. 
    
    

}
