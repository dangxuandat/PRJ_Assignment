/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle Listener.
 *
 * @author Admin
 */
public class Listener implements ServletContextListener {
    private final Logger LOGGER = Logger.getLogger(Listener.class);
    Map<String,String> roadmap;
    List<String> authList;
    private Map<String,String> readRoadMapFromFile(String path){
        FileReader fr = null; 
        BufferedReader br = null;
        roadmap = new HashMap<>();
        try {
            fr = new FileReader(new File(path));
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!= null){
                int index = line.lastIndexOf("=");
                String functionName = line.substring(0, index);
                String url = line.substring(index + 1);
                roadmap.put(functionName, url);
            }
        } catch (IOException ex) {
            LOGGER.error(ex);
        }finally{
             try {
                if(br != null){
                 br.close();
             }
             
             if(fr != null){
                 fr.close();
             }
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        return roadmap;
    }
    
    
    private List<String> readAuthPageListFromFile(String path){
        FileReader fr = null;
        BufferedReader br = null;
        authList = new ArrayList<>();
        try {
            fr = new FileReader(new File(path));
            br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null){
                authList.add(line);
            }
        } catch (IOException ex) {
            LOGGER.error(ex);
        }finally{
            try {
                if(br != null){
                br.close();
                }
            
                if(fr != null){
                fr.close();
                }
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        return  authList;
    }
    private void loadLog4j(ServletContext context){
        String path = context.getInitParameter("log4j-config-location");
        String realPath = context.getRealPath("/" + path);
        
        //initialize LOG4J
        PropertyConfigurator.configure(realPath);
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String pathRoadMap = context.getInitParameter("roadmap");
        String path = sce.getServletContext().getRealPath(pathRoadMap);
        String authListMap = context.getInitParameter("authList");
        String pathAuthList = sce.getServletContext().getRealPath(authListMap);
        roadmap = readRoadMapFromFile(path);
        authList = readAuthPageListFromFile(pathAuthList);
        context.setAttribute("AUTH_LIST", authList);
        context.setAttribute("ROAD_MAP", roadmap);
        loadLog4j(context);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
