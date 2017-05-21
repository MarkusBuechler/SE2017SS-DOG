package de.htwg.se.yourgame.fxml

import com.google.inject.Injector

import scala.reflect.runtime.universe._
import scala.util.{Failure, Success, Try}
import scalafxml.core.ControllerDependencyResolver

/** Guice based dependency resolver for ScalaFXML controllers */
class GuiceDependencyResolver(implicit val injector: Injector) extends ControllerDependencyResolver {

  def get(paramName: String, dependencyType: Type): Option[Any] = {
    val rm = runtimeMirror(getClass.getClassLoader)
    val cls = Class.forName(rm.runtimeClass(dependencyType).getName)
    Try(injector.getInstance(cls)) match {
      case Success(instance) => Some(instance)
      case Failure(_) => None
    }
  }
}
