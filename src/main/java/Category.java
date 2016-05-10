import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Category {
  private int id;
  private String name;

  public Category(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Category> all() {
    String sql = "SELECT id, name FROM Categories";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Category.class);
    }
  }

  @Override
  public boolean equals(Object otherCategory) {
    if (!(otherCategory instanceof Category)) {
      return false;
    } else {
      Category newCategory = (Category) otherCategory;
      return this.getName().equals(newCategory.getName()) &&
             this.getId() == newCategory.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Categories(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Category find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Categories where id=:id";
      Category category = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Category.class);
      return category;
    }
  }

  public void addTask(Task task) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO categories_tasks (category_id, task_id) VALUES (:category_id, :task_id)";
    con.createQuery(sql)
      .addParameter("category_id", this.getId())
      .addParameter("task_id", task.getId())
      .executeUpdate();
    }
  }

  public List<Task> getTasks() {
  try(Connection con = DB.sql2o.open()){
    String joinQuery = "SELECT task_id FROM categories_tasks WHERE category_id = :category_id";
    List<Integer> taskIds = con.createQuery(joinQuery)
      .addParameter("category_id", this.getId())
      .executeAndFetch(Integer.class);
      //We pull the task_ids from the join table wherever the category_id is equal to the Category object we are calling the method on. So for this particular category, all of the task_ids represent the tasks that belong to that category. They are returned as a List object that we casted as List<Integer>.
    List<Task> tasks = new ArrayList<Task>();
    //We need to create an empty List to hold new Task objects.
    for (Integer taskId : taskIds) {
      String taskQuery = "Select * From tasks WHERE id = :taskId";
      Task task = con.createQuery(taskQuery)
        .addParameter("taskId", taskId)
        .executeAndFetchFirst(Task.class);
      tasks.add(task);
    }
    //We then loop through taskIds. For each id, we create a new query that fetches the task with that id and add it to tasks.

    return tasks;
    //return the arry 'tasks'
  }
}

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM categories WHERE id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", this.getId())
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM categories_tasks WHERE category_id = :categoryId";
        con.createQuery(joinDeleteQuery)
          .addParameter("categoryId", this.getId())
          .executeUpdate();
    }
  }

}
