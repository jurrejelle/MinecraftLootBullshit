package kaptainwutax.featureutils.loot.function;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.enchantment.Enchantment;
import kaptainwutax.featureutils.loot.enchantment.Enchantments;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.HashSet;
import java.util.List;

import static kaptainwutax.featureutils.loot.enchantment.Enchantments.getApplicableEnchantments;
import static kaptainwutax.featureutils.loot.enchantment.Enchantments.getCategories;

public class ApplyDamageFunction implements LootFunction{
    public ApplyDamageFunction(){

    }

    @Override
    public ItemStack process(ItemStack baseStack, LootContext context) {
        context.advance(1);
        return baseStack;
    }
}
