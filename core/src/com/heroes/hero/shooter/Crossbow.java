package com.heroes.hero.shooter;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.heroes.hero.Npc;
import com.heroes.hero.Vector2D;
import com.heroes.view.AtlasAnimation;

import java.util.ArrayList;

public class Crossbow extends Shooter {

    public Crossbow(ArrayList<Npc> friends, int x, int y, int count) {
        super(6, 3, new int[]{2, 3}, 10, 4, 16);
        super.friends = friends;
        super.vector2D = new Vector2D(x, y);
        super.count = count;
        atlas = new TextureAtlas("atlases/crossbow.atlas");
        super.setAnimation();
    }
}
