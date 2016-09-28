import java.util.ArrayList;

public interface ReaderWriteFileItem {
    ArrayList<FileItem> read();

    void write(CustomFile file);
}
