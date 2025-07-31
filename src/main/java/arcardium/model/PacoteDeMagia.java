/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.TagMagia;

/**
 *
 * @author Luísa
 */
public class PacoteDeMagia {
    private String nome;
    private String descricao;
    private int custo;
    private TagMagia tag;

    public PacoteDeMagia(String nome, String descricao, int custo, TagMagia tag) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.tag = tag;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public TagMagia getTag() {
        return tag;
    }

    public void setTag(TagMagia tag) {
        this.tag = tag;
    }
}
