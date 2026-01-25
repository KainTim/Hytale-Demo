package com.greiflo;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.modules.entity.component.DisplayNameComponent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class AddComponentsEnderman extends EntityTickingSystem<EntityStore> {


    private final ComponentType<EntityStore, EndermanComponent> endermanComponentType;

    public AddComponentsEnderman(ComponentType<EntityStore, EndermanComponent> endermanComponentType) {
        this.endermanComponentType = endermanComponentType;
    }

    @Override
    public void tick(float v, int i, @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        DisplayNameComponent displayNameComponent = store.getComponent(ref, DisplayNameComponent.getComponentType());
        assert displayNameComponent != null;
        assert displayNameComponent.getDisplayName() != null;
        var displayName = displayNameComponent.getDisplayName().getAnsiMessage();
        if (!"Enderman".equals(displayName)){
            return;
        }
        commandBuffer.addComponent(ref, endermanComponentType, new EndermanComponent());
    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(DisplayNameComponent.getComponentType(), Query.not(endermanComponentType));
    }
}
