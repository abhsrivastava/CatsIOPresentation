import cats.implicits._
import org.http4s.server.blaze._
import org.http4s.implicits._
import cats.effect._
import org.http4s.dsl.io._
import org.http4s._
import scala.concurrent.ExecutionContext.Implicits.global
import org.http4s.server.Router

object Http4sEx1 extends App {
    implicit val cs: ContextShift[IO] = IO.contextShift(global)
    val helloWorldService = HttpService[IO] {
    case GET -> Root / "hello" / name =>
        Ok(s"Hello, $name.")
    }
    val httpApp = Router("/" -> helloWorldService).orNotFound
    val builder = BlazeServerBuilder[IO].bindHttp(8080, "localhost").withHttpApp(httpApp).start
    val server = builder.unsafeRunSync()
}