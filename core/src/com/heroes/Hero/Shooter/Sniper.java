package com.heroes.Hero.Shooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.heroes.Hero.Npc;
import com.heroes.Hero.Vector2D;
import com.heroes.View.AtlasAnimation;

import java.util.ArrayList;

public class Sniper extends Shooter {

    public Sniper(ArrayList<Npc> friends, int x, int y, int count) {
        super(12, 10, new int[]{8, 10}, 15, 9, 32);
        super.friends = friends;
        super.vector2D = new Vector2D(x, y);
        super.vector2 = new Vector2(x, y);
        super.count = count;
        atlas = new TextureAtlas(Gdx.files.internal("atlases/sniper.atlas"));
        super.animation = new AtlasAnimation(atlas, "IDLE", 9, Animation.PlayMode.LOOP);
    }
}
