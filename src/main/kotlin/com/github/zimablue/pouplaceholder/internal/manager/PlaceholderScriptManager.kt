package com.github.zimablue.pouplaceholder.internal.manager

import com.github.zimablue.devoutserver.plugin.lifecycle.Awake
import com.github.zimablue.devoutserver.plugin.lifecycle.AwakePriority
import com.github.zimablue.devoutserver.plugin.lifecycle.PluginLifeCycle
import com.github.zimablue.devoutserver.plugin.script.PluginScriptManager
import com.github.zimablue.pouplaceholder.PouPlaceholder
import java.io.File

object PlaceholderScriptManager {
    val pluginScriptManager = PluginScriptManager(
        plugin = PouPlaceholder,
        path = PouPlaceholder.dataDirectory.resolve("scripts").toFile()
    ) {
        eval("""
            const PlaceholderTool = Packages.com.github.zimablue.pouplaceholder.internal.script.PlaceholderTool;
        """.trimIndent())
    }

    @Awake(PluginLifeCycle.LOAD,AwakePriority.NORMAL)
    fun onLoad() {

        PouPlaceholder.savePackagedResource("scripts/testplaceholder.js")
    }
}