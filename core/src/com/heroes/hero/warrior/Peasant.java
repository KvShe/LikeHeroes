package com.heroes.hero.warrior;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.heroes.hero.Npc;
import com.heroes.hero.Status;
import com.heroes.hero.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Peasant extends Warrior {
    public Peasant(ArrayList<Npc> friends, int x, int y, int count) {
        super(4, 5, new int[]{1, 3}, 10, 4);
        super.friends = friends;
        super.vector2D = new Vector2D(x, y);
        super.count = count;
        atlas = new TextureAtlas("atlases/peasant.atlas");
        super.setAnimation();
    }

    @Override
    public void step(List<Npc> enemies) {
        if (getState() == Status.USED) setState(Status.STAND);
    }
}
