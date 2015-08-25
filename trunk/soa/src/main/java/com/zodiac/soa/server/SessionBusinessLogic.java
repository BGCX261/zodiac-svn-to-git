/*
 * Copyright (C) 2013 Zodiac Innovation
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
package com.zodiac.soa.server;

import com.zodiac.security.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Brian Estrada <brianseg014@gmail.com>
 */
public abstract class SessionBusinessLogic extends BusinessLogic {
    
    public SessionBusinessLogic() {}
    
    public boolean set(Session session){
        getMessageContext().put(MessageContext.SOA_SESSION, session);
        String sessionId = open();
        if (sessionId != null) {
            session.setSessionID(sessionId);
            return true;
        } else {
            return false;
        }
    }
    
    public String open() {
        HttpServletRequest httpServletRequest = 
                (HttpServletRequest)getMessageContext().get(MessageContext.SERVLET_REQUEST);
        HttpSession httpSession = httpServletRequest.getSession(true);
        return httpServletRequest.getRequestedSessionId();
    }
    
    public boolean destroy() {
        HttpServletRequest httpServletRequest = 
                (HttpServletRequest)getMessageContext().get(MessageContext.SERVLET_REQUEST);
        HttpSession httpSession = httpServletRequest.getSession(false);
        if(httpSession != null){
            httpSession.invalidate();
            return true;
        } else {
            return false;
        }
    }
    
}
