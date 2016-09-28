import java.util.ArrayList;

public interface IWriterReader {
    void write(CustomFile customFile);

    ArrayList<FileItem> read();
}
