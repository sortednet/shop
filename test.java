import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class test {

        public static void main(String[] args) throws Exception {
                File reportFile = new File("test.out");
                BufferedWriter o = new BufferedWriter(new FileWriter(reportFile)) ;
                o.write("\r\n");
                o.flush();
                o.close();
        }

}
