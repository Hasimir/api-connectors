package com.wordnik.client.api

import com.wordnik.client.model.Quote
import com.wordnik.client.common.ApiInvoker
import com.wordnik.client.common.ApiException

import java.io.File
import java.util.Date

import scala.collection.mutable.HashMap

class QuoteApi {
  var basePath: String = "https://www.bitmex.com/api/v1"
  var apiInvoker = ApiInvoker
  
  def addHeader(key: String, value: String) = apiInvoker.defaultHeaders += key -> value 

  def getBucketed (symbol: String, filter: Any, columns: List[String], start: Double, reverse: Boolean, startTime: Date, endTime: Date, binSize: String= "1m", count: Double= 100) : Option[List[Quote]]= {
    // create path and map variables
    val path = "/quote/bucketed".replaceAll("\\{format\\}","json")

    val contentType = {
      "application/json"}

    // query params
    val queryParams = new HashMap[String, String]
    val headerParams = new HashMap[String, String]

    if(String.valueOf(binSize) != "null") queryParams += "binSize" -> binSize.toString
    if(String.valueOf(symbol) != "null") queryParams += "symbol" -> symbol.toString
    if(String.valueOf(filter) != "null") queryParams += "filter" -> filter.toString
    if(String.valueOf(columns) != "null") queryParams += "columns" -> columns.toString
    if(String.valueOf(count) != "null") queryParams += "count" -> count.toString
    if(String.valueOf(start) != "null") queryParams += "start" -> start.toString
    if(String.valueOf(reverse) != "null") queryParams += "reverse" -> reverse.toString
    if(String.valueOf(startTime) != "null") queryParams += "startTime" -> startTime.toString
    if(String.valueOf(endTime) != "null") queryParams += "endTime" -> endTime.toString
    try {
      apiInvoker.invokeApi(basePath, path, "GET", queryParams.toMap, None, headerParams.toMap, contentType) match {
        case s: String =>
          Some(ApiInvoker.deserialize(s, "Array", classOf[Quote]).asInstanceOf[List[Quote]])
        case _ => None
      }
    } catch {
      case ex: ApiException if ex.code == 404 => None
      case ex: ApiException => throw ex
    }
  }
  }

