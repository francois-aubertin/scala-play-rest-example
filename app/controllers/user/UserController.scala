package controllers.user

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import services.user.UserServiceComponent
import domain.user.User
import domain.asset.Asset

trait UserController extends Controller {
    self: UserServiceComponent =>
    
    implicit val userReads = (__ \ "email").read[String]
                                           .map(resource => UserResource(resource))
    
    def createUser = Action(parse.json) {request =>
        request.body.validate[UserResource].fold(
            valid = (user => {
                userService.saveUser(User(Option.empty,
                                          user.email,
                                          Seq()))
                Created
            }),
            invalid = (e => {
                val error = e.mkString
                Logger.error(error)
                BadRequest(error)
            })
        )
    }

}

case class UserResource (val email: String) {
}
