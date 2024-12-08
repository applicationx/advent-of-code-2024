package com.appx.model;

import com.appx.model.objects.Guard;
import com.appx.model.objects.Obstacle;

public class Tile {
    // Each tile can contain zero or one object for now.
    // If empty, store null.
    // If obstacle or guard, store the object.
    private GameObject object;

    public Tile(GameObject object) {
        this.object = object;
    }

    public GameObject getObject() {
        return object;
    }

    public void setObject(GameObject obj) {
        this.object = obj;
    }

    public boolean isBlocked() {
        // If an obstacle is here, it's blocked
        return object instanceof Obstacle;
    }

    public boolean hasGuard() {
        return object instanceof Guard;
    }
}
