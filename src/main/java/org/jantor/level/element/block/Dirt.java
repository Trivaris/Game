package org.jantor.level.element.block;

import org.jantor.image.BlockImage;

public class Dirt extends Block {
    public static final BlockImage image = new BlockImage("dirt.png");

    public Dirt() {
        super(image);
    }
}