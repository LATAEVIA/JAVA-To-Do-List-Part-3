import java.util.List;
import org.sql2o.*;

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
    String sql = "SELECT id, name FROM categories";
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
      String sql = "INSERT INTO categories(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Category find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM categories where id=:id";
      Category category = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Category.class);
      return category;
    }
  }

  public List<Task> getTasks() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks where categoryId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Task.class);
    }
  }
}


// import java.util.ArrayList;
//
// public class Category {
//   private String mName;
//   private static ArrayList<Category> instances = new ArrayList<Category>();
//   private int mId;
//   private ArrayList<Task> mTasks;
//
//   public Category(String name) {
//     mName = name;
//     instances.add(this);
//     mId = instances.size();
//     mTasks = new ArrayList<Task>();
//   }
//
//   public String getName() {
//     return mName;
//   }
//
//   public static ArrayList<Category> all() {
//     return instances;
//   }
//
//   public static void clear() {
//     instances.clear();
//   }
//
//   public int getId() {
//     return mId;
//   }
//
//   public static Category find(int id) {
//     try {
//       return instances.get(id - 1);
//     } catch (IndexOutOfBoundsException e) {
//       return null;
//     }
//   }
//
//   public ArrayList<Task> getTasks() {
//     return mTasks;
//   }
//
//   public void addTask(Task task) {
//     //void = no return, just action
//     mTasks.add(task);
//   }
// }
