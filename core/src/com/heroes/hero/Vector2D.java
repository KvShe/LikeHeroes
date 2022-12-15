package com.heroes.hero;

import java.util.List;

public class Vector2D {
    public int x;
    public int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double findDistance(Vector2D hero) {
        return Math.sqrt(Math.pow((this.x - hero.x), 2) + Math.pow((this.y - hero.y), 2));
    }
    public Npc findNearestEnemy(List<Npc> enemies) {
        Npc enemy = null;
        double minDistance = Double.MAX_VALUE;
        for (Npc npc : enemies) {
            if (npc.getState() != Status.DEAD) {
                double distance = findDistance(npc.getVector2D());
                if (minDistance > distance) {
                    minDistance = distance;
                    enemy = npc;
                }
            }
        }
        return enemy;
    }
    public boolean isValid(Vector2D vector2D, List<Npc> friends) {
        for (Npc friend : friends) {
            if (vector2D.equals(friend.getVector2D()) && friend.getState() != Status.DEAD) {
                return false;
            }
        }
        return true;
    }
}
