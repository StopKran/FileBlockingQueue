package com.stopkran.fileBlockingQueue;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class AutomaticFileBlockingQueueDecorator<E> extends FileBlockingQueueDecorator<E> implements AutomaticFileBlockingQueue<E>{

    boolean autosave;
    boolean currentStateSaved;
    Exception lastException;

    public AutomaticFileBlockingQueueDecorator(BlockingQueue<E> decoratedBlockingQueue) {
        super(decoratedBlockingQueue);
        autosave = false;
    }

    public AutomaticFileBlockingQueueDecorator(BlockingQueue<E> decoratedBlockingQueue, File queueFile) {
        super(decoratedBlockingQueue, queueFile);
        autosave = true;
        if (this.queueFile.exists()){
            try {
                this.loadQueue();
            } catch (Exception e) {}
        }
    }

    public AutomaticFileBlockingQueueDecorator(BlockingQueue<E> decoratedBlockingQueue, String queueFile) {
        super(decoratedBlockingQueue, queueFile);
        autosave = true;
        if (this.queueFile.exists()){
            try {
                this.loadQueue();
            } catch (Exception e) {}
        }
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

    public boolean add(E o) {
        boolean tmp = decoratedBlockingQueue.add(o);
        save();
        return tmp;
    }

    public void put(E o) throws InterruptedException {
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

    public E poll(long l, TimeUnit timeUnit) throws InterruptedException {
        E tmp = decoratedBlockingQueue.poll(l, timeUnit);
        save();
        return tmp;
    }

    public E take() throws InterruptedException {
        E tmp = decoratedBlockingQueue.take();
        save();
        return tmp;
    }

    public int remainingCapacity() {
        int tmp = decoratedBlockingQueue.remainingCapacity();
        save();
        return tmp;
    }

    public boolean offer(E o, long l, TimeUnit timeUnit) throws InterruptedException {
        boolean tmp = decoratedBlockingQueue.offer(o, l, timeUnit);
        save();
        return tmp;
    }

    public boolean offer(E o) {
        boolean tmp = decoratedBlockingQueue.offer(o);
        save();
        return tmp;
    }

    public E remove() {
        E tmp = decoratedBlockingQueue.remove();
        save();
        return tmp;
    }

    public E poll() {
        E tmp = decoratedBlockingQueue.poll();
        save();
        return tmp;
    }

    public E element() {
        E tmp = decoratedBlockingQueue.element();
        save();
        return tmp;
    }

    public E peek() {
        E tmp = decoratedBlockingQueue.peek();
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
