package com.tikaiz;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import javax.annotation.Nullable;

public class CustomComponent implements Component<EntityStore> {

    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return new CustomComponent();
    }
}