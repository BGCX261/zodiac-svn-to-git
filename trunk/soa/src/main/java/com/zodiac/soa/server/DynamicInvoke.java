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

import com.zodiac.security.Session;
import com.zodiac.security.auditory.Audit;
import com.zodiac.security.auditory.AuditAll;
import com.zodiac.security.auditory.AuditEvent;
import com.zodiac.security.auditory.Auditable;
import com.zodiac.security.auditory.Auditor;
import com.zodiac.security.authority.Grant;
import com.zodiac.security.authority.Revoke;
import com.zodiac.security.authority.RevokeAll;
import com.zodiac.soa.InvokeException;
import com.zodiac.soa.Request;
import com.zodiac.soa.SOAException;
import com.zodiac.soa.SessionException;
import com.zodiac.util.ZodiacConfigurator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * A class for dymaical invokes of constructors and methods.
 *
 * @author Brian Estrada <brianseg014@gmail.com>
 */
public class DynamicInvoke {
    
    /**
     * Invoke a Class' method given from a request.
     * 
     * @param request a request
     * @return an object result
     * @throws InvokeException when executes a wrong request
     */
    public static Object invoke(MessageContext messageContext, Request request) 
            throws InvokeException {
        //Verify if the application is allowed
        boolean only_allowed_application = 
                Boolean.parseBoolean(ZodiacConfigurator.getInstance()
                    .get(ZodiacConfigurator.ONLY_ALLOWED_APPLICATION).toString());
        if(only_allowed_application){
            String applicationId = messageContext.get(MessageContext.APPLICATION).toString();
            List<String> applications =
                    (List<String>)ZodiacConfigurator.getInstance()
                        .get(ZodiacConfigurator.APPLICATIONS);
            if(!applications.contains(applicationId)){
                throw new SOAException("The application " + applicationId + 
                        " is not allowed.");
            }
        }
        
        //Verify the count of arguments and the count of parameters to be the same
        if(request.getArgumentsConstructor() != null
                && request.getParametersConstructor() != null) {
            if(request.getArgumentsConstructor().length != 
                    request.getParametersConstructor().length){
                throw new IllegalArgumentException("Request.ArgumentsConstructor and "
                        + "Request.ParametersConstructor must have the same number of parameters.");
            }
        } else {
            if((request.getArgumentsConstructor() != null && request.getParametersConstructor() == null)
                || (request.getArgumentsConstructor() == null && request.getParametersConstructor() != null)){
                throw new IllegalArgumentException("Request.ArgumentsConstructor and "
                        + "Request.ParametersConstructor must be null or be set.");
            }
        }
        
        try{
            HttpServletRequest httpServletRequest = 
                            (HttpServletRequest)messageContext.get(MessageContext.SERVLET_REQUEST);
            
            Class clazz = Class.forName(request.getClazz());
            
            //Validate instance of BusinessLogic to be called from web service
            if(!BusinessLogic.class.isAssignableFrom(clazz)){
                throw new InvokeException(request.getClazz() + " is not instace of com.zodiac.soa.server.BussinessLogic");
            }
            
            //If is a PrivateBusinessLogic, valid that it is in session
            if(PrivateBusinessLogic.class.isAssignableFrom(clazz)){
                Object sessionObject = messageContext.get(MessageContext.SOA_SESSION);
                if(!(sessionObject instanceof Session)){
                    throw new SessionException("Session is not valid.");
                }
            }
            
            //Add the context
            BusinessLogic.setMessageContext(messageContext);
            
            //Create the instance of the class
            Object object;
            if(request.getArgumentsConstructor() != null
                    && request.getArgumentsConstructor().length > 0){
                object = clazz.getConstructor(request.getParametersConstructor())
                        .newInstance(request.getArgumentsConstructor());
            } else {
                object = clazz.newInstance();
            }
            
            //Create the callable method
            Method method = clazz.getMethod(request.getMethod(), request.getParametersMethod());
            
            //Verify if the user can access the method
            boolean deniedAccess = false;
            RevokeAll revokeAll = method.getAnnotation(RevokeAll.class);
            if(revokeAll != null){
                deniedAccess = true;
            }
            
            //Verify if the user should be audited
            boolean auditMethod = false;
            AuditAll auditAll = method.getAnnotation(AuditAll.class);
            if(auditAll != null){
                auditMethod = true;
            }
            
            //Verfy access privilege and auditable by profile
            if(PrivateBusinessLogic.class.isAssignableFrom(clazz)){
                Session session = (Session)messageContext.get(MessageContext.SOA_SESSION);
                Revoke revoke = method.getAnnotation(Revoke.class);
                if(revoke != null){
                    if(!Arrays.asList(revoke.profile()).contains(session.getProfile())){
                        deniedAccess = true;
                    }
                }
                
                Grant grant = method.getAnnotation(Grant.class);
                if(grant != null){
                    if(Arrays.asList(grant.profile()).contains(session.getProfile())){
                        deniedAccess = false;
                    }
                }
                
                Audit audit = method.getAnnotation(Audit.class);
                if(audit != null){
                    if(!Arrays.asList(audit.profile()).contains(session.getProfile())){
                        auditMethod = true;
                    }
                }
            }
            
            //Access denied
            if(deniedAccess){
                throw new SessionException("Access denied.");
            }
            
            //Call auditors
            if(auditMethod){
                List<String> auditors = 
                        (List<String>)ZodiacConfigurator.getInstance().get(ZodiacConfigurator.AUDITORS);
                if(auditors != null){
                    Auditable auditable = method.getAnnotation(Auditable.class);
                    int id = -1;
                    String name = null;
                    String description = null;
                    if(auditable != null){
                        id = auditable.id();
                        name = auditable.name();
                        description = auditable.description();
                    }
                    String remoteAddress = null;
                    if(httpServletRequest != null){
                        remoteAddress = httpServletRequest.getRemoteAddr();
                    }
                    Session session = (Session)messageContext.get(MessageContext.SOA_SESSION);
                    
                    Iterator<String> iterator = auditors.iterator();
                    while(iterator.hasNext()){
                        Class auditorClass = Class.forName(iterator.next());
                        Auditor auditor = (Auditor)auditorClass.newInstance();
                        AuditEvent auditEvent = new AuditEvent(
                                id, 
                                name, 
                                description, 
                                clazz, 
                                method, 
                                request.getArgumentsMethod(), 
                                remoteAddress, 
                                messageContext.get(MessageContext.APPLICATION).toString(), 
                                session, 
                                new Date());
                        auditor.audit(auditEvent);
                    }
                }
            }
            
            return method.invoke(object, request.getArgumentsMethod());
        }
        catch(ClassNotFoundException|InstantiationException|
                NoSuchMethodException|IllegalAccessException|
                InvocationTargetException|SessionException|
                NoSuchFieldException ex){
            throw new InvokeException(ex);
        }

    }
    
}
