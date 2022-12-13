package com.heroes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heroes.Hero.Healer.Monk;
import com.heroes.Hero.Healer.Wizard;
import com.heroes.Hero.Npc;
import com.heroes.Hero.Shooter.Crossbow;
import com.heroes.Hero.Shooter.Sniper;
import com.heroes.Hero.Status;
import com.heroes.Hero.Warrior.Lancer;
import com.heroes.Hero.Warrior.Peasant;
import com.heroes.Hero.Warrior.Robber;
import com.heroes.View.Asset;
import com.heroes.View.ViewUI;

import java.util.ArrayList;

public class Heroes extends ApplicationAdapter {
    public static final int WITH = 1100;
    public static final int HEIGHT = 1000;
    public static final int step = 1;
    public static final int LEFT_WIGHT = 1;
    public static final int RIGHT_WIGHT = 11;
    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 7;

    SpriteBatch batch;
    private final ViewUI adapter = new ViewUI();
    BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.CHARTREUSE);
        Asset.load();

        addHeroes();
        for (Npc value : black) {
            value.animation.reverse();
        }
    }

    @Override
    public void render() {

        adapter.button(); // реагирует на нажатие клавиши
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        batch.draw(Asset.board, 0, 0);

//        switch (green.get(0).getState()) {
//            case STAND:
//                Asset.animationStand.setTime(Gdx.graphics.getDeltaTime());
//                batch.draw(Asset.animationStand.getFrame(), green.get(0).getVector().x, green.get(0).getVector().y);
//                break;
//            case DEAD:
//                Asset.animationDie.setTime(Gdx.graphics.getDeltaTime());
//                batch.draw(Asset.animationDie.getFrame(), green.get(0).getVector().x, green.get(0).getVector().y);
//                break;
//            case ATTACK:
//                Asset.animationAttack.setTime(Gdx.graphics.getDeltaTime());
//                batch.draw(Asset.animationAttack.getFrame(), green.get(0).getVector().x, green.get(0).getVector().y);
//                break;
//            case HURT:
//                Asset.animationHurt.setTime(Gdx.graphics.getDeltaTime());
//                batch.draw(Asset.animationHurt.getFrame(), green.get(0).getVector().x, green.get(0).getVector().y);
//                break;
//        }
//
//        switch (black.get(2).getState()) {
//            case STAND:
//                Asset.animationStandRobber.setTime(Gdx.graphics.getDeltaTime());
//                batch.draw(Asset.animationStandRobber.getFrame(), black.get(2).getVector().x, black.get(2).getVector().y);
//                break;
//            case DEAD:
//                Asset.animationDieRobber.setTime(Gdx.graphics.getDeltaTime());
//                batch.draw(Asset.animationDieRobber.getFrame(), black.get(2).getVector().x, black.get(2).getVector().y);
//                break;
//            case ATTACK:
//                Asset.animationAttackRobber.setTime(Gdx.graphics.getDeltaTime());
//                batch.draw(Asset.animationAttackRobber.getFrame(), black.get(2).getVector().x, black.get(2).getVector().y);
//                break;
//            case HURT:
//                Asset.animationWalkRobber.setTime(Gdx.graphics.getDeltaTime());
//                batch.draw(Asset.animationWalkRobber.getFrame(), black.get(2).getVector().x, black.get(2).getVector().y);
//                break;
//        }

        int scale = 100;

        for (int i = green.size() - 1; i >= 0; i--) {
            Npc npc = green.get(i);
            if (npc.getState() == Status.DEAD && npc.isFree(black)) {
                continue;
            }

            npc.animation.setTime(Gdx.graphics.getDeltaTime());
            batch.draw(npc.animation.getFrame(), (npc.getVector2D().x * scale) - 100, (npc.getVector2D().y * scale));
        }

        for (Npc npc : green) {
            if (npc.getState() != Status.DEAD) {
                font.draw(batch, npc.getInfo(), (npc.getVector2D().x * scale) - 20, (npc.getVector2D().y * scale) + 70);
            }
        }

        for (int i = black.size() - 1; i >= 0; i--) {
            Npc npc = black.get(i);
            if (npc.getState() == Status.DEAD && npc.isFree(green)) {
                continue;
            }

            npc.animation.setTime(Gdx.graphics.getDeltaTime());
            batch.draw(npc.animation.getFrame(), (npc.getVector2D().x * scale) - 100, (npc.getVector2D().y * scale));
        }

        for (Npc npc : black) {
            if (npc.getState() != Status.DEAD) {
                font.draw(batch, npc.getInfo(), (npc.getVector2D().x * scale) + 70, (npc.getVector2D().y * scale) + 70);
            }

        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public static ArrayList<Npc> green = new ArrayList<>();
    public static ArrayList<Npc> black = new ArrayList<>();

    public void addHeroes() {
        green.add(new Sniper(green, 1, 0, 1));
        green.add(new Monk(green, 1, 1, 1));
        green.add(new Lancer(green, 1, 2, 1));
        green.add(new Peasant(green, 1, 3, 1));
        green.add(new Lancer(green, 1, 4, 1));
        green.add(new Sniper(green, 1, 5, 1));
        green.add(new Lancer(green, 1, 6, 1));
        green.add(new Lancer(green, 1, 7, 1));

        black.add(new Crossbow(black, 11, 0, 1));
        black.add(new Wizard(black, 11, 1, 1));
        black.add(new Robber(black, 11, 2, 1));
        black.add(new Robber(black, 11, 3, 1));
        black.add(new Robber(black, 11, 4, 1));
        black.add(new Robber(black, 11, 5, 1));
        black.add(new Robber(black, 11, 6, 1));
        black.add(new Robber(black, 11, 7, 1));
    }

    public static void priorityMove() {
        int TEAM_SIZE = black.size();
        for (int i = 0; i < TEAM_SIZE; i++) {
            String nameClass = black.get(i).getClass().toString();
            String greenClass = green.get(i).getClass().toString();
            if (nameClass.contains("Shooter")) black.get(i).step(green);
            if (greenClass.contains("Shooter")) green.get(i).step(black);
        }
        for (int i = 0; i < TEAM_SIZE; i++) {
            String nameClass = black.get(i).getClass().toString();
            String greenClass = green.get(i).getClass().toString();
            if (nameClass.contains("Warrior")) black.get(i).step(green);
            if (greenClass.contains("Warrior")) green.get(i).step(black);
        }
        for (int i = 0; i < TEAM_SIZE; i++) {
            String nameClass = black.get(i).getClass().toString();
            String greenClass = green.get(i).getClass().toString();
            if (nameClass.contains("Healer")) black.get(i).step(green);
            if (greenClass.contains("Healer")) green.get(i).step(black);
        }
        System.out.println("---------------------------------------------------------");
    }
}
