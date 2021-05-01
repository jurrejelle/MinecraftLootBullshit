package seedFinding;

import kaptainwutax.seedutils.mc.ChunkRand;

public enum Rotation {
    NONE,
    CLOCKWISE_90,
    CLOCKWISE_180,
    COUNTERCLOCKWISE_90;

    public static Rotation randomRotation(ChunkRand rand) {
        return values()[rand.nextInt(values().length)];
    }
}