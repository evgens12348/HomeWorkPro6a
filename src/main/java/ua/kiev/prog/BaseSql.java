package ua.kiev.prog;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BaseSql {
    static Scanner sc = new Scanner(System.in);

    public static void selected() {
        while (true) {
            System.out.println("1: add apartment to base");
            System.out.println("2: apartment cleaning by parameters");
            System.out.print("-> ");
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    addApartmentToBase();
                    break;
                case "2":
                    setParameters();
                    break;
                default:
                    return;
            }
        }
    }

    public static void setParameters() {
        try {
            PreparedStatement preparedStatement = ConnectSql.connectDB().prepareStatement(requestGeneration(interview()));
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
                System.out.print(resultSetMetaData.getColumnName(i) + "\t\t");
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    System.out.print(resultSet.getString(i) + "\t\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String interview() {
        System.out.println("Select the fields by which the apartment will be sampled:");
        System.out.println("1: view all: ");
        System.out.println("2: district apartment: ");
        System.out.println("3: address apartment: ");
        System.out.println("4: area apartment: ");
        System.out.println("5: number of rooms apartment: ");
        System.out.println("6: price apartment: ");
        System.out.print("-> ");
        String s = sc.nextLine();
        return s;
    }

    private static String requestGeneration(String s) {
        StringBuilder sb = new StringBuilder();
        char[] p = s.toCharArray();
        Set<String> temp = new HashSet<>();
        for (char c : p) {
            switch (c) {
                case '1':
                    temp.add("all");
                    break;
                case '2':
                    temp.add("district");
                    break;
                case '3':
                    temp.add("address");
                    break;
                case '4':
                    temp.add("area");
                    break;
                case '5':
                    temp.add("number_of_rooms");
                    break;
                case '6':
                    temp.add("price");
                    break;
            }
        }
        String[] temp1 = temp.toArray(new String[temp.size()]);
        for (int i = 0; i < temp1.length; i++) {
            if (temp1[i].equals("all")) return "SELECT * FROM apartments;";
            if (i != temp1.length - 1) sb.append(temp1[i]).append(sortFilter(temp1[i])).append(" AND ");
            else sb.append(temp1[i]).append(sortFilter(temp1[i]));
        }
        return "SELECT * FROM apartments WHERE " + sb.toString() + ";";
    }

    private static String sortFilter (String string){
        System.out.print("Enter search criteria for "+string+": -->");
        String s=sc.nextLine();
        return "='"+s+"' ";


    }

    private static void addApartmentToBase() {
        System.out.print("Enter district apartment: ");
        String district = sc.nextLine();
        System.out.print("Enter address apartment: ");
        String address = sc.nextLine();
        System.out.print("Enter area apartment: ");
        Double area = sc.nextDouble();
        System.out.print("Enter number of rooms apartment: ");
        Byte rooms = sc.nextByte();
        System.out.print("Enter price apartment: ");
        Double price = sc.nextDouble();
        Date date = new Date(System.currentTimeMillis());
        String sql = "INSERT INTO apartments (district, address, area, number_of_rooms, price, date) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = ConnectSql.connectDB().prepareStatement(sql);
            preparedStatement.setString(1, district);
            preparedStatement.setString(2, address);
            preparedStatement.setDouble(3, area);
            preparedStatement.setByte(4, rooms);
            preparedStatement.setDouble(5, price);
            preparedStatement.setDate(6, date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Adding failed.");
        }


    }
}
