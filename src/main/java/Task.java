import java.util.List; //to work with list class
import org.sql2o.*;//to work with database


public class Task {
  private int id;
  private String description;
  private int categoryId;


  public Task(String description, int categoryId) {
    this.description = description;
    this.categoryId = categoryId;
  }
//Getter methods
  public String getDescription() {
    return description;
  }

  public int getId() {
    return id;
    //how are we getting id?
  }

  public int getCategoryId() {
    return categoryId;
    //how does the server know to populate the Task table-categoryId column with the contents of Categories table- id column? is it
  }

  public static List<Task> all() {
    //'list', as opposed to 'arraylist' is generic so no need for property or constructor?
    String sql = "SELECT id, description, categoryId FROM tasks";
    //all information from a row, which for now is id, description from the tasks table.
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Task.class);
      //turn each row of information into a Java object based on the argument. In this case we pass Task.class, which creates the Task objects and stores them into a List<Task> object, which we then return
      //What does .class do exactly??
    }
  }

  @Override
  public boolean equals(Object otherTask) {
    if (!(otherTask instanceof Task)) {
      return false;
    } else {
      Task newTask = (Task) otherTask;
      return this.getDescription().equals(newTask.getDescription()) &&
        this.getId() == newTask.getId();
        //not sure how these last three lines affect firstTask vs secondTask equality
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO tasks(description, categoryId) VALUES (:description, :categoryId)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("description", this.description)
      .addParameter("categoryId", this.categoryId)
      //where did we "add the int categoryId parameter to our save() method" (https://www.learnhowtoprogram.com/java/java-database-basics/to-do-list-with-db-backed-one-to-many)
        //We replace the :description placeholder with this.description using .addParameter("description", this.description)
        .executeUpdate()
        .getKey();
    }
  }

  public static Task find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks where id=:id";
      Task task = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Task.class);
        //return the first item in the collection returned by our database, cast as a Task object
      return task;
    }
  }
}
