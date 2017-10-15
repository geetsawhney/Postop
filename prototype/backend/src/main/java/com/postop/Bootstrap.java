package com.postop;

import com.postop.controller.PostOpController;
import com.postop.model.Patient;
import com.postop.service.PostOpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

import static spark.Spark.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Bootstrap {
    public static final String IP_ADDRESS = "localhost";
    public static final int PORT = 8080;

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) throws Exception {

    	//Check if the database file exists in the current directory. Abort if not
        DataSource dataSource = configureDataSource();
        if (dataSource == null) {
            System.out.printf("Could not find todo.db in the current directory (%s). Terminating\n",
                    Paths.get(".").toAbsolutePath().normalize());
            System.exit(1);
        }
        
      Sql2o db=new Sql2o(dataSource);
      String sql = "SELECT * FROM Patient";
	try (Connection conn = db.open()) {
      List<Patient> todos =  conn.createQuery(sql).executeAndFetch(Patient.class);
      System.out.println(todos.get(1).email);
      }
	
   
        //Specify the IP address and Port at which the server should be run
        ipAddress(IP_ADDRESS);
        port(PORT);

        //Specify the sub-directory from which to serve static resources (like html and css)
        staticFileLocation("/public");

        PostOpService model = new PostOpService();
		new PostOpController(model);
    }

    /**
     * Check if the database file exists in the current directory. If it does
     * create a DataSource instance for the file and return it.
     * @return javax.sql.DataSource corresponding to the todo database
     */
    private static DataSource configureDataSource()  {
        Path postOpPath = Paths.get(".", "prototype.db");
        if ( !(Files.exists(postOpPath) )) {
            try { Files.createFile(postOpPath); }
            catch (java.io.IOException ex) {
                logger.error("Failed to create toto.db file in current directory. Aborting");
            }
        }
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:prototype.db");
        return dataSource;
    }
}

