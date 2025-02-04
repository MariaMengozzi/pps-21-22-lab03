package u03.exercise

import scala.annotation.tailrec

object Lists extends App:

  // A generic linkedlist
  enum List[E]:
    case Cons(head: E, tail: List[E])
    case Nil()
  // a companion object (i.e., module) for List
  object List:

    import u02.Optionals.Option
    import u02.Optionals.Option.None
    import u02.Optionals.Option.Some
    import u02.AlgebraicDataTypes.Person

    def sum(l: List[Int]): Int = l match
      case Cons(h, t) => h + sum(t)
      case _ => 0

    def map[A, B](l: List[A])(mapper: A => B): List[B] = l match
      case Cons(h, t) => Cons(mapper(h), map(t)(mapper))
      case Nil() => Nil()

    def filter[A](l1: List[A])(pred: A => Boolean): List[A] = l1 match
      case Cons(h, t) if pred(h) => Cons(h, filter(t)(pred))
      case Cons(_, t) => filter(t)(pred)
      case Nil() => Nil()

    def drop[A](l: List[A], n: Int): List[A] = l match
      case Cons(h, t) if n > 0 => drop(t, n-1)
      case _ => l

    def append[A](left: List[A], right: List[A]): List[A] = left match
      case Nil() => right
      case Cons(h,t) => Cons(h, append(t, right))

    def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] = l match
      case Cons(h, t) => append(f(h), flatMap(t)(f))
      case _ => Nil()

    def mapFlatMap[A, B](l: List[A])(mapper: A => B): List[B] =
      flatMap(l)(v => Cons(mapper(v),Nil()))

    def filterFlatMap[A](l: List[A])(p: A => Boolean): List[A] =
      flatMap(l)(v => v match
        case i if p(v) => Cons(v,Nil())
        case _ => Nil())

    def max(l: List[Int]): Option[Int] =
      def _max(list: List[Int], max: Option[Int]): Option[Int] = (list, max) match
        case (Cons(h, t), Some(x)) if x > h => _max(t, max)
        case (Cons(h, t), _) => _max(t, Some(h))
        case (Nil(), _) => max

      _max(l, None())

    def teacherCourses(t: List[Person]): List[String] = 
      flatMap(t)(p => p match 
        case Person.Teacher(_, c) => Cons(c,Nil())
        case _ => Nil()
      )

    def foldRight[A,B] (l: List[A])(reduce: B)(f: (A,B) => B): B = l match
      case Cons(h, t) => f(h, foldRight(t)(reduce)(f))
      case _ => reduce
      
    def reverseList[A](l: List[A]): List[A] = l match
      case Cons(h, t) => append(reverseList(t), Cons(h,Nil()))
      case _ => Nil()

    def foldLeft[A,B] (l: List[A])(reduce: B)(f: (B,A) => B): B =
      foldRight(reverseList(l))(reduce)((x,y) => f(y,x))
