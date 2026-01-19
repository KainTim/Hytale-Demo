package com.tikaiz.helpers;

import com.hypixel.hytale.math.vector.Vector3d;

public abstract class TeleportHelper {

    public static Vector3d randomTeleport(Vector3d position) {
        Vector3d addPos = new Vector3d((Math.random() * 20) - 10, 3, (Math.random() * 20) - 10);

        return position.add(addPos);
    }
}
