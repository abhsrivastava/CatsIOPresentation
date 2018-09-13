import cats.implicits._
import org.http4s.server.blaze._
import org.http4s.server.Router
import cats.effect.{IO, IOApp, ExitCode, ContextShift}
import org.http4s._, org.http4s.dsl.io._
import scala.concurrent.ExecutionContext.Implicits.global

object Http4sEx1 extends IOApp {
    val helloWorldService = HttpService[IO] {
    case GET -> Root / "hello" / name =>
        Ok(s"Hello, $name.")
    }
    val httpRoute = Router("/" -> helloWorldService)
    val httpApp : HttpApp[IO] = httpRoute.orNotFound
    def run(args: List[String]): IO[ExitCode] =
        BlazeServerBuilder[IO]
        .bindHttp(8080, "localhost")
        .withHttpApp(httpApp)
        .serve
        .compile
        .drain
        .as(ExitCode.Success)    
}