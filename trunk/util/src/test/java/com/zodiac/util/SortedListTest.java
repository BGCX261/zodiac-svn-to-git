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

import java.util.List;
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
public class SortedListTest {
    
    public SortedListTest() {
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
     * Test of add method, of class SortedList.
     */
    @Test
    public void testAdd_GenericType() {
        System.out.println("add");
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Integer i4 = 5;
        Integer i5 = 6;
        
        Integer t1 = 0;
        Integer t2 = 4;
        Integer t3 = 7;
        
        List<Integer> instance = new SortedList();
        boolean expResult = true;
        boolean result;
        result = instance.add(i1);
        assertEquals(expResult, result);
        
        result = instance.add(i2);
        assertEquals(expResult, result);
        
        result = instance.add(i3);
        assertEquals(expResult, result);
        
        result = instance.add(i4);
        assertEquals(expResult, result);
        
        result = instance.add(i5);
        assertEquals(expResult, result);
        
        result = instance.add(t1);
        assertEquals(expResult, result);
        
        result = instance.add(t2);
        assertEquals(expResult, result);
        
        result = instance.add(t3);
        assertEquals(expResult, result);
        
        for(int i = 0; i < instance.size(); i++){
            if(i != instance.get(i)){
                fail("The number on " + i + " does not math.");
            }
        }
    }
    
}
