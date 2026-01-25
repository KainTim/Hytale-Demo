package com.greiflo;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;

public class EndermanComponent implements Component<EntityStore> {

    private float tickInterval;
    private float elapsedTime;

    public EndermanComponent(float tickInterval, float elapsedTime) {
        this.tickInterval = tickInterval;
        this.elapsedTime = elapsedTime;
    }

    public EndermanComponent() {
        this(10f, 0f);
        randomizeTickInterval();
    }

    public EndermanComponent(EndermanComponent other) {
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
        return new EndermanComponent(this);
    }

    public void randomizeTickInterval() {
        tickInterval = (float) ((Math.random()*25)+5);
    }
}