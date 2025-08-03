/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arcardium.model;

import arcardium.model.enums.NomeEfeito;
import arcardium.model.enums.RankInimigo;
import arcardium.model.enums.TipoAlvo;
import arcardium.model.enums.TipoDeEfeito;
import arcardium.utils.MathUtils;
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
    private boolean estaDefendendo;
    private int regeneraçaoDeMana;
    private int chanceDeCritico;
    private double danoCritico;
    private List<EfeitoAtivo> efeitoAtivo;
    private List<Personagem> aliadosInvocados;

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
        this.estaDefendendo = false;
        this.chanceDeCritico = 5;
        this.danoCritico = 1.2;
        this.efeitoAtivo = new ArrayList<>();
        this.aliadosInvocados = new ArrayList<>();
    }

    public Personagem(String nome, int hp, int mp, int regeneraçaoDeMana, int atk, int def, int agi, int precisao, int evasao) {
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
        this.estaDefendendo = false;
        this.efeitoAtivo = new ArrayList<>();
        this.aliadosInvocados = new ArrayList<>();
        this.regeneraçaoDeMana = regeneraçaoDeMana;
    }

    public List<Personagem> getAliadosInvocados() {
        return aliadosInvocados;
    }

    public void setAliadosInvocados(List<Personagem> aliadosInvocados) {
        this.aliadosInvocados = aliadosInvocados;
    }
    
    public void resetarAliadosInvocados(){
        this.aliadosInvocados.clear();
    }

    public boolean adicionarAliados(Inimigo aliado) {
        // Continua a invocar até ter no maximo 3 invocações
        if (aliadosInvocados.size() < 3) {
            aliadosInvocados.add(aliado);
            return true;
        } else {
            
            Personagem inimigoComMenorHp = aliadosInvocados.get(0);

            for (Personagem i : aliadosInvocados) {
                if (i.getHp() < inimigoComMenorHp.getHp()) {
                    inimigoComMenorHp = i;
                }
            }

            // Remove o que tem menor HP e invoca um novo
            aliadosInvocados.remove(inimigoComMenorHp);

            // Adiciona o novo aliado
            aliadosInvocados.add(aliado);
            return true;
        }
    }

    public int getChanceDeCritico() {
        return chanceDeCritico;
    }

    public void setChanceDeCritico(int chanceDeCritico) {
        this.chanceDeCritico = chanceDeCritico;
    }

    public double getDanoCritico() {
        return danoCritico;
    }

    public void setDanoCritico(double danoCritico) {
        this.danoCritico = danoCritico;
    }

    public String getNome() {
        return nome;
    }

    public boolean isEstaDefendendo() {
        return estaDefendendo;
    }

    public void setEstaDefendendo(boolean estaDefendendo) {
        this.estaDefendendo = estaDefendendo;
    }

    public int getRegeneraçaoDeMana() {
        return regeneraçaoDeMana;
    }

    public void setRegeneraçaoDeMana() {
        this.regeneraçaoDeMana = regeneraçaoDeMana;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getPrecisao() {
        int precisaoTotal = this.precisao;
        for (EfeitoAtivo efeito : this.efeitoAtivo) {
            if (efeito.getTipoEfeito() == TipoDeEfeito.DEBUFF_PRECISAO) {
                precisaoTotal += efeito.getValor();
            }
            if (efeito.getTipoEfeito() == TipoDeEfeito.DEBUFF_PRECISAO) {
                precisaoTotal -= efeito.getValor();
            }
        }
        return Math.max(0, precisaoTotal);
    }

    public int getEvasao() {
        int evasaoTotal = this.evasao;
        for (EfeitoAtivo efeito : this.efeitoAtivo) {
            if (efeito.getTipoEfeito() == TipoDeEfeito.BUFF_EVASAO) {
                evasaoTotal += efeito.getValor();
            }
            if (efeito.getTipoEfeito() == TipoDeEfeito.DEBUFF_EVASAO) {
                evasaoTotal -= efeito.getValor();
            }
        }
        return Math.max(0, evasaoTotal);
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
        Iterator<EfeitoAtivo> iterator = this.efeitoAtivo.iterator();
        boolean headerExibido = false;

        while (iterator.hasNext()) {
            EfeitoAtivo efeito = iterator.next();

            if (!headerExibido) {
                System.out.println("=====&  [EFEITOS POR TURNO]  %======");
                headerExibido = true;
            }

            if (efeito.getTipoEfeito() == TipoDeEfeito.DANO_POR_TURNO) {
                int dano = MathUtils.calculaDano(this, efeito.getValor());
                System.out.println(this.nome + " sofreu [" + dano + "] de dano [DANO POR TURNO]");
                this.tomarDano(efeito.getValor());

            } else if (efeito.getTipoEfeito() == TipoDeEfeito.CURA) {
                System.out.println(this.nome + " curou [" + efeito.getValor() + "] [CURA POR TURNO]");
                this.receberCura(efeito.getValor());
            }

            efeito.setDuracao(efeito.getDuracao() - 1);
            if (efeito.getDuracao() <= 0) {
                iterator.remove();
            }
        }

        if (this.regeneraçaoDeMana > 0) {
            int regeneracao = this.getRegeneraçaoDeMana();
            this.setMp(this.mp += regeneracao);
            if (this.mp > this.getMaxMp()) {
                this.mp = this.getMaxMp();
            }
        }
    }

    public void diminuirDuracaoEfeitoCC() {
        Iterator<EfeitoAtivo> iterator = this.efeitoAtivo.iterator();
        while (iterator.hasNext()) {
            EfeitoAtivo efeito = iterator.next();
            if (efeito.getTipoEfeito() == TipoDeEfeito.CONTROLE) {
                efeito.setDuracao(efeito.getDuracao() - 1);
                if (efeito.getDuracao() <= 0) {
                    iterator.remove();
                }
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

    public void tomarDano(int dano) {
        int defesaTotal = this.getDef();
        int danoReal = (int) (dano * (100.0 / (100.0 + defesaTotal)));
        if (this.isEstaDefendendo()) {

            danoReal = danoReal / 2;
        }

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
        if (efeitoAtivo.isEmpty()) {
            return false;
        }
        for (EfeitoAtivo e : efeitoAtivo) {
            if (e.getNomeEfeito() == nome) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaSeEstaSobCC() {
        if (efeitoAtivo.isEmpty()) {
            return false;
        }
        for (EfeitoAtivo e : efeitoAtivo) {
            if (e.getTipoEfeito() == TipoDeEfeito.CONTROLE) {
                return true;
            }
        }
        return false;
    }

    public void resetarEfeitos() {
        if (!this.efeitoAtivo.isEmpty()) {
            this.efeitoAtivo.clear();
        }

    }

    protected void receberCura(int valor) {
        this.hp += valor;
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
    }

    public void removerEfeito(NomeEfeito efeito) {
        Iterator<EfeitoAtivo> iterator = this.efeitoAtivo.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getNomeEfeito() == efeito) {
                iterator.remove();
            }
        }
    }

    public boolean lancarHabilidade(Magia magia, List<Personagem> alvos) {
        InimigoFactory inimigoFactory = new InimigoFactory();
        int custo = magia.getCustoMana();
        if (this.getMp() < custo) {
            System.out.println("MP insuficiente para lançar " + magia.getNome());
            return false;
        }

        if (this instanceof Mago) {
            this.setMp(this.getMp() - custo);
        }
        //Aqui que acontece a magia dessa nova atualização agora fazemos isso para cada efeito da magia
        for (Efeito efeitos : magia.getEfeitos()) {
            TipoDeEfeito tipoEfeito = efeitos.getTipoEfeito();
            int valor = efeitos.getValor();
            int duracao = efeitos.getDuracao();
            NomeEfeito nomeEfeito = efeitos.getNomeEfeito();

            if (efeitos.getTipoAlvo() == TipoAlvo.ALIADO) {
                switch (tipoEfeito) {
                    case INVOCACAO:
                        Inimigo inimigo = inimigoFactory.criaInvocacao(this, nomeEfeito);
                        this.adicionarAliados(inimigo);
                        break;

                    case BUFF_EVASAO:
                    case BUFF_DEFESA:
                    case BUFF_AGILIDADE:
                    case BUFF_ATAQUE:
                        this.aplicarEfeito(tipoEfeito, valor, duracao, nomeEfeito);
                        break;
                    case CURA:
                        this.receberCura(valor);
                        break;
                }
            } else {
                for (Personagem alvo : alvos) {
                    // Alteração que faz com que fique de 5% até 95% previnindo erros e 100% de critico.
                    int chanceDeAcerto = Math.max(5, Math.min(95, 85 + (this.getPrecisao() - alvo.getEvasao())));
                    if (new Random().nextInt(100) >= chanceDeAcerto) {
                        alvo.aplicarEfeito(TipoDeEfeito.BUFF_DEFESA, 0, 0, NomeEfeito.ESQUIVOU);
                        continue;
                    }
                    switch (tipoEfeito) {
                        //Agora dano por turno ja proca de inicio tambem.  
                        case DANO_POR_TURNO:
                        case DANO_DIRETO:
                            int danoFinal = valor;
                            if (new Random().nextInt(100) < this.getChanceDeCritico()) {
                                danoFinal = (int) (valor * this.getDanoCritico());
                                alvo.aplicarEfeito(TipoDeEfeito.CONTROLE, 0, 1, nomeEfeito.SOFREUCRITICO);
                            }
                            alvo.tomarDano(danoFinal);
                            break;
                        case DEBUFF_ATAQUE:
                        case DEBUFF_DEFESA:
                        case DEBUFF_AGILIDADE:
                        case DEBUFF_EVASAO:
                        case DEBUFF_PRECISAO:
                        case PARALIZANTE:
                        case CONTROLE:
                            alvo.aplicarEfeito(tipoEfeito, valor, duracao, nomeEfeito);
                            break;
                    }
                }
            }
        }
        return true;
    }

    public String toStringStatus() {
        return "ATK: " + atk + "|DEF: " + def + "|AGI: " + agi + "|EVA: " + evasao + "|PRE: " + precisao;
    }
}
