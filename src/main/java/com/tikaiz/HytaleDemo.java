package com.tikaiz;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.SystemType;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.CancellableEcsEvent;
import com.hypixel.hytale.component.system.EcsEvent;
import com.hypixel.hytale.server.core.event.events.BootEvent;
import com.hypixel.hytale.server.core.modules.entity.component.DisplayNameComponent;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.events.AllWorldsLoadedEvent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.NotificationUtil;
import com.hypixel.hytale.server.npc.AllNPCsLoadedEvent;
import com.hypixel.hytale.server.spawning.LoadedNPCEvent;
import com.hypixel.hytale.server.spawning.assets.spawns.config.NPCSpawn;
import com.tikaiz.commands.ExampleCommand;
import jdk.jfr.EventType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.logging.Level;

public class HytaleDemo extends JavaPlugin {

    public HytaleDemo(@Nonnull JavaPluginInit init) {
        super(init);
        LoggerSingleton.getInstance().setHytaleLogger(this.getLogger());
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new ExampleCommand());
        this.getEntityStoreRegistry().registerSystem(new DamageEventSystem());
        var type = this.getEntityStoreRegistry()
                .registerComponent(CustomComponent.class, CustomComponent::new);
        this.getEntityStoreRegistry().registerSystem(new AddDefaultComponentTickSystem(type));
        this.getEntityStoreRegistry().registerSystem(new CustomComponentTickSystem(type));
//        var list = new ArrayList<EntityStore>();
        this.getEventRegistry().registerGlobal(AllWorldsLoadedEvent.class, (x)->{
            LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("World has loaded!");
        });
//        Universe.get().getDefaultWorld().getEntityStore().getStore().fetch(new SystemType<>(), Query.any(),list);
//        EntityStore.REGISTRY.assertInStoreThread();
//        this.getEventRegistry().registerGlobal(LoadedNPCEvent.class, (x)->{
//            String id = x.getBuilderInfo().;
//            Arrays.stream(x.getNPCs()).forEach(x->x.getFlockDefinition().);
//            x.getAllNPCs().forEach((integer, builderInfo) -> {
//                builderInfo.
//            });
//            NotificationUtil.sendNotificationToUniverse(x.getBuilderInfo().getKeyName());
//        });
//        this.getEventRegistry().register(Damage.class,(x)->{
//            Damage.Source source = x.getSource();
//        });
    }
}