package com.github.zimablue.pouplaceholder

import com.github.zimablue.devoutserver.plugin.Plugin
import com.github.zimablue.pouplaceholder.api.manager.PouPlaceholderManager
import com.github.zimablue.pouplaceholder.internal.command.PlaceholderCommand
import com.github.zimablue.pouplaceholder.internal.manager.PouPlaceholderManagerImpl
import net.minestom.server.MinecraftServer

object PouPlaceholder : Plugin() {

    val placeholderManager: PouPlaceholderManager = PouPlaceholderManagerImpl

    override fun onEnable() {
        super.onEnable()
        logger.info("PouPlaceholder enabled")
        MinecraftServer.getCommandManager().register(PlaceholderCommand)
    }

}