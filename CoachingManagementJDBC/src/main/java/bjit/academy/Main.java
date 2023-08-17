package bjit.academy;

public class Main {
    public static void main(String[] args) {
        DBOperation dbOperation = new DBOperation();
        dbOperation.doConnectDB();
        dbOperation.countStudent();
        dbOperation.fetchResult();
    }
}
