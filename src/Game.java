import java.util.ArrayList;

public class Game {

    private static final byte CUBE_MIN = 1;
    private static final byte CUBE_MAX = 6;
    private final int[] vinCube = {5, 6};

    private Entity attacker;
    private Entity defender;

    public void play() {
        this.init();

        String head = this.attacker instanceof Player ? "Игрок Монстр" : "Монстр Игрок";
        System.out.println(head);

        while (!this.checkFinish()) {
            this.playRound();
            this.swap();
        }
    }

    private void init() {
        this.attacker = new Player((byte) 15, (byte) 15, (byte) 1, (byte) 6, (short) 50);
        this.defender = new Monster((byte) 15, (byte) 15, (byte) 1, (byte) 6, (short) 50);
    }

    private void playRound() {
        for (int i = 0; i < this.getAttackModifier(); i++) {
            if (this.checkCube(this.getCube())) {
                this.defender.receiveAttack(attacker.getDamage());
                this.printStatistic();
            }
        }
    }

    private boolean checkFinish() {
        return !this.attacker.checkAlive() || !this.defender.checkAlive();
    }

    private void swap() {
        Entity temp = this.attacker;
        this.attacker = this.defender;
        this.defender = temp;
    }

    private int getAttackModifier() {
        int modifier = attacker.getAttack() - defender.getProtection() + 1;

        return modifier > 0 ? modifier : 1;
    }

    private byte getCube() {
        return (byte) ((Math.random() * (CUBE_MAX - CUBE_MIN)) + CUBE_MIN);
    }

    private boolean checkCube(int cube) {
        for (int i : this.vinCube) {
            if (i == cube) {

                return true;
            }
        }

        return false;
    }

    private void printStatistic() {
        String attackerHealth = String.valueOf(this.attacker.getHealthCurrent());
        String defenderHealth = String.valueOf(this.defender.getHealthCurrent());

        String text = this.attacker instanceof Player ? attackerHealth + "   " + defenderHealth : defenderHealth + "   " + attackerHealth;

        System.out.println(text);
    }
}
