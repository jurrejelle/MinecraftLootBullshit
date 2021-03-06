package kaptainwutax.featureutils.loot.enchantment;

import java.util.HashSet;
import java.util.List;
import java.util.function.BiPredicate;
import kaptainwutax.featureutils.loot.LootContext;

public class EnchantmentInstance extends Enchantment {
    private int level;
    public EnchantmentInstance(String name, Integer rarity, HashSet<String> category, Integer minLevel, Integer maxLevel, Integer level, HashSet<String> incompatible) {
        super(name,rarity,category,minLevel,maxLevel,(n,i) -> (n<1+(i*10)), (n,i) -> (n>(6+(i*10))), incompatible);
        this.level = level;
    }

    public EnchantmentInstance(String name, Integer rarity, HashSet<String> category, Integer minLevel, Integer maxLevel, BiPredicate<Integer,Integer> minCost, BiPredicate<Integer,Integer> maxCost, int level, HashSet<String> incompatible, boolean isTreasure, boolean isDiscoverable) {
        super(name,rarity,category,minLevel,maxLevel, minCost, maxCost, incompatible, isTreasure, isDiscoverable);
        this.level = level;
    }

    public EnchantmentInstance(Enchantment e, int level){
        this(e.getName(),e.getRarity(),e.getCategory(),e.getMinLevel(),e.getMaxLevel(),e.getIsLowerThanMinCost(),e.getIsHigherThanMaxCost(), level, e.getIncompatible(), e.isTreasure(),e.isDiscoverable());
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static int getTotalWeight(List<EnchantmentInstance> list) {
        int n = 0;
        int n2 = list.size();
        for (int i = 0; i < n2; ++i) {
            EnchantmentInstance weighedRandomInstance = list.get(i);
            n += weighedRandomInstance.getRarity();
        }
        return n;
    }

    public static EnchantmentInstance getRandomItem(LootContext random, List<EnchantmentInstance> list, int n) {
        int n2 = random.nextInt(n);
        return getWeightedItem(list, n2);
    }

    public static EnchantmentInstance getWeightedItem(List<EnchantmentInstance> list, int n) {
        int n2 = list.size();
        for (int i = 0; i < n2; ++i) {
            EnchantmentInstance weighedRandomItem = list.get(i);
            if ((n -= weighedRandomItem.getRarity()) >= 0) continue;
            return weighedRandomItem;
        }
        return null;
    }

    public static EnchantmentInstance getRandomItem(LootContext random, List<EnchantmentInstance> list) {
        return getRandomItem(random, list, getTotalWeight(list));
    }

}
