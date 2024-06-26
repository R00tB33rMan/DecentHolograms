package eu.decentsoftware.holograms.api.utils.scheduler;

import eu.decentsoftware.holograms.api.DecentHolograms;
import eu.decentsoftware.holograms.api.DecentHologramsAPI;
import eu.decentsoftware.holograms.api.Settings;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class S {

    private static final DecentHolograms DECENT_HOLOGRAMS = DecentHologramsAPI.get();

    private static final int POOL_SIZE = Settings.getConfig().getInt("pool-size");
    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(POOL_SIZE);

    public static void stopTask(int id) {
        Bukkit.getScheduler().cancelTask(id);
    }

    public static void sync(Runnable runnable) {
        Bukkit.getScheduler().runTask(DECENT_HOLOGRAMS.getPlugin(), runnable);
    }

    public static BukkitTask sync(Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLater(DECENT_HOLOGRAMS.getPlugin(), runnable, delay);
    }

    public static void async(Runnable runnable) {
        EXECUTOR_SERVICE.execute(runnable);
    }

    public static void async(Runnable runnable, long delay) {
        EXECUTOR_SERVICE.schedule(runnable, delay * 50, TimeUnit.MILLISECONDS);
    }

    public static BukkitTask asyncTask(Runnable runnable, long interval, long delay) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(DECENT_HOLOGRAMS.getPlugin(), runnable, delay, interval);
    }

}
