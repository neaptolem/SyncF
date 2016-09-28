public class ReaderWriterCreator {
    public static ReaderWriteFileItem create(String name, String type) {
        switch (type) {
            case "txt":
                return new TextFileItemsRW(name + "." + type);
            case "xml":
                return new XMLFileItemsRW(name + "." + type);
            default:
                throw new RuntimeException("Unsuported type of file: " + type);
        }
    }
}
