package com.heroes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heroes.view.GameState;
import com.heroes.hero.healer.Monk;
import com.heroes.hero.healer.Wizard;
import com.heroes.hero.Npc;
import com.heroes.hero.shooter.Crossbow;
import com.heroes.hero.shooter.Sniper;
import com.heroes.hero.Status;
import com.heroes.hero.warrior.Lancer;
import com.heroes.hero.warrior.Peasant;
import com.heroes.hero.warrior.Robber;
import com.heroes.view.Asset;
import com.heroes.view.ViewUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class LikeHeroes extends ApplicationAdapter {
    public static final int WINDOW_HEIGHT = 1000;
    public static final int WINDOW_WIGHT = 1280;
    private GameState gameState = GameState.PLAY;
    public static final int WITH = 1100;
    public static final int HEIGHT = 1000;
    public static final int LEFT_WIGHT = 1;
    public static final int RIGHT_WIGHT = 11;
    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 7;
    private static final int SCALE = 100;

    public SpriteBatch batch;
    private final ViewUI adapter = new ViewUI();
    BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.CHARTREUSE);
        Asset.load();

        addHeroes();
    }

    @Override
    public void render() {
        adapter.button(); // реагирует на нажатие клавиши

        ScreenUtils.clear(Color.CLEAR);

        batch.begin();
        batch.draw(Asset.board, 0, 0);

        drawHeroes(true);
        drawHeroes(false);
        indicatorHp(green, -20);
        indicatorHp(black, 70);

        theEnd();
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
            if (nameClass.contains("shooter")) black.get(i).step(green);
            if (greenClass.contains("shooter")) green.get(i).step(black);
        }
        for (int i = 0; i < TEAM_SIZE; i++) {
            String nameClass = black.get(i).getClass().toString();
            String greenClass = green.get(i).getClass().toString();
            if (nameClass.contains("warrior")) black.get(i).step(green);
            if (greenClass.contains("warrior")) green.get(i).step(black);
        }
        for (int i = 0; i < TEAM_SIZE; i++) {
            String nameClass = black.get(i).getClass().toString();
            String greenClass = green.get(i).getClass().toString();
            if (nameClass.contains("healer")) black.get(i).step(green);
            if (greenClass.contains("healer")) green.get(i).step(black);
        }
        System.out.println("---------------------------------------------------------");
    }
    private void theEnd() {
        if (black.get(0).getVector2D().findNearestEnemy(green) == null || green.get(0).getVector2D().findNearestEnemy(black) == null) {
            font.draw(batch, "Game Over", (float) WINDOW_WIGHT / 2 - font.getLineHeight() / 2, (float) WINDOW_HEIGHT / 2);
        }
    }

    public void isEnd() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//                BitmapFont font = new BitmapFont();
//                batch.begin();
                ScreenUtils.clear(0, 0, 0, 1);
//                font.draw(batch, "Game Over", (float) WINDOW_WIGHT / 2 - font.getLineHeight() / 2, (float) WINDOW_HEIGHT / 2);
//                batch.end();
            }
        }, 1000);
    }

    private void drawHeroes(boolean stateDead) {
        for (int x = 1; x < 12; x++) {
            for (int y = 8; y >= 0; y--) {
                for (Npc npc : black) {
                    if (npc.getVector2D().x == x && npc.getVector2D().y == y) {
                        if (stateDead) {
                            drawAnimate(npc, true);
                        }
                    }
                }
                for (Npc npc : green) {
                    if (npc.getVector2D().x == x && npc.getVector2D().y == y) {
                        if (stateDead) {
                            drawAnimate(npc, false);
                        }
                    }
                }
            }
        }
    }

    private void drawAnimate(Npc npc, boolean pivot) {
        npc.animation.setTime(Gdx.graphics.getDeltaTime());
        if (pivot) {
            batch.draw(npc.animation.getFrame(), (npc.getVector2D().x * SCALE) - 100, (npc.getVector2D().y * SCALE), 150, 0, 300, 300, -1, 1, 0);
        } else {
            batch.draw(npc.animation.getFrame(), (npc.getVector2D().x * SCALE) - 100, (npc.getVector2D().y * SCALE));
        }
    }
    private void indicatorHp(List<Npc> team, int position) {
        for (Npc npc : team) {
            if (npc.getState() != Status.DEAD) {
                font.draw(batch, npc.getInfo(), (npc.getVector2D().x * SCALE) + position, (npc.getVector2D().y * SCALE) + 70);
            }
        }
    }
}
