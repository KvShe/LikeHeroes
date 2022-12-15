package com.heroes.hero;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.heroes.view.AtlasAnimation;

import java.util.ArrayList;
import java.util.List;

public abstract class Npc implements BaseInterface {
    private final int attack;
    private final int protection;
    private final int[] damage;
    private int hp;
    private final int maxHp;
    private final int speed;
    private Status state;
    public List<Npc> friends;
    protected Vector2D vector2D;
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
            setState(Status.DEAD);
//        } else if (hp < this.hp) {
//            this.state = Status.HURT;
//            setAnimation();
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
        setAnimation();
    }

    public List<Npc> getFriends() {
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

    public void setAnimation() {
        switch (this.state) {
            case DEAD:
                this.animation = new AtlasAnimation(atlas, "DIE", 9, Animation.PlayMode.NORMAL);
                break;
            case STAND:
                this.animation = new AtlasAnimation(atlas, "IDLE", 9, Animation.PlayMode.LOOP);
                break;
            case ATTACK:
                this.animation = new AtlasAnimation(atlas, "ATTACK", 9, Animation.PlayMode.LOOP);
                break;
            case HURT:
                this.animation = new AtlasAnimation(atlas, "HURT", 9, Animation.PlayMode.LOOP);
                break;
            case WALK:
                this.animation = new AtlasAnimation(atlas, "WALK", 9, Animation.PlayMode.LOOP);
        }
    }
}
