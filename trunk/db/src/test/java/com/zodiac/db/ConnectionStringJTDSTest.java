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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brian
 */
public class ConnectionStringJTDSTest {
    
    public ConnectionStringJTDSTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConnectionString method, of class ConnectionStringJTDS.
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
        ConnectionString instance = new ConnectionStringJTDS(connectionParameter);
        String expResult = "jdbc:jtds:postgresql://192.168.0.2:5432/websols";
        String result = instance.getConnectionString();
        assertEquals(expResult, result);
    }
}
