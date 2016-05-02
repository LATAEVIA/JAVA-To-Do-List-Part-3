import java.util.List;
import org.sql2o.*;

public class Task {
  private int id;
  private String description;
  //no use if preceeding 'm' for memberproperty. allows database to match column with property

  public Task(String description) {
    this.description = description;
    //'this' used to clarify when local and memeber property are same
  }

  public String getDescription() {
    //Method to return info
    return description;
    //can only view local property, cna't edit
  }

  public int getId() {
    return id;
  }

  public static List<Task> all() {
    String sql = "SELECT id, description FROM tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Task.class);
    }
  }
}
