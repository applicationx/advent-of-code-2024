package com.appx.model.objects;

import com.appx.model.Direction;
import com.appx.model.GameObject;

public class Guard extends GameObject {
    private Direction direction;
    public Guard(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction d) {
        this.direction = d;
    }
}
