import cats.effect.{IO, IOApp, ExitCode}
import scala.concurrent.duration._
import cats.syntax.all._

object CatsIOEx10 extends IOApp {
    def run(args: List[String]) : IO[ExitCode] = {
        val io1 = IO.delay(5 seconds) *> IO(20)
        val io2 = IO.delay(2 seconds) *> IO(10)
        val io3 = IO.delay(1 seconds) *> IO(50)
        val program = (io1, io2, io3).parMapN{ (a, b, c) => a + b + c}
        program.flatMap(x => IO{println(s"result $x")}).as(ExitCode.Success)
    }
}