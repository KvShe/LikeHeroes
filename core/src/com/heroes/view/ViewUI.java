package com.heroes.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.heroes.LikeHeroes;

public class ViewUI extends InputAdapter {

    public void button() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            LikeHeroes.priorityMove();
        }
    }
}
