package seedFinding;

import java.util.HashMap;
import java.util.HashSet;
import javax.naming.Context;
import kaptainwutax.biomeutils.Biome;
import kaptainwutax.featureutils.loot.function.EnchantRandomlyFunction;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.featureutils.structure.RuinedPortal;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.featureutils.loot.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DesertPyramidExample {
    public static void main(String[] args) {
        DesertPyramid dp = new DesertPyramid(MCVersion.v1_16_1);
        long structureSeed = 24110655973188L;
        long worldSeed = 7948314503011477316L;
        for(int x=-100;x<100;x++) {
            for (int z = -100; z < 100; z++) {
                CPos pyramidPosition = dp.getInRegion(structureSeed, x, z, new ChunkRand());
                OverworldBiomeSource overworldBiomeSource =
                    new OverworldBiomeSource(MCVersion.v1_16_1, worldSeed);
                Biome ourBiome = overworldBiomeSource
                    .getBiome(pyramidPosition.getX() << 4, 0, pyramidPosition.getZ() << 4);
                DesertPyramidProperties dpProp =
                    new DesertPyramidProperties(structureSeed, pyramidPosition);
                if (ourBiome.getName() == "desert") {
                    System.out.println(
                        worldSeed + ", " + ourBiome.getName() + ", " +
                            pyramidPosition.toBlockPos());
                    List<ItemStack> ItemList =
                        dpProp.getPyramidLoot(new ChunkRand(), MCVersion.v1_16_1);
                    for (ItemStack item : ItemList) {
                        if (item.getItem().getEnchantment() == null) {
                            System.out.println(item.getItem().getName() + " : " + item.getCount());
                        } else {
                            System.out.println(item.getItem().getName() + " enchantment:" +
                                item.getItem().getEnchantment() + " " + item.getItem().getLevel());
                        }
                    if(item.getItem().getName()=="enchanted_book" && item.getItem().getLevel()==4 && item.getItem().getEnchantment()=="efficiency"){
                        for (ItemStack item2 : ItemList) {
                            System.out.println(item2.getItem().getName() + " : " + item2.getCount());
                        }
                        System.out.println(worldSeed + ", " + ourBiome.getName() + ", " + pyramidPosition.toBlockPos());
                    }
                    }
                }
            }
        }
    }
}



