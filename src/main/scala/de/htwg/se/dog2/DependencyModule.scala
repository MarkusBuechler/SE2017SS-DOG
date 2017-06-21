package de.htwg.se.dog2

import com.google.inject.AbstractModule
import de.htwg.se.dog2.controller.{TGameController, gameController}
import de.htwg.se.dog2.util.FileIOJsonImpl
import de.htwg.se.dog2.util.FileIOXmlImpl
import de.htwg.se.dog2.util.TFileIO
import de.htwg.se.dog2.view.{DogGui, DogTui}
import net.codingwell.scalaguice.ScalaModule

/**
 * Created by margogo on 24.04.17.
 */
class DependencyModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {

        bind[TFileIO].to[FileIOJsonImpl.FileIO]
//    bind[TFileIO].to[FileIOXmlImpl.FileIO]
    bind[TGameController].to[gameController]
    bind[DogGui]
    bind[DogTui]

  }
}
