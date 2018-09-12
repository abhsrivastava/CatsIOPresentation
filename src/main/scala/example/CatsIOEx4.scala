import cats.effect._
import cats.syntax.all._

object CatsIOEx4 extends IOApp {
    def run(args: List[String]) : IO[ExitCode] = {
        val io = IO { println("foo"); 10}
        val program = for {
            x <- io
            y <- io
        } yield x + y
        program.as(ExitCode.Success)
    }
}