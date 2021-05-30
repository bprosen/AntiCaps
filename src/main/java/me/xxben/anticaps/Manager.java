package me.xxben.anticaps;

import me.xxben.anticaps.storage.ConfigReader;

public class Manager {

    private int maxLetters;

    public Manager() {
        maxLetters = ConfigReader.getInt("config", "max-capital-letters");
    }

    public void refreshConfig() { maxLetters = ConfigReader.getInt("config", "max-capital-letters"); }

    public int getMaxLetters() { return maxLetters; }
}
