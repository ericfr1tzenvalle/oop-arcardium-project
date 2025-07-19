/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Luísa
 */
public class MagiaFactory {

    private final Random rand = new Random();

    public Magia criarMagiaUnica(List<Magia> magiasExistentes) {
        Magia magiaGerada = new Magia("Nenhuma", "Não faz nada", 999, TipoDeEfeito.CURA, 0,0, TipoAlvo.ALIADO, NomeEfeito.MALDICAO);

        while (true) {
            int magiaNum = rand.nextInt(9) + 1;

            switch (magiaNum) {

                case 1:
                    magiaGerada = new Magia("Sugada Nervosa", "Suga vitalidade dos inimigos a cada turno", 10, TipoDeEfeito.DANO_POR_TURNO, 5, 4, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.SANGRAMENTO);
                    break;
                case 2:
                    magiaGerada = new Magia("Força do URSO", "Voce reune a FORÇA da natureza pra si", 10, TipoDeEfeito.BUFF_ATAQUE, 10, 2, TipoAlvo.ALIADO, NomeEfeito.FORCA_DO_URSO);
                    break;
                case 3:
                    magiaGerada = new Magia("Pele de PEDRA", "Voce reune a DEFESA da natureza pra si", 10, TipoDeEfeito.BUFF_DEFESA, 5, 3, TipoAlvo.ALIADO, NomeEfeito.PELE_DE_PEDRA);
                    break;
                case 4:
                    magiaGerada = new Magia("Seta de Gelo", "Lança uma farpa de gelo em um único alvo.", 10, TipoDeEfeito.DANO_DIRETO, 25, 1, TipoAlvo.ALVO_UNICO, NomeEfeito.NENHUM);
                    break;
                case 5:
                    magiaGerada = new Magia("Chuva de Meteoros", "Atinge todos os inimigos com fragmentos cósmicos.", 25, TipoDeEfeito.DANO_DIRETO, 15, 1, TipoAlvo.TODOS_INIMIGOS, NomeEfeito.NENHUM);
                    break;
                case 6:
                    magiaGerada = new Magia("Toque Nocivo", "Aplica VENENO em um único alvo por 3 turnos.", 15, TipoDeEfeito.DANO_POR_TURNO, 8, 3, TipoAlvo.ALVO_UNICO, NomeEfeito.VENENO);
                    break;
                case 7:
                    magiaGerada = new Magia("Imagem Espelhada", "Aumenta sua DEFESA por 2 turnos.", 12, TipoDeEfeito.BUFF_DEFESA, 10, 2, TipoAlvo.ALIADO, NomeEfeito.PELE_DE_PEDRA);
                    break;
                case 8:
                    magiaGerada = new Magia("Maldição da Fraqueza", "Reduz o ATAQUE de um inimigo por 2 turnos.", 18, TipoDeEfeito.DEBUFF_ATAQUE, 5, 2, TipoAlvo.ALVO_UNICO, NomeEfeito.MALDICAO);
                    break;
                case 9:
                    magiaGerada = new Magia("Toque Restaurador", "Recupera uma quantidade significativa de VIDA.", 20, TipoDeEfeito.CURA, 40, 1, TipoAlvo.ALIADO, NomeEfeito.NENHUM);
                    break;

            }
            if (verificaMagiaGerada(magiasExistentes, magiaGerada)) {
                return magiaGerada;
            }

        }

    }

    public boolean verificaMagiaGerada(List<Magia> magias, Magia magiaGerada) {
        for (Magia magia : magias) {
            if (magia.getNome().equals(magiaGerada.getNome())) {
                return false;
            }
        }
        return true;
    }

}
