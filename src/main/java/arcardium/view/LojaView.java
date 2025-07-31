package arcardium.view;

import arcardium.model.Jogador;
import arcardium.model.PacoteDeMagia;
import java.util.List;

/**
 *
 * @author Luísa
 */
public class LojaView {

    public void exibirLoja(Jogador jogador, List<PacoteDeMagia> pacotes) {
        // Remover linha de debug: jogador.setOuro(1000);
        System.out.println();
        System.out.println("╔═══════════════[ LOJA ]══════════════╗");
        System.out.println("   OURO: [" + jogador.getOuro() + "]");
        System.out.println("╠═════════════════════════════════════╣");

        if (pacotes.isEmpty()) {
            System.out.println("   Nenhum pacote disponível no momento.");
        } else {
            int contador = 1;
            for (PacoteDeMagia pacote : pacotes) {
                System.out.println(" [" + contador++ + "] " + pacote.getNome().toUpperCase());
                System.out.println("     " + pacote.getDescricao());
                System.out.println("     Custo: " + pacote.getCusto() + " ouro");
                System.out.println("──────────────────────────────────────");
            }
        }

        System.out.println("╠═══════════[ CURINGAS ]══════════════╣");
        System.out.println("   > Nenhum no momento");
        System.out.println("╠══════════════[ OPÇÕES ]═════════════╣");
        System.out.println(" [8] Atualizar Pacotes");
        System.out.println(" [9] Sair");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.print("> ");
        
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
