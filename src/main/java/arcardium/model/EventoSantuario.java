/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.utils.ConsoleUtils;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Éric
 */
public class EventoSantuario extends Evento {

    @Override
    public void executar(Jogador jogador, MagiaFactory magiaFactory) {
        int valor = 100;
        Random random = new Random();
        Mago mago = (Mago) jogador.getHeroi();
        view.mostrarEvento("SANTUARIO");
        int escolha = sc.nextInt();
        if(escolha == 2){
            view.pularEvento();
            return;
        }
        view.mostrarOpcoesSantuario();
        escolha = sc.nextInt();
        while (escolha == 2 && !jogador.gastarOuro(valor)) {
            System.out.println("> Não tem ouro suficiente");
            view.mostrarOpcoesSantuario();
            escolha = sc.nextInt();
        }
        switch (escolha) {
            case 1:
                int bonusAtk = 10;
                int perdaHp = (int) (mago.getMaxHp() * 0.10); // > 10% do HP máximo atual

                System.out.println("> Em troca de vida... você escolheu a força."
                        + "\n> ---" + perdaHp + " HP MÁXIMO"
                        + "\n> +++" + bonusAtk + " ATAQUE");

                mago.setMaxHp(mago.getMaxHp() - perdaHp);

                // > Ajusta HP atual se estiver acima do novo máximo
                if (mago.getHp() > mago.getMaxHp()) {
                    mago.setHp(mago.getMaxHp());
                }
                mago.setAtk(mago.getAtk() + bonusAtk);
                break;
                
            case 2:
                System.out.println("> Em troca de ouro... você é presenteado com poder."
                        + "\n> --- " + valor + " OURO");

                List<Magia> magiasAprimoraveis = mago.getMagiasAprimoraveis();

                if (!magiasAprimoraveis.isEmpty()) {
                    int indiceMagia = random.nextInt(magiasAprimoraveis.size());
                    Magia m = magiasAprimoraveis.get(indiceMagia);
                    m.aprimorarMagia();
                    System.out.println("> " + m.getNome() + " FOI [APRIMORADA]");
                } else {
                    System.out.println("> Nenhuma magia pode ser aprimorada no momento.");
                }
                
                break;
            case 3:
                int valorXp = (int) (jogador.getAndarAtual() * 20) + 100;
                System.out.println("> Em troca de poder... você será presenteado com sabedoria.");
                System.out.println(">> Escolha uma das magias a esquecer:");

                int magiaEsquecida = -1;

                while (true) {
                    int contador = 0;
                    for (Magia magia : mago.getMagias()) {
                        System.out.println(contador++ + ": " + magia.toString());
                    }

                    System.out.print("> ");
                    magiaEsquecida = sc.nextInt();

                    if (magiaEsquecida >= 0 && magiaEsquecida < mago.getMagias().size()) {
                        break;
                    } else {
                        System.out.println("> Escolha inválida. Tente novamente.");
                    }
                }

                Magia magia = mago.getMagias().get(magiaEsquecida);
                mago.removerMagia(magia);

                System.out.println("> --- " + magia.getNome() + " MAGIA"
                        + "\n> +++" + valorXp + " EXPERIÊNCIA");
                jogador.ganharXP(valorXp);
                break;

        }
        ConsoleUtils.aguardarEnter();
    }

}
