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

import java.util.Iterator;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author Brian Estrada <brianseg014@gmail.com>
 */
public class ConnectionParameterTest {
    
    /**
     * Test of constructor to set all parameters
     */
    @Test
    public void testConstructor2(){
        System.out.println("ConnectionParameter(String host, String port, String database, "
                + "String user, String password,String driver, String dbms)");
        String expHost = "host";
        String expPort = "port";
        String expDatabase = "database";
        String expUser = "user";
        String expPassword = "pass";
        String expDriver = "driver";
        String expDBMS = "dbms";
        
        ConnectionParameter instance = new ConnectionParameter(
                expHost, expPort, expDatabase, expUser, expPassword, expDriver, expDBMS);
        String resultHost = instance.getHost();
        String resultPort = instance.getPort();
        String resultDatabase = instance.getDatabase();
        String resultUser = instance.getUser();
        String resultPassword = instance.getPassword();
        String resultDriver = instance.getDriver();
        String resultDBMS = instance.getDBMS();
        
        assertTrue(resultHost.equals(expHost));
        assertTrue(resultPort.equals(expPort));
        assertTrue(resultDatabase.equals(expDatabase));
        assertTrue(resultUser.equals(expUser));
        assertTrue(resultPassword.equals(expPassword));
        assertTrue(resultDriver.equals(expDriver));
        assertTrue(resultDBMS.equals(expDBMS));
        
    }

    /**
     * Test of getHost method, of class ConnectionParameter.
     */
    public void testGetHost() {
        System.out.println("getHost");
        ConnectionParameter instance = new ConnectionParameter();
        String expResult = "host";
        instance.setHost(expResult);
        String result = instance.getHost();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of setHost method, of class ConnectionParameter.
     */
    public void testSetHost() {
        System.out.println("setHost");
        String host = "host";
        ConnectionParameter instance = new ConnectionParameter();
        instance.setHost(host);
    }

    /**
     * Test of getPort method, of class ConnectionParameter.
     */
    public void testGetPort() {
        System.out.println("getPort");
        ConnectionParameter instance = new ConnectionParameter();
        String expResult = "5432";
        instance.setPort(expResult);
        String result = instance.getPort();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of setPort method, of class ConnectionParameter.
     */
    public void testSetPort() {
        System.out.println("setPort");
        String port = "5432";
        ConnectionParameter instance = new ConnectionParameter();
        instance.setPort(port);
    }

    /**
     * Test of getDatabase method, of class ConnectionParameter.
     */
    public void testGetDatabase() {
        System.out.println("getDatabase");
        ConnectionParameter instance = new ConnectionParameter();
        String expResult = "test";
        instance.setDatabase(expResult);
        String result = instance.getDatabase();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of setDatabase method, of class ConnectionParameter.
     */
    public void testSetDatabase() {
        System.out.println("setDatabase");
        String database = "test";
        ConnectionParameter instance = new ConnectionParameter();
        instance.setDatabase(database);
    }

    /**
     * Test of getUser method, of class ConnectionParameter.
     */
    public void testGetUser() {
        System.out.println("getUser");
        ConnectionParameter instance = new ConnectionParameter();
        String expResult = "user";
        instance.setUser(expResult);
        String result = instance.getUser();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of setUser method, of class ConnectionParameter.
     */
    public void testSetUser() {
        System.out.println("setUser");
        String user = "user";
        ConnectionParameter instance = new ConnectionParameter();
        instance.setUser(user);
    }

    /**
     * Test of getPassword method, of class ConnectionParameter.
     */
    public void testGetPassword() {
        System.out.println("getPassword");
        ConnectionParameter instance = new ConnectionParameter();
        String expResult = "abc123";
        instance.setPassword(expResult);
        String result = instance.getPassword();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of setPassword method, of class ConnectionParameter.
     */
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "abc123";
        ConnectionParameter instance = new ConnectionParameter();
        instance.setPassword(password);
    }

    /**
     * Test of getDriver method, of class ConnectionParameter.
     */
    public void testGetDriver() {
        System.out.println("getDriver");
        ConnectionParameter instance = new ConnectionParameter();
        String expResult = "driver";
        instance.setDriver(expResult);
        String result = instance.getDriver();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of setDriver method, of class ConnectionParameter.
     */
    public void testSetDriver() {
        System.out.println("setDriver");
        String driver = "driver";
        ConnectionParameter instance = new ConnectionParameter();
        instance.setDriver(driver);
    }

    /**
     * Test of getDbms method, of class ConnectionParameter.
     */
    public void testGetDbms() {
        System.out.println("getDbms");
        ConnectionParameter instance = new ConnectionParameter();
        String expResult = "dbms";
        instance.setDBMS(expResult);
        String result = instance.getDBMS();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of setDbms method, of class ConnectionParameter.
     */
    public void testSetDbms() {
        System.out.println("setDbms");
        String dbms = "";
        ConnectionParameter instance = new ConnectionParameter();
        instance.setDBMS(dbms);
    }

    /**
     * Test of getArgs method, of class ConnectionParameter.
     */
    public void testGetArgs() {
        System.out.println("getArgs");
        ConnectionParameter instance = new ConnectionParameter();
        String expResult1 = "arg1";
        String expResult2 = "arg2";
        instance.addArgs(expResult1);
        instance.addArgs(expResult2);
        Iterator<String> result = instance.getArgs();
        assertTrue(result.next().equals(expResult1));
        assertTrue(result.next().equals(expResult2));
    }

    /**
     * Test of addArgs method, of class ConnectionParameter.
     */
    public void testAddArgs() {
        System.out.println("addArgs");
        String args = "arg1";
        ConnectionParameter instance = new ConnectionParameter();
        instance.addArgs(args);
    }
}
