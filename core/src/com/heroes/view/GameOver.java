package com.heroes.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.heroes.LikeHeroes;

public class GameOver extends ScreenAdapter {
    LikeHeroes heroes;


    public GameOver(LikeHeroes heroes) {
        this.heroes = heroes;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        BitmapFont font = new BitmapFont();

        heroes.batch.begin();
        font.draw(heroes.batch, "Game Over", 500, 500);
        heroes.batch.end();
    }
}
