package com.tikaiz.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ExampleCommand extends AbstractPlayerCommand {

    public ExampleCommand() {
        super("tel", "Super test command!");
    }

    DefaultArg<List<Double>> arg = this.withListDefaultArg("Pos", "Position", ArgTypes.DOUBLE, new ArrayList<>(),"Tell the Player the Position");

    @Override
    protected void execute(@Nonnull CommandContext commandContext,
                           @Nonnull Store<EntityStore> store,
                           @Nonnull Ref<EntityStore> ref,
                           @Nonnull PlayerRef playerRef,
                           @Nonnull World world) {
        int size = arg.get(commandContext).size();
        playerRef.sendMessage(Message.raw("Arg size: " + size));
        if (!(size == 3 || size == 0)){

            playerRef.sendMessage(Message.raw("Either specify 0 or 3 Arguments"));
            return;
        }


        var transform = store.getComponent(ref, TransformComponent.getComponentType());
        if (transform == null) return;
        if (size == 0){
            var pos = transform.getPosition();
            playerRef.sendMessage(Message.raw("Player Position: " + pos));
            return;
        }
        var x = arg.get(commandContext).get(0);
        var y = arg.get(commandContext).get(1);
        var z = arg.get(commandContext).get(2);

        var newPos = new Vector3d(x,y,z);

        Teleport teleportForPlayer = Teleport.createForPlayer(newPos, new Vector3f());
        store.addComponent(ref,Teleport.getComponentType(),teleportForPlayer);
//        var results = new ArrayList<Object>();
//        store.fetch();

//        transform.setPosition(newPos);

        playerRef.sendMessage(Message.raw("Set Player Position to: " + newPos));


//        String message = messageArg.get(commandContext); // get the argument text by the player
//        playerRef.sendMessage(Message.raw("Player Name: " + playerRef.getUsername()));
//        var mass = store.getComponent(ref, PhysicsValues.getComponentType()).getMass();
//        playerRef.sendMessage(Message.raw("Player Mass: " + mass));
//        var archetype = store.getArchetype(ref);
//        playerRef.sendMessage(Message.raw("Archetype count: "+archetype.length()));
//        for (int i = 0; i < archetype.length(); i++) {
//            var type = archetype.get(i);
//            if (type!=null){
//                var typeClass =  type.getTypeClass();
//                playerRef.sendMessage(Message.raw("Type class: "+typeClass));
//                LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("Type class: "+typeClass);
//            }
//        }
    }

}