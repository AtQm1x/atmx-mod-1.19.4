package com.atmx.AtomixMod.data;

import com.atmx.AtomixMod.block.ModBlocks;
import com.atmx.AtomixMod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.DRAGON_GEM_ITEM, RecipeCategory.DECORATIONS,
                ModBlocks.DRAGON_GEM_BLOCK);

        // JUST AN EXAMPLE
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DRAGON_GEM_ORE)
                .pattern("SSS")
                .pattern("SCS")
                .pattern("SSS")
                .input('S', Items.END_STONE)
                .input('C', ModItems.DRAGON_GEM_ITEM)
                .criterion(FabricRecipeProvider.hasItem(Items.END_STONE),
                        FabricRecipeProvider.conditionsFromItem(Items.END_STONE))
                .criterion(FabricRecipeProvider.hasItem(ModItems.DRAGON_GEM_ITEM),
                        FabricRecipeProvider.conditionsFromItem(ModItems.DRAGON_GEM_ITEM))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.DRAGON_GEM_ORE)));
    }
}