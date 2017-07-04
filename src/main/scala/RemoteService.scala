package io.github.laidback.cbdi

case class Remote(username: String, password: String)

trait RemoteRepositoryComponent {
  val userRepository: RemoteRepository

  trait RemoteRepository {
    def create(user: Remote): Unit
  }
}

trait RemoteRepositoryComponentImpl extends RemoteRepositoryComponent {
  override val userRepository: RemoteRepository = new RemoteRepositoryImpl

  private class RemoteRepositoryImpl extends RemoteRepository {
    override def create(user: Remote): Unit = println(user)
  }
}

trait RemoteServiceComponent {
  val userService: RemoteService

  trait RemoteService {
    def create(username: String, password: String): Unit
  }
}

trait RemoteServiceComponentImpl extends RemoteServiceComponent {
  this: RemoteRepositoryComponent =>
  override val userService: RemoteService = new RemoteServiceImpl

  private class RemoteServiceImpl extends RemoteService {
    def create(username: String, password: String): Unit =
      userRepository.create(Remote(username, password))
  }
}

trait RemoteComponent extends RemoteServiceComponent with RemoteRepositoryComponent

trait RemoteComponentImpl extends RemoteComponent
  with RemoteServiceComponentImpl
  with RemoteRepositoryComponentImpl

