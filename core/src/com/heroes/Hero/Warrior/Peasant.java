package com.heroes.Hero.Warrior;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.heroes.Hero.Npc;
import com.heroes.Hero.Status;
import com.heroes.Hero.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Peasant extends Warrior {
    public Peasant(ArrayList<Npc> friends, int x, int y) {
        super(4, 5, new int[]{1, 3}, 10, 4);
        super.friends = friends;
        super.vector = new Vector2D(x, y);
        super.vector2 = new Vector2(x, y);
        super.texture = new Texture("peasant.png");
    }

    @Override
    public void step(List<Npc> enemies) {
        if (getState() == Status.USED) setState(Status.STAND);
    }
}
