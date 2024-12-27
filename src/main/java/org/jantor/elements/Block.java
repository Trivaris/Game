package org.jantor.elements;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.screens.Level;
import org.jantor.screens.Death;
import org.jantor.utils.CollisionManager;
import org.jantor.utils.GreenfootImage;
import org.jantor.utils.Vector2D;

public class Block extends Element {
    BlockType type;

    public enum BlockType {
        DIRT,
        GRASS,
        STONE,
        SAND,
        DEATHBLOCK,
        GOALBLOCK,
        BLUE;

        private String filePath() {
            return "images/blocks/" + name().toLowerCase() + ".png";
        }

        public GreenfootImage getImage() {
            return new GreenfootImage(filePath());
        }

        public static BlockType getByString(String name) {
            try {
                return BlockType.valueOf(name);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    public Block(BlockType type, Vector2D position) {
        super(type.getImage(), position);
        this.type = type;
    }

    public void act() {
        if (!CollisionManager.wouldCollide(0,-3, Player.class,this)) return;
        switch (type) {
            case DEATHBLOCK:
                Greenfoot.setWorld(new Death("You touched the Death Block!"));
                break;
            case GOALBLOCK:
                Greenfoot.setWorld(new Level(Constants.levelNamesReversed.get(Constants.currentLevel + 1)));
                break;
        }

    }

}
