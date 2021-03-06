package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class NetherFossil extends UniformStructure<NetherFossil> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
			.add(MCVersion.v1_16, new RegionStructure.Config(2, 1, 14357921));

	public NetherFossil(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public NetherFossil(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.NETHER;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.SOUL_SAND_VALLEY;
	}

}
