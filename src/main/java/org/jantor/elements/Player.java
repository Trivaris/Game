package org.jantor.elements;

import org.jantor.elements.movement.PlayerMovement;

public class Player extends Entity {

    public int score = 0;

    public Player() {
        super(null, 5);
        this.movement = new PlayerMovement(this);
    }

    public void collect(Class<? extends Collectable> cls) {
        if (!isTouching(cls)) return;
        score += 1;
        System.out.println("Collecting " + cls.getSimpleName());
        System.out.println("Score: " + score);
        super.removeTouching(cls);
    }


}
