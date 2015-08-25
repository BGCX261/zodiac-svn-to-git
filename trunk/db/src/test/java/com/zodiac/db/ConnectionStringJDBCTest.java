/*
 * Copyright (C) 2012 Zodiac Innovation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zodiac.db;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author brian
 */
public class ConnectionStringJDBCTest {
    

    /**
     * Test of getConnectionString method, of class ConnectionStringJDBC.
     */
    @Test
    public void testGetConnectionString() {
        System.out.println("getConnectionString");
        String host = "192.168.0.2";
        String port = "5432";
        String database = "websols";
        String user = "postgres";
        String password = "websols";
        String driver = "org.postgresql.Driver";
        String dbms = "PostgreSQL";
        ConnectionParameter connectionParameter = new ConnectionParameter(
                host, port, database, user, password, driver, dbms);
        ConnectionString instance = new ConnectionStringJDBC(connectionParameter);
        String expResult = "jdbc:postgresql://192.168.0.2:5432/websols";
        String result = instance.getConnectionString();
        assertEquals(expResult, result);
    }
}
