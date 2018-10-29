/*
 * Copyright (c) 2016, GigaSpaces Technologies, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.insightedge.examples.basic

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.insightedge.spark.context.InsightEdgeConfig
//import org.insightedge.spark.implicits.basic._
import org.insightedge.spark.implicits.all._

import scala.util.Random

/**
  * Generates 1000 Persons, converts to Spark RDD and saves to Data Grid. Persons have fixed IDs.
  */
object SparkExerciseGrid {

  def main(args: Array[String]): Unit = {
    val settings = if (args.length > 0) args else Array("spark://127.0.0.1:7077", sys.env("INSIGHTEDGE_SPACE_NAME"))
    if (settings.length != 2) {
      System.err.println("Usage: SavePersonRdd <spark master url> <space name>")
      System.exit(1)
    }
    val Array(master, space) = settings
    val config = InsightEdgeConfig(space)
    
    val spark = SparkSession.builder
      .appName("spark-exercise-2")
      .master(master)
      .insightEdgeConfig(config)
      .getOrCreate()
    val sc = spark.sparkContext
    
    
  

    val personsNum = 1000
    println(s"Saving $personsNum Persons RDD to the space")
    val rdd = sc.parallelize(1 to personsNum).map { 
      i => Person("John_" + i, "Brown_" + i, Random.nextInt(50), "555-555-" + (Math.random() * 9999))
    }
    
    rdd.saveToGrid()
    
    //Count Total number of persons
    val personsRDD = sc.gridRdd[Person]()
    println(s"Persons RDD count: ${personsRDD.count()}")
    
    
    import spark.implicits._
    //Find Persons By FirstName
    val ds = spark.read.grid[Person].as[Person]
    ds.printSchema()
    val count = ds.filter( o => o.getFirstName() == "Satya").count()
    println(s"Number of Persons with firstname 'Satya': $count")
    ds.filter( o => o.getFirstName() == "Satya").collect().foreach(println)
    
    
    //Find Persons By LastName
    val lncount = ds.filter( o => o.getLastName() == "LastName").count()
    println(s"Number of Persons with lastname == 'LastName': $lncount")
    ds.filter( o => o.getLastName() == "LastName").collect().foreach(println)
    
    //Find Persons By Age
    val agcount = ds.filter( o => o.getAge() > 30 ).count()
    println(s"Number of Persons with age > 30: $agcount")
    
    ds.filter( o => o.getAge() > 30 ).collect().foreach(println)
    
   
       
    sc.stopInsightEdgeContext()
  }

}
