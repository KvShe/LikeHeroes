package com.heroes.hero.warrior;

import com.heroes.hero.Npc;
import com.heroes.hero.Status;
import com.heroes.hero.Vector2D;
import com.heroes.LikeHeroes;

import java.util.List;

public abstract class Warrior extends Npc {
    public Warrior(int attack, int protection, int[] damage, int hp, int speed) {
        super(attack, protection, damage, hp, speed);
    }

    @Override
    public void step(List<Npc> enemies) {
        if (getState() == Status.DEAD) return;

        Npc enemy = vector2D.findNearestEnemy(enemies);
        if (enemy == null) return;

        double distance = vector2D.findDistance(enemy.getVector2D());
        if (distance <= Math.sqrt(2)) {
            damage(enemy);
            setState(Status.ATTACK);
            return;
        }

        int x = getVector2D().x;
        int y = getVector2D().y;
        int xDelta = x - enemy.getVector2D().x;
        int yDelta = y - enemy.getVector2D().y;
        int step = 1;

        setVector2D(
                xDelta > 0 && isValid(x - step, y) ? new Vector2D(x - step, y) :
                xDelta < 0 && isValid(x + step, y) ? new Vector2D(x + step, y) :
                yDelta > 0 && isValid(x, y - step) ? new Vector2D(x, y - step) :
                                                        new Vector2D(x, y + step));
        setState(Status.STAND);
    }

    private boolean isValid(int x, int y) {
        return x >= LikeHeroes.LEFT_WIGHT
            && x <= LikeHeroes.RIGHT_WIGHT
            && y >= LikeHeroes.LOWER_BOUND
            && y <= LikeHeroes.UPPER_BOUND
            && vector2D.isValid(new Vector2D(x, y), getFriends());
    }
}
