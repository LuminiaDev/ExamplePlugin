package com.luminiadev.exampleplugin.customitem;

import cn.nukkit.Player;
import cn.nukkit.entity.effect.Effect;
import cn.nukkit.entity.effect.EffectType;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomFood;
import cn.nukkit.network.protocol.types.inventory.creative.CreativeItemCategory;

public class ItemFoodCustom extends ItemCustomFood {

    public ItemFoodCustom() {
        super("lumi:example_food", "Example Food", "bread");
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition.edibleBuilder(this, CreativeItemCategory.ITEMS)
                .foil(true)
                .build();
    }

    @Override
    public int getFoodRestore() {
        return 4;
    }

    @Override
    public float getSaturationRestore() {
        return 9.6F;
    }

    @Override
    public boolean onEaten(Player player) {
        player.addEffect(Effect.get(EffectType.HASTE).setAmplifier(1).setDuration(100));
        return true;
    }
}
