package u03.exercise

import Lists.List.*
import org.junit.*
import org.junit.Assert.*

class ListsTask1Test:
  val lst = Cons(10, Cons(20, Cons(30, Nil())))

  @Test def testDrop(): Unit =
    assertEquals(Cons(20, Cons(30,Nil())), drop(lst, 1))

  @Test def testDrop2(): Unit =
    assertEquals(Cons(30,Nil()), drop(lst, 2))

  @Test def testDrop5(): Unit =
    assertEquals(Nil(), drop (lst ,5))

  @Test def testAppend(): Unit =
    val tail = Cons (40, Nil())
    assertEquals(Cons(10, Cons(20, Cons(30, Cons(40, Nil())))), append(lst, tail))

  @Test def testAppendToNil(): Unit =
    val tail = Cons (40, Nil())
    assertEquals(tail, append(Nil(), tail))

  @Test def testFlatMap(): Unit =
    assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), flatMap(lst)(v => Cons( v + 1, Nil())))

  @Test def testFlatMap2(): Unit =
    assertEquals(Cons(11, Cons(12, Cons(21, Cons(22, Cons(31, Cons(32, Nil())))))), flatMap(lst)(v => Cons( v + 1, Cons( v + 2, Nil()))))
