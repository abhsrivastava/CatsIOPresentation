import cats.effect.{IO, IOApp, ExitCode}
import cats.syntax.all._
import scala.concurrent.duration._

object CatsIOEx8 extends IOApp {
    def run(args: List[String]) : IO[ExitCode] = {
        val program = retryWithBackOff(IO.raiseError(new Exception("boom")), 5 seconds, 3)
        program.attempt.flatMap{ either => 
            either match {
                case Right(_) => IO(ExitCode.Success)
                case Left(ex) => IO(ExitCode(2))
            }
        }
    }

    def retryWithBackOff[A](io: IO[A], initDelay: FiniteDuration, maxRetries: Int) : IO[A] = {
        println("came inside")
        io.handleErrorWith{err => 
            if (maxRetries > 1)
                IO.sleep(initDelay) *> retryWithBackOff(io, initDelay * 2, maxRetries - 1)
            else 
                IO.raiseError(err)
        }
    }
}