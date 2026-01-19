package com.tikaiz.helpers;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.world.ParticleUtil;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public abstract class TeleportHelper {

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
}
