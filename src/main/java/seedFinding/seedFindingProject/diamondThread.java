package seedFinding.seedFindingProject;


import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.EndCity;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

import java.util.List;

import static seedFinding.GetDiamondCountFromEndCityChest.checkEndCityStructure;


public class diamondThread implements Runnable{
    private final int offset;
    private final int totalThreads;
    private final int startingPoint = 200000000;
    public diamondThread(int offset, int totalThreads){
        this.offset = offset;
        this.totalThreads = totalThreads;
    }
    public void run() {
        //System.out.println("Started WorldSeedThread "+(offset+1)+"/"+totalThreads);
        ChunkRand chunkRand = new ChunkRand();
        EndCity endCity = new EndCity(MCVersion.v1_16_1);
        LootContext a1 = new LootContext(30);
        //RuinedPortal ruinedPortal = new RuinedPortal(Dimension.OVERWORLD,MCVersion.v1_14_4);
        for (long structureSeed = startingPoint + offset; structureSeed < 1L << 48; structureSeed+=this.totalThreads) {

            CPos endCityPosition = endCity.getInRegion(structureSeed,3,3,chunkRand);

            chunkRand.setDecoratorSeed(structureSeed, endCityPosition.getX()<<4,endCityPosition.getZ()<<4, 40010, MCVersion.v1_16_1);
            //I have no clue how many calls you have to skip for this, might have to explore more
            chunkRand.advance(4);
            a1.setSeed(chunkRand.nextLong());
            List<ItemStack> ItemList = MCLootTables.END_CITY_TREASURE_CHEST.generate(a1);
            int diamondsThisStack = 0;
            for (ItemStack item2 : ItemList) {
                if (item2.getItem().getName().contains("diamond")) {
                    diamondsThisStack += item2.getCount();
                }
            }
            a1.setSeed(chunkRand.nextLong());
            ItemList = MCLootTables.END_CITY_TREASURE_CHEST.generate(a1);
            for (ItemStack item2 : ItemList) {
                if (item2.getItem().getName().contains("diamond")) {
                    diamondsThisStack += item2.getCount();
                }
            }
            if(diamondsThisStack>30){
                if(checkEndCityStructure(structureSeed,3,3,chunkRand)) {
                    System.out.println("found structureseed: " + structureSeed +", "+endCityPosition.toBlockPos());
                }
            }
        }
    }
}


