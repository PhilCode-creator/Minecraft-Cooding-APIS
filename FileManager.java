package eu.hylix.bungee.utils;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class FileManager {

    Plugin main;

    public FileManager(Plugin main){

        this.main = main;

    }

    public File createNewFile(String filename, String path){
        File f = new File("plugins/"+path, filename);
        if(!f.exists()){
            try {
                f.createNewFile();
                return f;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public File getFile(String filename, String path){
        return new File("plugins/"+path, filename);
    }
    public boolean exists(String filename, String path){
        return getFile(filename, path).exists();
    }
    public void deleteFile(String filename, String path){
        File f = new File("plugins/"+path, filename);
        if(f.exists()){
            f.delete();
        }
    }
    public Configuration getConfiguration(String filename, String path){
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(getFile(filename, path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void saveFile(File file, Configuration cfg){
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}