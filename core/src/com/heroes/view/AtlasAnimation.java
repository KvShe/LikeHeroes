package com.heroes.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AtlasAnimation {
    private Animation<TextureAtlas.AtlasRegion> animation;
    private float x, y, time;
    private Array<TextureAtlas.AtlasRegion> arrayAnimations;

    public AtlasAnimation(TextureAtlas atlas, String name, int fps, Animation.PlayMode playMode) {
        this.arrayAnimations = atlas.findRegions(name);
        animation = new Animation<>(1.0f / fps, arrayAnimations);
        animation.setPlayMode(playMode);
    }

    public void reverse() {
        for (int i = 0; i < arrayAnimations.size; i++) {
            arrayAnimations.get(i).flip(true, false);
        }
    }

    public void setTime(float time) {
        this.time += time;
    }

    public TextureRegion getFrame() {
        return animation.getKeyFrame(time);
    }
}
