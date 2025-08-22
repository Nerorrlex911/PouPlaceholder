package com.github.zimablue.pouplaceholder.internal.script

import com.github.zimablue.pouplaceholder.api.placeholder.PouPlaceHolder
import com.github.zimablue.pouplaceholder.internal.manager.PlaceholderScriptManager
import net.minestom.server.entity.LivingEntity

object PlaceholderTool {
    @JvmStatic
    fun registerPlaceholder(identifier: String, name: String, author: String, version: String, path: String) {
        object : PouPlaceHolder(identifier,name,author,version) {
            override fun onPlaceHolderRequest(params: String, entity: LivingEntity, def: String): String {
                val names = path.split("::")
                val fileName = names[0]
                val funcName = names[1]
                return PlaceholderScriptManager.pluginScriptManager.run(fileName, funcName,null, arrayOf(params,entity,def)).toString()
            }
        }.register()

    }
}