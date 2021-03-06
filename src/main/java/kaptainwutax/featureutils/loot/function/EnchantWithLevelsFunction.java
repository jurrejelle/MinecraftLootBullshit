package kaptainwutax.featureutils.loot.function;

import static kaptainwutax.featureutils.loot.enchantment.EnchantmentInstance.getRandomItem;
import static kaptainwutax.featureutils.loot.enchantment.Enchantments.filterCompatibleEnchantments;
import static kaptainwutax.featureutils.loot.enchantment.Enchantments.getApplicableEnchantments;
import static kaptainwutax.featureutils.loot.enchantment.Enchantments.getCategories;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kaptainwutax.featureutils.loot.enchantment.Enchantment;
import kaptainwutax.featureutils.loot.enchantment.EnchantmentInstance;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.mathutils.util.Mth;

public class EnchantWithLevelsFunction implements LootFunction{



    int minLevel;
    int maxLevel;
    boolean treasure;
    boolean discoverable;
    public static HashMap<String, Integer> enchantmentlist;

    public EnchantWithLevelsFunction(int minLevel, int maxLevel){
        this(minLevel,maxLevel,true, true);
    }

    public EnchantWithLevelsFunction(int minLevel, int maxLevel, boolean treasure){
        this(minLevel,maxLevel,treasure, true);
    }

    public EnchantWithLevelsFunction(int minLevel, int maxLevel, boolean treasure, boolean discoverable){
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.treasure = treasure;
        this.discoverable = discoverable;
        enchantmentlist = new HashMap<>();
        enchantmentlist.put("leather_helmet", 15);
        enchantmentlist.put("leather_chestplate", 15);
        enchantmentlist.put("leather_leggings", 15);
        enchantmentlist.put("leather_boots", 15);
        enchantmentlist.put("chainmail_helmet", 12);
        enchantmentlist.put("chainmail_chestplate", 12);
        enchantmentlist.put("chainmail_leggings", 12);
        enchantmentlist.put("chainmail_boots", 12);
        enchantmentlist.put("iron_helmet", 9);
        enchantmentlist.put("iron_chestplate", 9);
        enchantmentlist.put("iron_leggings", 9);
        enchantmentlist.put("iron_boots", 9);
        enchantmentlist.put("golden_helmet", 25);
        enchantmentlist.put("golden_chestplate", 25);
        enchantmentlist.put("golden_leggings", 25);
        enchantmentlist.put("golden_boots", 25);
        enchantmentlist.put("diamond_helmet", 10);
        enchantmentlist.put("diamond_chestplate", 10);
        enchantmentlist.put("diamond_leggings", 10);
        enchantmentlist.put("diamond_boots", 10);
        enchantmentlist.put("turtle_helmet", 9);
        enchantmentlist.put("netherite_helmet", 15);
        enchantmentlist.put("netherite_chestplate", 15);
        enchantmentlist.put("netherite_leggings", 15);
        enchantmentlist.put("netherite_boots", 15);
        enchantmentlist.put("fishing_rod", 1);
        enchantmentlist.put("book", 1);
        enchantmentlist.put("wooden_pickaxe", 15);
        enchantmentlist.put("wooden_axe", 15);
        enchantmentlist.put("wooden_hoe", 15);
        enchantmentlist.put("wooden_shovel", 15);
        enchantmentlist.put("wooden_sword", 15);
        enchantmentlist.put("stone_pickaxe", 5);
        enchantmentlist.put("stone_axe", 5);
        enchantmentlist.put("stone_hoe", 5);
        enchantmentlist.put("stone_shovel", 5);
        enchantmentlist.put("stone_sword", 5);
        enchantmentlist.put("iron_pickaxe", 14);
        enchantmentlist.put("iron_axe", 14);
        enchantmentlist.put("iron_hoe", 14);
        enchantmentlist.put("iron_shovel", 14);
        enchantmentlist.put("iron_sword", 14);
        enchantmentlist.put("golden_pickaxe", 22);
        enchantmentlist.put("golden_axe", 22);
        enchantmentlist.put("golden_hoe", 22);
        enchantmentlist.put("golden_shovel", 22);
        enchantmentlist.put("golden_sword", 22);
        enchantmentlist.put("diamond_pickaxe", 10);
        enchantmentlist.put("diamond_axe", 10);
        enchantmentlist.put("diamond_hoe", 10);
        enchantmentlist.put("diamond_shovel", 10);
        enchantmentlist.put("diamond_sword", 10);
        enchantmentlist.put("netherite_pickaxe", 15);
        enchantmentlist.put("netherite_axe", 15);
        enchantmentlist.put("netherite_hoe", 15);
        enchantmentlist.put("netherite_shovel", 15);
        enchantmentlist.put("netherite_sword", 15);
    }

