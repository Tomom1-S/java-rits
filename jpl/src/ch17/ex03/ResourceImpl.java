package ch17.ex03;

import java.lang.ref.WeakReference;

public class ResourceImpl implements Resource {
    Object key;
    boolean needsRelease = false;
    WeakReference<Object> keyRef;

    public ResourceImpl(Object key) {
        this.key = key;
        this.keyRef = new WeakReference<>(key);

        // .. 外部リソースの設定
        needsRelease = true;
    }

    @Override
    public void use(Object key, Object... args) {
        Object tmpKey = keyRef.get();
        if (key != tmpKey) {
            throw new IllegalArgumentException("wrong key");
        }

        // ... リソースの使用 ...
    }

    @Override
    public void release() {
        if (needsRelease) {
            needsRelease = false;

            // .. リソースの解放 ...
        }
    }
}
