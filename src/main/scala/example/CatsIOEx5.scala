import cats.effect._
import cats.syntax.all._

object CatsIOEx5 extends IOApp {
    def fib(n: Int, a: BigDecimal = 0, b: BigDecimal = 1) : IO[BigDecimal] = {
        IO(a + b).flatMap{b2 => 
            if (n > 1) 
                fib(n - 1, b, b2) 
            else IO.pure(b2)
        }
    }

    def run(args: List[String]) : IO[ExitCode] = {
        val program = fib(args(0).toInt).flatMap{result =>
            IO { println(s"result: $result")}
        }
        program.as(ExitCode.Success)
    }
}