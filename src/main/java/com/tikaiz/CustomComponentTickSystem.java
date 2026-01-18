package com.tikaiz;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.modules.entity.component.DisplayNameComponent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.logging.Level;

public class CustomComponentTickSystem extends EntityTickingSystem<EntityStore> {
    private final ComponentType<EntityStore, CustomComponent> customComponentType;

    public CustomComponentTickSystem(ComponentType<EntityStore, CustomComponent> poisonComponentType) {
        this.customComponentType = poisonComponentType;
    }
    @Override
    public void tick(float dt,
                     int i,
                     @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk,
                     @NonNullDecl Store<EntityStore> store,
                     @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        CustomComponent component = store.getComponent(ref, customComponentType);
        if (component == null) {
            LoggerSingleton.getInstance().getHytaleLogger().at(Level.SEVERE).log("This should never happen! ");
            return;
        }
        DisplayNameComponent displayNameComponent = store.getComponent(ref, DisplayNameComponent.getComponentType());
        if (displayNameComponent == null || displayNameComponent.getDisplayName() == null) {
            LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("new Event Ticking system called on : " + ref);
            return;
        }
        var displayName = displayNameComponent.getDisplayName().getAnsiMessage();
        LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("new Event Ticking system called on : " + displayName);

    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(customComponentType);
    }
}
