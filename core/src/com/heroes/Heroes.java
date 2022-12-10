package com.heroes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.heroes.View.ViewUI;
import sun.security.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Heroes extends ApplicationAdapter {
    public static final int WITH = 1100;
    public static final int HEIGHT = 1000;
    public static final int LEFT_WIGHT = 100;
    public static final int RIGHT_WIGHT = 1100;
    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 1100;
    SpriteBatch batch;
    private final ViewUI adapter = new ViewUI();
    BitmapFont font;
    private Music backgroundMusic;
    private Texture board;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        board = new Texture("board1.png");

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("combat.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f);
        backgroundMusic.play();

//		img = new Texture("skeleton.png");
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

        batch.draw(board, 0, 0);
        for (int i = green.size() - 1; i >= 0; i--) {
            Npc npc = green.get(i);
            if (npc.getState() == Status.DEAD && npc.isFree(black)) {
                continue;
            }

            npc.animation.setTime(Gdx.graphics.getDeltaTime());
            batch.draw(npc.animation.getFrame(), npc.getVector().x - 200, npc.getVector().y - 70);
//            npc.render(batch, 1);
            font.draw(batch, npc.getInfo(), npc.getVector().x + 50, npc.getVector().y + 15);
        }

//        for (Npc npc : green) {
//            if (npc.getState() == Status.DEAD && npc.isFree(black)) {
//                continue;
//            }
//            npc.animation.setTime(Gdx.graphics.getDeltaTime());
//            batch.draw(npc.animation.getFrame(), npc.getVector().x - 200, npc.getVector().y - 70);
////            npc.render(batch, 1);
//            font.draw(batch, npc.getInfo(), npc.getVector().x + 50, npc.getVector().y + 15);
//        }

//        for (Npc value : black) {
//            value.animation.reverse();
//        }

        for (int i = black.size() - 1; i >= 0; i--) {
            Npc npc = black.get(i);
            if (npc.getState() == Status.DEAD && npc.isFree(green)) {
                continue;
            }



            npc.animation.setTime(Gdx.graphics.getDeltaTime());
//            npc.animation.reverse();
            batch.draw(npc.animation.getFrame(), npc.getVector().x - 200, npc.getVector().y - 70);
//            npc.render(batch, 1);
            font.draw(batch, npc.getInfo(), npc.getVector().x + 50, npc.getVector().y + 15);
        }


//        for (Npc npc : black) {
//            if (npc.getState() == Status.DEAD && npc.isFree(green)) {
//                continue;
//            }
//            npc.animation.setTime(Gdx.graphics.getDeltaTime());
//
//            batch.draw(npc.animation.getFrame(), npc.getVector().x - 200, npc.getVector().y);
//
////            npc.render(batch, -1);
//            font.draw(batch, npc.getInfo(), npc.getVector().x + 20, npc.getVector().y + 15);
//        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        for (Npc npc : green) npc.dispose();
        for (Npc npc : black) npc.dispose();
        backgroundMusic.dispose();
    }

    public static ArrayList<Npc> green = new ArrayList<>();
    public static ArrayList<Npc> black = new ArrayList<>();

    public static void addHeroes() {
        int step = 0;
        green.add(new Sniper(green, 100, 0, 1));
        green.add(new Monk(green, 100, 100, 1));
        green.add(new Lancer(green, 100, 200, 1));
        green.add(new Peasant(green, 100, 300, 1));
        green.add(new Lancer(green, 100, 400, 1));
        green.add(new Sniper(green, 100, 500, 1));
        green.add(new Lancer(green, 100, 600, 1));
        green.add(new Lancer(green, 100, 700, 1));
//        green.add(new Lancer(green, 100, 800, 1));
//        green.add(new Lancer(green, 100, 900, 1));

        black.add(new Crossbow(black, 1100, 0, 1));
        black.add(new Wizard(black, 1100, 100, 1));
        black.add(new Robber(black, 1100, 200, 1));
        black.add(new Robber(black, 1100, 300, 1));
        black.add(new Robber(black, 1100, 400, 1));
        black.add(new Robber(black, 1100, 500, 1));
        black.add(new Robber(black, 1100, 600, 1));
        black.add(new Robber(black, 1100, 700, 1));
//        black.add(new Robber(black, 1100, 800, 1));
//        black.add(new Robber(black, 1100, 900, 1));
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
