/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.utils.ConsoleUtils;
import java.util.List;


/**
 *
 * @author Luísa
 */
public class EventoDescanso extends Evento{

    public EventoDescanso() {
        super();
    }

    
    public void executar(Jogador jogador, MagiaFactory magiaFactory) {
        ConsoleUtils.limparTela();
        view.mostrarEvento("DESCANSO");
        Heroi heroi = jogador.getHeroi();
        int escolha = sc.nextInt();
        if(escolha == 1){
            int cura = (int) (jogador.getHeroi().getMaxHp() * 0.30);
            heroi.receberCura(cura);
        }else{
            Mago mago = (Mago) heroi;
            List<Magia> magias = mago.getMagiasAprimoraveis();
            view.mostrarOpcoesMagias(magias);
            int opcaoMagia = sc.nextInt();
            Magia magiaEscolhida = magias.get(opcaoMagia - 1);
            mago.aprimorarMagia(magiaEscolhida);
            view.mostrarMagiaAprimorada(magiaEscolhida);
        }
        
        
        
       
    }
    
}
