package com.github.zimablue.pouplaceholder.internal.manager

import com.github.zimablue.devoutserver.util.colored
import com.github.zimablue.pouplaceholder.PouPlaceholder
import com.github.zimablue.pouplaceholder.api.manager.PouPlaceholderManager
import com.github.zimablue.pouplaceholder.api.placeholder.PouPlaceHolder
import net.minestom.server.entity.LivingEntity
import java.util.*
import java.util.regex.Pattern
import com.github.zimablue.pouplaceholder.util.livingEntity

internal object PouPlaceholderManagerImpl : PouPlaceholderManager() {

    private val pattern = Pattern.compile(
        String.format(
            "\\%s((?<identifier>[a-zA-Z0-9]+)_)(?<parameters>[^%s%s]+)\\%s",
            "%",
            "%",
            "%",
            "%"
        )
    )

    override fun replace(entity: LivingEntity, text: String, def: String): String {
        var new = text
        val matcher = pattern.matcher(new)
        if (matcher.find()) {
            val builder = StringBuffer()
            do {
                val identifier = matcher.group("identifier")
                val parameters = matcher.group("parameters")
                val rpgPlaceHolder = this[identifier]
                rpgPlaceHolder?.also {
                    val requested: String =
                        rpgPlaceHolder.onPlaceHolderRequest(parameters, entity, "0").toString()
                    matcher.appendReplacement(builder, requested)
                }
            } while (matcher.find())
            new = matcher.appendTail(builder).toString()
        }
        return new
    }

    override fun replace(uuid: UUID, text: String, def: String): String {
        return replace(uuid.livingEntity()?:return text, text, def)
    }

    override fun put(key: String, value: PouPlaceHolder): PouPlaceHolder? {
        PouPlaceholder.logger.info(
            "&d[&9PouPlaceholder&d] &ePouPlaceholder &6{} &ehas successfully registered! From: &d{} &9{} &eBy: &a{}".colored(),
            key,
            value.name,
            value.version,
            value.author
        )
        return super.put(key, value)
    }


}