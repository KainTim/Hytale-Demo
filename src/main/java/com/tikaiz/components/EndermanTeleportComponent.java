package com.tikaiz.components;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;

public class EndermanTeleportComponent implements Component<EntityStore> {

    private final float tickInterval;
    private float elapsedTime;

    public EndermanTeleportComponent(float tickInterval, float elapsedTime) {
        this.tickInterval = tickInterval;
        this.elapsedTime = elapsedTime;
    }

    public EndermanTeleportComponent() {
        this(10f, 0f);
    }

    public EndermanTeleportComponent(EndermanTeleportComponent other) {
        this.tickInterval = other.tickInterval;
        this.elapsedTime = other.elapsedTime;
    }

    public float getTickInterval() {
        return tickInterval;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void addElapsedTime(float dt) {
        this.elapsedTime += dt;
    }

    public void resetElapsedTime() {
        this.elapsedTime = 0f;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return new EndermanTeleportComponent(this);
    }
}