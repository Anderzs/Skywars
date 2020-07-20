package dk.legendebente.skywars.packets;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Title extends Reflection {

    private String _title;
    private String _subtitle;
    private int _fadeInTime;
    private int _showTime;
    private int _fadeOutTime;

    public Title(String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime){
        this._title = title;
        this._subtitle = subtitle;
        this._fadeInTime = fadeInTime;
        this._showTime = showTime;
        this._fadeInTime = fadeInTime;
    }

    public Title(){

    }

    public void sendTitle(Player player){
        try {
            Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + this._title + "\"}");
            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle,
                    this._fadeInTime, this._showTime, this._fadeOutTime);

            Object chatsTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + this._subtitle + "\"}");
            Constructor<?> timingTitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object timingPacket = timingTitleConstructor.newInstance(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatsTitle,
                    this._fadeInTime, this._showTime, this._fadeOutTime);

            sendPacket(player, packet);
            sendPacket(player, timingPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(String title){
        this._title = title;
    }

    public void setSubtitle(String subtitle){
        this._subtitle = subtitle;
    }

    public void setFadeInTime(int fadeInTime){
        this._fadeInTime = fadeInTime;
    }

    public void setShowTime(int showTime){
        this._showTime = showTime;
    }

    public void setFadeOutTime(int fadeOutTime){
        this._fadeOutTime = fadeOutTime;
    }

    public void setAllTime(int[] time){
        this._fadeInTime = time[0];
        this._showTime = time[1];
        this._fadeOutTime = time[2];
    }

}
