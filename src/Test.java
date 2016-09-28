import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Test extends Thread {

    private FileController fileController;
    private CustomFile customFile;
    private String name;
    private CustomFile testCustomFile = new CustomFile();

    public Test() {
    }

    public Test(FileController fileController, CustomFile customFile,String name) {
        this.fileController = fileController;
        this.customFile = customFile;
        this.name=name;
    }

    public void run() {
        sleep(2000);
        ArrayList<FileItem> fileItemArrayList;
        ArrayList<String> tests = new ArrayList<>();
        for (int i=0;i<5;i++){
            sleep(2000);
            testCustomFile = customFile.copy();
            fileItemArrayList = generateCustomFile(name + "" + i);
            testCustomFile.setData(fileItemArrayList);
            customFile.setData(fileItemArrayList);
            tests.add(String.valueOf(customFile.equals(testCustomFile)));
        }
        sleep(1000);
        for (String item : tests) {
            System.out.println(item);
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
