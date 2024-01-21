package com.nomiceu.nomilabs.groovy;

import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.ItemMaterialInfo;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

@SuppressWarnings("unused")
public class LabsVirtualizedRegistries {
    public static final ReplaceRecipeManager REPLACE_RECIPE_MANAGER = new ReplaceRecipeManager();

    public static class ReplaceRecipeManager extends VirtualizedRegistry<Pair<ItemStack, ItemMaterialInfo>> {
        @Override
        public void onReload() {
            restoreFromBackup().forEach((pair) -> OreDictUnifier.registerOre(pair.getLeft(), pair.getRight()));
        }

        @Override
        public void afterScriptLoad() {
            ReplaceRecipe.reloadRecyclingRecipes();
        }

        public void registerOre(ItemStack stack, ItemMaterialInfo info) {
            addBackup(Pair.of(stack, OreDictUnifier.getMaterialInfo(stack)));
            OreDictUnifier.registerOre(stack, info);
        }
    }
}