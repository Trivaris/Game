package org.jantor.utils;

import greenfoot.Color;
import org.jantor.screens.Screen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class GreenfootImage extends greenfoot.GreenfootImage {
    public GreenfootImage(String filename) {
        super(filename);
    }

    public GreenfootImage(int width, int height) {
        super(width, height);
    }
    public GreenfootImage(Vector2D vec) {
        this(vec.x, vec.y);
    }


    public void drawRoundRect(int borderRadius, int width, int height, Color color) {

        this.setColor(Screen.backgroundColor);
        this.fill();

        this.setColor(color);

        this.fillOval(0, 0, borderRadius * 2, borderRadius * 2);
        this.fillOval(width - borderRadius * 2, 0, borderRadius * 2, borderRadius * 2);
        this.fillOval(0, height - borderRadius * 2, borderRadius * 2, borderRadius * 2);
        this.fillOval(width - borderRadius * 2, height - borderRadius * 2, borderRadius * 2, borderRadius * 2);

        this.fillRect(borderRadius, 0, width - 2 * borderRadius, height);
        this.fillRect(0, borderRadius, width, height - 2 * borderRadius);
    }
    public void drawRoundRect(int borderRadius, Vector2D vec, Color color) {
        drawRoundRect(borderRadius, vec.x, vec.y, color);
    }

    public void drawString(String string, Vector2D vec) {
        super.drawString(string, vec.x, vec.y);
    }

    public GreenfootImage mirror() {
        BufferedImage bufferedImage = this.getAwtImage();
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bufferedImage.getWidth(), 0);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage flippedImage = op.filter(bufferedImage, null);

        GreenfootImage flippedGreenfootImage = new GreenfootImage(flippedImage.getWidth(), flippedImage.getHeight());
        Graphics2D g = flippedGreenfootImage.getAwtImage().createGraphics();
        g.drawImage(flippedImage, 0, 0, null);
        g.dispose();

        return flippedGreenfootImage;
    }

}
