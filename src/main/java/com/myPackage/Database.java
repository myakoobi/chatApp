package com.myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
    // Update this if your port, DB, or password is different
private static final String URL = 
    "jdbc:mysql://localhost:3306/chatapp?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "chatuser";
    private static final String PASSWORD = "chatpass"; // Your password, if any

    public static void saveMessage(String msg) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO messages (content) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, msg);
            stmt.executeUpdate();
            System.out.println("✅ Message saved to DB");
        } catch (Exception e) {
            System.err.println("❌ Failed to save message:");
            e.printStackTrace();
        }
        System.out.println("testing done")
    }
}
