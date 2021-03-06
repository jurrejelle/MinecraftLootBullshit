package kaptainwutax.featureutils.misc;

import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.featureutils.Feature;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.Dimension;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.VersionMap;

public class SlimeChunk extends Feature<SlimeChunk.Config, SlimeChunk.Data> {

	public static final VersionMap<SlimeChunk.Config> CONFIGS = new VersionMap<SlimeChunk.Config>()
			.add(MCVersion.v1_8, new SlimeChunk.Config(10));

	public SlimeChunk(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public SlimeChunk(Config config, MCVersion version) {
		super(config, version);
	}

	@Override
	public String getName() {
		return "slime_chunk";
	}

	public int getRarity() {
		return this.getConfig().rarity;
	}

	@Override
	public boolean canStart(SlimeChunk.Data data, long structureSeed, ChunkRand rand) {
		rand.setSlimeSeed(structureSeed, data.chunkX, data.chunkZ, this.getVersion());
		return (rand.nextInt(this.getRarity()) == 0) == data.isSlime;
	}

	@Override
	public boolean canSpawn(SlimeChunk.Data data, BiomeSource source) {
		return true;
	}

	@Override
	public boolean isValidDimension(Dimension dimension) {
		return dimension == Dimension.OVERWORLD;
	}

	public SlimeChunk.Data at(int chunkX, int chunkZ, boolean isSlime) {
		return new SlimeChunk.Data(this, chunkX, chunkZ, isSlime);
	}

	public static class Config extends Feature.Config {
		public final int rarity;

		public Config(int rarity) {
			this.rarity = rarity;
		}
	}

	public static class Data extends Feature.Data<SlimeChunk> {
		public final boolean isSlime;

		public Data(SlimeChunk feature, int chunkX, int chunkZ, boolean isSlime) {
			super(feature, chunkX, chunkZ);
			this.isSlime = isSlime;
		}
	}

}
