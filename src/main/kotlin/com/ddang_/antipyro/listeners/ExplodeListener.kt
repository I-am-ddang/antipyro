package com.ddang_.antipyro.listeners

import org.bukkit.entity.Creeper
import org.bukkit.entity.EnderCrystal
import org.bukkit.entity.Minecart
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import org.bukkit.event.entity.EntityExplodeEvent

class ExplodeListener: Listener {

    private val explodeCause = setOf(DamageCause.BLOCK_EXPLOSION)
    private val explodeTarget = setOf("End Crystal", "Minecart with TNT", "Primed TNT", )

    @EventHandler
    fun damage(e: EntityDamageEvent) {
        val p = e.entity
        if (p !is Player) {
            return
        }
        if (!explodeCause.contains(e.cause)) {
            return
        }
        e.isCancelled = true
    }

    @EventHandler
    fun damageBy(e: EntityDamageByEntityEvent) {
        val p = e.entity
        if (p !is Player) {
            return
        }
        val k = e.damager
        if (e.cause != DamageCause.ENTITY_EXPLOSION) {
            return
        }
        if (!explodeTarget.contains(k.name)) {
            return
        }
        e.isCancelled = true
    }
}