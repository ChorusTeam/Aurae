package net.yeoxuhang.ambiance.client;

import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class AmbianceClient {
    private static final List<ScheduledTask> tasks = new ArrayList<>();
    private static final ReentrantLock lock = new ReentrantLock();

    public static void schedule(Level world, int delayTicks, Consumer<Level> action) {
        lock.lock();
        try {
            tasks.add(new ScheduledTask(world, delayTicks, action));
        } finally {
            lock.unlock();
        }
    }
    public static void onTick() {
        lock.lock();
        try {
            // Iterate in reverse to avoid ConcurrentModificationException
            for (int i = tasks.size() - 1; i >= 0; i--) {
                ScheduledTask task = tasks.get(i);
                task.decrementDelay();
                if (task.isReady()) {
                    task.execute();
                    tasks.remove(i); // Remove the task after execution
                }
            }
        } finally {
            lock.unlock();
        }
    }
    private static class ScheduledTask {
        private final Level world;
        private int delay;
        private final Consumer<Level> action;

        public ScheduledTask(Level world, int delayTicks, Consumer<Level> action) {
            this.world = world;
            this.delay = delayTicks;
            this.action = action;
        }

        public void decrementDelay() {
            if (delay > 0) {
                delay--;
            }
        }

        public boolean isReady() {
            return delay <= 0;
        }

        public void execute() {
            action.accept(world); // Execute the action, passing the world
        }
    }
}
