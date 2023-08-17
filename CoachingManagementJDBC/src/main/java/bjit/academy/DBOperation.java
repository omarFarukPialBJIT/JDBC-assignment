package bjit.academy;
import javax.xml.transform.Result;
import java.sql.*;
public class DBOperation {

    Connection conn;
    public void doConnectDB() {
        String connectionStr = "jdbc:mysql://localhost:3306/"+Utils.DB_NAME;
        String username = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(connectionStr,username,password);
            System.out.println("DB connection successful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchStudent(){
        try {
            Statement stmt =conn.createStatement();
            ResultSet result =stmt.executeQuery("SELECT * FROM "+Utils.STUDENT_TABLE_NAME);

            while (result.next()) {
                String stuName = result.getString(Utils.COLUMN_STUDENT_NAME);
                int stuRoll = result.getInt(Utils.COLUMN_STUDENT_ROLL);
                String stuPhone = result.getString(Utils.COLUMN_STUDENT_PHONE);

                System.out.println("Name: "+stuName+" Roll: "+stuRoll+ " Phone: "+stuPhone);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void countStudent(){
        try {
            Statement stmt =conn.createStatement();
            ResultSet result =stmt.executeQuery("SELECT COUNT("+Utils.COLUMN_RESULT_STUDENT_ID+") FROM "+Utils.RESULT_TABLE_NAME);
            result.next();
            System.out.println("Student attended in the exam: "+result.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchResult() {
        try {
            Statement stmt =conn.createStatement();
            ResultSet result =stmt.executeQuery("SELECT * FROM "+Utils.RESULT_TABLE_NAME+
                    " INNER JOIN "+Utils.STUDENT_TABLE_NAME+" ON "+Utils.RESULT_TABLE_NAME+
                    "."+Utils.COLUMN_RESULT_STUDENT_ID+"="+Utils.STUDENT_TABLE_NAME+"."+Utils.COLUMN_STUDENT_ID+
                    " ORDER BY "+Utils.RESULT_TABLE_NAME+ "."+Utils.COLUMN_RESULT_MERIT+" ASC");

            while (result.next()) {
                String studentName = result.getString(Utils.COLUMN_STUDENT_NAME);
                String studentPhone = result.getString(Utils.COLUMN_STUDENT_PHONE);
                double resultMarks = result.getDouble(Utils.COLUMN_RESULT_MARKS);
                int resultMerit = result.getInt(Utils.COLUMN_RESULT_MERIT);



                System.out.println("Name: "+studentName+", Marks: "+ resultMarks + ", Merit: "+ resultMerit
                        + ", Phone: "+studentPhone);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
