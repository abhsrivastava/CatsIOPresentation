import cats.effect.{IOApp, IO}
import cats.syntax.all._
import scala.concurrent.ExecutionContext
import java.util.concurrent.Executors

object CatsIOEx9 extends App {
    val Main = ExecutionContext.global
    val BlockingIO = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())
    val program = for {
        _ <- IO { println("what is your name")}
        _ <- IO.shift(BlockingIO)
        name <- IO { readLine }
        _ <- IO.shift(Main)
    } yield s"Hello $name"
    val output = program.unsafeRunSync
    println(output)
}