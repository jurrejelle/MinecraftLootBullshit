package seedFinding;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class RuinedPortalProperties {
    public static final HashSet<Integer> TYPE_DESERT = new HashSet<>(Arrays.asList(2,17,130));
    public static final HashSet<Integer> TYPE_JUNGLE = new HashSet<>(Arrays.asList(21,22,23,149,151,168,169));
    public static final HashSet<Integer> TYPE_SWAMP = new HashSet<>(Arrays.asList(6,134));
    public static final HashSet<Integer> TYPE_MOUNTAIN = new HashSet<>(Arrays.asList(3, 13, 20, 25, 34, 36, 38, 39, 131, 133, 158, 162, 163, 164, 165, 166, 167));
    public static final HashSet<Integer> TYPE_OCEAN = new HashSet<>(Arrays.asList(0,10,24,44,45,46,47,48,49,50));
    public static final HashSet<Integer> TYPE_NETHER = new HashSet<>(Arrays.asList(8,170,171,172,173));
    public final long structureSeed;
    public final CPos chunkPosition;

    public RuinedPortalProperties(long structureSeed, CPos chunkPosition) {
        this.structureSeed = structureSeed;
        this.chunkPosition = chunkPosition;
    }

    public Integer getType(ChunkRand chunkRand, Biome biome) {
        chunkRand.setCarverSeed(structureSeed, chunkPosition.getX(), chunkPosition.getZ(), MCVersion.v1_16_1);
        if(TYPE_DESERT.contains(biome.getId()) || TYPE_OCEAN.contains(biome.getId()) || TYPE_SWAMP.contains(biome.getId())){
            //Do not advance any calls
        } else if(TYPE_JUNGLE.contains(biome.getId()) || TYPE_NETHER.contains(biome.getId())){
            chunkRand.advance(1);
        } else if(TYPE_MOUNTAIN.contains(biome.getId())) {
            chunkRand.advance(2);
        } else {
            chunkRand.advance(2);
        }
        if (chunkRand.nextFloat() >= 0.05F) {
            return chunkRand.nextInt(10);
        } else {
            return chunkRand.nextInt(3);
        }
    }
/*
    // TODO: add biome groups
    public StructureConfigurations.RuinedPortalVerticalPlacement getHeight(ChunkRand chunkRand, Biome biome) {
        chunkRand.setCarverSeed(structureSeed, chunkPosition.getX(), chunkPosition.getZ(), MCVersion.v1_16_1);
        if (biome.getId() == 2) {
            return StructureConfigurations.RuinedPortalVerticalPlacement.PARTLY_BURIED;
        } else if (biome.getId() == 21) {
            return StructureConfigurations.RuinedPortalVerticalPlacement.ON_LAND_SURFACE;
        } else if (biome.getId() == 6) {
            return StructureConfigurations.RuinedPortalVerticalPlacement.ON_OCEAN_FLOOR;
        } else if (biome.getId() == 3) {
            boolean vp = chunkRand.nextFloat() < 0.5F;
            return vp ? StructureConfigurations.RuinedPortalVerticalPlacement.IN_MOUNTAIN : StructureConfigurations.RuinedPortalVerticalPlacement.ON_LAND_SURFACE;
        } else if (biome.isOcean(biome.getId())) {
            return StructureConfigurations.RuinedPortalVerticalPlacement.ON_OCEAN_FLOOR;
        } else if (biome.getId() == 8) {
            return StructureConfigurations.RuinedPortalVerticalPlacement.IN_NETHER;
        } else {
            boolean vp = chunkRand.nextFloat() < 0.5F;
            return vp ? StructureConfigurations.RuinedPortalVerticalPlacement.UNDERGROUND : StructureConfigurations.RuinedPortalVerticalPlacement.ON_LAND_SURFACE;
        }
    }
*/

}