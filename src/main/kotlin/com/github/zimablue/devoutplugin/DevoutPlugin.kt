package com.github.zimablue.devoutplugin

import com.github.zimablue.devoutserver.plugin.Plugin

class DevoutPlugin : Plugin() {

    override fun onEnable() {
        super.onEnable()
        logger.info("DevoutPlugin enabled")
    }

}