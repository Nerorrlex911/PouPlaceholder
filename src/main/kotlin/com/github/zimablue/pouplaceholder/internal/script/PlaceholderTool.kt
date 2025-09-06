package com.github.zimablue.pouplaceholder.internal.script

import com.github.zimablue.pouplaceholder.PouPlaceholder.placeholderManager
import com.github.zimablue.pouplaceholder.api.placeholder.PouPlaceHolder
import com.github.zimablue.pouplaceholder.internal.manager.PlaceholderScriptManager
import net.minestom.server.entity.LivingEntity
import java.util.*

object PlaceholderTool {
    @JvmStatic
    fun registerPlaceholder(identifier: String, name: String, author: String, version: String, path: String) {
        object : PouPlaceHolder(identifier,name,author,version) {
            override fun onPlaceHolderRequest(params: String, entity: LivingEntity, def: String): String {
                return PlaceholderScriptManager.pluginScriptManager.run(path,null, params,entity,def).toString()
            }
        }.register()

    }
    @JvmStatic
    fun replacePlaceholder(text: String, entity: LivingEntity, def: String): String {
        return placeholderManager.replace(entity, text, def)
    }
    @JvmStatic
    fun replacePlaceholder(text: String, entity: UUID, def: String): String {
        return placeholderManager.replace(entity, text, def)
    }
}