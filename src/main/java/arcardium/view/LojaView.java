/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.view;

import arcardium.model.Curinga;
import arcardium.model.Jogador;
import arcardium.model.PacoteDeMagia;
import java.util.List;

/**
 *
 * @author Luísa
 */
public class LojaView {
    
    public void exibirLoja(Jogador jogador, List<PacoteDeMagia> pacotes){
        jogador.setOuro(1000);
        System.out.println("=$=========$ LOJA $===========$=");
        System.out.println("Ouro: [" + jogador.getOuro()+"]");
        System.out.println("xxxxxxxxxxx         xxxxxxxxxxxx");
        int contador = 1;
        for(PacoteDeMagia pacote: pacotes){
            System.out.println("[" + contador++ + "] ==$ " + pacote.getNome() + " $==");
            System.out.println("    >" + pacote.getDescricao() + "< ");
        }
        System.out.println("xxxxxxxxxx Curingas xxxxxxxxxxx");
        System.out.println("> Nenhum no momento");
        System.out.println("=$=========$ OPÇÕES $=========$=");
        System.out.println("[8] Atualizar Pacotes");
        System.out.println("[9] Sair");
        System.out.print("> ");
        
        
    }
      public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    
}
