package dk.legendebente.skywars.disguise;

import com.mojang.authlib.GameProfile;
import dk.legendebente.skywars.chathandler.ChatHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ChangeName {

    public static void changeName(String name, Player player) {
        try {
            for(Player players : Bukkit.getOnlinePlayers()){
                Method getHandle = players.getClass().getMethod("getHandle", (Class<?>[]) null);
                Object entityPlayer = getHandle.invoke(players);
                Class<?> entityHuman = entityPlayer.getClass().getSuperclass();
                Field bH = entityHuman.getDeclaredField("bH");
                bH.setAccessible(true);
                bH.set(entityPlayer, new GameProfile(players.getUniqueId(), ChatHandler.format(name)));
                players.hidePlayer(players);
                players.showPlayer(players);
            }

            for (Player players : Bukkit.getOnlinePlayers()) {
                players.hidePlayer(player);
                players.showPlayer(player);
                players.setPlayerListName(ChatHandler.format(name));
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
