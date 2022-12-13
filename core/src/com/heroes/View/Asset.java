package com.heroes.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Map;

public class Asset {
    public static Texture board;
    public static Music backgroundMusic;
    public static AtlasAnimation animationStand;
    public static AtlasAnimation animationDie;
    public static AtlasAnimation animationAttack;
    public static AtlasAnimation animationHurt;
    public static AtlasAnimation animationWalk;
    public static AtlasAnimation animationStandRobber;
    public static AtlasAnimation animationDieRobber;
    public static AtlasAnimation animationAttackRobber;
    public static AtlasAnimation animationWalkRobber;
    public static TextureAtlas atlasSniper;
    public static TextureAtlas atlasRobber;
    public Map<TextureAtlas, String> map;


    public static void load() {
        board = new Texture("board1.png");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("combat.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f);
        backgroundMusic.play();

        atlasSniper = new TextureAtlas("atlases/sniper.atlas");
        animationStand = new AtlasAnimation(atlasSniper, "Elf_01__IDLE", 9, Animation.PlayMode.LOOP);
        animationStand.setTime(Gdx.graphics.getDeltaTime());

        animationDie = new AtlasAnimation(atlasSniper, "Elf_01__DIE", 9, Animation.PlayMode.NORMAL);
        animationDie.setTime(Gdx.graphics.getDeltaTime());

        animationAttack = new AtlasAnimation(atlasSniper, "Elf_01__ATTACK", 9, Animation.PlayMode.NORMAL);
        animationAttack.setTime(Gdx.graphics.getDeltaTime());

        animationHurt = new AtlasAnimation(atlasSniper, "Elf_01__HURT", 9, Animation.PlayMode.LOOP);
        animationHurt.setTime(Gdx.graphics.getDeltaTime());

        atlasRobber = new TextureAtlas("atlases/robber.atlas");
        animationStandRobber = new AtlasAnimation(atlasRobber, "Troll_03_1_IDLE", 9, Animation.PlayMode.LOOP);
        animationStandRobber.setTime(Gdx.graphics.getDeltaTime());

        animationAttackRobber = new AtlasAnimation(atlasRobber, "Troll_03_1_ATTACK", 9, Animation.PlayMode.LOOP);
        animationAttackRobber.setTime(Gdx.graphics.getDeltaTime());

        animationDieRobber = new AtlasAnimation(atlasRobber, "Troll_03_1_DIE", 9, Animation.PlayMode.NORMAL);
        animationDieRobber.setTime(Gdx.graphics.getDeltaTime());

        animationWalkRobber = new AtlasAnimation(atlasRobber, "Troll_03_1_RUN", 9, Animation.PlayMode.LOOP);
        animationWalkRobber.setTime(Gdx.graphics.getDeltaTime());


    }
}
