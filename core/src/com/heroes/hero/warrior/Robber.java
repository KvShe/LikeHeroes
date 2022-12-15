package com.heroes.hero.warrior;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.heroes.hero.Npc;
import com.heroes.hero.Vector2D;

import java.util.ArrayList;

public class Robber extends Warrior {
    public Robber(ArrayList<Npc> friends, int x, int y, int count) {
        super(8, 3, new int[]{2, 4}, 10, 6);
        super.friends = friends;
        super.vector2D = new Vector2D(x, y);
        super.count = count;
        atlas = new TextureAtlas("atlases/robber.atlas");
        super.setAnimation();
    }
}
