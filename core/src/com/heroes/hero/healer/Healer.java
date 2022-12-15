package com.heroes.hero.healer;

import com.heroes.hero.Npc;
import com.heroes.hero.Status;

import java.util.List;

public abstract class Healer extends Npc {
    private double percent;

    public Healer(int attack, int protection, int[] damage, int hp, int speed) {
        super(attack, protection, damage, hp, speed);
    }

    @Override
    public void step(List<Npc> enemies) {
        if (getState() == Status.DEAD) return;
        percent = 1;
        Npc friendToCure = findCureFriend();
        Npc friendRelive = findDeadFriend();

        if (percent > 0.75) {
            Npc nearestEnemy = findNearestShooter(enemies);
            if (nearestEnemy == null) {
                nearestEnemy = vector2D.findNearestEnemy(enemies);
                if (nearestEnemy == null) {
                    return;
                }
            }
            nearestEnemy.setHp(nearestEnemy.getHp() + getDamage()[0]);
            System.out.println(getClass().getSimpleName() + " -> " + nearestEnemy.getClass().getSimpleName());
        } else if (percent > 0.5 && friendRelive != null) {
            friendRelive.setState(Status.STAND);
            friendRelive.setHp(1);
        } else {
            friendToCure.setHp(friendToCure.getHp() - getDamage()[0]);
            System.out.println(getClass().getSimpleName() + " -> " + friendToCure.getClass().getSimpleName());
        }
    }

    private Npc findDeadFriend() {
        Npc heroRelive = null;
        for (Npc friend : getFriends()) {
            if (friend.getState() == Status.DEAD && friend.getClass().toString().contains("shooter")) {
                heroRelive = friend;
            }
        }
        return heroRelive;
    }

    private Npc findCureFriend() {
        Npc friendToCure = null;
        for (Npc friend : getFriends()) {
            if (friend.getState() != Status.DEAD) {
                double heroPercent = (double) (friend.getHp() * (count - 1)) / (friend.getMaxHp() * (count - 1));
                if (heroPercent < percent) {
                    percent = heroPercent;
                    friendToCure = friend;
                }
            }
        }
        return friendToCure;
    }

    private Npc findNearestShooter(List<Npc> enemies) {
        Npc nearestEnemy = null;
        double distance = Double.MAX_VALUE;
        for (Npc enemy : enemies) {
            if (enemy.getClass().toString().contains("shooter") && enemy.getState() != Status.DEAD) {
                double minDistance = vector2D.findDistance(enemy.getVector2D());
                if (minDistance < distance) {
                    nearestEnemy = enemy;
                }
            }
        }
        return nearestEnemy;
    }
}
