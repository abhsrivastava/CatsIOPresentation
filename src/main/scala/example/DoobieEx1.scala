import doobie._
import doobie.implicits._

import cats._
import cats.effect._
import cats.implicits._
import cats.syntax.all._

case class Person(id: Long, name: String, age: Int)
object DoobieEx1 extends App {
    val xa = Transactor.fromDriverManager[IO](
        "org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", ""
    )    

    // let us create a table
    val drop = sql"""DROP TABLE IF EXISTS PERSON""".update.run
    val create = sql"""CREATE TABLE PERSON(ID SERIAL, NAME VARCHAR(200) NOT NULL, AGE SMALLINT NOT NULL)""".update.run
    def insert(name: String, age: Int) = {
        sql"""insert into PERSON(name, age) values ($name, $age)""".update.run
    }
    val program = for {
        _ <- drop
        _ <- create
        _ <- insert("foo", 10)
        _ <- insert("bar", 20)
        _ <- insert("baz", 30)
    } yield()
    // create table and insert data
    program.transact(xa).unsafeRunSync
    // query data
    val query = sql"""select id, name, age from person""".query[Person]
    val result = query.list.transact(xa).unsafeRunSync
    result.foreach(println)
}