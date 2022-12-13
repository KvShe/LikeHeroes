package com.heroes.Hero.Warrior;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.heroes.Hero.Npc;
import com.heroes.Hero.Vector2D;
import com.heroes.View.AtlasAnimation;

import java.util.ArrayList;

public class Robber extends Warrior {
    public Robber(ArrayList<Npc> friends, int x, int y, int count) {
        super(8, 3, new int[]{2, 4}, 10, 6);
        super.friends = friends;
        super.vector2D = new Vector2D(x, y);
        super.vector2 = new Vector2(x, y);
        super.count = count;
        atlas = new TextureAtlas("atlases/robber.atlas");
        super.animation = new AtlasAnimation(atlas, "IDLE", 9, Animation.PlayMode.LOOP);
    }
}
