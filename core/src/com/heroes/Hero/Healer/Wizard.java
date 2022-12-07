package com.heroes.Hero.Healer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.heroes.Hero.Npc;
import com.heroes.Hero.Vector2D;

import java.util.ArrayList;

public class Wizard extends Healer {
    ArrayList<Npc> enemy;
    public Wizard(ArrayList<Npc> friends, int x, int y) {
        super(17, 12, new int[]{-5, -5}, 30, 9);
        super.friends = friends;
        super.vector2 = new Vector2(x, y);
        super.vector = new Vector2D(x, y);
        super.texture = new Texture("wizard.png");
    }
}
