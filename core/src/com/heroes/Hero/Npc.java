package com.heroes.Hero;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.heroes.View.AtlasAnimation;

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
    protected Vector2D vector2D;
    protected Vector2 vector2;
    public TextureRegion textureRegion;
    public int count;
    public AtlasAnimation animation;
    public TextureAtlas atlas;

    public Npc(int attack, int protection, int[] damage, int hp, int speed) {
        this.attack = attack;
        this.protection = protection;
        this.damage = damage;
        this.hp = hp;
        this.speed = speed;
        maxHp = hp;
        state = Status.STAND;
        textureRegion = new TextureRegion();
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
            animation = new AtlasAnimation(atlas, "DIE", 9, Animation.PlayMode.NORMAL);
//        } else if (hp < this.hp) {
//            this.state = Status.HURT;
//            animation = new AtlasAnimation(atlas, "HURT", 9, Animation.PlayMode.NORMAL);
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

    public Vector2D getVector2D() {
        return vector2D;
    }

    public void setVector2D(Vector2D vector2D) {
        this.vector2D = vector2D;
    }

    public void damage(Npc enemy) {
        int harm;
        if (this.attack == enemy.protection) {
            harm = (this.getDamage()[0] + this.getDamage()[1]) / 2;
        } else if (this.attack > enemy.protection) {
            harm = this.getDamage()[1];
        } else {
            harm = this.getDamage()[0];
        }
        enemy.hp -= harm;
    }

    @Override
    public String getInfo() {
        return hp + "/" + maxHp;
    }

    @Override
    public String toString() {
        return "vector: " + vector2D;
    }

    public boolean isFree(ArrayList<Npc> enemies) {
        for (Npc enemy : enemies) {
            if (getVector2D().x == enemy.vector2D.x && getVector2D().y == enemy.vector2D.y) return true;
        }
        return false;
    }
}
