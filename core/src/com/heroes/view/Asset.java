package com.heroes.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Asset {
    public static Texture board;
    public static Music backgroundMusic;

    public static void load() {
        board = new Texture("board1.png");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("combat.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f);
        backgroundMusic.play();
    }
}
