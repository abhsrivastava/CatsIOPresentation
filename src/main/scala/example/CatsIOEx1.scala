import cats.effect._
import cats.syntax.all._

object CatsIOEx1 extends IOApp {
  def run(args: List[String]) : IO[ExitCode] = {
    IO { println("Hello World") }.as(ExitCode.Success)
  }
}
