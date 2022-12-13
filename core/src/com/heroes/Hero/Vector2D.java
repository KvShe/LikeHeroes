package com.heroes.Hero;

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

    public double distance(Vector2D hero) {
        return Math.sqrt(Math.pow((this.x - hero.x), 2) + Math.pow((this.y - hero.y), 2));
    }
    public Npc findNearestOpponent(List<Npc> enemies) {
        int indexOpponent = 0;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getState() != Status.DEAD) {
                double distance = distance(enemies.get(i).getVector2D());
                if (minDistance > distance) {
                    minDistance = distance;
                    indexOpponent = i;
                }
            }
        }
        return enemies.get(indexOpponent);
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
