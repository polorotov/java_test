public class Player extends Entity {

    short maxReceivedDamage = 0;
    short healHealthValue;
    private static final byte HEAL_HEALTH_PERCENT = 30;

    private byte healQuantity = 4;

    public Player(byte attack, byte protection, byte damageMin, byte damageMax, short health) {
        super(attack, protection, damageMin, damageMax, health);
        this.healHealthValue = (short) (healthMax * HEAL_HEALTH_PERCENT / 100);
    }

    public void receiveAttack(short damage) {
        this.receiveDamage(damage);
        if (!this.checkAlive()) {

            return;
        }
        this.setMaxReceivedDamage(damage);
        this.heal();
    }

    private void setMaxReceivedDamage(short damage) {
        if (damage > this.maxReceivedDamage) {
            this.maxReceivedDamage = damage;
        }
    }

    private void heal() {
        if (!this.canHeal() || !this.needHeal()) {

            return;
        }

        short healPoint = (short) (this.healthMax - this.healthCurrent);
        if (healPoint > this.healHealthValue) {
            healPoint = this.healHealthValue;
        }

        this.healthCurrent += healPoint;
        this.healQuantity--;
    }

    private boolean canHeal() {
        return this.healQuantity > 0;
    }

    private boolean needHeal() {
        return this.healHealthValue < this.healthMax - this.healthCurrent || this.maxReceivedDamage >= this.healthCurrent;
    }
}
