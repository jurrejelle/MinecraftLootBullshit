package seedFinding;

import kaptainwutax.biomeutils.source.EndBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.function.EnchantWithLevelsFunction;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.structure.EndCity;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;
import kaptainwutax.terrainutils.terrain.EndChunkGenerator;
import kaptainwutax.featureutils.loot.item.ItemStack;

public class GetDiamondCountFromEndCityChest {
    static EndCity END_CITY = new EndCity(MCVersion.v1_16_1);

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
    private static int getHeight(int chunkX, int chunkZ, EndChunkGenerator endChunkGenerator) {
        ChunkRand rand = new ChunkRand(chunkX + chunkZ * 10387313L);
        Rotation rotation = Rotation.randomRotation(rand);
        int x_offset = 5;
        int z_offset = 5;
        if (rotation == Rotation.CLOCKWISE_90) {
            x_offset = -5;
        } else if (rotation == Rotation.CLOCKWISE_180) {
            x_offset = -5;
            z_offset = -5;
        } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
            z_offset = -5;
        }

        int x_center = (chunkX << 4) + 7;
        int z_center = (chunkZ << 4) + 7;
        int NW_corner = endChunkGenerator.getHeightInGround(x_center, z_center);
        int SW_corner = endChunkGenerator.getHeightInGround(x_center, z_center + z_offset);
        int NE_corner = endChunkGenerator.getHeightInGround(x_center + x_offset, z_center);
        int SE_corner = endChunkGenerator.getHeightInGround(x_center + x_offset, z_center + z_offset);
        return Math.min(Math.min(NW_corner, SW_corner), Math.min(NE_corner, SE_corner));
    }
    public static boolean checkEndCityStructure(long structureSeed, int regionX, int regionZ, ChunkRand chunkRand) {
        CPos city1 = END_CITY.getInRegion(structureSeed, regionX, regionZ, chunkRand);
        ChunkRand cityRand = new ChunkRand();

        // rotations used to check for extra .advance() calls for loot
        cityRand.setCarverSeed(structureSeed, city1.getX(), city1.getZ(), MCVersion.v1_16_5);
        Rotation rotation = Rotation.randomRotation(cityRand);

        // Middle three towers below fat tower can be offset by 1 block
        int offset_x = cityRand.nextInt(2);
        int offset_z = cityRand.nextInt(2);

        // tested different rotations and checked how many advances are needed for each
        int calls = 2;
        switch(rotation) {
            case NONE :
                calls = 2;
                break;

            case CLOCKWISE_90 :
                calls = 4;
                break;

            case CLOCKWISE_180 :
                if (offset_z == 0) calls = 4;
                else calls = 2;
                break;

            case COUNTERCLOCKWISE_90 :
                calls = 2;
                break;
        }

        //this is to ensure fat tower spawns -> 2/3 chance for initial fat tower, and then I just check for only one additional regular tower, which gaurantees fat above it
        if (cityRand.nextInt(3) == 0) return false;
        int tower_count = 1 + cityRand.nextInt(3);
        if (tower_count != 1) return false;

        // end biome check
        EndBiomeSource endBiomeSource = new EndBiomeSource(MCVersion.v1_16_5, structureSeed);
        if (!END_CITY.canSpawn(city1.getX(), city1.getZ(), endBiomeSource)) return false;

        // end terrain check
        EndChunkGenerator endChunkGenerator = new EndChunkGenerator(endBiomeSource);
        int height = getHeight(city1.getX(), city1.getZ(), endChunkGenerator);
        if (height < 60) return false;
        return true;
    }
}

