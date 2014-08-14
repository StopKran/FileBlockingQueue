package com.stopkran.fileBlockingQueue;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class FileBlockingQueueDecorator<E> implements FileBlockingQueue<E>{

    BlockingQueue decoratedBlockingQueue = null;
    File queueFile = null;
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public FileBlockingQueueDecorator(BlockingQueue decoratedBlockingQueue) {
        this.decoratedBlockingQueue = decoratedBlockingQueue;
    }

    public FileBlockingQueueDecorator(BlockingQueue decoratedBlockingQueue, File queueFile) {
        this.decoratedBlockingQueue = decoratedBlockingQueue;
        this.queueFile = queueFile;
    }

    public FileBlockingQueueDecorator(BlockingQueue decoratedBlockingQueue, String queueFile) {
        this.decoratedBlockingQueue = decoratedBlockingQueue;
        this.queueFile = new File(queueFile);
    }

    @Override
    public synchronized void saveQueue() throws IOException {

        if (inputStream != null) {
             inputStream.close();
             inputStream = null;
        }
        outputStream = new ObjectOutputStream(new FileOutputStream(queueFile, false));

        for(Object writeObj : decoratedBlockingQueue){
            outputStream.writeObject(writeObj);
        }
        outputStream.flush();
    }

    @Override
    public synchronized void loadQueue() throws IOException, ClassNotFoundException {
        if (inputStream == null){
            if (outputStream != null) {
                outputStream.close();
                outputStream = null;
            }
            inputStream = new ObjectInputStream(new FileInputStream(queueFile));
        }
        decoratedBlockingQueue.clear();
        while (true){
            try {
                decoratedBlockingQueue.add(inputStream.readObject());
            } catch (IOException e){
                break;
            }
        }
    }

    @Override
    public void setFile(File file) {
        queueFile = file;
    }

    @Override
    public void setFile(String fileName) {
        queueFile = new File(fileName);
    }

    @Override
    public boolean add(Object o) {
        return decoratedBlockingQueue.add(o);
    }

    @Override
    public boolean offer(Object o) {
        return decoratedBlockingQueue.offer(o);
    }

    @Override
    public void put(Object o) throws InterruptedException {
        decoratedBlockingQueue.put(o);
    }

    @Override
    public boolean offer(Object o, long l, TimeUnit timeUnit) throws InterruptedException {
        return decoratedBlockingQueue.offer(o, l, timeUnit);
    }

    @Override
    public Object take() throws InterruptedException {
        return decoratedBlockingQueue.take();
    }

    @Override
    public Object poll(long l, TimeUnit timeUnit) throws InterruptedException {
        return decoratedBlockingQueue.poll(l, timeUnit);
    }

    @Override
    public int remainingCapacity() {
        return decoratedBlockingQueue.remainingCapacity();
    }

    @Override
    public boolean remove(Object o) {
        return decoratedBlockingQueue.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return decoratedBlockingQueue.contains(o);
    }

    @Override
    public int drainTo(Collection collection) {
        return decoratedBlockingQueue.drainTo(collection);
    }

    @Override
    public int drainTo(Collection collection, int i) {
        return decoratedBlockingQueue.drainTo(collection, i);
    }

    @Override
    public Object remove() {
        return decoratedBlockingQueue.remove();
    }

    @Override
    public Object poll() {
        return decoratedBlockingQueue.poll();
    }

    @Override
    public Object element() {
        return decoratedBlockingQueue.element();
    }

    @Override
    public Object peek() {
        return decoratedBlockingQueue.peek();
    }

    @Override
    public int size() {
        return decoratedBlockingQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return decoratedBlockingQueue.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return decoratedBlockingQueue.iterator();
    }

    @Override
    public Object[] toArray() {
        return decoratedBlockingQueue.toArray();
    }

    public Object[] toArray(Object[] objects) {
        return decoratedBlockingQueue.toArray(objects);
    }

    @Override
    public boolean containsAll(Collection collection) {
        return decoratedBlockingQueue.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection collection) {
        return decoratedBlockingQueue.addAll(collection);
    }

    @Override
    public boolean removeAll(Collection collection) {
        return decoratedBlockingQueue.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection collection) {
        return decoratedBlockingQueue.retainAll(collection);
    }

    @Override
    public void clear() {
        decoratedBlockingQueue.clear();
    }

    @Override
    public boolean equals(Object o) {
        return decoratedBlockingQueue.equals(o);
    }

    @Override
    public int hashCode() {
        return decoratedBlockingQueue.hashCode();
    }
}
