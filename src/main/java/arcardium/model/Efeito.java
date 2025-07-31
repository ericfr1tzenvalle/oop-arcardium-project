/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;

/**
 *
 * @author Luísa
 */
public class Efeito {
    private TipoDeEfeito tipoEfeito;
    private TipoAlvo tipoAlvo;
    private NomeEfeito nomeEfeito;
    private int valor;
    private int duracao;
    public Efeito(TipoDeEfeito tipoEfeito, int valor, int duracao, TipoAlvo tipoAlvo, NomeEfeito nomeEfeito) {
        this.tipoEfeito = tipoEfeito;
        this.tipoAlvo = tipoAlvo;
        this.nomeEfeito = nomeEfeito;
        this.valor = valor;
        this.duracao = duracao;
    }

    public TipoDeEfeito getTipoEfeito() {
        return tipoEfeito;
    }

    public void setTipoEfeito(TipoDeEfeito tipoEfeito) {
        this.tipoEfeito = tipoEfeito;
    }

    public TipoAlvo getTipoAlvo() {
        return tipoAlvo;
    }

    public void setTipoAlvo(TipoAlvo tipoAlvo) {
        this.tipoAlvo = tipoAlvo;
    }

    public NomeEfeito getNomeEfeito() {
        return nomeEfeito;
    }

    public void setNomeEfeito(NomeEfeito nomeEfeito) {
        this.nomeEfeito = nomeEfeito;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    
    
}
