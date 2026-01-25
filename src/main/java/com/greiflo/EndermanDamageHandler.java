package com.greiflo;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.modules.entity.component.DisplayNameComponent;
import com.hypixel.hytale.server.core.modules.entity.component.PositionDataComponent;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.item.ItemComponent;
import com.hypixel.hytale.server.core.modules.entity.item.PickupItemComponent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import static com.greiflo.EndermanTeleportTickingSystem.EndermanTeleport;

public class EndermanDamageHandler extends EntityEventSystem<EntityStore, Damage> {


    protected EndermanDamageHandler(ComponentType<EntityStore, EndermanComponent> endermanComponentType) {
        super(Damage.class);
        this.endermanComponentType = endermanComponentType;
    }

    private final ComponentType<EntityStore, EndermanComponent> endermanComponentType;

    @Override
    public void handle(int i, @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer, @NonNullDecl Damage damage) {

        var ref = archetypeChunk.getReferenceTo(i);
        TransformComponent transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
        var itemstack = new ItemStack("Rock_Stone", 64, null);
        Holder<EntityStore> drops = ItemComponent.generateItemDrop(store, itemstack, transformComponent.getPosition().clone(), Vector3f.ZERO,0,0,0);

        commandBuffer.addEntity(drops, AddReason.SPAWN);

        EndermanComponent endermanTeleportComponent = store.getComponent(ref, endermanComponentType);

        var entityName = store.getComponent(ref, DisplayNameComponent.getComponentType());

        var teleportChance = Math.random()*100;

        if (endermanTeleportComponent != null) {
            endermanTeleportComponent.resetElapsedTime();
        }
        var chance = (int)teleportChance;
        if(chance > 15) return;

        //if(entityName != null && entityName.getDisplayName() != null) NotificationUtil.sendNotificationToUniverse(entityName.getDisplayName());

        if(entityName == null || entityName.getDisplayName() == null) return;
        var raw = entityName.getDisplayName().getAnsiMessage();
        //NotificationUtil.sendNotificationToUniverse(raw);

        if(!"Enderman".equals(raw)) return;

        EndermanTeleport(ref, transformComponent.getPosition(), commandBuffer);

    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.any();
    }
}
