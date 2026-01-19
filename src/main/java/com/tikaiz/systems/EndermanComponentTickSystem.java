package com.tikaiz.systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.tikaiz.components.EndermanTeleportComponent;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import static com.tikaiz.helpers.TeleportHelper.EndermanTeleport;

public class EndermanComponentTickSystem extends EntityTickingSystem<EntityStore> {
    private final ComponentType<EntityStore, EndermanTeleportComponent> endermanComponentType;

    public EndermanComponentTickSystem(ComponentType<EntityStore, EndermanTeleportComponent> poisonComponentType) {
        this.endermanComponentType = poisonComponentType;
    }

    @Override
    public void tick(float dt,
                     int i,
                     @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk,
                     @NonNullDecl Store<EntityStore> store,
                     @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        EndermanTeleportComponent endermanTeleportComponent = store.getComponent(ref, endermanComponentType);
        assert endermanTeleportComponent != null;
        var transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
        assert transformComponent != null;


        endermanTeleportComponent.addElapsedTime(dt);
        if (endermanTeleportComponent.getElapsedTime() >= endermanTeleportComponent.getTickInterval()) {
            endermanTeleportComponent.resetElapsedTime();
            endermanTeleportComponent.randomizeTickInterval();
            EndermanTeleport(ref,transformComponent.getPosition(),commandBuffer);
        }
    }


    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(endermanComponentType, TransformComponent.getComponentType());
    }
}
