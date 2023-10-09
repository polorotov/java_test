public class Entity {
    private byte attack;
    private byte protection;
    private byte damageMin, damageMax;
    short healthMax;
    short healthCurrent;
    short healHealthValue;

    short maxReceivedDamage = 0;
    private static final byte HEAL_HEALTH_PERCENT = 30;

    private static final byte ATTACK_MIN = 1;
    private static final byte ATTACK_MAX = 30;

    private static final byte PROTECTION_MIN = 1;
    private static final byte PROTECTION_MAX = 30;

    private static final short HEALTH_MIN = 0;

    public Entity(byte attack, byte protection, byte damageMin, byte damageMax, short health) {
        this.setAttack(attack);
        this.setProtection(protection);
        this.setDamage(damageMin, damageMax);
        this.setHealth(health);

        this.healHealthValue = (short) (healthMax * HEAL_HEALTH_PERCENT / 100);
    }

    public void receiveAttack(short damage) {
        this.receiveDamage(damage);
    }

    public boolean checkAlive() {
        return this.healthCurrent > 0;
    }

    public byte getAttack() {
        return this.attack;
    }

    public byte getDamage() {
        return (byte) ((Math.random() * (this.damageMax - this.damageMin)) + this.damageMin);
    }

    public byte getProtection() {
        return this.protection;
    }

    public short getHealthCurrent() {
        return healthCurrent;
    }

    private void setAttack(byte attack) {
        if (attack < ATTACK_MIN || attack > ATTACK_MAX) {
            System.out.println("no");

            return;
        }
        this.attack = attack;
    }

    private void setProtection(byte protection) {
        if (protection < PROTECTION_MIN || protection > PROTECTION_MAX) {
            System.out.println("no");

            return;
        }
        this.protection = protection;
    }

    private void setDamage(byte damageMin, byte damageMax) {
        this.damageMin = damageMin;
        this.damageMax = damageMax;
    }

    private void setHealth(short health) {
        if (health < HEALTH_MIN) {
            System.out.println("no");

            return;
        }
        this.healthMax = this.healthCurrent = health;
    }

    void receiveDamage(short damage) {
        this.healthCurrent -= damage;
        if (this.healthCurrent < 0) {
            this.healthCurrent = 0;
        }
    }
}
