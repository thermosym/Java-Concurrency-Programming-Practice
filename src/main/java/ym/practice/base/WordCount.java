package ym.practice.base;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;


public class WordCount {
			
    private final Map<String, AtomicInteger> m;
    
    public WordCount() {
        this(1);
    }
    
    public WordCount(int parLevel) {
        this.m = (parLevel == 1) ? new HashMap<String, AtomicInteger>() : 
            new ConcurrentHashMap<String, AtomicInteger>(4096, 0.75f, parLevel);
    }
    
    public int getSize() {
        return m.size();
    }
    
    public WordCount add(String word, int count) {
        AtomicInteger cc = m.get(word);
        if (cc != null) {
            cc.addAndGet(count);
        } else {
            if (m instanceof ConcurrentMap) {
                cc = ((ConcurrentMap<String, AtomicInteger>) m).putIfAbsent(word, new AtomicInteger(count));
                // Another thread might have added the same value in the meantime
                if (cc != null) {
                    cc.addAndGet(count);
                }
            } else {
                m.put(word, new AtomicInteger(count));
            }
        }
        return this;
    }

    public WordCount add(WordCount wc) {
        for (Map.Entry<String, AtomicInteger> e : wc.m.entrySet()) {
            add(e.getKey(), e.getValue().get());
        }
        return this;
    }
    
    public void set(String word, int count) {
        AtomicInteger cc = m.get(word);
        if (cc != null) {
            cc.set(count);
        } else {
            if (m instanceof ConcurrentMap) {
                cc = ((ConcurrentMap<String, AtomicInteger>) m).putIfAbsent(word, new AtomicInteger(count));
                // Another thread might have added the same value in the meantime
                if (cc != null) {
                    cc.set(count);
                }
            } else {
                m.put(word, new AtomicInteger(count));
            }
        }
    }
    
    public void forEachInRange(int lo, int hi, BiConsumer<String, Integer> block) {
        Iterator<Map.Entry<String, AtomicInteger>> it = m.entrySet().iterator();
        for (int i = 0; i < lo; i++) {
            it.next();
        }
        for (int i = lo; i < hi; i++) {
            Map.Entry<String, AtomicInteger> e = it.next();
            block.accept(e.getKey(), e.getValue().get());
        }
    }

    public void print(PrintStream ps) {
        for (Entry<String, AtomicInteger> e : m.entrySet()) {
            ps.printf("%20s: %d\n",  e.getKey(), e.getValue());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WordCount)) {
            return false;
        }
        WordCount wc = (WordCount) o;
        if (m.size() != wc.m.size()) {
            return false;
        }
        for (Entry<String, AtomicInteger> e : wc.m.entrySet()) {
            if (m.get(e.getKey()).get() != e.getValue().get()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(m);
    }
    
    Set<Entry<String, AtomicInteger>> getEntries() {
        return m.entrySet();
    }

}
