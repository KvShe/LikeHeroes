package com.heroes.hero.shooter;

import com.heroes.hero.Npc;
import com.heroes.hero.Status;

import java.util.List;

public abstract class Shooter extends Npc {
    protected int shots;

    public Shooter(int attack, int protection, int[] damage, int hp, int speed, int shots) {
        super(attack, protection, damage, hp, speed);
        this.shots = shots;
    }

    @Override
    public void step(List<Npc> enemies) {
        if (getState() == Status.DEAD) return;

        takeArrowsFrom(getFriends());
        if (this.shots == 0) return;

        Npc enemy = this.vector2D.findNearestEnemy(enemies);
        if (enemy == null) {
            setState(Status.STAND);
            return;
        }

        this.damage(enemy);
        setState(Status.ATTACK);

        System.out.println(getClass().getSimpleName() + " -> " + enemy.getClass().getSimpleName()); // TODO: 14.12.2022
    }

    @Override
    public void damage(Npc enemy) {

        int harm;
        if (this.getAttack() == enemy.getProtection()) harm = (this.getDamage()[0] + this.getDamage()[1]) / 2;
        else if (this.getAttack() > enemy.getProtection()) harm = this.getDamage()[1];
        else harm = this.getDamage()[0];

        if (this.getVector2D().findDistance(enemy.getVector2D()) > this.getSpeed()) harm /= 2;

        enemy.setHp(enemy.getHp() - harm);

        this.shots--;
    }
    private void takeArrowsFrom(List<Npc> friends) {
        for (Npc friend : friends) {
            if (friend.getClass().getSimpleName().equals("Peasant") && friend.getState() == Status.STAND) {
                this.shots++;
                friend.setState(Status.USED);
                break;
            }
        }
    }
}
