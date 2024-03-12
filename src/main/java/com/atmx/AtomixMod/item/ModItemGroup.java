package com.atmx.AtomixMod.item;

import com.atmx.AtomixMod.atmxMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup TMX_ITEM_GROUP;

    public static void registerItemGroups() {
        TMX_ITEM_GROUP = FabricItemGroup.builder(new Identifier(atmxMod.MOD_ID, "custom"))
                .displayName(Text.translatable("itemgroup.custom"))
                .icon(() -> new ItemStack(ModItems.DRAGON_GEM_ITEM)).build();
    }
}
