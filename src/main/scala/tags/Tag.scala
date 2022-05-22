package tags

import org.scalajs.dom.Node
import org.scalajs.dom.document
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.annotation.JSExportAll
import org.scalajs.dom.Element
import scala.scalajs.js
import js.JSConverters._


@JSExportAll
case class TagDef(name: String, contents: (Def | String | Null)*) extends Def:
  lazy val htmlElement: Element =
    val element = document.createElement(name)

    contents.foreach {
      case t: TagDef => element.appendChild(t.htmlElement)
      case a: AttrDef => element.setAttribute(a.name, a.value)
      case s: String => element.append(s)
      case _ => ()
    }
    element

  lazy val lines: js.Array[String] =
    htmlElement.outerHTML.split("\n").toJSArray

case class Tag(name: String):
  def apply(contents: (Def | String)*) = TagDef(name, contents*)


