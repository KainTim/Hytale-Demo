package com.tikaiz.systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.tikaiz.components.EndermanTeleportComponent;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import static com.tikaiz.helpers.TeleportHelper.EndermanTeleport;

public class DamageEventSystem extends EntityEventSystem<EntityStore, Damage> {
    private final ComponentType<EntityStore, EndermanTeleportComponent> endermanTeleportComponentType;

    public DamageEventSystem(ComponentType<EntityStore, EndermanTeleportComponent> type) {
        super(Damage.class);
        this.endermanTeleportComponentType = type;
    }

    @Override
    public void handle(int i,
                       @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk,
                       @NonNullDecl Store<EntityStore> store,
                       @NonNullDecl CommandBuffer<EntityStore> commandBuffer,
                       @NonNullDecl Damage damage) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        var transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
        assert transformComponent != null;
        var endermanComponent = store.getComponent(ref, endermanTeleportComponentType);
        assert endermanComponent != null;

        endermanComponent.resetElapsedTime();
        if (Math.random() < 0.15) {
            EndermanTeleport(ref,transformComponent.getPosition(),commandBuffer);
        }
    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(TransformComponent.getComponentType(), endermanTeleportComponentType);
    }
}
