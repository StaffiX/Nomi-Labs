package com.nomiceu.nomilabs.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Objects;

/**
 * Util class to compare ItemStacks based on Item, Tag and Meta.
 */
@SuppressWarnings("unused")
public class ItemTagMeta extends ItemMeta {
    private final NBTTagCompound tag;

    public ItemTagMeta(ItemStack stack) {
        super(stack);
        tag = stack.getTagCompound();
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof ItemTagMeta itemTagMeta)) return false;
        return Objects.equals(tag, itemTagMeta.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tag);
    }

    /**
     * Compares ItemStacks based on Item, Tag and Meta.
     */
    public static boolean compare(ItemStack a, ItemStack b) {
        return ItemMeta.compare(a, b) && Objects.equals(a.getTagCompound(), b.getTagCompound());
    }
}