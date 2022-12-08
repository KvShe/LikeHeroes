package com.heroes.View;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator implements ApplicationListener {
    private TextureAtlas textureAtlas;
    public Animation<TextureRegion> animation;
    public SpriteBatch spriteBatch;
    float stateTime;
    String nameAtlas;
    String nameAction;

    @Override
    public void create() {
        textureAtlas = new TextureAtlas(Gdx.files.internal(nameAtlas));
        TextureRegion[] frames = new TextureRegion[4];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = textureAtlas.findRegion(nameAction, i);
        }
        animation = new Animation<>(0.025f, frames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
