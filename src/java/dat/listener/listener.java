/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class listener implements ServletContextListener {
    Map<String,String> roadmap;
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
            br.close();
            fr.close();
        } catch (Exception e) {
        }
        return roadmap;
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String path = sce.getServletContext().getRealPath("/WEB-INF/roadmap.txt");
        roadmap = readRoadMapFromFile(path);
        context.setAttribute("ROAD_MAP", roadmap);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
