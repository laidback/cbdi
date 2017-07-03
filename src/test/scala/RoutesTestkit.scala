package de.fti.skeleton

import org.scalatest._
import akka.http.scaladsl.server._
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.typesafe.config.ConfigFactory

class RoutesTestkit extends WordSpec
  with Matchers
  with ScalatestRouteTest
  with HomeComponent
  with StatusComponent {

  val config = ConfigFactory.load()
  val homeActor = system.actorOf(HomeActor.props)
  val statusActor = system.actorOf(StatusActor.props)

  "Home Page" should {
    "GET home page should return 200 OK" in {
      val getRequest = HttpRequest(HttpMethods.GET, uri="/")
      getRequest ~> homeRoute ~> check {
        status.isSuccess() shouldEqual true
      }
    }
  }

  "Status Page" should {
    "GET status page should return 200 OK" in {
      val getRequest = HttpRequest(HttpMethods.GET, uri="/status")
      getRequest ~> statusRoute ~> check {
        status.isSuccess() shouldEqual true
      }
    }
  }
}

