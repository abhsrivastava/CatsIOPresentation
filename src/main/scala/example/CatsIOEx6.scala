import cats.effect._
import cats.syntax.all._
import scala.concurrent.duration._

// object CatsIOEx6 extends IOApp {
//     def foo[F[_]: Sync](implicit timer: Timer[F]) = {
//         F.delay(println("start")) >> timer.sleep(5 seconds) >> F.delay(println("end"))
//     }
//     def run(args: List[String]) : IO[ExitCode] = {
//         val program = foo().start.flatMap{ f => 
//             f.sleep(2 seconds) >> f.cancel
//         }
//     }
// }