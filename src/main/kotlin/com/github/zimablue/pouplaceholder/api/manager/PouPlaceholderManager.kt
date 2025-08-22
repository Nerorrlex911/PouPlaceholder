package com.github.zimablue.pouplaceholder.api.manager

import com.github.zimablue.devoutserver.util.map.KeyMap
import com.github.zimablue.pouplaceholder.api.placeholder.PouPlaceHolder
import net.minestom.server.entity.LivingEntity
import java.util.*

/**
 * PouPlaceholderManager
 *
 * 占位符管理器
 *
 * 主要负责:
 * - 维护占位符
 * - 进行文本中占位符的替换
 *
 * @constructor Create empty Pou placeholder manager
 */
abstract class PouPlaceholderManager : KeyMap<String, PouPlaceHolder>() {

    /**
     * 替换占位符
     *
     * @param uuid 实体的UUID
     * @param text 待处理的文本
     * @return 处理后的文本
     */
    abstract fun replace(uuid: UUID, text: String): String

    /**
     * 替换占位符
     *
     * @param entity
     * @param text
     * @return
     */
    abstract fun replace(entity: LivingEntity, text: String): String
}