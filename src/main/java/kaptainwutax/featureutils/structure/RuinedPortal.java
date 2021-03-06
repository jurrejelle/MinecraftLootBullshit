package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class RuinedPortal extends UniformStructure<RuinedPortal> {

	public static final VersionMap<RegionStructure.Config> OVERWORLD_CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_16, new RegionStructure.Config(40, 15, 34222645));

	public static final VersionMap<RegionStructure.Config> NETHER_CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_16, new RegionStructure.Config(25, 10, 34222645));

	private final Dimension dimension;

	public RuinedPortal(Dimension dimension, MCVersion version) {
		this(dimension, getConfigs(dimension).getAsOf(version), version);
	}

	public RuinedPortal(Dimension dimension, RegionStructure.Config config, MCVersion version) {
		super(config, version);
		this.dimension = dimension;
	}

	public Dimension getDimension() {
		return this.dimension;
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == this.getDimension();
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome != Biome.THE_VOID && biome.getCategory() != Biome.Category.THE_END;
	}

	public static VersionMap<RegionStructure.Config> getConfigs(Dimension dimension) {
		switch(dimension) {
			case OVERWORLD: return OVERWORLD_CONFIGS;
			case NETHER: return NETHER_CONFIGS;
		}

		return new VersionMap<>();
	}

}
