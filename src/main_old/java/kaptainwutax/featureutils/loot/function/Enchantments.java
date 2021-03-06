package kaptainwutax.featureutils.loot.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.seedutils.mc.MCVersion;

public class Enchantments {
    private final static Integer COMMON = 10;
    private final static Integer UNCOMMON = 5;
    private final static Integer RARE = 2;
    private final static Integer VERY_RARE = 1;
    public final static HashSet<String>
        ARMOR_HEAD = new HashSet<>(Arrays.asList("GOLDEN_HELMET","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> ARMOR_CHEST = new HashSet<>(Arrays.asList("GOLDEN_CHESTPLATE","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> ARMOR_FEET = new HashSet<>(Arrays.asList("GOLDEN_BOOTS","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> ARMOR = new HashSet<>(Arrays.asList("GOLDEN_HELMET","GOLDEN_CHESTPLATE","GOLDEN_LEGGINGS","GOLDEN_BOOTS","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> BOW = new HashSet<>(Arrays.asList("BOW","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> BREAKABLE = new HashSet<>(Arrays.asList("ENCHANTED_BOOK", "BOOK","BOW","CROSSBOW","GOLDEN_HELMET","GOLDEN_CHESTPLATE","GOLDEN_BOOTS","GOLDEN_LEGGINGS","GOLDEN_SWORD","GOLDEN_AXE","GOLDEN_HOE","GOLDEN_PICKAXE","GOLDEN_SHOVEL"));
    public final static HashSet<String> CROSSBOW = new HashSet<>(Arrays.asList("CROSSBOW","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> FISHING_ROD = new HashSet<>(Arrays.asList("FISHING_ROD","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> DIGGER = new HashSet<>(Arrays.asList("GOLDEN_PICKAXE","GOLDEN_SHOVEL","GOLDEN_AXE","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> TRIDENT = new HashSet<>(Arrays.asList("TRIDENT","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> WEARABLE = new HashSet<>(Arrays.asList("GOLDEN_CHESTPLATE","GOLDEN_LEGGINGS","GOLDEN_BOOTS","GOLDEN_HELMET","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> WEAPON = new HashSet<>(Arrays.asList("GOLDEN_SWORD","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> DAMAGE = new HashSet<>(Arrays.asList("GOLDEN_SWORD","GOLDEN_AXE","ENCHANTED_BOOK", "BOOK"));
    public final static HashSet<String> VANISHABLE = new HashSet<>(Arrays.asList("CROSSBOW","GOLDEN_HELMET","GOLDEN_CHESTPLATE","FISHING_ROD","GOLDEN_SHOVEL","GOLDEN_HOE","TRIDENT","GOLDEN_SWORD","GOLDEN_PICKAXE","GOLDEN_LEGGINGS","GOLDEN_BOOTS","BOW","GOLDEN_AXE","ENCHANTED_BOOK", "BOOK"));
    public final static List<Enchantment> EnchantmentRegistry = new ArrayList<>(Arrays.asList(
            new Enchantment("protection",COMMON,ARMOR,1,4, (i, n) -> (n<1+(i-1)*11), (i, n) -> (n>1+(i-1)*11+11),new HashSet<>(Arrays.asList("protection","fire_protection","projectile_protection","blast_protection"))),
        new Enchantment("fire_protection",UNCOMMON,ARMOR,1,4, (i, n) -> (n<10+(i-1)*8), (i, n) -> (n>10+(i-1)*8+8),new HashSet<>(Arrays.asList("protection","fire_protection","projectile_protection","blast_protection"))),
        new Enchantment("feather_falling",UNCOMMON,ARMOR_FEET,1,4, (i, n) -> (n<5+(i-1)*6), (i, n) -> (n>5+(i-1)*6+6),new HashSet<>(Arrays.asList("feather_falling"))),
        new Enchantment("blast_protection",RARE,ARMOR,1,4, (i, n) -> (n<5+(i-1)*8), (i, n) -> (n>5+(i-1)*8+8),new HashSet<>(Arrays.asList("protection","fire_protection","projectile_protection","blast_protection"))),
        new Enchantment("projectile_protection",UNCOMMON,ARMOR,1,4, (i, n) -> (n<3+(i-1)*6), (i, n) -> (n>3+(i-1)*6+6),new HashSet<>(Arrays.asList("protection","fire_protection","projectile_protection","blast_protection"))),
        new Enchantment("respiration",RARE,ARMOR_HEAD,1,3, (i, n) -> (n<10*i), (i, n) -> (n>10*i+30), new HashSet<>(Arrays.asList("respiration"))),
        new Enchantment("aqua_affinity",RARE,ARMOR_HEAD,1,1, (i, n) -> (n<1), (i, n) -> (n>41), new HashSet<>(Arrays.asList("aqua_affinity"))),
        new Enchantment("thorns",VERY_RARE,ARMOR,1,3, (i, n) -> (n<10+(20*(i-1))), (i, n) -> (n>10+(20*(i-1))+50),new HashSet<>(Arrays.asList("thorns"))),
        new Enchantment("depth_strider",RARE,ARMOR_FEET,1,3, (i, n) -> (n<i*10), (i, n) -> (n>i*10+15),new HashSet<>(Arrays.asList("frost_walker","depth_strider"))),
        new Enchantment("frost_walker",RARE,ARMOR_FEET,1,2, (i, n) -> (n<i*10), (i, n) -> (n>i*10+15), new HashSet<>(Arrays.asList("frost_walker","depth_strider")), true),
        new Enchantment("binding_curse",VERY_RARE,WEARABLE,1,1, (i, n) -> (n<25), (i, n) -> (n>50), new HashSet<>(Arrays.asList("binding_curse")),true),
        new Enchantment("soul_speed",VERY_RARE,ARMOR_FEET, 1,3,(i, n) -> (n<i*10), (i, n) -> (n>i*10+15), new HashSet<>(Arrays.asList("soul_speed")), true, false),
        new Enchantment("sharpness",COMMON,DAMAGE,1,5, (i, n) -> (n<1+(i-1)*11), (i, n) -> (n>1+(i-1)*11+20), new HashSet<>(Arrays.asList("sharpness","smite","bane_of_arthropods"))),
        new Enchantment("smite",UNCOMMON,DAMAGE,1,5, (i, n) -> (n<5+(i-1)*8), (i, n) -> (n>5+(i-1)*8+20), new HashSet<>(Arrays.asList("sharpness","smite","bane_of_arthropods"))),
        new Enchantment("bane_of_arthropods",UNCOMMON,DAMAGE,1,5, (i, n) -> (n<5+(i-1)*8), (i, n) -> (n>5+(i-1)*8+20), new HashSet<>(Arrays.asList("sharpness","smite","bane_of_arthropods"))),
        new Enchantment("knockback",UNCOMMON,WEAPON,1,2, (i, n) -> (n<5+20*(i-1)), (i, n) -> (n>1+(i*10)+50), new HashSet<>(Arrays.asList("knockback"))),
        new Enchantment("fire_aspect",RARE,WEAPON,1,2, (i, n) -> (n<10+20*(i-1)), (i, n) -> (n>1+(i*10)+50),new HashSet<>(Arrays.asList("fire_aspect"))),
        new Enchantment("looting",RARE,WEAPON,1,3, (i, n) -> (n<15+(i-1)*9), (i, n) -> (n>1+(i*10)+50),new HashSet<>(Arrays.asList("looting"))),
        new Enchantment("sweeping",RARE,WEAPON,1,3, (i, n) -> (n<5+(i-1)*9), (i, n) -> (n>5+(i-1)*9+15), new HashSet<>(Arrays.asList("sweeping"))),
        new Enchantment("efficiency",COMMON,DIGGER,1,5, (i, n) -> (n<(1+10*(i-1))), (i, n) -> (n>1+(i*10)+50),new HashSet<>(Arrays.asList("efficiency"))),
        new Enchantment("silk_touch",VERY_RARE,DIGGER,1,1, (i, n) -> (n<15), (i, n) -> (n>1+(i*10)+50), new HashSet<>(Arrays.asList("fortune","silk_touch"))),
        new Enchantment("unbreaking",UNCOMMON,BREAKABLE,1,3, (i, n) -> (n<5+(i-1)*8), (i, n) -> (n>1+(i*10)+50),new HashSet<>(Arrays.asList("unbreaking"))),
        new Enchantment("fortune",RARE,DIGGER,1,3, (i, n) -> (n<15+(i-1)*9), (i, n) -> (n>1+(i*10)+50), new HashSet<>(Arrays.asList("fortune","silk_touch"))),
        new Enchantment("power",COMMON,BOW,1,5, (i, n) -> (n<1+(i-1)*10), (i, n) -> (n>1+(i-1)*10+15), new HashSet<>(Arrays.asList("power"))),
        new Enchantment("punch",RARE,BOW,1,2, (i, n) -> (n<12+(i-1)*20), (i, n) -> (n>12+(i-1)*20+25), new HashSet<>(Arrays.asList("punch"))),
        new Enchantment("flame",RARE,BOW,1,1, (i, n) -> (n<20), (i, n) -> (n>50), new HashSet<>(Arrays.asList("flame"))),
        new Enchantment("infinity",VERY_RARE,BOW,1,1, (i, n) -> (n<20), (i, n) -> (n>50), new HashSet<>(Arrays.asList("mending","infinity"))),
        new Enchantment("luck_of_the_sea",RARE,FISHING_ROD,1,3, (i, n) -> (n<15+(i-1)*9), (i, n) -> (n>1+(i*10)+50),new HashSet<>(Arrays.asList("luck_of_the_sea"))),
        new Enchantment("lure",RARE,FISHING_ROD,1,3, (i, n) -> (n<15+(i-1)*9), (i, n) -> (n>1+(i*10)+50), new HashSet<>(Arrays.asList("lure"))),
        new Enchantment("loyalty",UNCOMMON,TRIDENT,1,3, (i, n) -> (n<5+(i*7)), (i, n) -> (n>50),new HashSet<>(Arrays.asList("loyalty","riptide"))),
        new Enchantment("impaling",RARE,TRIDENT,1,5, (i, n) -> (n<1+(i-1)*8), (i, n) -> (n>1+(i-1)*8+20),new HashSet<>(Arrays.asList("impaling"))),
        new Enchantment("riptide",RARE,TRIDENT,1,3, (i, n) -> (n<10+(i*7)), (i, n) -> (n>50), new HashSet<>(Arrays.asList("riptide", "loyalty","channeling"))),
        new Enchantment("channeling",VERY_RARE,TRIDENT,1,1, (i, n) -> (n<25), (i, n) -> (n>50),new HashSet<>(Arrays.asList("channeling","riptide"))),
        new Enchantment("multishot",RARE,CROSSBOW,1,1, (i, n) -> (n<20), (i, n) -> (n>50), new HashSet<>(Arrays.asList("multishot", "piercing"))),
        new Enchantment("quick_charge",UNCOMMON,CROSSBOW,1,3, (i, n) -> (n<12+(i-1)*20), (i, n) -> (n>50), new HashSet<>(Arrays.asList("quick_charge"))),
        new Enchantment("piercing",COMMON,CROSSBOW,1,4, (i, n) -> (n<1+(i-1)*10), (i, n) -> (n>50), new HashSet<>(Arrays.asList("multishot", "piercing"))),
        new Enchantment("mending",RARE,BREAKABLE,1,1, (i, n) -> (n<i*25), (i, n) -> (n>i*25+50), new HashSet<>(Arrays.asList("mending","infinity")), true),
        new Enchantment("vanishing_curse",VERY_RARE,VANISHABLE,1,1, (i, n) -> (n<25), (i, n) -> (n>50), new HashSet<>(Arrays.asList("vanishing_curse")), true)
    ));
    public final static HashSet<String> SingleEnchants = new HashSet(Arrays.asList("aqua_affinity", "binding_curse","channeling","silk_touch","flame","infinity","multishot","quick_charge","mending","vanishing_curse"));
    public final static List<HashSet<String>> allCategories = new ArrayList(Arrays.asList(ARMOR,ARMOR_HEAD,ARMOR_CHEST,ARMOR_FEET,BOW,BREAKABLE,CROSSBOW,DIGGER,DAMAGE,FISHING_ROD,TRIDENT,WEARABLE,WEAPON,VANISHABLE));

    public Enchantment getEnchantment(String name){
        for(Enchantment enchantment : EnchantmentRegistry) {
            if (enchantment.getName().equals(name)) {
                return enchantment;
            }
        };
        return null;
    }

    public static HashSet<HashSet<String>> getCategories(ItemStack baseStack){
        HashSet<HashSet<String>> applicableCategories = new HashSet<>();
        for(HashSet<String> category : allCategories){
            if(category.contains(baseStack.getItem().getName().toUpperCase())){
                applicableCategories.add(category);
            }
        }
        return applicableCategories;
    }

    public static List<Enchantment> getApplicableEnchantments(HashSet<HashSet<String>> applicableCategories){
        return getApplicableEnchantments(applicableCategories, false, true);
    }

    public static List<Enchantment> getApplicableEnchantments(HashSet<HashSet<String>> applicableCategories, boolean isTreasure){
        return getApplicableEnchantments(applicableCategories, isTreasure, true);
    }



    public static List<Enchantment> getApplicableEnchantments(HashSet<HashSet<String>> applicableCategories, boolean isTreasure, boolean isDiscoverable){
        List<Enchantment> applicableEnchantments = new ArrayList<>();
        List<String> applicableEnchantmentNames = new ArrayList<>();
        for(Enchantment currentEnchantment : EnchantmentRegistry){
            if(!(currentEnchantment.isTreasure() && !isTreasure)&&(currentEnchantment.isDiscoverable()==isDiscoverable)){
                if (applicableCategories.contains(currentEnchantment.getCategory())) {
                    if (!(applicableEnchantmentNames.contains(currentEnchantment.getName()))) {
                        applicableEnchantments.add(currentEnchantment);
                        applicableEnchantmentNames.add(currentEnchantment.getName());
                    }
                }
            }
        }
        return applicableEnchantments;
    }

    public static void filterCompatibleEnchantments(List<EnchantmentInstance> list, EnchantmentInstance instance){
        for (EnchantmentInstance e : list){
            if(e.getIncompatible().contains(instance)){
                list.remove(e);
            }
        }
    }
}
