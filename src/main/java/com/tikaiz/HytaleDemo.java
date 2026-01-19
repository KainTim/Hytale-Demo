package com.tikaiz;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.tikaiz.commands.ExampleCommand;
import com.tikaiz.components.EndermanTeleportComponent;
import com.tikaiz.singletons.LoggerSingleton;
import com.tikaiz.systems.AddDefaultComponentTickSystem;
import com.tikaiz.systems.CustomComponentTickSystem;
import com.tikaiz.systems.DamageEventSystem;

import javax.annotation.Nonnull;

public class HytaleDemo extends JavaPlugin {

    public HytaleDemo(@Nonnull JavaPluginInit init) {
        super(init);
        LoggerSingleton.getInstance().setHytaleLogger(this.getLogger());
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new ExampleCommand());
        var type = this.getEntityStoreRegistry()
                .registerComponent(EndermanTeleportComponent.class, EndermanTeleportComponent::new);
        this.getEntityStoreRegistry().registerSystem(new AddDefaultComponentTickSystem(type));
        this.getEntityStoreRegistry().registerSystem(new CustomComponentTickSystem(type));
        this.getEntityStoreRegistry().registerSystem(new DamageEventSystem(type));
//        var list = new ArrayList<EntityStore>();
//        this.getEventRegistry().registerGlobal(AllWorldsLoadedEvent.class, (x)->{
//            LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("World has loaded!");
//        });
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