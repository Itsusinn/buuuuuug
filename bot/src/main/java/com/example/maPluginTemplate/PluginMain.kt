package com.example.maPluginTemplate

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.MemberCommandSender
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.isOperator
import net.mamoe.mirai.utils.info

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "org.example.mirai-plugin",
        name = "ExamplePlugin",
        version = "0.1.0"
    )
) {
    override fun onEnable() {
        CommandManager.registerCommand(MesagistoCommand)
        logger.info { "Plugin loaded" }
    }
}
object MesagistoCommand : CompositeCommand(
    PluginMain, "forward", "f", "fd",
    description = "当前信使插件支持的指令."
) {

    @SubCommand("setChannel", "channel", "sc")
    suspend fun MemberCommandSender.handleSetChannel() {
        if (!user.isOperator()) {
            sendMessage("您不是群主或管理员,无法设置信使频道")
            return
        }
        sendMessage("成功将目标群聊: ${subject.name} 的信使频道设置为 ${user.id}")
    }
}
