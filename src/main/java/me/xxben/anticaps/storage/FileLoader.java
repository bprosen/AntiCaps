package me.xxben.anticaps.storage;

public class FileLoader {

    public static void startUp() {
        FileManager.initializeFile("config");
        FileManager.initializeFile("lang");
    }

    public static void load() {
        FileManager.load("config");
        FileManager.load("lang");
    }

    public static void save() {
        FileManager.save("config");
        FileManager.save("lang");
    }
}