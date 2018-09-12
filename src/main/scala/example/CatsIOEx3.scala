import cats.effect._
import cats.syntax.all._

object CatsIOEx3 extends IOApp {
    def run(args: List[String]) : IO[ExitCode] = {
        val program = for {
            x <- IO {println("foo"); 10}
            y <- IO {println("foo"); 10}
        } yield x + y
        program.as(ExitCode.Success)
    }
}