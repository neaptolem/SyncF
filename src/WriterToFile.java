import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriterToFile {
    private String fileName;

    public WriterToFile(String fileName) {
        this.fileName = fileName;
    }

    public void writeToFile(CustomFile file) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
