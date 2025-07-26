/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.utils;

import arcardium.model.Magia;
import arcardium.model.Personagem;

/**
 *
 * @author Luísa
 */
public class MathUtils {

    public static int calculaDano(Personagem alvo, Magia magiaEscolhida) {
        int defesa = alvo.getDef();
        int danoBruto = magiaEscolhida.getValorEfeito();
        int dano = (int) (danoBruto * (100.0 / (100.0 + defesa)));
        if (alvo.isEstaDefendendo()) {
            dano = dano / 2;
        }
        if (dano < 1 && danoBruto > 0) {
            dano = 1;
        }
        return dano;
    }

}
