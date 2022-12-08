package com.heroes.Hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.heroes.View.Animator;

import java.util.ArrayList;

public abstract class Npc implements BaseInterface {
    private final int attack;
    private final int protection;
    private final int[] damage;
    private int hp;
    private final int maxHp;
    private final int speed;
    private Status state;
    public ArrayList<Npc> friends;
    protected Vector2D vector;
    protected Vector2 vector2;
    public Texture texture;
    public TextureRegion textureRegion;
    public int count;
    public Animator animator;

    public Npc(int attack, int protection, int[] damage, int hp, int speed) {
        this.attack = attack;
        this.protection = protection;
        this.damage = damage;
        this.hp = hp;
        this.speed = speed;
        maxHp = hp;
        state = Status.STAND;
        texture = null;
        textureRegion = new TextureRegion();
//        textureRegion.setRegion();
        count = 0;

    }

    public int getAttack() {
        return attack;
    }

    public int getProtection() {
        return protection;
    }

    public int[] getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp > getMaxHp()) {
            this.hp = maxHp;
        } else if (hp <= 0) {
            this.hp = 0;
            this.state = Status.DEAD;
        } else {
            this.hp = hp;
        }
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getSpeed() {
        return speed;
    }

    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    public ArrayList<Npc> getFriends() {
        return friends;
    }

    public Vector2D getVector() {
        return vector;
    }

    public void setVector(Vector2D vector) {
        this.vector = vector;
    }

    public void damage(Npc hero) {
        int harm;
        if (this.attack == hero.protection) {
            harm = (this.getDamage()[0] + this.getDamage()[1]) / 2;
        } else if (this.attack > hero.protection) {
            harm = this.getDamage()[1];
        } else {
            harm = this.getDamage()[0];
        }
        hero.hp -= harm;
        if (hero.hp <= 0) {
            hero.hp = 0;
            hero.state = Status.DEAD;
        }
    }

    @Override
    public String getInfo() {
        return hp + "/" + maxHp;
    }

    @Override
    public String toString() {
        return "vector: " + vector;
    }

    public void move(int x, int y) {
        setVector(new Vector2D(y, x));
    }

    public void render(Batch batch, int pivot) {
        animator = new Animator();
        if (pivot == -1) {
            if (getClass().getSimpleName().contains("Peasant")) {
                batch.draw(texture, vector.x + 100, vector.y, 80 * pivot, 100);
            } else if (getClass().getSimpleName().contains("Sniper")) {
                batch.draw(texture, vector.x + 100, vector.y, 80 * pivot, 100);
            } else if (getClass().getSimpleName().contains("Monk")) {
                batch.draw(texture, vector.x + 100, vector.y, 100 * pivot, 100);
            } else if (getClass().getSimpleName().contains("Robber")) {
                batch.draw(texture, vector.x + 100, vector.y, 80 * pivot, 100);
            } else if (getClass().getSimpleName().contains("Lancer")) {
                batch.draw(texture, vector.x + 100, vector.y, 100 * pivot, 100);
            } else {
                batch.draw(texture, vector.x + 100, vector.y, 120 * pivot, 100);
            }
        } else {
            if (getClass().getSimpleName().contains("Peasant")) {
                batch.draw(texture, vector.x, vector.y, 80 * pivot, 100);
            } else if (getClass().getSimpleName().contains("Sniper")) {
                batch.draw(texture, vector.x, vector.y, 80 * pivot, 100);
            } else if (getClass().getSimpleName().contains("Monk")) {
                batch.draw(texture, vector.x, vector.y, 100 * pivot, 100);
            } else if (getClass().getSimpleName().contains("Robber")) {
                batch.draw(texture, vector.x, vector.y, 80 * pivot, 100);
            } else if (getClass().getSimpleName().contains("Lancer")) {
                batch.draw(texture, vector.x, vector.y, 100 * pivot, 100);
            } else {
                batch.draw(texture, vector.x, vector.y, 120 * pivot, 100);
            }
        }
    }

    public void dispose() {
        texture.dispose();
    }

    private boolean isValid(ArrayList<Npc> enemies) {
        for (Npc enemy : enemies) {
            if (this.vector.x == enemy.vector.x && this.vector.y == enemy.vector.y && this.state == Status.DEAD) {
                return true;
            }
        }
        return false;
    }
    public boolean isFree(ArrayList<Npc> enemies) {
        for (Npc enemy : enemies) {
            if (getVector().x == enemy.vector.x && getVector().y == enemy.vector.y) return true;
        }
        return false;
    }
}
