// import cats.effect._
// import scala.concurrent.ExecutionContext
// import cats.syntax.all._
// import scala.concurrent.duration._

// object CatsIOEx12 extends App {
//     implicit val cs = IO.contextShift(ExecutionContext.global)
//     implicit val timer = cs.timer[IO]

//     val launchMissiles = IO.sleep(60 seconds) *> IO(println("launched missiles"))
//     val runToBunker = IO.sleep(2 seconds) *> IO(throw new Exception("Can't go back to bunker"))

//     val program = for {
//     fiber <- launchMissiles.start
//     _ <- runToBunker.handleErrorWith { error =>
//         // Retreat failed, cancel launch (maybe we should
//         // have retreated to our bunker before the launch?)
//         fiber.cancel *> IO.raiseError(error)
//     }
//     aftermath <- fiber.join
//     } yield {
//     aftermath
//     }
//     program.unsafeRunSync
// }
