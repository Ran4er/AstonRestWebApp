package ru.aston.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBUtilTest {

    @Test
    void getConnection() {
        try (Connection connection = DBUtil.getConnection()) {
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            fail("Exception thrown while getting connection: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void closeConnection() {
        try (Connection connection = DBUtil.getConnection()) {
            assertFalse(connection.isClosed());
            DBUtil.closeConnection(connection);
            assertTrue(connection.isClosed());
        } catch (SQLException e) {
            fail("Exception thrown while closing connection: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
