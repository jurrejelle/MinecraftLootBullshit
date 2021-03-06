package seedFinding;

import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.HashSet;
import javax.naming.Context;
import kaptainwutax.biomeutils.Biome;
import kaptainwutax.featureutils.loot.function.EnchantRandomlyFunction;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
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


public class RuinedPortalExample {
    public static void main(String[] args) {
        RuinedPortal rp = new RuinedPortal(Dimension.OVERWORLD, MCVersion.v1_16_1);
        ChunkRand rand = new ChunkRand();
        long structureSeed = 24110655973188L;
        long worldSeed = 7948314503011477316L;
        for(int x=-100;x<100;x++) {
            for (int z = -100; z < 100; z++) {
                CPos portalPosition = rp.getInRegion(structureSeed, x, z, rand);
                OverworldBiomeSource overworldBiomeSource =
                    new OverworldBiomeSource(MCVersion.v1_16_1, worldSeed);
                Biome ourBiome = overworldBiomeSource
                    .getBiome(portalPosition.getX() << 4, 0, portalPosition.getZ() << 4);
                RuinedPortalProperties rpProp =
                    new RuinedPortalProperties(structureSeed, portalPosition);
                Integer type = rpProp.getType(rand, ourBiome);
                //System.out.println(
                //    worldSeed + ", " + ourBiome.getName() + ", " + portalPosition.toBlockPos() +
                //        ", ruined_portal_" + (type + 1));
                rand.setDecoratorSeed(worldSeed, portalPosition.getX() << 4,
                    portalPosition.getZ() << 4, 40005, MCVersion.v1_16_1);
                LootContext a1 = new LootContext(rand.nextLong());
                List<ItemStack> ItemList = MCLootTables.RUINED_PORTAL_CHEST.generate(a1);
                for(ItemStack item : ItemList){
                    if(item.getItem().getName()=="golden_axe"){
                        System.out.println(worldSeed + ", " + ourBiome.getName() + ", " + portalPosition.toBlockPos() + ", ruined_portal_" + (type + 1));
                        for (ItemStack item2 : ItemList) {
                            System.out.println(item2.getItem().getName() + " : " + item2.getCount());
                            if(!(item2.getItem().getEnchantment()==null)){
                                System.out.println("Enchantment: "+item2.getItem().getEnchantment() + " : " + item2.getItem().getLevel());
                            }
                        }
                    }
                }
            }
        }
    }
}



