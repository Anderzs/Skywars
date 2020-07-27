package dk.legendebente.skywars.files;


import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.ranks.Rank;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class SkywarsFile {

    private File _file;
    private String _fileName;
    private FileConfiguration _config;

    public SkywarsFile(File _file){
        this._file = _file;
        this._fileName = _file.getName();
    }


    public void saveFile(){
        try {
            getConfiguration().save(_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(){
        if(!(this._file == null)){
            try {
                _file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this._file = new File(Skywars.getInstance().getDataFolder(), _fileName);
            try {
                this._file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getString(String path){
        return getConfiguration().getString(path);
    }

    public Object getObject(String path){
        return getConfiguration().get(path);
    }

    public void setObject(String path, Object object){
        getConfiguration().set(path, object);
    }

    public void setString(String path, String content){
        getConfiguration().set(path, content);
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

    public ConfigurationSection getConfigurationSection(String _path){
        return getConfiguration().getConfigurationSection(_path);
    }

    public FileConfiguration getConfiguration(){
        return this._config;
    }

    public void loadConfiguration(){
        this._config = YamlConfiguration.loadConfiguration(_file);
    }

    public boolean fileExists(File _file){
        if (!_file.exists()) {
            return false;
        } else {
            return true;
        }
    }

}
