package seedFinding;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.NetherBiomeSource;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.BastionRemnant;
import kaptainwutax.featureutils.structure.Mansion;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

import java.util.List;

public class TreasureBastionExample {
    public static void main(String[] args) {
        BastionRemnant bastionRemnant = new BastionRemnant(MCVersion.v1_16_1);
        ChunkRand rand = new ChunkRand();

        long structureSeed = 24110655973188L;
        long worldSeed = 7948314503011477316L;

        CPos MansionPosition = bastionRemnant.getInRegion(structureSeed, 0, 0, rand);
        NetherBiomeSource netherBiomeSource =
                new NetherBiomeSource(MCVersion.v1_16_1, worldSeed);

        Biome ourBiome = netherBiomeSource.getBiome(MansionPosition.getX()<<4, 0, MansionPosition.getZ()<<4);

        boolean canSpawn = bastionRemnant.canSpawn(MansionPosition.getX(),MansionPosition.getZ(),netherBiomeSource);

        //0,0 OF THE CHUNK THE CHEST SPAWNS IN. To do this, you might have to simulate the bastion generation yourself?
        rand.setDecoratorSeed(worldSeed, 6 << 4, 6 << 4, 40012, MCVersion.v1_16_1);

        LootContext a1 = new LootContext(rand.nextLong());

        List<ItemStack> ItemList = MCLootTables.BASTION_TREASURE_CHEST.generate(a1);
        System.out.println(worldSeed + ", " + ourBiome.getName() + ", " + MansionPosition.toBlockPos()+", "+canSpawn);
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
