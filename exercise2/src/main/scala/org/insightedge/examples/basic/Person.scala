package org.insightedge.examples.basic

import org.insightedge.scala.annotation._
import scala.beans.{BeanProperty, BooleanBeanProperty}

case class Person(
                   @BeanProperty 
                   @SpaceRouting 
                   var FirstName: String,
                   
                   @BeanProperty 
                   @SpaceIndex 
                   var LastName: String,
                   
                   @BeanProperty 
                   var age: Int,
                   
                   @BeanProperty var PhoneNumber: String)
{
  def this() = this(null, null, -1, null)
}




