package com.github.zimablue.pouplaceholder.util

import net.minestom.server.MinecraftServer
import net.minestom.server.entity.LivingEntity
import net.minestom.server.utils.entity.EntityFinder
import java.util.UUID


fun UUID.livingEntity() : LivingEntity? {
    MinecraftServer.getInstanceManager().instances.forEach { instance ->
        val entity = instance.getEntityByUuid(this)?:return@forEach
        if (entity is LivingEntity) {
            return entity
        }
    }
    return null
}