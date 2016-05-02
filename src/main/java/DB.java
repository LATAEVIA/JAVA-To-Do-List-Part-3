import org.sql2o.*;

public class DB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do", null, null);
  //"jdbc:postgresql://localhost:5432/to_do" is a URL that Java can use to access the database.
  //jdbc:postgresql driver we installed to access the Postgres serve
  //look @ localhost 4 server,  access over port 5432, to_do database
  //null,null = username and password
  //Now, when we want to access our database we can call DB.sql2o from anywhere in our application.
}
