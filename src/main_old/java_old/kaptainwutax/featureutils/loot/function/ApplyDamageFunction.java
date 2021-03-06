package kaptainwutax.featureutils.loot.function;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ApplyDamageFunction implements LootFunction{


    @Override
    public ItemStack process(ItemStack baseStack, LootContext context) {
        context.advance(1);
        return baseStack;
    }
}
