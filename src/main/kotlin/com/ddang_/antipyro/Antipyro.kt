package com.ddang_.antipyro

import com.ddang_.antipyro.listeners.ExplodeListener
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitScheduler

class Antipyro : JavaPlugin() {

    companion object {
        lateinit var scheduler: BukkitScheduler
            private set
        lateinit var instance: Plugin
        lateinit var players: MutableCollection<out Player>
            private set

        fun Long.rl(run: Runnable) = scheduler.runTaskLater(instance, run, this)
        fun Long.rt(delay: Long = 1, run: Runnable) = scheduler.runTaskTimer(instance, run, delay, this)
        fun String.broad() = Bukkit.broadcastMessage(this)
    }

    private val events = arrayOf(
        ExplodeListener()
    )

    override fun onEnable() {
        players = server.onlinePlayers
        instance = this
        scheduler = server.scheduler

        //이벤트
        server.pluginManager.apply { events.forEach { registerEvents(it, this@Antipyro) } }

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}