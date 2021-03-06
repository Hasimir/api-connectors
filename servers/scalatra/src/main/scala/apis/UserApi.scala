package apis

import com.wordnik.client.model.User
import com.wordnik.client.model.Margin
import com.wordnik.client.model.Transaction
import com.wordnik.client.model.Affiliate
import com.wordnik.client.model.AccessToken
import com.wordnik.client.model.Any
import java.io.File

import org.scalatra.{ TypedParamSupport, ScalatraServlet }
import org.scalatra.swagger._
import org.json4s._
import org.json4s.JsonDSL._
import org.scalatra.json.{ JValueResult, JacksonJsonSupport }
import org.scalatra.servlet.{FileUploadSupport, MultipartConfig, SizeConstraintExceededException}

import scala.collection.JavaConverters._

class UserApi (implicit val swagger: Swagger) extends ScalatraServlet 
    with FileUploadSupport
    with JacksonJsonSupport
    with SwaggerSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "UserApi"
  override protected val applicationName: Option[String] = Some("user")

  before() {
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }



  val getDepositAddressOperation = (apiOperation[String]("getDepositAddress")
      summary "Get a deposit address."
      parameters(
        queryParam[String]("currency").description("").optional.defaultValue("XBt"))
  )

  get("/depositAddress",operation(getDepositAddressOperation)) {
    val currency = params.getAs[String]("currency")
    println("currency: " + currency)
  }




  val getWalletHistoryOperation = (apiOperation[List[Transaction]]("getWalletHistory")
      summary "Get a history of all of your wallet transactions (deposits and withdrawals)."
      parameters(
        )
  )

  get("/walletHistory",operation(getWalletHistoryOperation)) {
    }




  val requestWithdrawalOperation = (apiOperation[Transaction]("requestWithdrawal")
      summary "Request a withdrawal to an external wallet."
      parameters(
        formParam[String]("otpToken").description("").optional,formParam[Double]("amount").description(""),formParam[String]("address").description(""),formParam[String]("currency").description("").defaultValue("XBt"))
  )

  post("/requestWithdrawal",operation(requestWithdrawalOperation)) {
    val otpToken = params.getAs[String]("otpToken")
    println("otpToken: " + otpToken)
  val amount = params.getAs[Double]("amount")
    println("amount: " + amount)
  val address = params.getAs[String]("address")
    println("address: " + address)
  val currency = params.getAs[String]("currency")
    println("currency: " + currency)
  }




  val cancelWithdrawalOperation = (apiOperation[Transaction]("cancelWithdrawal")
      summary "Cancel a withdrawal."
      parameters(
        formParam[String]("token").description(""))
  )

  post("/cancelWithdrawal",operation(cancelWithdrawalOperation)) {
    val token = params.getAs[String]("token")
    println("token: " + token)
  }




  val confirmWithdrawalOperation = (apiOperation[Transaction]("confirmWithdrawal")
      summary "Confirm a withdrawal."
      parameters(
        formParam[String]("token").description(""))
  )

  post("/confirmWithdrawal",operation(confirmWithdrawalOperation)) {
    val token = params.getAs[String]("token")
    println("token: " + token)
  }




  val requestEnableTFAOperation = (apiOperation[Boolean]("requestEnableTFA")
      summary "Get Google Authenticator secret key for setting up two-factor auth. Fails if already enabled."
      parameters(
        formParam[String]("`type`").description("").optional.defaultValue("GA"))
  )

  post("/requestEnableTFA",operation(requestEnableTFAOperation)) {
    val `type` = params.getAs[String]("`type`")
    println("`type`: " + `type`)
  }




  val confirmEnableTFAOperation = (apiOperation[Boolean]("confirmEnableTFA")
      summary "Confirm two-factor auth for this account."
      parameters(
        formParam[String]("token").description(""),formParam[String]("`type`").description("").optional.defaultValue("GA"))
  )

  post("/confirmEnableTFA",operation(confirmEnableTFAOperation)) {
    val token = params.getAs[String]("token")
    println("token: " + token)
  val `type` = params.getAs[String]("`type`")
    println("`type`: " + `type`)
  }




  val sendVerificationEmailOperation = (apiOperation[Boolean]("sendVerificationEmail")
      summary "Re-send verification email."
      parameters(
        queryParam[String]("email").description(""))
  )

  get("/resendVerificationEmail",operation(sendVerificationEmailOperation)) {
    val email = params.getAs[String]("email")
    println("email: " + email)
  }




  val confirmEmailOperation = (apiOperation[Boolean]("confirmEmail")
      summary "Confirm your email address with a token."
      parameters(
        formParam[String]("token").description(""))
  )

  post("/confirmEmail",operation(confirmEmailOperation)) {
    val token = params.getAs[String]("token")
    println("token: " + token)
  }




  val requestPasswordResetOperation = (apiOperation[Boolean]("requestPasswordReset")
      summary "Request a password reset."
      parameters(
        formParam[String]("email").description(""))
  )

  post("/requestPasswordReset",operation(requestPasswordResetOperation)) {
    val email = params.getAs[String]("email")
    println("email: " + email)
  }




  val confirmPasswordResetOperation = (apiOperation[Boolean]("confirmPasswordReset")
      summary "Confirm a password reset."
      parameters(
        formParam[String]("email").description(""),formParam[String]("token").description(""),formParam[String]("newPassword").description(""))
  )

  post("/confirmPasswordReset",operation(confirmPasswordResetOperation)) {
    val email = params.getAs[String]("email")
    println("email: " + email)
  val token = params.getAs[String]("token")
    println("token: " + token)
  val newPassword = params.getAs[String]("newPassword")
    println("newPassword: " + newPassword)
  }




  val getAffiliateStatusOperation = (apiOperation[List[Affiliate]]("getAffiliateStatus")
      summary "Get your current affiliate/referral status."
      parameters(
        )
  )

  get("/affiliateStatus",operation(getAffiliateStatusOperation)) {
    }




  val checkReferralCodeOperation = (apiOperation[Double]("checkReferralCode")
      summary "Check if a referral code is valid."
      parameters(
        queryParam[String]("referralCode").description("").optional)
  )

  get("/checkReferralCode",operation(checkReferralCodeOperation)) {
    val referralCode = params.getAs[String]("referralCode")
    println("referralCode: " + referralCode)
  }




  val newUserOperation = (apiOperation[User]("newUser")
      summary "Register a new user."
      parameters(
        formParam[String]("email").description(""),formParam[String]("password").description(""),formParam[String]("username").description(""),formParam[String]("firstname").description("").optional,formParam[String]("lastname").description("").optional,formParam[String]("acceptsTOS").description("").optional,formParam[String]("referrerID").description("").optional,formParam[String]("accountType").description("").optional.defaultValue("Trader"))
  )

  post("/",operation(newUserOperation)) {
    val email = params.getAs[String]("email")
    println("email: " + email)
  val password = params.getAs[String]("password")
    println("password: " + password)
  val username = params.getAs[String]("username")
    println("username: " + username)
  val firstname = params.getAs[String]("firstname")
    println("firstname: " + firstname)
  val lastname = params.getAs[String]("lastname")
    println("lastname: " + lastname)
  val acceptsTOS = params.getAs[String]("acceptsTOS")
    println("acceptsTOS: " + acceptsTOS)
  val referrerID = params.getAs[String]("referrerID")
    println("referrerID: " + referrerID)
  val accountType = params.getAs[String]("accountType")
    println("accountType: " + accountType)
  }




  val getMeOperation = (apiOperation[User]("getMe")
      summary "Get your user model. This also includes your margin data."
      parameters(
        )
  )

  get("/",operation(getMeOperation)) {
    }




  val updateMeOperation = (apiOperation[User]("updateMe")
      summary "Update your password, name, and other attributes."
      parameters(
        formParam[String]("firstname").description("").optional,formParam[String]("lastname").description("").optional,formParam[String]("oldPassword").description("").optional,formParam[String]("newPassword").description("").optional,formParam[String]("newPasswordConfirm").description("").optional,formParam[String]("accountType").description("").optional)
  )

  put("/",operation(updateMeOperation)) {
    val firstname = params.getAs[String]("firstname")
    println("firstname: " + firstname)
  val lastname = params.getAs[String]("lastname")
    println("lastname: " + lastname)
  val oldPassword = params.getAs[String]("oldPassword")
    println("oldPassword: " + oldPassword)
  val newPassword = params.getAs[String]("newPassword")
    println("newPassword: " + newPassword)
  val newPasswordConfirm = params.getAs[String]("newPasswordConfirm")
    println("newPasswordConfirm: " + newPasswordConfirm)
  val accountType = params.getAs[String]("accountType")
    println("accountType: " + accountType)
  }




  val loginOperation = (apiOperation[AccessToken]("login")
      summary "Log in to BitMEX."
      parameters(
        formParam[String]("email").description(""),formParam[String]("password").description(""),formParam[String]("token").description("").optional)
  )

  post("/login",operation(loginOperation)) {
    val email = params.getAs[String]("email")
    println("email: " + email)
  val password = params.getAs[String]("password")
    println("password: " + password)
  val token = params.getAs[String]("token")
    println("token: " + token)
  }




  val logoutOperation = (apiOperation[Unit]("logout")
      summary "Log out of BitMEX."
      parameters(
        )
  )

  post("/logout",operation(logoutOperation)) {
    }




  val logoutAllOperation = (apiOperation[Unit]("logoutAll")
      summary "Log all systems out of BitMEX. This will revoke all of your account's access tokens, logging you out on all devices."
      parameters(
        )
  )

  post("/logoutAll",operation(logoutAllOperation)) {
    }




  val savePreferencesOperation = (apiOperation[User]("savePreferences")
      summary "Save application preferences."
      parameters(
        formParam[Any]("prefs").description(""))
  )

  post("/preferences",operation(savePreferencesOperation)) {
    val prefs = params.getAs[Any]("prefs")
    println("prefs: " + prefs)
  }




  val getCommissionOperation = (apiOperation[List[any]]("getCommission")
      summary "Get your account's commission status."
      parameters(
        )
  )

  get("/commission",operation(getCommissionOperation)) {
    }




  val getMarginOperation = (apiOperation[Margin]("getMargin")
      summary "Get your account's margin status."
      parameters(
        )
  )

  get("/margin",operation(getMarginOperation)) {
    }

}
