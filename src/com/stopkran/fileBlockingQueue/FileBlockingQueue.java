package com.stopkran.fileBlockingQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;


public interface FileBlockingQueue<E> extends BlockingQueue<E> {
    public void saveQueue() throws FileNotFoundException, IOException;
    public void loadQueue() throws IOException, ClassNotFoundException;
    public void setFile (File file);
    public void setFile (String FileName);
}
