package seedFinding;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.featureutils.structure.JunglePyramid;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

public class Main3 {
    public static void main(String[] args) {
        JunglePyramid dp = new JunglePyramid(MCVersion.v1_16_1);
        ChunkRand rand = new ChunkRand();
        long structureSeed = 24110655973188L;
        long worldSeed = 7948314503011477316L;
        //for(int x=-10;x<0;x++) {
        //    for (int z = 0; z < 3; z++) {
                CPos templePosition = dp.getInRegion(structureSeed, -10, 0, new ChunkRand());
                OverworldBiomeSource overworldBiomeSource =
                    new OverworldBiomeSource(MCVersion.v1_16_1, worldSeed);
                Biome ourBiome = overworldBiomeSource
                    .getBiome(templePosition.getX() << 4, 0, templePosition.getZ() << 4);;
                if (dp.canSpawn(templePosition.getX(), templePosition.getZ(), overworldBiomeSource)){
                    System.out.println(
                        worldSeed + ", " + ourBiome.getName() + ", " +
                            templePosition.toBlockPos());
                    HashSet<Integer> checked = new HashSet<>(Arrays.asList(3,49,111,431,4718,5431,20442,34951,44711,45561,47374,62872));
                    for(int i=62872; i<3000000; i++) {
                        if(checked.contains(i)) continue;
                        rand.setDecoratorSeed(worldSeed, templePosition.getX() << 4,
                            templePosition.getZ() << 4, 40002, MCVersion.v1_16_1);
                        rand.advance(i);
                        System.out.println(i);
                        System.out.println("Chest 1:");
                        LootContext a1 = new LootContext(rand.nextLong());
                        List<ItemStack> ItemList = MCLootTables.JUNGLE_TEMPLE_CHEST.generate(a1);
                        boolean doFail = false;
                        for (ItemStack item : ItemList) {
                            if(!(item.getItem().getName().equals("gold_ingot") || item.getItem().getName().equals("rotten_flesh")) || !(item.getCount()<=4)){
                                doFail = true;
                            }
                            if (item.getItem().getEnchantment() == null) {
                                System.out
                                        .println(item.getItem().getName() + " : " + item.getCount());
                            } else {
                                System.out.println(item.getItem().getName() + " enchantment:" +
                                        item.getItem().getEnchantment() + " " +
                                        item.getItem().getLevel());
                            }
                        }
                        if(doFail == false){
                            System.out.println("CHECK THIS ONE: "+i);
                            //System.exit(0);
                        }
                        System.out.println("Chest 2:");
                        LootContext a2 = new LootContext(rand.nextLong());
                        List<ItemStack> ItemList2 = MCLootTables.JUNGLE_TEMPLE_CHEST.generate(a2);
                        doFail = false;
                        for (ItemStack item : ItemList2) {
                            if(!(item.getItem().getName().equals("gold_ingot") || item.getItem().getName().equals("rotten_flesh")) || !(item.getCount()<=2)){
                                doFail = true;
                            }
                            if (item.getItem().getEnchantment() == null) {
                                System.out
                                        .println(item.getItem().getName() + " : " + item.getCount());
                            } else {
                                System.out.println(item.getItem().getName() + " enchantment:" +
                                        item.getItem().getEnchantment() + " " +
                                        item.getItem().getLevel());
                            }
                        }
                        if(doFail == false){
                            System.out.println("CHECK THIS ONE: "+i);
                            System.exit(0);
                        }
                  }
              }
           // }
       // }
    }
}