    public ItemStack process(ItemStack itemStack, LootContext lootContext) {
        return this.enchantItem(lootContext, itemStack, getRandomValueFromBounds(lootContext), this.treasure, this.discoverable);
    }

    public ItemStack enchantItem(LootContext random, ItemStack itemStack, int n, boolean bl, boolean b2) {
        List<EnchantmentInstance> list = selectEnchantment(random, itemStack, n, bl, b2);
        for (EnchantmentInstance enchantmentInstance : list) {
            itemStack.getItem().getEnchantment().add(enchantmentInstance.getName());
            itemStack.getItem().getLevel().add(enchantmentInstance.getLevel());
        }
        return itemStack;
    }

    public  List<EnchantmentInstance> selectEnchantment(LootContext lootContext, ItemStack itemStack, int n, boolean bl, boolean b2) {
        ArrayList<EnchantmentInstance> arrayList = new ArrayList<>();
        Item item = itemStack.getItem();
        int n2;
        if(enchantmentlist.containsKey(item.getName())){
            n2 = enchantmentlist.get(item.getName());
        } else{
            return arrayList;
        }
        n += 1 + lootContext.nextInt(n2 / 4 + 1) + lootContext.nextInt(n2 / 4 + 1);
        float f = (lootContext.nextFloat() + lootContext.nextFloat() - 1.0f) * 0.15f;
        List<EnchantmentInstance> list = getAvailableEnchantmentResults(n = Mth.clamp(Math.round((float)n + (float)n * f), 1, Integer.MAX_VALUE), itemStack, bl, b2);
        if (!list.isEmpty()) {
            arrayList.add(getRandomItem(lootContext, list));
            while (lootContext.nextInt(50) <= n) {
                filterCompatibleEnchantments(list, arrayList.get(arrayList.size()-1));
                if (list.isEmpty()) break;
                arrayList.add(getRandomItem(lootContext, list));
                n /= 2;
            }
        }
        return arrayList;
    }



    public List<EnchantmentInstance> getAvailableEnchantmentResults(int n, ItemStack itemStack, boolean bl, boolean b2) {
        ArrayList<EnchantmentInstance> arrayList = new ArrayList<>();
        List<Enchantment> list = getApplicableEnchantments(getCategories(itemStack), this.treasure, this.discoverable);
        block0 : for (Enchantment enchantment : list) {
            if((enchantment.isTreasure()&&!bl)||!(enchantment.isDiscoverable()==this.discoverable)) continue;
            for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                if (enchantment.getIsLowerThanMinCost().test(i, n) || enchantment.getIsHigherThanMaxCost().test(i, n)) continue;
                arrayList.add(new EnchantmentInstance(enchantment, i));
                continue block0;
            }
        }
        return arrayList;
    }


    public int getRandomValueFromBounds(LootContext lootContext){
        if(this.minLevel==this.maxLevel){
            return this.minLevel;
        } else{
            return lootContext.nextInt(this.maxLevel-this.minLevel+1)+minLevel;
        }
    }
}
