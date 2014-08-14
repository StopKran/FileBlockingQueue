package com.stopkran.fileBlockingQueue;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class AutomaticFileBlockingQueueDecorator extends FileBlockingQueueDecorator implements AutomaticFileBlockingQueue{

    boolean autosave;
    boolean currentStateSaved;
    Exception lastException;

    public AutomaticFileBlockingQueueDecorator(BlockingQueue decoratedBlockingQueue) {
        super(decoratedBlockingQueue);
        autosave = false;
    }

    public AutomaticFileBlockingQueueDecorator(BlockingQueue decoratedBlockingQueue, File queueFile) {
        super(decoratedBlockingQueue, queueFile);
        autosave = true;
    }

    public AutomaticFileBlockingQueueDecorator(BlockingQueue decoratedBlockingQueue, String queueFile) {
        super(decoratedBlockingQueue, queueFile);
        autosave = true;
    }

    public boolean isCurrentStateSaved() {
        return currentStateSaved;
    }

    public Exception getLastException() {
        return lastException;
    }

    private void save(){
        try {
            this.saveQueue();
            currentStateSaved = true;
        } catch (IOException e) {
            currentStateSaved = false;
            lastException = e;
        }
    }

    public boolean isAutosave() {
        return autosave;
    }

    public void setAutosave(boolean autosave) {
        this.autosave = autosave;
    }

    public boolean add(Object o) {
        boolean tmp = decoratedBlockingQueue.add(o);
        save();
        return tmp;
    }

    public void put(Object o) throws InterruptedException {
        decoratedBlockingQueue.put(o);
        save();
    }

    public int drainTo(Collection collection) {
        int tmp = decoratedBlockingQueue.drainTo(collection);
        save();
        return tmp;
    }

    public int drainTo(Collection collection, int i) {
        int tmp = decoratedBlockingQueue.drainTo(collection, i);
        save();
        return tmp;
    }

    public boolean remove(Object o) {
        boolean tmp = decoratedBlockingQueue.remove(o);
        save();
        return tmp;
    }

    public Object poll(long l, TimeUnit timeUnit) throws InterruptedException {
        Object tmp = decoratedBlockingQueue.poll(l, timeUnit);
        save();
        return tmp;
    }

    public Object take() throws InterruptedException {
        Object tmp = decoratedBlockingQueue.take();
        save();
        return tmp;
    }

    public int remainingCapacity() {
        int tmp = decoratedBlockingQueue.remainingCapacity();
        save();
        return tmp;
    }

    public boolean offer(Object o, long l, TimeUnit timeUnit) throws InterruptedException {
        boolean tmp = decoratedBlockingQueue.offer(o, l, timeUnit);
        save();
        return tmp;
    }

    public boolean offer(Object o) {
        boolean tmp = decoratedBlockingQueue.offer(o);
        save();
        return tmp;
    }

    public Object remove() {
        Object tmp = decoratedBlockingQueue.remove();
        save();
        return tmp;
    }

    public Object poll() {
        Object tmp = decoratedBlockingQueue.poll();
        save();
        return tmp;
    }

    public Object element() {
        Object tmp = decoratedBlockingQueue.element();
        save();
        return tmp;
    }

    public Object peek() {
        Object tmp = decoratedBlockingQueue.peek();
        save();
        return tmp;
    }

    public void clear() {
        decoratedBlockingQueue.clear();
        save();
    }

    public boolean removeAll(Collection collection) {
        boolean tmp = decoratedBlockingQueue.removeAll(collection);
        save();
        return tmp;
    }

    public boolean retainAll(Collection collection) {
        boolean tmp = decoratedBlockingQueue.retainAll(collection);
        save();
        return tmp;
    }

    public boolean addAll(Collection collection) {
        boolean tmp = decoratedBlockingQueue.addAll(collection);
        save();
        return tmp;
    }
}
