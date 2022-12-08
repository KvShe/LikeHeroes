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


//            priorityMove();


//    private boolean spacePressed;
//    @Override
//    public boolean keyUp(int keycode) {
//        if (keycode == Input.Keys.SPACE) spacePressed = true;
//        return false;
//    }
//
//    @Override
//    public boolean keyDown(int keycode) {
//        if (keycode == Input.Keys.SPACE) spacePressed = false;
//        return super.keyDown(keycode);
//    }
//    public void nextMove() {
//        if (spacePressed) Heroes.priorityMove();
//    }
}
