import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
    //changing the DB.sql2o static variable to point to our to_do_test database, Note import org.sql2o.*;
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      //try with resources-useful with objects using two way data stream, ie database connection, will close connection after code in block is executed.

      String sql = "DELETE FROM tasks *;";
      //save SQL stmt into a String w/ String sql = "DELETE FROM tasks *;";. Command will tell database to delete all items from tasks table. then use  Connection to create query with SQL 'con.createQuery(sql)'

      con.createQuery(sql).executeUpdate();
      //On 'con.createQuery(sql)'query we create call executeUpdate(); which passes command to the database.
    }
  }

  @Test
  public void Task_instantiatesCorrectly_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(true, myTask instanceof Task);
  }

  @Test
  public void getDescription_taskInstantiatesWithDescription_String() {
    Task myTask = new Task("Mow the lawn");
    assertEquals("Mow the lawn", myTask.getDescription());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Task.all().size(), 0);
  }




}
