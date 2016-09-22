public class App implements ChangeHandler {
    static private FileController fileController=new FileController();
    static private CustomFile customFile=fileController.getData();

    public static void main(String[] args) {
        App app=new App();
        fileController.start();
        customFile.addToListener(app);
        for(int i=0;i<3;i++) {
            Test test = new Test(fileController, customFile, "Name "+i);
            test.start();
        }
    }

    @Override
    public void onChange() {
        System.out.println(customFile.toPrint());
    }
}
