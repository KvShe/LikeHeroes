package com.heroes.Hero.Healer;

import com.heroes.Hero.Npc;
import com.heroes.Hero.Status;

import java.util.List;

public abstract class Healer extends Npc {
    public Healer(int attack, int protection, int[] damage, int hp, int speed) {
        super(attack, protection, damage, hp, speed);
    }

    @Override
    public void step(List<Npc> enemies) {
        if (getState() == Status.DEAD) return;



        int minHp = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < getFriends().size(); i++) {

            if (getFriends().get(i).getState() != Status.DEAD) {
                if (getFriends().get(i).getState() == Status.INJURED) {
                    getFriends().get(i).setHp(getHp() - getDamage()[0]);
                    getFriends().get(i).setState(Status.STAND);
                    System.out.println(getClass().getSimpleName() + " -> " + getFriends().get(i).getClass().getSimpleName()); // TODO
                    return;
                }
                else if (minHp > getFriends().get(i).getMaxHp() - getFriends().get(i).getHp()) {
                    minHp = getFriends().get(i).getMaxHp() - getFriends().get(i).getHp();
                    index = i;
                }
            }
        }
        if (minHp != Integer.MAX_VALUE) {
            System.out.println(getClass().getSimpleName() + " -> " + getFriends().get(index).getClass().getSimpleName()); // TODO
            getFriends().get(index).setHp(getFriends().get(index).getHp() - getDamage()[0]);
        }
    }
}
