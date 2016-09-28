import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomFile {

    private ArrayList<FileItem> data = new ArrayList<>();
    private List<ChangeHandler> listener = new ArrayList<>();
    private IRWFactory rwFactory = ReaderWriterCreator.create("xml");
    private IWriterReader writerReaderFileItem = rwFactory.createWriterReader("sync");
    private Lock lock=new ReentrantLock();

    CustomFile(ArrayList<FileItem> fileItemArrayList) {
        this.data = fileItemArrayList;
    }

    CustomFile() {
    }

    public void load(){
        lock.lock();
        data = writerReaderFileItem.read();
        onChange();
        lock.unlock();
    }
    public void save(){
        lock.lock();
        writerReaderFileItem.write(this);
        lock.unlock();
    }

    public void addToListener(ChangeHandler changeHandler){
        listener.add(changeHandler);
    }

    public void compare(){
        lock.lock();
        ArrayList<FileItem> fileItems = writerReaderFileItem.read();
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

    public boolean equals(CustomFile customFile) {
        ArrayList<FileItem> customFileData = customFile.getData();
        int size = Math.max(customFileData.size(), this.data.size());
        for (int i = 0; i < size; i++) {
            if (!(this.data.get(i)).equals(customFileData.get(i))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<FileItem> getData() {
        return this.data;
    }

    public void setData(ArrayList<FileItem> data) {
        lock.lock();
        if (!this.equals(data)) {
            this.data = data;
            onChange();
        }
        lock.unlock();
    }

    public CustomFile copy() {

        ArrayList<FileItem> copyData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            copyData.add(data.get(i).copy());
        }
        CustomFile customfile = new CustomFile();
        customfile.data = copyData;
        return customfile;
    }
}
