package com.heroes.Hero.Warrior;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.heroes.Hero.Npc;
import com.heroes.Hero.Vector2D;
import com.heroes.View.AtlasAnimation;

import java.util.ArrayList;

public class Lancer extends Warrior {
    public Lancer(ArrayList<Npc> friends, int x, int y, int count) {
        super(4, 5, new int[]{1, 3}, 10, 4);
        super.friends = friends;
        super.vector = new Vector2D(x, y);
        super.vector2 = new Vector2(x, y);
        super.texture = new Texture("lancer.png");
        super.count = count;
        TextureAtlas atlas = new TextureAtlas("atlases/lancer.atlas");
        super.animation = new AtlasAnimation(atlas, "Knight_01__IDLE", 9, Animation.PlayMode.LOOP);
    }
}
