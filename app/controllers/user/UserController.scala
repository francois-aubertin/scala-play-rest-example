package controllers.user

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import services.user.UserServiceComponent
import domain.user.User

trait UserController extends Controller {
    self: UserServiceComponent =>0

    def emailAlreadyExists(implicit reads: Reads[String]) =
        Reads[String](js => reads.reads(js).flatMap { e =>
          userService.tryFindByEmail(e).map(_ => JsError("error.custom.emailAlreadyExists")).getOrElse(JsSuccess(e))
        })

    implicit val userReads = (__ \ "email").read[String](email andKeep emailAlreadyExists)
                                           .map(resource => UserResource(resource))
    
    implicit val userWrites = new Writes[User] {
        override def writes(user: User): JsValue = {
            Json.obj(
                "id" -> user.id,
                "email" -> user.email
            )
        }
    }
                                           
    def createUser = Action(parse.json) {request =>
        unmarshalJsValue(request) { resource: UserResource =>
            val user = User(Option.empty, resource.email)
            userService.createUser(user)
            Created
        }
    }
    
    def updateUser(id: Long) = Action(parse.json) {request =>
        unmarshalJsValue(request) { resource: UserResource =>
            val user = User(Option(id), resource.email)
            userService.updateUser(user)
            NoContent
        }
    }
    
    def findUserById(id: Long) = Action {
        val user = userService.tryFindById(id)
        if (user.isDefined) {
            Ok(Json.toJson(user))
        } else {
            NotFound
        }
    }
    
    def deleteUser(id: Long) = Action {
        userService.delete(id)
        NoContent
    }

    def unmarshalJsValue[R](request: Request[JsValue])(block: R => Result)(implicit rds : Reads[R]): Result =
        request.body.validate[R](rds).fold(
            valid = block,
            invalid = e => {
                val error = e.mkString
                Logger.error(error)
                BadRequest(error)
            }
        )

}

case class UserResource(email: String)
