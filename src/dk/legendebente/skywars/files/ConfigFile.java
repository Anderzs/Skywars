package dk.legendebente.skywars.files;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

    private File _file;
    private String _fileName;
    private FileConfiguration _configuration;

    public ConfigFile(File file){
        this._file = file;
        this._fileName = file.getName();
        loadConfiguration();
    }

    public File getFile(){
        return this._file;
    }

    public String getFileName(){
        return this._fileName;
    }

    public void setString(String path, String content){
        getConfiguration().set(path, content);
    }

    public String getString(String _path){
        return getConfiguration().getString(_path);
    }

    public void setDouble(String path, double content){
        getConfiguration().set(path, content);
    }

    public double getDouble(String _path){
        return getConfiguration().getDouble(_path);
    }

    public void setInt(String path, int content){
        getConfiguration().set(path, content);
    }

    public int getInt(String _path){
        return getConfiguration().getInt(_path);
    }

    public void saveFile(){
        try {
            getConfiguration().save(_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(){
        try {
            this._file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean fileExists(){
        if(!this._file.exists()){
            return false;
        } else {
            return true;
        }
    }

    public ConfigurationSection getConfigurationSection(String _path){
        return getConfiguration().getConfigurationSection(_path);
    }

    public FileConfiguration getConfiguration(){
        return this._configuration;
    }

    public void loadConfiguration(){
        this._configuration = YamlConfiguration.loadConfiguration(_file);
    }

}
