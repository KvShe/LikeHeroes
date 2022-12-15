package com.heroes.hero.healer;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.heroes.hero.Npc;
import com.heroes.hero.Vector2D;
import com.heroes.view.AtlasAnimation;

import java.util.ArrayList;

public class Wizard extends Healer {
    public Wizard(ArrayList<Npc> friends, int x, int y, int count) {
        super(17, 12, new int[]{-5, -5}, 30, 9);
        super.friends = friends;
        super.vector2D = new Vector2D(x, y);
        super.count = count;
        atlas = new TextureAtlas("atlases/wizard.atlas");
        super.setAnimation();
    }
}
