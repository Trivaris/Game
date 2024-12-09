package org.jantor.elements;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.jantor.constants.Constants;
import org.jantor.utils.Vector2D;

public class Element extends Actor {
    public final Vector2D position;

    public Element(GreenfootImage image, Vector2D position) {
        setImage(image);
        this.position = position;
    }

    public void addTo(World world) {
        world.addObject(this, getCoords().x, getCoords().y);
    }

    public void updateLocation(boolean isFloor) {
        if (!isInMainWorld() && isWithinScreenBounds()) addTo(Constants.world);
        if ( isInMainWorld() && isAtEdge() && !isFloor) removeFromWorld();
        if ( getWorld() != null) setLocation(getCoords().x, getCoords().y);
    }
    public void updateLocation() { updateLocation(false);}
    public boolean isInMainWorld() { return getWorld() == Constants.world; }

    public Vector2D getCoords() {
        Vector2D coords = Vector2D.copyFrom(position);
        coords.multiply(Constants.elementSize);

        coords.multiply(new Vector2D(1, -1)).add(Constants.originOffset);
        coords.add(Vector2D.copyFrom(Constants.elementSize).multiply(0.5));

        return coords;
    }

    public boolean isWithinScreenBounds() {
        return !isLeftOfScreen()
            && !isRightOfScreen()
            && !isAboveScreen()
            && !isBelowScreen();
    }
    public boolean isLeftOfScreen()  { return getCoords().x < -Constants.elementSize.x; }
    public boolean isRightOfScreen() { return getCoords().x > Constants.screenSize.x + Constants.elementSize.x; }
    public boolean isAboveScreen()   { return getCoords().y < -Constants.elementSize.y; }
    public boolean isBelowScreen()   { return getCoords().y > Constants.screenSize.y + Constants.elementSize.y; }

    public void removeFromWorld() { if(getWorld() != null) getWorld().removeObject(this); }

    @Override
    public boolean isTouching(Class cls) {
        return super.isTouching(cls);
    }
}
