import cats.effect.{IO, IOApp, ExitCode}
import scala.concurrent.duration._
import cats.syntax.all._
import cats._, cats.data._

object CatsIOEx11 extends IOApp {
    def run(args: List[String]) : IO[ExitCode] = {
        val io1 = IO.delay(5 seconds) *> IO(20)
        val io2 = IO.delay(2 seconds) *> IO(10)
        val io3 = IO.delay(1 seconds) *> IO(50)
        val ioList = NonEmptyList.of(io1, io2, io3)
        ioList.parSequence.flatMap{list => IO{println(s"sum: ${list.foldLeft(0)(_ + _)}")}}.as(ExitCode.Success)
    }
}