package seedFinding;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.enchantment.Enchantments;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;

import java.util.List;


public class DesertPyramidExample {
    public static void main(String[] args) {
        DesertPyramid desertPyramid = new DesertPyramid(MCVersion.v1_16_1);
        ChunkRand chunkRand = new ChunkRand();
        long structureSeed = 24110655973188L;
        long worldSeed = 7948314503011477316L;
        for(int regionX = 0;regionX<10;regionX++) {
            for (int regionZ = 0; regionZ < 10; regionZ++) {
                CPos pyramidPosition = desertPyramid.getInRegion(structureSeed, regionX, regionZ, chunkRand);
                OverworldBiomeSource overworldBiomeSource = new OverworldBiomeSource(MCVersion.v1_16_1, worldSeed);
                Biome ourBiome = overworldBiomeSource.getBiome(pyramidPosition.getX() << 4, 0, pyramidPosition.getZ() << 4);
                DesertPyramidProperties desertPyramidProperties = new DesertPyramidProperties(structureSeed, pyramidPosition);
                if (desertPyramid.canSpawn(pyramidPosition.getX(), pyramidPosition.getZ(), overworldBiomeSource)) {
                    System.out.println(worldSeed + ", " + ourBiome.getName() + ", " + pyramidPosition.toBlockPos());

                    List<ItemStack> ItemList = desertPyramidProperties.getPyramidLoot(chunkRand, MCVersion.v1_16_1);
                    for (ItemStack item : ItemList) {
                        if (item.getItem().getEnchantment().size() == 0) {
                            System.out.println(item.getItem().getName() + " : " + item.getCount());
                        } else {
                            System.out.print(item.getItem().getName() + " : " + item.getCount());
                            for (int i = 0; i < item.getItem().getEnchantment().size(); i++) {
                                System.out.println(", " + item.getItem().getEnchantment().get(i) + " " + item.getItem().getLevel().get(i));
                            }
                        }
                    }
                }
            }
        }
    }
}




