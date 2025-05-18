/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author josev
 */
public class ConfigLoader {
    Properties prop = new Properties();
    private String user;
    private String password;
    
    public ConfigLoader(){
        try(InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("database/config.properties")) {
            if (input == null) {
                System.err.println("Lo siento, no se pudo encontrar config.properties");
                return;
            }
            prop.load(input);
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
}
