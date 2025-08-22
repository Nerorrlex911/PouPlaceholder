package com.github.zimablue.pouplaceholder.util

import net.minestom.server.MinecraftServer
import net.minestom.server.entity.LivingEntity
import net.minestom.server.utils.entity.EntityFinder
import java.util.UUID


fun UUID.livingEntity() : LivingEntity? {
    // Use EntityFinder to find entities with the given UUID
    val entities = EntityFinder().setConstantUuid(this).find(MinecraftServer.getCommandManager().consoleSender)
    return entities.firstOrNull { it is LivingEntity } as? LivingEntity
}