package seedFinding;

import java.util.List;
import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.BuriedTreasure;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

public class BuriedTreasureExample {
    public static void main(String[] args) {
        BuriedTreasure buriedTreasure = new BuriedTreasure(MCVersion.v1_16_1);
        ChunkRand chunkRand = new ChunkRand();
        long structureSeed = 24110655973188L;
        long worldSeed = 7948314503011477316L;
        for (int x = 0; x < 20; x++) {
            for (int z = 0; z < 10; z++) {

                CPos buriedTreasurePosition = buriedTreasure.getInRegion(structureSeed, x, z, chunkRand);
                if (buriedTreasurePosition == null) continue;

                OverworldBiomeSource overworldBiomeSource = new OverworldBiomeSource(MCVersion.v1_16_1, worldSeed);
                Biome ourBiome = overworldBiomeSource.getBiome(buriedTreasurePosition.getX() << 4, 0, buriedTreasurePosition.getZ() << 4);

                if (buriedTreasure.canSpawn(buriedTreasurePosition.getX(), buriedTreasurePosition.getZ(), overworldBiomeSource)) {
                    System.out.println(worldSeed + ", " + ourBiome.getName() + ", " + buriedTreasurePosition.toBlockPos());

                    chunkRand.setDecoratorSeed(worldSeed, buriedTreasurePosition.getX() << 4,  buriedTreasurePosition.getZ() << 4, 30001, MCVersion.v1_16_1);
                    LootContext a1 = new LootContext(chunkRand.nextLong());
                    List<ItemStack> ItemList = MCLootTables.BURIED_TREASURE_CHEST.generate(a1);

                    for (ItemStack item : ItemList) {
                        if (item.getItem().getEnchantment().size() == 0) {
                            System.out.println(item.getItem().getName() + " : " + item.getCount());
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

