package com.heroes.hero.shooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.heroes.hero.Npc;
import com.heroes.hero.Vector2D;

import java.util.ArrayList;

public class Sniper extends Shooter {

    public Sniper(ArrayList<Npc> friends, int x, int y, int count) {
        super(12, 10, new int[]{8, 10}, 15, 9, 32);
        super.friends = friends;
        super.vector2D = new Vector2D(x, y);
        super.count = count;
        atlas = new TextureAtlas(Gdx.files.internal("atlases/sniper.atlas"));
        super.setAnimation();
    }
}
