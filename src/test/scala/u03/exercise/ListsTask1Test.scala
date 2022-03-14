package u03.exercise

import Lists.List.*
import org.junit.*
import org.junit.Assert.*

class ListsTask1Test:
  val lstA = Cons(10, Cons(20, Cons(30, Nil())))
  
  @Test def testDrop(): Unit =
    assertEquals(Cons(20, Cons(30,Nil())), drop(lstA, 1))

  @Test def testDrop2(): Unit =
    assertEquals(Cons(30,Nil()), drop(lstA, 2))

  @Test def testDrop5(): Unit =
    assertEquals(Nil(), drop (lstA ,5))
