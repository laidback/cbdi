package io.github.laidback.cbdi

case class Project(name: String, user: User)

trait ProjectRepositoryComponent {
  val projectRepository: ProjectRepository

  trait ProjectRepository {
    def create(project: Project): Unit
  }
}

trait ProjectRepositoryComponentImpl extends ProjectRepositoryComponent {
  override val projectRepository: ProjectRepository = new ProjectRepositoryImpl

  private class ProjectRepositoryImpl extends ProjectRepository {
    override def create(project: Project): Unit = println(project)
  }
}

trait ProjectServiceComponent {
  val projectService: ProjectService

  trait ProjectService {
    def create(name: String, user: User): Unit
  }
}

trait ProjectServiceComponentImpl extends ProjectServiceComponent {
  this: ProjectRepositoryComponent =>
  override val projectService: ProjectService = new ProjectServiceImpl

  private class ProjectServiceImpl extends ProjectService {
    def create(name: String, user: User): Unit =
      projectRepository.create(Project(name, user))
  }
}

trait ProjectComponent extends ProjectServiceComponent with ProjectRepositoryComponent

trait ProjectComponentImpl extends ProjectComponent
  with ProjectServiceComponentImpl
  with ProjectRepositoryComponentImpl

