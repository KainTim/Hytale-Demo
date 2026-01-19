package com.tikaiz.systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.modules.entity.component.DisplayNameComponent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.tikaiz.components.EndermanTeleportComponent;
import com.tikaiz.singletons.LoggerSingleton;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.logging.Level;

public class AddDefaultComponentTickSystem extends EntityTickingSystem<EntityStore> {
    private final ComponentType<EntityStore, EndermanTeleportComponent> customComponentType;

    public AddDefaultComponentTickSystem(ComponentType<EntityStore, EndermanTeleportComponent> poisonComponentType) {
        this.customComponentType = poisonComponentType;
    }
    @Override
    public void tick(float dt,
                     int i,
                     @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk,
                     @NonNullDecl Store<EntityStore> store,
                     @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        EndermanTeleportComponent component = store.getComponent(ref, customComponentType);
        if (component != null) {
            return;
        }
        DisplayNameComponent displayNameComponent = store.getComponent(ref, DisplayNameComponent.getComponentType());
        if (displayNameComponent == null || displayNameComponent.getDisplayName() == null) {
//            LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("Added Component to ref!");
            return;
        }
        var displayName = displayNameComponent.getDisplayName().getAnsiMessage();
        if (!"Bunny".equals(displayName)){
            return;
        }
        commandBuffer.addComponent(ref, customComponentType, new EndermanTeleportComponent());
        LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("Added Component to ref: " + displayName);
    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(DisplayNameComponent.getComponentType());
    }
}
