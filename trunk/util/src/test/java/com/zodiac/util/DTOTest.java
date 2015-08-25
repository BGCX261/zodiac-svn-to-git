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
package com.zodiac.util;

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
public class DTOTest {
    
    public DTOTest() {
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
     * Test of fromXML method, of class DTO.
     */
    @Test
    public void testFromXML() {
        System.out.println("fromXML");
        
        DTOExample result = new DTOExample();
        result.fromXML(DTOExample.xmlSerialized());
        
        DTOExample expResult = new DTOExample();
        assertEquals(result.getParam1(), expResult.getParam1());
        assertEquals(result.getParam2(), expResult.getParam2());
        assertEquals(result.getParam3(), expResult.getParam3());
    }

    /**
     * Test of toXML method, of class DTO.
     */
    @Test
    public void testToXML() {
        System.out.println("toXML");
        DTO instance = new DTOExample();
        String expResult = DTOExample.xmlSerialized();
        String result = instance.toXML();
        assertEquals(expResult, result);
    }
    
}
