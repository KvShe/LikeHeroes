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

        Npc enemy = vector2D.findNearestOpponent(enemies);
        double distance = vector2D.distance(enemy.getVector2D());

        int x = getVector2D().x;
        int y = getVector2D().y;
        int xEnemy = enemy.getVector2D().x;
        int yEnemy = enemy.getVector2D().y;
        int step = 1;

        if (distance <= Math.sqrt(2)) {
            damage(enemy);
        } else if (x > xEnemy && isValid(x - step, y)) {
            setVector2D(new Vector2D(x - step, y));
        } else if (x < xEnemy && isValid(x + step, y)) {
            setVector2D(new Vector2D(x + step, y));
        } else if (y > yEnemy && isValid(x, y - step)) {
            setVector2D(new Vector2D(x, y - step));
        } else if (y < yEnemy && isValid(x, y + step)) {
            setVector2D(new Vector2D(x, y + step));
        }

        System.out.println(getClass().getSimpleName() + " -> " + enemy.getClass().getSimpleName());
        if (getHp() < 3) setState(Status.INJURED);
    }

    private boolean isValid(int x, int y) {
        return x >= Heroes.LEFT_WIGHT
                && x <= Heroes.RIGHT_WIGHT
                && y >= Heroes.LOWER_BOUND
                && y <= Heroes.UPPER_BOUND
                && vector2D.isValid(new Vector2D(x, y), getFriends());
    }
}
