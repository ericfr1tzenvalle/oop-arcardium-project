/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.TipoDeEfeito;

/**
 *
 * @author Luísa
 */
public class EfeitoAtivo {
    private TipoDeEfeito tipoEfeito;
    private int valor;
    private int duracao;

    public EfeitoAtivo(TipoDeEfeito tipoEfeito, int valor, int duracao) {
        this.valor = valor;
        this.tipoEfeito = tipoEfeito;
        this.duracao = duracao;
    }
   
    public TipoDeEfeito getTipoEfeito() {
        return tipoEfeito;
    }

    public void setTipoEfeito(TipoDeEfeito tipoEfeito) {
        this.tipoEfeito = tipoEfeito;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getValor() {
        return valor;
    }
    
    
    
    
    
}
