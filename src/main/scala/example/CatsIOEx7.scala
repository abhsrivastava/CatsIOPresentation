import cats.effect.{IO, ExitCode, IOApp}
import cats.syntax.all._
import java.io._

object CatsIOEx7 extends IOApp {
    def run(args: List[String]) : IO[ExitCode] = {
        val program = IO(new BufferedReader(new FileReader(new File("/Users/abhisheksrivastava/temp/names.txt")))).bracket {in => 
            var content: String = ""
            var line = in.readLine()
            while(line != null) {
                content += line
                line = in.readLine()
            }
            IO(content)
        } {in => 
            IO(in.close())
        }
        program.as(ExitCode.Success)
    }
}