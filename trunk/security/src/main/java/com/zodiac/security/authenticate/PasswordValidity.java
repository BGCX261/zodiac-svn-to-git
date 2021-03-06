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
package com.zodiac.security.authenticate;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Brian Estrada <brianseg014@gmail.com>
 */
public class PasswordValidity implements PasswordRule{
    
    private int days;
    
    public PasswordValidity(int days) {
        setDays(days);
    }

    @Override
    public boolean compliant(Password password, Object ... args) {
        Date establishedDate = password.getEstablishedDate();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(establishedDate);
        calendar.add(Calendar.DATE, days);
        
        long now = new Date().getTime();
        long dueDate = calendar.getTime().getTime();
        
        return dueDate <= now;
    }
    
    private int getDays() {
        return days;
    }

    private void setDays(int days) {
        this.days = days;
    }
    
    
}
