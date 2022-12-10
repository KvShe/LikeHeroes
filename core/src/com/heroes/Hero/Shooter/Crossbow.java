package com.heroes.Hero.Shooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.heroes.Hero.Npc;
import com.heroes.Hero.Vector2D;
import com.heroes.View.AtlasAnimation;

import java.util.ArrayList;

public class Crossbow extends Shooter {
    public Crossbow(ArrayList<Npc> friends, int x, int y, int count) {
        super(6, 3, new int[]{2, 3}, 10, 4, 16);
        super.friends = friends;
        super.vector = new Vector2D(x, y);
        super.vector2 = new Vector2(x, y);
        super.texture = new Texture("Crossbow.png");
        super.count = count;
        TextureAtlas atlas = new TextureAtlas("atlases/crossbow.atlas");
        super.animation = new AtlasAnimation(atlas, "Warrior_03__IDLE", 9, Animation.PlayMode.LOOP);
    }
}
