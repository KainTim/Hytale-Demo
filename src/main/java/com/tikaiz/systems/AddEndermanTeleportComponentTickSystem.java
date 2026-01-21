package com.tikaiz.systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.modules.entity.component.DisplayNameComponent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.tikaiz.components.EndermanTeleportComponent;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.logging.Level;

public class AddEndermanTeleportComponentTickSystem extends EntityTickingSystem<EntityStore> {
    private final ComponentType<EntityStore, EndermanTeleportComponent> endermanComponentType;

    public AddEndermanTeleportComponentTickSystem(ComponentType<EntityStore, EndermanTeleportComponent> endermanComponentType) {
        this.endermanComponentType = endermanComponentType;
    }
    @Override
    public void tick(float dt,
                     int i,
                     @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk,
                     @NonNullDecl Store<EntityStore> store,
                     @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        DisplayNameComponent displayNameComponent = store.getComponent(ref, DisplayNameComponent.getComponentType());
        assert displayNameComponent != null;
        assert displayNameComponent.getDisplayName() != null;
        var displayName = displayNameComponent.getDisplayName().getAnsiMessage();
        if (!"Bunny".equals(displayName)){
            return;
        }
        commandBuffer.addComponent(ref, endermanComponentType, new EndermanTeleportComponent());
        HytaleLogger.getLogger().atInfo().log("Added Component to ref: " + displayName);
    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(DisplayNameComponent.getComponentType(),Query.not(endermanComponentType));
    }
}
