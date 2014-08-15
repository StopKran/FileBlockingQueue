package com.stopkran.fileBlockingQueue;


public interface AutomaticFileBlockingQueue<E> extends FileBlockingQueue<E>  {
    public void setAutosave(boolean autosave);
    public boolean isAutosave();
    public boolean isCurrentStateSaved();
    public Exception getLastException();
}
