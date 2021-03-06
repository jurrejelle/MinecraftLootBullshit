package seedFinding;

import java.util.List;
import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.BuriedTreasure;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.featureutils.structure.JunglePyramid;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

public class BuriedTreasureExample {
    public static void main(String[] args) {
        BuriedTreasure dp = new BuriedTreasure(MCVersion.v1_16_1);
        ChunkRand rand = new ChunkRand();
        long structureSeed = 24110655973188L;
        long worldSeed = 7948314503011477316L;
        for (int x = -100; x < 100; x++) {
            for (int z = -100; z < 100; z++) {

                CPos templePosition = dp.getInRegion(structureSeed, x, z, rand);
                if (templePosition == null) continue;

                OverworldBiomeSource overworldBiomeSource =
                        new OverworldBiomeSource(MCVersion.v1_16_1, worldSeed);
                Biome ourBiome = overworldBiomeSource
                        .getBiome(templePosition.getX() << 4, 0, templePosition.getZ() << 4);
                ;
                if (dp.canSpawn(templePosition.getX(), templePosition.getZ(), overworldBiomeSource)) {
                    System.out.println(
                            worldSeed + ", " + ourBiome.getName() + ", " +
                                    templePosition.toBlockPos());
                    rand.setDecoratorSeed(worldSeed, templePosition.getX() << 4,
                            templePosition.getZ() << 4, 30001, MCVersion.v1_16_1);
                    LootContext a1 = new LootContext(rand.nextLong());
                    List<ItemStack> ItemList = MCLootTables.BURIED_TREASURE_CHEST.generate(a1);
                    for (ItemStack item : ItemList) {
                        if (item.getItem().getEnchantment() == null) {
                            System.out
                                    .println(item.getItem().getName() + " : " + item.getCount());
                        } else {
                            System.out.println(item.getItem().getName() + " enchantment:" +
                                    item.getItem().getEnchantment() + " " +
                                    item.getItem().getLevel());
                        }
                    }
                }
            }
        }
    }
}

