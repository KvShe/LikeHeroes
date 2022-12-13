package com.heroes.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.heroes.Heroes;

public class ViewUI extends InputAdapter {

    public void button() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Heroes.priorityMove();
        }
    }
}
