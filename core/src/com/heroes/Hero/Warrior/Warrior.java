package com.heroes.Hero.Warrior;

import com.heroes.Hero.Npc;
import com.heroes.Hero.Status;
import com.heroes.Hero.Vector2D;
import com.heroes.Heroes;

import java.util.List;

public abstract class Warrior extends Npc {
    public Warrior(int attack, int protection, int[] damage, int hp, int speed) {
        super(attack, protection, damage, hp, speed);
    }

    @Override
    public void step(List<Npc> enemies) {
        if (getState() == Status.DEAD) return;

        Npc enemy = vector.findNearestOpponent(enemies);
        double distance = vector.distance(enemy.getVector());

        int x = getVector().x;
        int y = getVector().y;
        int step = 100;

        if (distance <= Math.sqrt(20_000)) {
            damage(enemy);
            System.out.println(getClass().getSimpleName() + " -> " + enemy.getClass().getSimpleName()); // TODO
        } else if (x > enemy.getVector().x && isValid(new Vector2D(x - step, y)) && x - step >= Heroes.LEFT_WIGHT) {
            setVector(new Vector2D(x - step, y));
            System.out.println(getClass().getSimpleName() + " -> " + enemy.getClass().getSimpleName()); // TODO
        } else if (x < enemy.getVector().x && isValid(new Vector2D(x + step, y)) && x + step <= Heroes.WITH) {
            setVector(new Vector2D(x + step, y));
            System.out.println(getClass().getSimpleName() + " -> " + enemy.getClass().getSimpleName()); // TODO
        } else if (y > enemy.getVector().y && isValid(new Vector2D(x, y - step)) && y - step >= Heroes.LOWER_BOUND) {
            setVector(new Vector2D(x, y - step));
            System.out.println(getClass().getSimpleName() + " -> " + enemy.getClass().getSimpleName()); // TODO
        } else if ((y < enemy.getVector().y && isValid(new Vector2D(x, y + step))) && y + step < Heroes.HEIGHT) {
            setVector(new Vector2D(getVector().x, y + step));
            System.out.println(getClass().getSimpleName() + " -> " + enemy.getClass().getSimpleName()); // TODO
        }

        if (getHp() < 3) setState(Status.INJURED);
    }

    public boolean isValid(Vector2D vector) {
        for (Npc npc : getFriends()) if (vector.x == npc.getVector().x && vector.y == npc.getVector().y) return false;
        return true;
    }
}
