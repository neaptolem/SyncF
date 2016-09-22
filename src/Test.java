import java.util.ArrayList;
import java.util.Date;


public class Test extends Thread {
    private FileController fileController;
    private CustomFile customFile;
    private String name;
    public Test() {
    }

    public Test(FileController fileController, CustomFile customFile,String name) {
        this.fileController = fileController;
        this.customFile = customFile;
        this.name=name;
    }

    public void run() {
        for (int i=0;i<5;i++){
            sleep(20);
            customFile.setData(generateCustomFile(name+""+i));
        }
    }

    public ArrayList<FileItem> generateCustomFile(String  s) {
        ArrayList<FileItem> fileItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                fileItems.add(new FileItem(s + " " + i + " " + j, new Date()));
            }
        }
        return fileItems;
    }

    private void sleep(int miliseconds) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
