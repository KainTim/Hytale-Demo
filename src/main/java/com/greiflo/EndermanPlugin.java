package com.greiflo;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

public class EndermanPlugin extends JavaPlugin {

    public EndermanPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        var type = this.getEntityStoreRegistry()
                .registerComponent(EndermanComponent.class, EndermanComponent::new);
        this.getEntityStoreRegistry().registerSystem(new AddComponentsEnderman(type));
        this.getEntityStoreRegistry().registerSystem(new EndermanTeleportTickingSystem(type));
        this.getEntityStoreRegistry().registerSystem(new EndermanDamageHandler(type));

    }
}