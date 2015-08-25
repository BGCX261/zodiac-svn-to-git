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

import com.zodiac.util.AbstractDTO;

/**
 *
 * @author brian
 */
public class DTOExample extends AbstractDTO {
    
    private String param1;
    
    public String param2;
    
    protected String param3;
    
    public DTOExample(){
        param1 = param1();
        param2 = param2();
        param3 = param3();
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

    public String getParam3() {
        return param3;
    }
    
    public static String param1(){
        return "param1";
    }
    
    public static String param2(){
        return "param2";
    }
    
    public static String param3(){
        return "param3";
    }
    
    public static String xmlSerialized(){
        return "<com.zodiac.util.DTOExample>\n" +
                            "  <param1>param1</param1>\n" +
                            "  <param2>param2</param2>\n" +
                            "  <param3>param3</param3>\n" +
                            "</com.zodiac.util.DTOExample>";
    }
    
}
