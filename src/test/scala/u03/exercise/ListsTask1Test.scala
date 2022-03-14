package u03.exercise

import Lists.*
import List.*
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

  @Test def testFilterFlatMap(): Unit =
    assertEquals(filter(lst)(_ >= 20), filterFlatMap(lst)(_ >= 20))

  @Test def testMapFlatMap(): Unit =
    assertEquals(map(lst)(_ + 1), mapFlatMap(lst)(_+1))

  @Test def testMax(): Unit =
    import u02.Optionals.Option.Some
    assertEquals(Some(25), max(Cons(10, Cons(25, Cons(20, Nil())))))

  @Test def testMaxNil(): Unit =
    import u02.Optionals.Option.None
    assertEquals(None(), max(Nil()))

  @Test def testTeacherCourses(): Unit =
    import u02.AlgebraicDataTypes.Person
    val p = Cons(Person.Student("mario", 2015), Cons(Person.Teacher("p1","c1"), Cons(Person.Teacher("t2","c2"),Nil())))
    assertEquals(Cons("c1", Cons("c2", Nil())), teacherCourses(p))

  @Test def testFoldRight(): Unit =
    val l = Cons(3, Cons(7, Cons(1, Cons(5, Nil()))))
    assertEquals(-8, foldRight(l)(0)(_ - _))
    //f(3, f(7, f(1, f(5,0)))) = f(3, f(7, f(1, 5))) = f(3, f(7, -4)) = f(3, 11) = -8

  @Test def testFoldLeft(): Unit =
    val l = Cons(3, Cons(7, Cons(1, Cons(5, Nil()))))
    assertEquals(-16, foldLeft(l)(0)(_ - _))
    // f(f(f(f(0,5),1),7),3) = f(f(f(-5,1),7),3) = f(f(-6,7),3) = f(-13,3) = -16