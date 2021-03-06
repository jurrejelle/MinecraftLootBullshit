package kaptainwutax.featureutils.structure;

import kaptainwutax.biomeutils.Biome;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class SwampHut extends OldStructure<SwampHut> {

	public static final VersionMap<OldStructure.Config> CONFIGS = new VersionMap<OldStructure.Config>()
			.add(MCVersion.v1_8, new OldStructure.Config(14357617))
			.add(MCVersion.v1_13, new OldStructure.Config(14357620));

	public SwampHut(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public SwampHut(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biome.SWAMP;
	}

}
