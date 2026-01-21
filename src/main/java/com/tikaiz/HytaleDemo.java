package com.tikaiz;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.tikaiz.commands.ExampleCommand;
import com.tikaiz.components.EndermanTeleportComponent;
import com.tikaiz.systems.AddEndermanTeleportComponentTickSystem;
import com.tikaiz.systems.EndermanComponentTickSystem;
import com.tikaiz.systems.DamageEventSystem;

import javax.annotation.Nonnull;

public class HytaleDemo extends JavaPlugin {

    public HytaleDemo(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new ExampleCommand());
        var type = this.getEntityStoreRegistry()
                .registerComponent(EndermanTeleportComponent.class, EndermanTeleportComponent::new);
        this.getEntityStoreRegistry().registerSystem(new AddEndermanTeleportComponentTickSystem(type));
        this.getEntityStoreRegistry().registerSystem(new EndermanComponentTickSystem(type));
        this.getEntityStoreRegistry().registerSystem(new DamageEventSystem(type));
    }
}