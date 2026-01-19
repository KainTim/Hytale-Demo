package com.tikaiz;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.modules.entity.EntityModule;
import com.hypixel.hytale.server.core.modules.entity.component.DisplayNameComponent;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.NotificationUtil;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import static com.tikaiz.TeleportHelper.randomTeleport;

public class DamageEventSystem extends EntityEventSystem<EntityStore, Damage> {
    private final ComponentType<EntityStore, EndermanTeleportComponent> endermanTeleportComponentType;

    protected DamageEventSystem(ComponentType<EntityStore, EndermanTeleportComponent> type) {
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
        TransformComponent transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
        assert transformComponent != null;
        var displayNameComponent = store.getComponent(ref, DisplayNameComponent.getComponentType());
        assert displayNameComponent != null;
        assert displayNameComponent.getDisplayName() != null;
        var name = displayNameComponent.getDisplayName().getAnsiMessage();

        NotificationUtil.sendNotificationToUniverse(name);
        if (Math.random() < 0.3) {
            var transform = transformComponent.getTransform();
            randomTeleport(commandBuffer, ref, transform.getPosition());
        }
    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(TransformComponent.getComponentType(), DisplayNameComponent.getComponentType(), endermanTeleportComponentType);
    }
}
