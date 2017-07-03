package io.github.laidback.cbdi

case class User(username: String, password: String)

trait UserRepositoryComponent {
  val userRepository: UserRepository

  trait UserRepository {
    def create(user: User): Unit
  }
}

trait UserRepositoryComponentImpl extends UserRepositoryComponent {
  override val userRepository: UserRepository = new UserRepositoryImpl

  private class UserRepositoryImpl extends UserRepository {
    override def create(user: User): Unit = println(user)
  }
}

trait UserServiceComponent {
  val userService: UserService

  trait UserService {
    def create(username: String, password: String): Unit
  }
}

trait UserServiceComponentImpl extends UserServiceComponent {
  this: UserRepositoryComponent =>
  override val userService: UserService = new UserServiceImpl

  private class UserServiceImpl extends UserService {
    def create(username: String, password: String): Unit =
      userRepository.create(User(username, password))
  }
}

trait UserComponent extends UserServiceComponent with UserRepositoryComponent

trait UserComponentImpl extends UserComponent
  with UserServiceComponentImpl
  with UserRepositoryComponentImpl

