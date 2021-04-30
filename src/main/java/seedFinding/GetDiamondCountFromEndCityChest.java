package seedFinding;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.function.EnchantWithLevelsFunction;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;

public class GetDiamondCountFromEndCityChest {
    public static int getDiamondCount(LootContext lootContext) {
        int num_rolls = lootContext.nextInt(6-2+1) + 2;
        int[] table = new int[]{
                5,1,2,7,
                10,1,4,8,
                15,1,2,7,
                2,1,2,6,
                5,1,1,10,
                3,0,0,0,
                1,0,0,0,
                1,0,0,0,
                1,0,0,0,
                3,2,0,0,
                3,2,1,0,
                3,2,2,0,
                3,2,3,0,
                3,2,4,0,
                3,2,5,0,
                3,2,6,0,
                3,2,7,0,
                3,2,8,0,
                3,2,9,0,
                3,2,10,0,
                3,2,11,0,
                3,2,12,0,
                3,2,13,0};
        String[] enchantmentItemNames = new String[]{"diamond_sword", "diamond_boots", "diamond_chestplate", "diamond_leggings", "diamond_helmet", "diamond_pickaxe", "diamond_shovel", "iron_sword", "iron_boots", "iron_chestplate", "iron_leggings", "iron_helmet", "iron_pickaxe", "iron_shovel"};
        EnchantWithLevelsFunction ewlf = null;
        int itemi, rv, r_class, i_count, enti;
        int diamondCount = 0;
        for(int ri = 0; ri < num_rolls; ri++){
            rv = lootContext.nextInt(85);
            for(itemi = 0; rv > 0; ){
                rv = rv - table[4*itemi];
                if (rv >= 0){
                    itemi++;
                }
            }

            r_class = table[4*itemi + 1];


            if (r_class == 0){
                if(itemi == 8) diamondCount++;
                continue;
            }
            if (r_class == 1){
                i_count = table[4*itemi + 2] + lootContext.nextInt( table[4*itemi + 3]-table[4*itemi + 2] + 1);
                //The case for diamonds
                if (itemi == 0) diamondCount += i_count;
                continue;
            }

            //Only other option is r_class = 2 , so enchantable items

            //We know itemi > 8, because all enchanted items come after eachother after itemi>8
            //So checking this for diamonds is enough
            if(itemi<=15) diamondCount++;

            enti = table[4*itemi + 2];
            String itemName = enchantmentItemNames[enti];
            //Only instantiate it if it needs it, e.g. if it never comes here, it doesn't have to instantiate ewlf either
            if(ewlf==null) ewlf = new EnchantWithLevelsFunction(20, 39, true);

            ewlf.enchantItem(lootContext, new ItemStack(new Item(itemName)), lootContext.nextInt(20)+20, true, true);

        }
        return diamondCount;
    }
}
