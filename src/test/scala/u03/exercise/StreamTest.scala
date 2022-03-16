package u03.exercise

import Streams.*
import Lists.List.*
import org.junit.Test
import org.junit.Assert.*


class StreamTest:
  val s = Stream.take(Stream.iterate(0)( _ + 1))(10)

  @Test def testDrop(): Unit =
    assertEquals(Cons(6, Cons(7, Cons(8, Cons(9, Nil())))),
      Stream.toList(Stream.drop(s)(6)))

  @Test def testConstant(): Unit =
    assertEquals( Cons("x", Cons("x", Cons("x", Cons("x", Cons("x", Nil()))))),
      Stream.toList(Stream.take(Stream.constant("x"))(5)))

  @Test def testConstant2(): Unit =
    assertEquals( Cons(1, Cons(1, Cons(1, Cons(1, Cons(1, Nil()))))),
      Stream.toList(Stream.take(Stream.constant(1))(5)))

  @Test def testFibonacci(): Unit =
    val fibs: Stream[Int] = fibonacci
    assertEquals(Cons(0, Cons(1, Cons(1, Cons(2, Cons(3, Cons(5, Cons(8, Cons(13, Nil())))))))),
      Stream.toList(Stream.take(fibs)(8)))
