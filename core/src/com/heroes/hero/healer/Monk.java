package com.heroes.hero.healer;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.heroes.hero.Npc;
import com.heroes.hero.Vector2D;

import java.util.ArrayList;

public class Monk extends Healer {
    public Monk(ArrayList<Npc> friends, int x, int y, int count) {
        super(12, 7, new int[]{-4, -4}, 30, 5);
        super.friends = friends;
        super.vector2D = new Vector2D(x, y);
        super.count = count;
        atlas = new TextureAtlas("atlases/monk.atlas");
        super.setAnimation();
    }
}
