package com.heroes.Hero.Shooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.heroes.Hero.Npc;
import com.heroes.Hero.Status;

import java.util.List;

public abstract class Shooter extends Npc {
    protected int shots;
    public TextureAtlas atlas;

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
        Npc opponent = this.vector.findNearestOpponent(team);
        this.damage(opponent);
        System.out.println(getClass().getSimpleName() + " -> " + opponent.getClass().getSimpleName()); // TODO
        this.shots--;
    }

    @Override
    public void damage(Npc hero) {
        int harm;
        if (this.getAttack() == hero.getProtection()) harm = (this.getDamage()[0] + this.getDamage()[1]) / 2;
        else if (this.getAttack() > hero.getProtection()) harm = this.getDamage()[1];
        else harm = this.getDamage()[0];

        if (this.getVector().distance(hero.getVector()) > this.getSpeed()) harm /= 2;
        hero.setHp(hero.getHp() - harm);
    }
}
