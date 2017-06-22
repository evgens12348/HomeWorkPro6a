package ua.kiev.prog;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
            ConnectSql.initDB();
            BaseSql.selected();
    }
}
