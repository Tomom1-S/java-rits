## 練習問題14.8

### 柴田さんコメント
ロックを取る順番を決めておく  
→ lockA を取っていないと lockB を取れない

hug() の中で下のような処理をする

```java
class Friendly {
    int thisHash = this.hashCode();
    int partnerHash = partner.hashCode();
    
    if (thisHash < partnerHash) {
        synchronized(this) {
            synchronized(partner){ /* do something */ }
        }
    } else {
        synchronized(this) {
            synchronized(partner){ /* do something */ }
        }
    }
}
```