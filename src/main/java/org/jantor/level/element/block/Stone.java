package org.jantor.level.element.block;

import org.jantor.image.BlockImage;

public class Stone extends Block {
    public static final BlockImage image = new BlockImage("stone.png");

    public Stone() {
        super(image);
    }
}