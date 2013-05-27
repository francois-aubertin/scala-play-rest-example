package domain.user

import domain.asset.Asset

case class User(val id: Option[Long],
                val email: String,
                val assets: Traversable[Asset] = Seq[Asset]()) {

}
