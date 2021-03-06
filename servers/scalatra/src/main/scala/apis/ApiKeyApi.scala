package apis

import com.wordnik.client.model.ApiKey
import java.io.File

import org.scalatra.{ TypedParamSupport, ScalatraServlet }
import org.scalatra.swagger._
import org.json4s._
import org.json4s.JsonDSL._
import org.scalatra.json.{ JValueResult, JacksonJsonSupport }
import org.scalatra.servlet.{FileUploadSupport, MultipartConfig, SizeConstraintExceededException}

import scala.collection.JavaConverters._

class ApiKeyApi (implicit val swagger: Swagger) extends ScalatraServlet 
    with FileUploadSupport
    with JacksonJsonSupport
    with SwaggerSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "ApiKeyApi"
  override protected val applicationName: Option[String] = Some("apiKey")

  before() {
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }



  val createKeyOperation = (apiOperation[ApiKey]("createKey")
      summary "Create a new API Key."
      parameters(
        formParam[String]("name").description("").optional,formParam[String]("cidr").description("").optional,formParam[Boolean]("enabled").description("").optional)
  )

  post("/",operation(createKeyOperation)) {
    val name = params.getAs[String]("name")
    println("name: " + name)
  val cidr = params.getAs[String]("cidr")
    println("cidr: " + cidr)
  val enabled = params.getAs[Boolean]("enabled")
    println("enabled: " + enabled)
  }




  val getKeysOperation = (apiOperation[List[ApiKey]]("getKeys")
      summary "Get your API Keys."
      parameters(
        )
  )

  get("/",operation(getKeysOperation)) {
    }




  val removeOperation = (apiOperation[Boolean]("remove")
      summary "Remove an API Key."
      parameters(
        formParam[String]("apiKeyID").description("").optional)
  )

  delete("/",operation(removeOperation)) {
    val apiKeyID = params.getAs[String]("apiKeyID")
    println("apiKeyID: " + apiKeyID)
  }




  val disableOperation = (apiOperation[ApiKey]("disable")
      summary "Disable an API Key."
      parameters(
        formParam[String]("apiKeyID").description("").optional)
  )

  post("/disable",operation(disableOperation)) {
    val apiKeyID = params.getAs[String]("apiKeyID")
    println("apiKeyID: " + apiKeyID)
  }




  val enableOperation = (apiOperation[ApiKey]("enable")
      summary "Enable an API Key."
      parameters(
        formParam[String]("apiKeyID").description("").optional)
  )

  post("/enable",operation(enableOperation)) {
    val apiKeyID = params.getAs[String]("apiKeyID")
    println("apiKeyID: " + apiKeyID)
  }

}
