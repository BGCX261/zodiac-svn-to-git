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
package com.zodiac.soa.server;

import com.zodiac.soa.Request;
import com.zodiac.soa.Response;
import com.zodiac.soa.SOAException;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;

/**
 *
 * @author Brina Estrada <brianseg014@gmail.com>
 */
@WebService(endpointInterface="com.zodiac.soa.server.DynamicService",
        serviceName="DynamicService", portName="DynamicPort")
public class DynamicServiceImpl implements DynamicService {
    
    @Resource(name="wsContext")
    private WebServiceContext wsContext;
    
    @PostConstruct
    public void postConstruct(){}
    
    @PreDestroy
    public void preDestroy(){}
    
    
    
    @Override
    public String run(String xml){        
        Response response = null;
        Request request = new Request();
        request.fromXML(xml);
        
        MessageContext messageContext = new MessageContext(wsContext.getMessageContext());
        try{
            
            Map<String,Object> headers = (Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
            String application = headers.get("Zodiac-Application").toString();
            messageContext.put(MessageContext.APPLICATION, application);
            if(application == null) {
                throw new SOAException("Zodiac-Application must be specified in request headers.");
            }
            
            Object result = DynamicInvoke.invoke(messageContext, request);
            response = new Response(result);
        }
        catch(Exception ex){
            if(request.isTextModeException()){
                response = new Response(ex.toString());
            } else {
                response = new Response(ex);
            }
        } finally {
            return response.toXML();
        }
    }
        
}
