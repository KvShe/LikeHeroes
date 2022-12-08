package com.heroes.Hero.Healer;

import com.heroes.Hero.Npc;
import com.heroes.Hero.Status;

import java.util.ArrayList;
import java.util.List;

public abstract class Healer extends Npc {
    public Healer(int attack, int protection, int[] damage, int hp, int speed) {
        super(attack, protection, damage, hp, speed);
    }

    //    @Override
    public void step2(List<Npc> enemies) {
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
                } else if (minHp > getFriends().get(i).getMaxHp() - getFriends().get(i).getHp()) {
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

    @Override
    public void step(List<Npc> enemies) {
        if (getState() == Status.DEAD) return;
        int index = 0;
        Npc hero = null;
        double percent = 1;
        Npc heroRelive = null;
        if (count != 0) {
            for (Npc friend : getFriends()) {
                if (friend.getState() == Status.DEAD && friend.getClass().toString().contains("Shooter")) {
                    heroRelive = friend;
                }
                if (friend.getState() != Status.DEAD) {
                    double heroPercent = (double) (friend.getHp() * (count - 1)) / (friend.getMaxHp() * (count - 1));
                    if (heroPercent < percent) {
                        percent = heroPercent;
                        hero = friend;
                    }
                }
            }
            if (percent > 0.75) {
                double distance = Double.MAX_VALUE;
                for (Npc enemy : enemies) {
                    if (enemy.getClass().toString().contains("Shooter") && enemy.getState() != Status.DEAD) {
                        double minDistance = vector.distance(enemy.getVector());
                        if (minDistance < distance) {
                            hero = enemy;
                        }
                    }
                }
                if (hero == null) {
                    hero = vector.findNearestOpponent(enemies);
                }
                hero.setHp(hero.getHp() + getDamage()[0]);
                System.out.println(getClass().getSimpleName() + " -> " + hero.getClass().getSimpleName());
            } else if (percent > 0.5 && heroRelive != null) {
                heroRelive.setState(Status.STAND);
                heroRelive.setHp(1);
            }
            else {
                hero.setHp(hero.getHp() - getDamage()[0]);
                System.out.println(getClass().getSimpleName() + " -> " + hero.getClass().getSimpleName());
            }
        }
    }

    private void relive(Npc hero) {
        if (hero == null) return;
    }
}
