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
package com.zodiac.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Brian Estrada <brianseg014@gmail.com>
 */
public class ZodiacConfigurator {
    
    private static Map<String,Object> data;
    
    private static ZodiacConfigurator INSTANCE;
    
    private ZodiacConfigurator(){
        data = new HashMap();
        xml("zodiac.xml");
    }
    
    public final void xml(String filename){
        try {
            File stocks = new File(filename);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(stocks);
            document.getDocumentElement().normalize();
            
            NodeList nodeList = document.getElementsByTagName("zodiac");
            if(nodeList.getLength() > 1){
                throw new ParserConfigurationException("zodiac should appear once  in "+filename+".");
            }
            
            Element zodiac = (Element)nodeList.item(0);
            
            //zodiac soa
            nodeList = zodiac.getElementsByTagName("soa");
            if(nodeList.getLength() > 1){
                throw new ParserConfigurationException("soa should appear once in "+filename+".");
            }
            Element soa = (Element)nodeList.item(0);
            
            //zodiac soa auditors
            nodeList = soa.getElementsByTagName("auditors");
            if(nodeList.getLength() > 1){
                throw new ParserConfigurationException("auditors should appear once in "+filename+".");
            }
            Element auditors = (Element)nodeList.item(0);
            
            //zodiac soa auditors auditor
            nodeList = auditors.getElementsByTagName("auditor");
            int auditorCount = nodeList.getLength();
            if(auditorCount > 0){
                List<String> auditorList = new ArrayList();
                for(int i = 0; i < auditorCount; i++){
                    Node auditor = nodeList.item(i);
                    auditorList.add(auditor.getNodeValue());
                }
                data.put(AUDITORS, auditorList);
            }
            
            //zodiac soa only-allowed-application
            nodeList = soa.getElementsByTagName("only-allowed-application");
            int countOnlyAllowedApp = nodeList.getLength();
            String only_allowed_application = "false";
            if(countOnlyAllowedApp <= 1) {
                Element _only_allowed_application = (Element)nodeList.item(0);
                String only_allowed_application_value = _only_allowed_application.getNodeValue();
                if (only_allowed_application_value.equals("true") ||
                        only_allowed_application_value.equals(("false"))){
                    only_allowed_application = only_allowed_application_value;
                } else {
                    throw new ParserConfigurationException("only-allowed-application allowed values true/false in "+filename+".");
                }
            } else if(countOnlyAllowedApp > 1) {
                throw new ParserConfigurationException("only-allowed-application should appear once in "+filename+".");
            }
            data.put(ONLY_ALLOWED_APPLICATION, Boolean.parseBoolean(only_allowed_application));
            
            //zodiac soa application-id
            nodeList = soa.getElementsByTagName("application-id");
            int applicationIdCount = nodeList.getLength();
            if(applicationIdCount <= 1){
                Element application_id = (Element)nodeList.item(0);
                data.put(APPLICATION_ID, application_id.getNodeValue());
            } else {
                throw new ParserConfigurationException("application-id should appear once "+filename+".");
            }
            
            //zodiac soa applications
            nodeList = soa.getElementsByTagName("applications");
            if(nodeList.getLength() > 1){
                throw new ParserConfigurationException("applications should appear once in "+filename+".");
            }
            Element applications = (Element)nodeList.item(0);
            
            //zodiac soa applications application
            nodeList = applications.getElementsByTagName("application");
            int applicationCount = nodeList.getLength();
            if(applicationCount > 0){
                List<String> applicationList = new ArrayList();
                for(int i = 0; i < applicationCount; i++){
                    Node application = nodeList.item(i);
                    applicationList.add(application.getNodeValue());
                }
                data.put(APPLICATIONS, applicationList);
            }
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(ZodiacConfigurator.class.getName()).log(Level.ERROR, null, ex);
        }
        
    }
    
    public Object get(String key){
        return data.get(key);
    }
    
    public static ZodiacConfigurator getInstance(){
        if(INSTANCE == null){
            synchronized(ZodiacConfigurator.class){
                INSTANCE = new ZodiacConfigurator();
            }
        }
        return INSTANCE;
    }
    
    public static final String AUDITORS = "auditors";
    
    public static final String APPLICATIONS = "applications";
    
    public static final String ONLY_ALLOWED_APPLICATION = "only-allowed-application";
    
    public static final String APPLICATION_ID = "application-id";
    
}
