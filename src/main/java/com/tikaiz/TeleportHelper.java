package com.tikaiz;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

abstract class TeleportHelper {

    static void randomTeleport(@NonNullDecl CommandBuffer<EntityStore> commandBuffer, Ref<EntityStore> ref, Vector3d position) {
        Vector3d addPos = new Vector3d((Math.random() * 20) - 10, 3, (Math.random() * 20) - 10);

        var newPos = position.add(addPos);

        Teleport teleportForPlayer = Teleport.createForPlayer(newPos, new Vector3f());
        commandBuffer.addComponent(ref, Teleport.getComponentType(), teleportForPlayer);
    }
}
