package Insert;

import Mappers.ConnectionImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertAreaCodes {
    Connection conn = ConnectionImpl.getConnection();

    public InsertAreaCodes() throws IllegalAccessException, InstantiationException, ClassNotFoundException {

    }

    public void insert() throws SQLException {
        Statement s = conn.createStatement();
        s.execute("CREATE TABLE areacodes ("
                + "areacode INT, "
                + "postalcode INT, "
                + "PRIMARY KEY (areacode, postalcode))");

        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (1, 6211);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (1, 6212);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (1, 6213);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (1, 6214);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (1, 6215);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (1, 6216);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (2, 6217);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (2, 6218);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (2, 6219);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (2, 6220);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (2, 6221);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (2, 6222);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (3, 6223);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (3, 6224);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (3, 6225);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (3, 6226);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (3, 6228);");
        s.execute("INSERT INTO areacodes (areacode, postalcode) VALUES (3, 6229);");
    }
}
