package seedFinding;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.DesertPyramid;
import kaptainwutax.featureutils.structure.Shipwreck;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

import java.util.List;

public class shipwreckExample {
    public static void main(String[] args) {
        Shipwreck shipwreck = new Shipwreck(MCVersion.v1_16_1);
        ChunkRand chunkRand = new ChunkRand();
        long structureSeed = 24110655973188L;
        long worldSeed = 7948314503011477316L;
        int regionX = 9;
        int regionZ = 2;
        CPos shipwreckPos = shipwreck.getInRegion(structureSeed, regionX, regionZ, chunkRand);
        OverworldBiomeSource overworldBiomeSource = new OverworldBiomeSource(MCVersion.v1_16_1, worldSeed);
        Biome ourBiome = overworldBiomeSource.getBiome(shipwreckPos.getX() << 4, 0, shipwreckPos.getZ() << 4);
        if (shipwreck.canSpawn(shipwreckPos.getX(), shipwreckPos.getZ(), overworldBiomeSource)) {
            System.out.println(worldSeed + ", " + ourBiome.getName() + ", " + shipwreckPos.toBlockPos());
            chunkRand.setDecoratorSeed(worldSeed, shipwreckPos.getX() << 4,
                    shipwreckPos.getZ() << 4, 40006, MCVersion.v1_16_1);
            LootContext a1;
            List<ItemStack> ItemList;
            chunkRand.advance(2);
            a1 = new LootContext(chunkRand.nextLong());
            ItemList = MCLootTables.SHIPWRECK_SUPPLY_CHEST.generate(a1);
            for (ItemStack item2 : ItemList) {
                if (item2.getItem().getEnchantment().size() == 0) {
                    System.out.println(item2.getItem().getName() + " : " + item2.getCount());
                } else {
                    System.out.print(item2.getItem().getName() + " : " + item2.getCount());
                    for (int i = 0; i < item2.getItem().getEnchantment().size(); i++) {
                        System.out.println(", " + item2.getItem().getEnchantment().get(i) + " " + item2.getItem().getLevel().get(i));
                    }
                }
            }


            //Second chest is in the next chunk over Z, so .getZ()+1
            chunkRand.setDecoratorSeed(worldSeed, shipwreckPos.getX() << 4,
                    (shipwreckPos.getZ() + 1) << 4, 40006, MCVersion.v1_16_1);

            System.out.println("\n");
            chunkRand.advance(2);
            a1 = new LootContext(chunkRand.nextLong());
            ItemList = MCLootTables.SHIPWRECK_MAP_CHEST.generate(a1);
            for (ItemStack item2 : ItemList) {
                if (item2.getItem().getEnchantment().size() == 0) {
                    System.out.println(item2.getItem().getName() + " : " + item2.getCount());
                } else {
                    System.out.print(item2.getItem().getName() + " : " + item2.getCount());
                    for (int i = 0; i < item2.getItem().getEnchantment().size(); i++) {
                        System.out.println(", " + item2.getItem().getEnchantment().get(i) + " " + item2.getItem().getLevel().get(i));
                    }
                }
            }
        }
    }
}
