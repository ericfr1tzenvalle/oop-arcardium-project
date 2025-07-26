/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.TipoSala;

/**
 *
 * @author Luísa
 */
public class Sala {
    private final TipoSala tipo;

    public Sala(TipoSala tipo) {
        this.tipo = tipo;

    }

    public TipoSala getTipo(){return tipo;}
    
     @Override
    public String toString() {return tipo.getNome();}
    
    
}
