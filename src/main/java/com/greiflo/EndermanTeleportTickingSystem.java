package com.greiflo;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.world.ParticleUtil;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class EndermanTeleportTickingSystem extends EntityTickingSystem<EntityStore> {
    private final ComponentType<EntityStore, EndermanComponent> endermanComponentType;

    public EndermanTeleportTickingSystem(ComponentType<EntityStore, EndermanComponent> endermanComponentType) {
        this.endermanComponentType = endermanComponentType;
    }

    @Override
    public void tick(float dt, int i, @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        EndermanComponent endermanTeleportComponent = store.getComponent(ref, endermanComponentType);
        assert endermanTeleportComponent != null;
        var transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
        assert transformComponent != null;


        endermanTeleportComponent.addElapsedTime(dt);
        if (endermanTeleportComponent.getElapsedTime() >= endermanTeleportComponent.getTickInterval()) {
            endermanTeleportComponent.resetElapsedTime();
            endermanTeleportComponent.randomizeTickInterval();
            EndermanTeleport(ref, transformComponent.getPosition(), commandBuffer);
        }
    }

    public static Vector3d randomTeleport(Vector3d position) {
        Vector3d addPos = new Vector3d((Math.random() * 20) - 10, 3, (Math.random() * 20) - 10);

        return position.add(addPos);
    }
    public static void EndermanTeleport(Ref<EntityStore> ref, Vector3d position, CommandBuffer<EntityStore> commandBuffer) {
        ParticleUtil.spawnParticleEffect("Effect_Death",position,commandBuffer);
        Vector3d newPos = randomTeleport(position);
        ParticleUtil.spawnParticleEffect("Effect_Death",position,commandBuffer);

        Teleport teleportForPlayer = Teleport.createForPlayer(newPos, new Vector3f());
        commandBuffer.addComponent(ref, Teleport.getComponentType(), teleportForPlayer);
    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(endermanComponentType, TransformComponent.getComponentType());
    }
}
