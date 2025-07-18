/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.TipoDeEfeito;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Éric
 */
public abstract class Personagem {

    private String nome;
    private int hp;
    private int maxHp;
    private int maxMp;
    private int mp;
    private int atk;
    private int def;
    private int agi;
    private List<EfeitoAtivo> efeitoAtivo;

    public Personagem(String nome, int hp, int mp, int atk, int def, int agi) {
        this.nome = nome;
        this.maxHp = hp;
        this.maxMp = mp;
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.agi = agi;
        this.efeitoAtivo = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getAtk() {
        int ataqueTotal = this.atk;
        for (EfeitoAtivo efeito : this.efeitoAtivo) {
            if (efeito.getTipoEfeito() == TipoDeEfeito.BUFF_ATAQUE) {
                ataqueTotal += efeito.getValor();
            }
            if (efeito.getTipoEfeito() == TipoDeEfeito.DEBUFF_ATAQUE) {
                ataqueTotal -= efeito.getValor();
            }
        }
        return Math.max(0, ataqueTotal);

    }

    public int getDef() {
        int defesaTotal = this.def;
        for (EfeitoAtivo efeito : this.efeitoAtivo) {
            if (efeito.getTipoEfeito() == TipoDeEfeito.BUFF_DEFESA) {
                defesaTotal += efeito.getValor();
            }
            if (efeito.getTipoEfeito() == TipoDeEfeito.DEBUFF_DEFESA) {
                defesaTotal -= efeito.getValor();
            }
        }
        return Math.max(0, defesaTotal);
    }

    public void processarEfeitosPorTurno() {
        //Utilizo o ITERATOR pra conseguir fazer verificacões sem dar ConcurrentModificationException.
        Iterator<EfeitoAtivo> iterator = this.efeitoAtivo.iterator();

        while (iterator.hasNext()) {
            EfeitoAtivo efeito = iterator.next();
            if (efeito.getTipoEfeito() == TipoDeEfeito.DANO_POR_TURNO) {
                System.out.println(this.nome + "sofreu [" + efeito.getValor() + "]" + " de dano [DANO POR TURNO]");
                this.tomarDano(efeito.getValor());
            } else if (efeito.getTipoEfeito() == TipoDeEfeito.CURA) {
                System.out.println(this.nome + "curou [" + efeito.getValor() + "]" + " [CURA POR TURNO]");
                this.receberCura(efeito.getValor());
            }
            efeito.setDuracao(efeito.getDuracao() - 1);
            if (efeito.getDuracao() <= 0) {
                iterator.remove();
            }

        }

    }

    public int getAgi() {
        return agi;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    protected void setHp(int hp) {
        this.hp = hp;
    }

    protected void setMp(int mp) {
        this.mp = mp;
    }

    protected void setAtk(int atk) {
        this.atk = atk;
    }

    protected void setDef(int def) {
        this.def = def;
    }

    protected void setAgi(int agi) {
        this.agi = agi;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public void atacar(Heroi h) {
        h.tomarDano(atk);
    }

    public void tomarDano(int dano) {
        int danoReal = dano - this.def;
        if (danoReal > 0) {
            this.hp -= danoReal;
        }
    }
    public List<EfeitoAtivo> getEfeitosAtivos() {
    return efeitoAtivo;
}

    public void aplicarEfeito(TipoDeEfeito tipo, int valor, int duracao) {
        EfeitoAtivo efeito = new EfeitoAtivo(tipo, valor, duracao);
        efeitoAtivo.add(efeito);

    }
    public void resetarEfeitos(){
        this.efeitoAtivo.clear();
    }

    protected void receberCura(int valor) {
        this.hp += valor;
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
    }

}
