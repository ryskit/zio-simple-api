package com.ryskit.api

import zio.*
import zhttp.http.*
import zhttp.http.{Http, Method, Request, Response}
import zhttp.service.Server
import zio.json.*

@jsonMemberNames(SnakeCase)
case class Greeting(message: String)

object Greeting {
  implicit val decoder: JsonDecoder[Greeting] = DeriveJsonDecoder.gen[Greeting]
  implicit val encoder: JsonEncoder[Greeting] = DeriveJsonEncoder.gen[Greeting]
}

object Main extends ZIOAppDefault {
  val port = 9000

  val app: Http[Any, Nothing, Request, Response] = Http.collect[Request] { case Method.GET -> !! =>
    val greeting = Greeting("hello world!")
    Response.json(greeting.toJson)
  }

  private val program = for {
    _ <- Console.printLine(s"server is running at http://localhost:$port")
    _ <- Server.start(port, app)
  } yield ()

  override def run: ZIO[Environment with ZIOAppArgs with Scope, Any, Any] = program
}
