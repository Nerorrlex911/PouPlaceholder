package com.github.zimablue.pouplaceholder.api.placeholder

import com.github.zimablue.devoutserver.util.map.component.Registrable
import com.github.zimablue.pouplaceholder.PouPlaceholder
import net.minestom.server.entity.LivingEntity


/**
 * PouPlaceholder
 *
 * @param identifier 标识符
 * @constructor
 * @property name 插件名
 * @property author 作者
 * @property version 版本
 */
abstract class PouPlaceHolder(
    identifier: String,
    val name: String,
    val author: String,
    val version: String,
) : Registrable<String> {


    final override val key: String = identifier

    /**
     * 解析变量
     *
     * @param params 参数
     * @param entity 实体
     * @param def 默认值
     * @return 返回值
     */
    abstract fun onPlaceHolderRequest(params: String, entity: LivingEntity, def: String): String?
    override fun register() {
        PouPlaceholder.placeholderManager.register(key, this)
    }

    override fun toString(): String {
        return "PouPlaceHolder(name='$name', author='$author', version='$version', identifier='$key')"
    }
}