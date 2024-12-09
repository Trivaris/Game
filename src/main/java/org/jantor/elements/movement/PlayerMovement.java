package org.jantor.elements.movement;

import greenfoot.Greenfoot;
import org.jantor.constants.Constants;
import org.jantor.elements.Collectable;
import org.jantor.elements.Player;
import org.jantor.utils.Vector2D;

import java.util.Arrays;

public class PlayerMovement extends EntityMovement {

    public PlayerMovement(Player player) {
        super(player);
    }

    @Override
    public void act() {
        latestDirection.add(currentDirection).normalize();
        lastDirection.copy(currentDirection);
        currentDirection.toZero();

        ((Player) entity).collect(Collectable.class);

        Arrays.stream(Movement.Direction.values()).forEach(direction -> {
            if (Greenfoot.isKeyDown(direction.name().toLowerCase())) currentDirection.add(direction.vector).normalize();
        });

        if (Greenfoot.isKeyDown(Movement.Direction.UP.name().toLowerCase()) && onGround) {
            verticalMomentum = -jumpStrength;
            onGround = false;
        }

        boolean isNearLeftEdge = entity.getX() < Constants.screenSize.x / 4;
        boolean isNearRightEdge = entity.getX() > Constants.screenSize.x * 0.75;

        int screenOffsetX =
                isNearLeftEdge && currentDirection.equals(Direction.LEFT.vector)
                ? -1 * entity.speed

                : (isNearRightEdge && currentDirection.equals(Direction.RIGHT.vector)
                ? entity.speed

                : 0);
        Constants.originOffset.add(new Vector2D(-screenOffsetX, 0));
        Constants.renderer.updateBlocks();

        super.act();
    }

}