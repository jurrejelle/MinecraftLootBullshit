package kaptainwutax.featureutils.loot.function;

import java.util.HashSet;

public class Enchantment {
    private String name;
    private Integer rarity;
    private HashSet<String> category;
    private Integer minLevel;
    private Integer maxLevel;

    public Enchantment(String name, Integer rarity, HashSet<String> category, Integer minLevel, Integer maxLevel) {
        this.name = name;
        this.rarity = rarity;
        this.category = category;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public HashSet<String> getCategory() {
        return category;
    }

    public void setCategory(HashSet<String> category) {
        this.category = category;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }
}
