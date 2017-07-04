package io.github.laidback.cbdi

import akka.Done
import akka.NotUsed
import akka.util._
import akka.event.{Logging, LoggingAdapter}
import akka.actor._
import akka.stream._
import akka.stream.scaladsl._
import akka.http._
import akka.http.scaladsl._
import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model._
import scala.io.StdIn
import scala.util.{Try, Success, Failure}
import scala.concurrent.Future

trait RemoteService

trait HttpRemoteService extends RemoteService {
  implicit val httpClient: Flow[(HttpRequest, NotUsed), (Try[HttpResponse], NotUsed), _] 
}

trait HttpRemoteServiceImpl extends HttpRemoteService {
  implicit val httpClient: Flow[(HttpRequest, NotUsed), (Try[HttpResponse], NotUsed), _] 
}

trait TopComponent
extends UserComponent
with ProjectComponent

trait TopComponentImpl
extends TopComponent
with UserComponentImpl
with ProjectComponentImpl

abstract class MyApp extends TopComponent

object Main extends App
  with ProjectComponentImpl
  with UserComponentImpl {

  /** Instantiate global implicits */
  implicit val actorSystem = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = actorSystem.dispatcher
  implicit val log: LoggingAdapter = Logging(actorSystem, getClass)


  /** Wire up your application calling your components directly */
  val userComponent =  new UserServiceComponentImpl 
                      with UserRepositoryComponentImpl
//                      with HttpRemoteServiceImpl
  userComponent.userService.create("Lukas", "From Component")

  val projectComponent = new ProjectServiceComponentImpl 
                        with ProjectRepositoryComponentImpl
  projectComponent.projectService.create("MyProject", User("Lukas", "From Component"))


  /** Wire up your application injected through the traits */
  userService.create("Lukas", "From Trait with Service")
  userRepository.create(User("Lukas", "From Trait with Repo"))

  projectService.create("MyProject", User("Lukas", "From Trait with Service"))
  projectRepository.create(Project("MyProject", User("Lukas", "From Trait with Repo")))


  /** Wire up your application calling the application directly with its dependency */
  val appWithTop = new MyApp with TopComponentImpl
  appWithTop.userService.create("Lukas", "From App with Top with Service")
  appWithTop.userRepository.create(User("Lukas", "From App with Top with Repo"))

  appWithTop.projectService.create("MyProject", User("Lukas", "From App with Top with Service"))
  appWithTop.projectRepository.create(Project("MyProject", User("Lukas", "From App with Top with Repo")))


  /** Wire up your application calling the application directly with its dependencies separate */
  val appWithSingle = new MyApp with UserComponentImpl with ProjectComponentImpl
  appWithSingle.userService.create("Lukas", "From App no Top with Service")
  appWithSingle.userRepository.create(User("Lukas", "From App no Top with Repo"))

  appWithSingle.projectService.create("MyProject", User("Lukas", "From App no Top with Service"))
  appWithSingle.projectRepository.create(Project("MyProject", User("Lukas", "From App no Top with Repo")))

  System.exit(0)
}

