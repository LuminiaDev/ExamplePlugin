package com.luminiadev.exampleplugin.customenchantment;

import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.item.enchantment.EnchantmentRarity;
import cn.nukkit.item.enchantment.EnchantmentType;
import cn.nukkit.utils.Identifier;

public class EnchantmentCustomExample extends Enchantment {

    public EnchantmentCustomExample() {
        super(new Identifier("lumi:custom_enchant"), "Example enchantment", EnchantmentRarity.COMMON, EnchantmentType.ALL);
    }
}
