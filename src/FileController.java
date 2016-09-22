public class FileController extends Thread implements ChangeHandler {
    private CustomFile data=new CustomFile();
    public void run(){
        System.out.println("FileController run");
        data.addToListener(this);
        data.load();
        for(;;){
            data.compare();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onChange() {
        data.save();
    }

    public CustomFile getData() {
        return data;
    }
}
