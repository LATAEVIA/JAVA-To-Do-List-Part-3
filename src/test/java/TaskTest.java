import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

    @Before
    public void setUp() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
    }

    @After
    public void tearDown() {
      try(Connection con = DB.sql2o.open()) {
        String deleteTasksQuery = "DELETE FROM tasks *;";
        String deleteCategoriesQuery = "DELETE FROM categories *;";
        con.createQuery(deleteTasksQuery).executeUpdate();
        con.createQuery(deleteCategoriesQuery).executeUpdate();
      }
    }

  @Test
  public void Task_instantiatesCorrectly_true() {
    Task myTask = new Task("Mow the lawn", 1);
    assertEquals(true, myTask instanceof Task);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Task.all().size(), 0);
  }

  @Test
  public void getDescription_taskInstantiatesWithDescription_String() {
    Task myTask = new Task("Mow the lawn", 1);
    assertEquals("Mow the lawn", myTask.getDescription());
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Task firstTask = new Task("Mow the lawn", 1);
    Task secondTask = new Task("Mow the lawn", 1);
    assertTrue(firstTask.equals(secondTask));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    assertTrue(Task.all().get(0).equals(myTask));
  }

  @Test
  public void save_assignsIdToObject() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    Task savedTask = Task.all().get(0);
    //SO if any of our tasks have the same description, they will be assigned the same id in the database?
    //Why doesn't savedTask (as a new object) have a diff unique identifer? besause of override?
    //Will saved task have an index of (1)?
    assertEquals(myTask.getId(), savedTask.getId());
  }

  @Test
  public void find_findsTaskInDatabase_true() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertTrue(myTask.equals(savedTask));
  }

  @Test
    public void save_savesCategoryIdIntoDB_true() {
      Category myCategory = new Category("Household chores");
      myCategory.save();
      Task myTask = new Task("Mow the lawn", myCategory.getId());
      myTask.save();
      Task savedTask = Task.find(myTask.getId());
      assertEquals(savedTask.getCategoryId(), myCategory.getId());
    }
}
// import org.junit.*;
// import static org.junit.Assert.*;
// import java.time.LocalDateTime;
//
//
// public class TaskTest {
//   @After
//   public void tearDown() {
//     Task.clear();
//   }
//
//   @Test
//   public void Task_createNewInstance_true() {
//     Task testTask = new Task("NewInstance");
//     assertTrue(testTask instanceof Task);
//   }
//
//   @Test
//   public void Task_instantiatesWithDescription_String() {
//     Task myTask = new Task("Mow the lawn", 1);
//     assertEquals("Mow the lawn", myTask.getDescription());
//   }
//
//   @Test
//   public void isCompleted_isFalseAfterInstantiation_false() {
//     Task myTask = new Task("Mow the lawn", 1);
//     assertEquals(false, myTask.isCompleted());
//   }
//
//   @Test
//   public void getCreatedAt_instantiatesWithCurrentTime_today() {
//     Task myTask = new Task("Mow the lawn", 1);
//     assertEquals(LocalDateTime.now().getDayOfWeek(), myTask.getCreatedAt().getDayOfWeek());
//   }
//
//   @Test
//   public void all_returnsAllInstancesOfTask_true() {
//     Task firstTask = new Task("Mow the lawn", 1);
//     Task secondTask = new Task("Buy groceries");
//     assertTrue(Task.all().contains(firstTask));
//     assertTrue(Task.all().contains(secondTask));
//   }
//
//   @Test
//   public void clear_emptiesAllTasksFromArrayList_0() {
//     Task myTask = new Task("Mow the lawn", 1);
//     Task.clear();
//     assertEquals(Task.all().size(), 0);
//   }
//
//   @Test
//   public void getId_tasksInstantiateWithAnID_1() {
//     Task myTask = new Task("Mow the lawn", 1);
//     assertEquals(1, myTask.getId());
//   }
//   // @Test
//   // public void getId_tasksInstantiateWithAnID_1() {
//   //   Task.clear();  // THIS TEST WILL FAIL WITHOUT THIS LINE!
//   //   Task myTask = new Task("Mow the lawn", 1);
//   //   Task myTask2 = new Task("Mow the lawn2");
//   //   assertEquals(2, myTask2.getId());
//   // }
//
//   @Test
//   public void find_returnsTaskWithSameId_secondTask() {
//     Task firstTask = new Task("Mow the lawn", 1);
//     Task secondTask = new Task("Buy groceries");
//     assertEquals(Task.find(secondTask.getId()), secondTask);
//   }
//
//   @Test
//   public void find_returnsNullWhenNoTaskFound_null() {
//     assertTrue(Task.find(999) == null);
//   }
//
// }
