package config.jdbc;

import jakarta.inject.Singleton;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * @author Lucia
 */

@Singleton
public class ConfigJDBCProperties {

    private Properties p;

    //con injeccion de dependencia esto es private
    private ConfigJDBCProperties() {
        Path p1 = Paths.get("propertiesJDBC/mysql-properties.xml");
        p= new Properties();
        InputStream propertiesStream=null;
        try {
            propertiesStream = Files.newInputStream(p1);
            p.loadFromXML(propertiesStream);
        } catch (IOException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getProperty(String clave) {
        return p.getProperty(clave);
    }

}
