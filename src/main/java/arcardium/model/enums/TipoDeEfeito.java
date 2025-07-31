package arcardium.model.enums;

/**
 *
 * @author Éric
 */
public enum TipoDeEfeito {
    DANO_DIRETO("Dano"), // Causa dano puro.
    CURA("Cura"), // Recupera HP do alvo.
    ELEMENTAL("Elemental"),
    CONTROLE("Controle"), // Exemplo: stun, freeze, silenciar.

    // Buffs (aumentos temporários)
    BUFF_ATAQUE("Buff de Ataque"),
    BUFF_DEFESA("Buff de Defesa"),
    BUFF_EVASAO("Buff de Evasão"),
    BUFF_AGILIDADE("Buff de Agilidade"),
    // Debuffs (reduções temporárias)
    DEBUFF_ATAQUE("Debuff de Ataque"),
    DEBUFF_DEFESA("Debuff de Defesa"),
    DEBUFF_AGILIDADE("Debuff de Agilidade"),
    DEBUFF_PRECISAO("Debuff de Precisão"),
    DEBUFF_EVASAO("Debuff de Evasão"),
    DANO_POR_TURNO("Dano por Turno"), // Dano contínuo (veneno, queimadura).
    PARALIZANTE("Paralizante");

    private final String nome;

    TipoDeEfeito(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public boolean isBuff() {
        return this.name().startsWith("BUFF_");
    }

    public boolean isDebuff() {
        return this.name().startsWith("DEBUFF_");
    }

    public boolean isDanoContinuo() {
        return this == DANO_POR_TURNO;
    }
}
