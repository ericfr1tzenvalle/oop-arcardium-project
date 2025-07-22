/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
    private int precisao;
    private int evasao;
    private List<EfeitoAtivo> efeitoAtivo;

    public Personagem(String nome, int hp, int mp, int atk, int def, int agi, int precisao, int evasao) {
        this.nome = nome;
        this.maxHp = hp;
        this.maxMp = mp;
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.agi = agi;
        this.precisao = precisao;
        this.evasao = evasao;
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

    public int getPrecisao() {
        return precisao;
    }

    public int getEvasao() {
        int evasaoTotal = this.evasao;
        for(EfeitoAtivo efeito: this.efeitoAtivo){
            if(efeito.getTipoEfeito() == TipoDeEfeito.BUFF_EVASAO){
                evasaoTotal += efeito.getValor();
            }
        }
        return evasaoTotal;
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
                System.out.println(this.nome + " sofreu [" + efeito.getValor() + "]" + " de dano [DANO POR TURNO]");
                this.tomarDano(efeito.getValor());
            } else if (efeito.getTipoEfeito() == TipoDeEfeito.CURA) {
                System.out.println(this.nome + " curou [" + efeito.getValor() + "]" + " [CURA POR TURNO]");
                this.receberCura(efeito.getValor());
            }
            efeito.setDuracao(efeito.getDuracao() - 1);
            if (efeito.getDuracao() <= 0) {
                iterator.remove();
            }

        }

    }

    public int getAgi() {
        int agiTotal = this.agi;
        for (EfeitoAtivo e : this.efeitoAtivo) {
            if (e.getTipoEfeito() == TipoDeEfeito.BUFF_AGILIDADE) {
                agiTotal += e.getValor();
            }
            if (e.getTipoEfeito() == TipoDeEfeito.DEBUFF_AGILIDADE) {
                agiTotal -= e.getValor();
            }
        }
        return Math.max(0, agiTotal);
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
        int defesaTotal = this.getDef();
        int danoReal = (int) (dano * (100.0 / (100.0 + defesaTotal)));

        if (dano > 0 && danoReal < 1) {
            danoReal = 1;
        }
        this.hp -= danoReal;

    }

    public List<EfeitoAtivo> getEfeitosAtivos() {
        return efeitoAtivo;
    }

    public void aplicarEfeito(TipoDeEfeito tipo, int valor, int duracao, NomeEfeito nome) {
        EfeitoAtivo efeito = new EfeitoAtivo(tipo, valor, duracao, nome);
        efeitoAtivo.add(efeito);

    }

    public boolean verificaEfeitoAtivo(NomeEfeito nome) {
        for (EfeitoAtivo e : efeitoAtivo) {
            if (e.getNomeEfeito() == nome) {
                return true;
            }
        }
        return false;
    }

    public void resetarEfeitos() {
        this.efeitoAtivo.clear();
    }

    protected void receberCura(int valor) {
        this.hp += valor;
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
    }

    public boolean lancarHabilidade(Magia magia, List<Personagem> alvos) {
        
        int custo = magia.getCustoMana();
        int mpAtual = this.getMp();
        int valor = magia.getValorEfeito();
        int duracao = magia.getDuracaoEfeito();
        NomeEfeito nomeEfeito = magia.getNomeEfeito();
        if (mpAtual >= custo) {
            TipoDeEfeito efeito = magia.getTipoEfeito();
            if (magia.getTipoAlvo() == TipoAlvo.ALIADO) {
                switch (efeito) {
                    case BUFF_EVASAO:
                    case BUFF_DEFESA:
                    case BUFF_AGILIDADE:
                    case BUFF_ATAQUE:
                        this.aplicarEfeito(efeito, valor, duracao, nomeEfeito);
                        break;
                    case CURA:
                        this.aplicarEfeito(efeito, valor, duracao, nomeEfeito);
                        this.receberCura(valor);
                        break;
                }

            } else {
                for (Personagem alvo : alvos) {
                    if (efeito == TipoDeEfeito.DANO_DIRETO || efeito == TipoDeEfeito.DANO_POR_TURNO) {
                        int chanceDeAcerto = 85 + (this.getPrecisao() - alvo.getEvasao());
                        if (new Random().nextInt(100) >= chanceDeAcerto) {
                            alvo.aplicarEfeito(TipoDeEfeito.BUFF_DEFESA, 0, 1, NomeEfeito.ESQUIVOU);
                            continue; 
                        }
                    }
                    
                    switch (efeito) {
                        case DEBUFF_ATAQUE:
                            alvo.aplicarEfeito(efeito, valor, duracao, nomeEfeito);
                            break;
                        case DEBUFF_DEFESA:
                            alvo.aplicarEfeito(efeito, valor, duracao, nomeEfeito);
                            break;
                        case DANO_POR_TURNO:
                            alvo.aplicarEfeito(efeito, valor, duracao, nomeEfeito);
                            break;
                        case DANO_DIRETO:
                            alvo.tomarDano(valor);
                    }
                }

            }
            if (this instanceof Mago) {
                this.setMp(this.getMp() - custo);
            }
            return true;
        } else {
            System.out.println("MP insuficiente para lançar " + magia.getNome());
            return false;
        }
    }

}
