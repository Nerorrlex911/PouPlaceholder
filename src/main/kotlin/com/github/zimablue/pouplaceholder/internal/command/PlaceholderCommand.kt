package com.github.zimablue.pouplaceholder.internal.command

import com.github.zimablue.pouplaceholder.PouPlaceholder
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.arguments.minecraft.ArgumentUUID
import net.minestom.server.entity.Player

object PlaceholderCommand : Command("pouplaceholder","pouph") {
    val uuidArg = ArgumentUUID("uuid")
    val textArg = ArgumentString("text")
    init {
        setDefaultExecutor { commandSender, commandContext ->
            commandSender.sendMessage(arrayOf(
                "/pouplaceholder parse <uuid> <text>",
                " - Parses the text with placeholders for the given UUID.",
                "/pouplaceholder parseme <text>",
                " - Parses the text with placeholders for your own UUID.",
                "/pouplaceholder registered",
                " - Lists all registered placeholders.",
            ))
        }
        addSubcommand(object : Command("parse") {
            init {
                setDefaultExecutor { commandSender, commandContext ->
                    commandSender.sendMessage("Usage: /pouplaceholder parse <uuid> <text>")
                }
                addSyntax({ commandSender, commandContext ->
                    val uuid = commandContext.get(uuidArg)
                    val text = commandContext.get(textArg)
                    val parsed = PouPlaceholder.placeholderManager.replace(uuid,text)
                    commandSender.sendMessage("Parsed text for UUID $uuid: $parsed")
                }, uuidArg, textArg)
            }
        })
        addSubcommand(object : Command("parseme") {
            init {
                setDefaultExecutor { commandSender, commandContext ->
                    commandSender.sendMessage("Usage: /pouplaceholder parseme <text>")
                }
                addSyntax({ commandSender, commandContext ->
                    if(commandSender !is Player) {
                        commandSender.sendMessage("This command can only be used by players.")
                        return@addSyntax
                    }
                    val uuid = commandSender.uuid
                    val text = commandContext.get(textArg)
                    val parsed = PouPlaceholder.placeholderManager.replace(commandSender, text)
                    commandSender.sendMessage("Parsed text for your UUID $uuid: $parsed")
                }, textArg)
            }
        })
        addSubcommand(object : Command("registered") {
            init {
                setDefaultExecutor { commandSender, commandContext ->
                    val placeholders = PouPlaceholder.placeholderManager.values.map{it.toString()}
                    if (placeholders.isEmpty()) {
                        commandSender.sendMessage("No registered placeholders.")
                    } else {
                        commandSender.sendMessage(arrayOf("Registered placeholders: ")+placeholders)
                    }
                }
            }
        })
    }
}