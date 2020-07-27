package dk.legendebente.skywars.kits;

import org.bukkit.entity.Player;

public abstract class Kit {

    private String _kitName;

    public Kit(String _kitName){
        this._kitName = _kitName;
    }

    public abstract void giveKit(Player player);

}
