package com.heroes.Hero.Shooter;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.heroes.Hero.Npc;
import com.heroes.Hero.Vector2D;

import java.util.ArrayList;

public class Sniper extends Shooter {
    public Sniper(ArrayList<Npc> friends, int x, int y) {
        super(12, 10,  new int[]{8, 10}, 15, 9, 32);
        super.friends = friends;
        super.vector = new Vector2D(x, y);
        super.vector2 = new Vector2(x, y);
        super.texture = new Texture("sniper.png");
    }
}
