package com.tikaiz.systems;

//public class EntitySpawnEventSystem extends EntityEventSystem<EntityStore, LoadedNPCEvent> {
//    protected EntitySpawnEventSystem() {
//        super(LoadedNPCEvent.class);
//    }
//
////    @Override
////    public void handle(int i,
////                       @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk,
////                       @NonNullDecl Store<EntityStore> store,
////                       @NonNullDecl CommandBuffer<EntityStore> commandBuffer,
////                       @NonNullDecl Damage damage) {
////        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
////        TransformComponent component = store.getComponent(ref, TransformComponent.getComponentType());
////        if (component == null) {
////            LoggerSingleton.getInstance().getHytaleLogger().at(Level.SEVERE).log("Transform was Null");
////            return;
////        }
////        var displayNameComponent = store.getComponent(ref, DisplayNameComponent.getComponentType());
////        if (displayNameComponent == null) {
////            LoggerSingleton.getInstance().getHytaleLogger().at(Level.SEVERE).log("Display name was Null");
////            return;
////        }
////
////        NotificationUtil.sendNotificationToUniverse(displayNameComponent.getDisplayName());
////        LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("Display name: " + displayNameComponent.getDisplayName());
////
////        var uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
////        if (uuidComponent == null) {
////            LoggerSingleton.getInstance().getHytaleLogger().at(Level.SEVERE).log("UUID was Null");
////            return;
////        }
////        LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("UUID: " + uuidComponent.getUuid());
////        var transform = component.getTransform();
////        LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log("Transform: "+transform);
////
////        Vector3d addPos = new Vector3d((Math.random()*20)-10, 3, (Math.random()*20)-10);
////
////        var newPos = transform.getPosition().add(addPos);
////
////        Teleport teleportForPlayer = Teleport.createForPlayer(newPos, new Vector3f());
////        new Thread(()->{
////            store.addComponent(ref,Teleport.getComponentType(),teleportForPlayer);
////        }).start();
////    }
//
//    @NullableDecl
//    @Override
//    public Query<EntityStore> getQuery() {
//        return Query.any();
//    }
//
//
//    @Override
//    public void handle(int i,
//                       @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk,
//                       @NonNullDecl Store<EntityStore> store,
//                       @NonNullDecl CommandBuffer<EntityStore> commandBuffer,
//                       @NonNullDecl LoadedNPCEvent loadedNPCEvent) {
//        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
//        var transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
//        NotificationUtil.sendNotificationToUniverse(transformComponent != null ? transformComponent.getTransform().toString() : "null");
//        LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log(transformComponent != null ? transformComponent.getTransform().toString() : "null");
//        var displayNameComponent = store.getComponent(ref, DisplayNameComponent.getComponentType());
//        NotificationUtil.sendNotificationToUniverse(displayNameComponent != null ? displayNameComponent.getDisplayName() != null ? displayNameComponent.getDisplayName().getAnsiMessage() : "null" : "null");
//        LoggerSingleton.getInstance().getHytaleLogger().at(Level.INFO).log(displayNameComponent != null ? displayNameComponent.getDisplayName() != null ? displayNameComponent.getDisplayName().getAnsiMessage() : "null" : "null");
//
////        loadedNPCEvent.getBuilderInfo().getKeyName()
//
//    }
//}
