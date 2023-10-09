public class Player extends Entity {
    private byte healQuantity = 4;

    public Player(byte attack, byte protection, byte damageMin, byte damageMax, short health) {
        super(attack, protection, damageMin, damageMax, health);
    }

    public void receiveAttack(short damage) {
        this.receiveDamage(damage);

        if (!this.checkAlive()) {

            return;
        }

        this.heal();
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
