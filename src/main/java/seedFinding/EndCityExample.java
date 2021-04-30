package seedFinding;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.EndBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.EndCity;
import kaptainwutax.featureutils.structure.Mansion;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

import java.util.List;

public class EndCityExample {
    public static void main(String[] args) {

        EndCity endCity = new EndCity(MCVersion.v1_16_1);
        ChunkRand rand = new ChunkRand();

        long structureSeed = 24110655973188L;
        long worldSeed = 7948314503011477316L;

        CPos EndCityPosition = endCity.getInRegion(structureSeed, 0, 10, rand);
        EndBiomeSource endBiomeSource =
                new EndBiomeSource(MCVersion.v1_16_1, worldSeed);

        Biome ourBiome = endBiomeSource.getBiome(EndCityPosition.getX()<<4, 0, EndCityPosition.getZ()<<4);

        boolean canSpawn = endCity.canSpawn(EndCityPosition.getX(),EndCityPosition.getZ(),endBiomeSource);

        
        //0,0 OF THE CHUNK THE CHEST SPAWNS IN. To do this, you might have to simulate mansion generation yourself?
        rand.setDecoratorSeed(worldSeed, 352,3648, 40010, MCVersion.v1_16_1);
        //I have no clue how many calls you have to skip for this, might have to explore more
        rand.advance(4);
        for(int j=0;j<2;j++) {
            LootContext a1 = new LootContext(rand.nextLong());

            List<ItemStack> ItemList = MCLootTables.END_CITY_TREASURE_CHEST.generate(a1);
            System.out.println(worldSeed + ", " + ourBiome.getName() + ", " + EndCityPosition.toBlockPos() + ", " + canSpawn);
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