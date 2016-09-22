import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class ReaderFromFile {
    private String fileName;

    public ReaderFromFile(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<FileItem> readFile() {
        String line, line1;
        ArrayList<FileItem> file = new ArrayList<>();
        try {
            File readFile = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            if (line != null) {
                line1 = br.readLine();
                file.add(new FileItem(line, new Date(Long.parseLong(line1))));

            } else {
                file.add(new FileItem("0", new Date()));
                return file;
            }
            while ((line = br.readLine()) != null) {
                line1 = br.readLine();
                file.add(new FileItem(line, new Date(Long.parseLong(line1))));
            }

            return file;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
