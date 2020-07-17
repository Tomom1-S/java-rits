package ch17.ex04;

import ch17.ex03.Resource;
import ch17.ex03.ResourceImpl;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {
    final ReferenceQueue<Object> queue;
    final Map<Reference<?>, Resource> refs;
    final Thread reaper;
    boolean shutdown = false;

    public ResourceManager() {
        queue = new ReferenceQueue<>();
        refs = new HashMap<>();
        reaper = new ReaperThread();
        reaper.start();

        // ... リソースの初期化 ...
    }

    public synchronized void shutdown() {
        if(!shutdown) {
            shutdown = true;
            reaper.interrupt();
        }
    }

    public synchronized Resource getResource(Object key) {
        if (shutdown) {
            throw new IllegalStateException();
        }

        Resource res = new ResourceImpl(key);
        Reference<?> ref = new PhantomReference<Object>(key, queue);
        refs.put(ref, res);
        return res;
    }

    public class ReaperThread extends Thread {
        public void run() {
            Reference<?> ref = null;
            Resource res = null;
            while (true) {
                try {
                    // 柴田さん：remove するときの条件が必要
                    // shutdown が呼ばれていない、など複数条件が必要
                    ref = queue.remove();
                    synchronized (ResourceManager.this) {
                        res = refs.get(ref);
                        refs.remove(ref);
                    }
                    res.release();
                    ref.clear();
                } catch (InterruptedException e) {
                    res.release();
                    ref.clear();
                    break;  // すべて終了
                }
            }
        }
    }
}
