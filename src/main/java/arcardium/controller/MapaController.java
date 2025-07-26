/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.controller;

import arcardium.model.Sala;
import arcardium.model.enums.TipoSala;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Luísa
 */
public class MapaController {

    private final Random random = new Random();

    public List<List<Sala>> gerarMapa(int profundidade) {
        //Inicia a Lista de Lista
        List<List<Sala>> mapa = new ArrayList<>();
        //A profundidade se refere a andares
        for (int i = 0; i < profundidade; i++) {
            List<Sala> andar = new ArrayList<>();
            //Em cada andar pode haver de 2 a 5 salas disponives
            int numeroDeSalasNoAndar = 3;
            //No atual momento temos só de COMBATE
            for (int j = 0; j < numeroDeSalasNoAndar; j++) {
                andar.add(new Sala(gerarTipoSalaAleatoria()));
            }

            mapa.add(andar);

        }
        //Garantir que o ultimo seja sempre o CHEFE
        List<Sala> ultimoAndar = new ArrayList<>();
        ultimoAndar.add(new Sala(TipoSala.CHEFE));
        mapa.add(ultimoAndar);
        return mapa;
    }

    public TipoSala gerarTipoSalaAleatoria() {
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 70) {
            chance = random.nextInt(100);
            return TipoSala.COMBATE;
        } else {
            return TipoSala.EVENTO;
        }

    }

}
