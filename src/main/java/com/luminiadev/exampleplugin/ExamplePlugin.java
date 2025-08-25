package com.luminiadev.exampleplugin;

import cn.nukkit.event.player.PlayerJumpEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.registry.Registries;
import cn.nukkit.utils.TextFormat;
import com.luminiadev.exampleplugin.command.ExampleGiveCommand;
import com.luminiadev.exampleplugin.command.ExampleSetBlockCommand;
import com.luminiadev.exampleplugin.command.RandomEffectsCommand;
import com.luminiadev.exampleplugin.command.SimplePluginCommand;
import com.luminiadev.exampleplugin.customblock.BlockCustomExample;
import com.luminiadev.exampleplugin.customenchantment.EnchantmentCustomExample;
import com.luminiadev.exampleplugin.customitem.ItemCustomExample;
import com.luminiadev.exampleplugin.customitem.ItemFoodCustom;
import com.luminiadev.exampleplugin.listener.EventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ExamplePlugin extends PluginBase {

    @Override
    public void onLoad() {
        log.info("ExamplePlugin loaded");

        // Registering custom item, block & enchantment
        Registries.ITEM.registerCustom(List.of(
                ItemCustomExample.class,
                ItemFoodCustom.class
        ));
        Registries.BLOCK.registerCustom(List.of(BlockCustomExample.class));
        Registries.ENCHANTMENT.registerCustom(new EnchantmentCustomExample(), true);
    }

    @Override
    public void onEnable() {
        log.info("ExamplePlugin enabled");

        this.getServer().getCommandMap().register("ExamplePlugin", new RandomEffectsCommand(this));
        this.getServer().getCommandMap().register("ExamplePlugin", new SimplePluginCommand(this));
        this.getServer().getCommandMap().register("ExamplePlugin", new ExampleGiveCommand(this));
        this.getServer().getCommandMap().register("ExamplePlugin", new ExampleSetBlockCommand(this));

        // Registering non-reflection event
        this.getServer().getPluginManager().subscribeEvent(PlayerJumpEvent.class, event -> {
            event.getPlayer().sendActionBar(TextFormat.GREEN + "You jumped!");
        }, this);
        // Registering reflection events
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        log.info("ExamplePlugin disabled");
    }
}