package com.luminiadev.exampleplugin;

import cn.nukkit.block.Block;
import cn.nukkit.event.player.PlayerJumpEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import com.luminiadev.exampleplugin.command.ExampleGiveCommand;
import com.luminiadev.exampleplugin.command.ExampleSetBlockCommand;
import com.luminiadev.exampleplugin.command.RandomEffectsCommand;
import com.luminiadev.exampleplugin.command.SimplePluginCommand;
import com.luminiadev.exampleplugin.customblock.BlockCustomExample;
import com.luminiadev.exampleplugin.customenchantment.EnchantmentCustomExample;
import com.luminiadev.exampleplugin.customitem.ItemCustomExample;
import com.luminiadev.exampleplugin.listener.EventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ExamplePlugin extends PluginBase {

    @Override
    public void onLoad() {
        log.info("ExamplePlugin loaded");

        Item.registerCustomItem(ItemCustomExample.class).assertOK();
        Block.registerCustomBlock(List.of(BlockCustomExample.class)).assertOK();
        Enchantment.register(new EnchantmentCustomExample(), true).assertOK();
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