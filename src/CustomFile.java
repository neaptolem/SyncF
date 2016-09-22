import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomFile {
    private ArrayList<FileItem> data=new ArrayList<FileItem>();
    private List<ChangeHandler> listener=new ArrayList<ChangeHandler>();
    private ReaderFromFile readerFromFile=new ReaderFromFile("sync.txt");
    private WriterToFile writerToFile=new WriterToFile("sync.txt");
    private Lock lock=new ReentrantLock();


    public void load(){
        lock.lock();
        data=readerFromFile.readFile();
        onChange();
        lock.unlock();
    }
    public void save(){
        lock.lock();
        writerToFile.writeToFile(this);
        lock.unlock();
    }

    public void setData(ArrayList<FileItem> data) {
        lock.lock();
        if(! this.equals(data)){
            this.data=data;
            onChange();
        }
        lock.unlock();
    }

    public void addToListener(ChangeHandler changeHandler){
        listener.add(changeHandler);
    }
    public void compare(){
        lock.lock();
        ArrayList<FileItem> fileItems=readerFromFile.readFile();
        if (! this.equals(fileItems)){
            this.data=fileItems;
            onChange();
        }
        lock.unlock();
    }

    public String toPrint() {
        lock.lock();
        String s = new String();
        for (FileItem item : data) {
            s += item.getData() + "\n" + item.getDate() + "\n";
        }
        lock.unlock();
        return s;
    }
    @Override
    public String toString(){
        lock.lock();
        String s = new String();
        for (FileItem item : data) {
            s += item.toString() + "\n";
        }
        lock.unlock();
        return s;
    }
    private void onChange(){
        for(ChangeHandler item:listener){
            item.onChange();
        }
    }
    private boolean equals(ArrayList<FileItem> fileItems){
        int size = Math.max(fileItems.size(), this.data.size());
        for (int i = 0; i < size; i++) {
            if (! this.data.get(i).equals(fileItems.get(i))) {
                return false;
            }
        }
        return true;
    }
}
