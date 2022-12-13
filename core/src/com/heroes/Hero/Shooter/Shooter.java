package com.heroes.Hero.Shooter;

import com.heroes.Hero.Npc;
import com.heroes.Hero.Status;

import java.util.List;

public abstract class Shooter extends Npc {
    protected int shots;

    public Shooter(int attack, int protection, int[] damage, int hp, int speed, int shots) {
        super(attack, protection, damage, hp, speed);
        this.shots = shots;
    }

    @Override
    public void step(List<Npc> team) {
        if (getState() == Status.DEAD) {
            return;
        }
        // Крестьянин даёт стрелы
//        for (int i = 0; i < getTeam().size(); i++) {
//            if (getTeam().get(i).getClass().getSimpleName().equals("Peasant") && getTeam().get(i).getState() == Status.STAND) {
//                this.shots++;
//                getTeam().get(i).setState(Status.USED);
//                break;
//            }
//        }
        if (this.shots < 1) return;
        Npc opponent = this.vector2D.findNearestOpponent(team);
        this.damage(opponent);
        setState(Status.ATTACK);
        System.out.println(getClass().getSimpleName() + " -> " + opponent.getClass().getSimpleName()); // TODO: 13.12.2022
        this.shots--;

    }

    @Override
    public void damage(Npc enemy) {

        int harm;
        if (this.getAttack() == enemy.getProtection()) harm = (this.getDamage()[0] + this.getDamage()[1]) / 2;
        else if (this.getAttack() > enemy.getProtection()) harm = this.getDamage()[1];
        else harm = this.getDamage()[0];

        if (this.getVector2D().distance(enemy.getVector2D()) > this.getSpeed()) harm /= 2;

        enemy.setHp(enemy.getHp() - harm);
    }
}
