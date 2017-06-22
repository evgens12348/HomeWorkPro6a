package ua.kiev.prog;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ConnectSql.initDB();
            BaseSql.selected();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
