package kaptainwutax.featureutils.loot.function;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EnchantRandomlyFunction implements LootFunction{
    private final static Integer COMMON = 10;
    private final static Integer UNCOMMON = 5;
    private final static Integer RARE = 2;
    private final static Integer VERY_RARE = 1;
    private final HashSet<String> ARMOR_HEAD = new HashSet<>(Arrays.asList("GOLDEN_HELMET","DIAMOND_HELMET"));
    private final HashSet<String> ARMOR_CHEST = new HashSet<>(Arrays.asList("GOLDEN_CHESTPLATE","DIAMOND_CHESTPLATE"));
    private final HashSet<String> ARMOR_FEET = new HashSet<>(Arrays.asList("GOLDEN_BOOTS","DIAMOND_BOOTS"));
    private final HashSet<String> ARMOR = new HashSet<>(Arrays.asList("GOLDEN_HELMET","GOLDEN_CHESTPLATE","GOLDEN_LEGGINGS","GOLDEN_BOOTS","DIAMOND_HELMET","DIAMOND_CHESTPLATE","DIAMOND_LEGGINGS","DIAMOND_BOOTS"));
    private final HashSet<String> BOW = new HashSet<>(Arrays.asList("BOW"));
    private final HashSet<String> BREAKABLE = new HashSet<>(Arrays.asList("BOW","CROSSBOW","GOLDEN_HELMET","GOLDEN_CHESTPLATE","GOLDEN_BOOTS","GOLDEN_LEGGINGS","GOLDEN_SWORD","GOLDEN_AXE","GOLDEN_HOE","GOLDEN_PICKAXE","GOLDEN_SHOVEL","DIAMOND_HELMET","DIAMOND_CHESTPLATE","DIAMOND_LEGGINGS","DIAMOND_BOOTS","DIAMOND_SWORD"));
    private final HashSet<String> CROSSBOW = new HashSet<>(Arrays.asList("CROSSBOW"));
    private final HashSet<String> FISHING_ROD = new HashSet<>(Arrays.asList("FISHING_ROD"));
    private final HashSet<String> DIGGER = new HashSet<>(Arrays.asList("GOLDEN_PICKAXE","GOLDEN_SHOVEL","GOLDEN_AXE"));
    private final HashSet<String> TRIDENT = new HashSet<>(Arrays.asList("TRIDENT"));
    private final HashSet<String> WEARABLE = new HashSet<>(Arrays.asList("GOLDEN_CHESTPLATE","GOLDEN_LEGGINGS","GOLDEN_BOOTS","GOLDEN_HELMET","DIAMOND_HELMET","DIAMOND_CHESTPLATE","DIAMOND_LEGGINGS","DIAMOND_BOOTS"));
    private final HashSet<String> WEAPON = new HashSet<>(Arrays.asList("GOLDEN_SWORD","DIAMOND_SWORD"));
    private final HashSet<String> VANISHABLE = new HashSet<>(Arrays.asList("CROSSBOW","GOLDEN_HELMET","GOLDEN_CHESTPLATE","FISHING_ROD","GOLDEN_SHOVEL","GOLDEN_HOE","TRIDENT","GOLDEN_SWORD","GOLDEN_PICKAXE","GOLDEN_LEGGINGS","GOLDEN_BOOTS","BOW","GOLDEN_AXE","DIAMOND_HELMET","DIAMOND_CHESTPLATE","DIAMOND_LEGGINGS","DIAMOND_BOOTS","DIAMOND_SWORD"));
    private final List<Enchantment> EnchantmentRegistry = new ArrayList<>(Arrays.asList(
            new Enchantment("protection",COMMON,ARMOR,1,4),
            new Enchantment("fire_protection",UNCOMMON,ARMOR,1,4),
            new Enchantment("feather_falling",UNCOMMON,ARMOR_FEET,1,4),
            new Enchantment("blast_protection",RARE,ARMOR,1,4),
            new Enchantment("projectile_protection",UNCOMMON,ARMOR,1,4),
            new Enchantment("respiration",RARE,ARMOR_HEAD,1,3),
            new Enchantment("aqua_affinity",RARE,ARMOR_HEAD,1,1),
            new Enchantment("thorns",VERY_RARE,ARMOR,1,3),
            new Enchantment("depth_strider",RARE,ARMOR_FEET,1,3),
            new Enchantment("frost_walker",RARE,ARMOR_FEET,1,2),
            new Enchantment("binding_curse",VERY_RARE,WEARABLE,1,1),
//            new Enchantment("soul_speed",VERY_RARE,ARMOR_FEET,1,3),
            new Enchantment("sharpness",COMMON,WEAPON,1,5),
            new Enchantment("smite",UNCOMMON,WEAPON,1,5),
            new Enchantment("bane_of_arthropods",UNCOMMON,WEAPON,1,5),
            new Enchantment("knockback",UNCOMMON,WEAPON,1,2),
            new Enchantment("fire_aspect",RARE,WEAPON,1,2),
            new Enchantment("looting",RARE,WEAPON,1,3),
            new Enchantment("sweeping",RARE,WEAPON,1,3),
            new Enchantment("efficiency",COMMON,DIGGER,1,5),
            new Enchantment("silk_touch",VERY_RARE,DIGGER,1,1),
            new Enchantment("unbreaking",UNCOMMON,ARMOR,1,3),
            new Enchantment("unbreaking",UNCOMMON,DIGGER,1,3),
            new Enchantment("fortune",RARE,DIGGER,1,3),
            new Enchantment("power",COMMON,BOW,1,5),
            new Enchantment("punch",RARE,BOW,1,2),
            new Enchantment("flame",RARE,BOW,1,1),
            new Enchantment("infinity",VERY_RARE,BOW,1,1),
            new Enchantment("luck_of_the_sea",RARE,FISHING_ROD,1,3),
            new Enchantment("lure",RARE,FISHING_ROD,1,3),
            new Enchantment("loyalty",UNCOMMON,TRIDENT,1,3),
            new Enchantment("impaling",RARE,TRIDENT,1,5),
            new Enchantment("riptide",RARE,TRIDENT,1,3),
            new Enchantment("channeling",VERY_RARE,TRIDENT,1,1),
            new Enchantment("multishot",RARE,CROSSBOW,1,1),
            new Enchantment("quick_charge",UNCOMMON,CROSSBOW,1,3),
            new Enchantment("piercing",COMMON,CROSSBOW,1,4),
            new Enchantment("mending",RARE,BREAKABLE,1,1),
            new Enchantment("vanishing_curse",VERY_RARE,VANISHABLE,1,1)
    ));
    private final static HashSet<String> SingleEnchants = new HashSet(Arrays.asList("aqua_affinity", "binding_curse","channeling","silk_touch","flame","infinity","multishot","quick_charge","mending","vanishing_curse"));
    private final List<HashSet<String>> allCategories = new ArrayList(Arrays.asList(ARMOR,ARMOR_HEAD,ARMOR_CHEST,ARMOR_FEET,BOW,BREAKABLE,CROSSBOW,DIGGER,FISHING_ROD,TRIDENT,WEARABLE,WEAPON,VANISHABLE));


    @Override
    public ItemStack process(ItemStack baseStack, LootContext context) {
        HashSet<HashSet<String>> applicableCategories = new HashSet<>();
        for(HashSet<String> category : allCategories){
            if(category.contains(baseStack.getItem().getName().toUpperCase())){
                applicableCategories.add(category);
            }
        }
        List<Enchantment> applicableEnchantments = new ArrayList<>();
        for(Enchantment currentEnchantment : EnchantmentRegistry){
            if(applicableCategories.contains(currentEnchantment.getCategory())){
                applicableEnchantments.add(currentEnchantment);
            }
        }
        int enchantNr = context.nextInt(applicableEnchantments.size());
        Enchantment enchantment = applicableEnchantments.get(enchantNr);
        int level = 1;
        if(!(SingleEnchants.contains(enchantment.getName()))){
            level = context.nextInt(enchantment.getMaxLevel())+1;
        }
        baseStack.getItem().setEnchantment(enchantment.getName());
        baseStack.getItem().setLevel(level);
        return baseStack;
    }
}
