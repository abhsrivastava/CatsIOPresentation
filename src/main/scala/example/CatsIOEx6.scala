import cats.effect._
import cats.syntax.all._
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.Executors
import scala.concurrent.duration._

object CatsIOEx6 extends IOApp {
    def run(args: List[String]) : IO[ExitCode] = {
        implicit val sc = Executors.newScheduledThreadPool(5)
        val d = 5 hour

        val ct = IO.cancelable[String] { cb => 
            val r = new Runnable{
                def run() = {
                    Thread.sleep(10000)
                    cb(Right("foo"))
                }
            }
            val f = sc.schedule(r, d.length, d.unit)
            IO(f.cancel(true))
        }
        val program : IO[Unit] = for {
            fiber <- ct.start
            _ <- IO.delay(5 seconds)
            _ <- fiber.cancel
        } yield()
        program.as(ExitCode.Success)
    }
}