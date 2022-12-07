package com.heroes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
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
import com.heroes.View.KeyBoardAdapter;

import java.util.ArrayList;

public class Heroes extends ApplicationAdapter {
    public static final int WITH = 1100;
    public static final int HEIGHT = 1000;
    public static final int LEFT_WIGHT = 100;
    public static final int RIGHT_WIGHT = 1100;
    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 1100;
    SpriteBatch batch;
    Texture img;
    private KeyBoardAdapter adapter = new KeyBoardAdapter();
    BitmapFont font;
    private Music backgroundMusic;
    public static ArrayList<Npc> nameTeams;
    public static int count = 0;


    @Override
    public void create() {
//        Gdx.input.setInputProcessor(adapter); // без неё нажатие на клавишу работает


        batch = new SpriteBatch();
        font = new BitmapFont();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("combat.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f);
        backgroundMusic.play();

//		img = new Texture("skeleton.png");
        addHeroes();
    }

    @Override
    public void render() {

        adapter.button(); // реагирует на нажатие клавиши
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        for (Npc npc : green) {
            if (npc.getState() == Status.DEAD && npc.isFree(black)) {
                continue;
            }
            npc.render(batch, 1);
            font.draw(batch, npc.getInfo(), npc.getVector().x + 50, npc.getVector().y + 7);
        }
        for (Npc npc : black) {
            if (npc.getState() == Status.DEAD && npc.isFree(green)) {
                continue;
            }
            npc.render(batch, -1);
            font.draw(batch, npc.getInfo(), npc.getVector().x + 20, npc.getVector().y + 7);
        }
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
        green.add(new Sniper(green, 100, 0 + 5));
        green.add(new Monk(green, 100, 100 + 5));
        green.add(new Lancer(green, 100, 200 + 5));
        green.add(new Peasant(green, 100, 300 + 5));
        green.add(new Lancer(green, 100, 400 + 5));
        green.add(new Sniper(green, 100, 500 + 5));
        green.add(new Lancer(green, 100, 600 + 5));
        green.add(new Lancer(green, 100, 700 + 5));
        green.add(new Lancer(green, 100, 800 + 5));
        green.add(new Lancer(green, 100, 900 + 5));

        black.add(new Crossbow(black, 1100, 0 + 5));
        black.add(new Wizard(black, 1100, 100 + 5));
        black.add(new Robber(black, 1100, 200 + 5));
        black.add(new Robber(black, 1100, 300 + 5));
        black.add(new Robber(black, 1100, 400 + 5));
        black.add(new Robber(black, 1100, 500 + 5));
        black.add(new Robber(black, 1100, 600 + 5));
        black.add(new Robber(black, 1100, 700 + 5));
        black.add(new Robber(black, 1100, 800 + 5));
        black.add(new Robber(black, 1100, 900 + 5));
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
        System.out.println(black.get(1).getClass().getSimpleName() + " -> " + black.get(1).getState());
    }
}
