import cats.effect._
import cats.syntax.all._

object CatsIOEx2 {
    def main(args: Array[String]) : Unit = {
        val program = IO { println("Hello World")}
        program.unsafeRunSync()
    }
}
