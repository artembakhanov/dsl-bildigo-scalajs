import tags.TagDef
import tags._
import tags.Attr._
import tags.+++
import scala.scalajs.js.annotation.JSExportTopLevel

enum HrefMode(val tag: String):
  case EntityMode extends HrefMode("e")
  case DeclarationMode extends HrefMode("d")
  case LineMode extends HrefMode("L")

object Helpers {
  def generateHref(mode: HrefMode, data: String): String =
    s"#${mode.tag}:${data}"

  @JSExportTopLevel("hightlightToken")
  @JSExportTopLevel("ht")
  def hightlightToken(tokenType: String, content: TagDef): TagDef =
    span(
      clazz := tokenType,
      content
    )

  @JSExportTopLevel("declaration")
  @JSExportTopLevel("d")
  def declaration(declId: String, content: TagDef*): TagDef =
    a(
      (Seq(
        id := declId,
        href := generateHref(HrefMode.DeclarationMode, declId)
      )
        ++ content)*
    )
  @JSExportTopLevel("entity")
  @JSExportTopLevel("e")
  def entity(eid: Int, declId: String, content: TagDef*): TagDef =
    a(
      (Seq(
        id := eid.toString,
        href := generateHref(HrefMode.DeclarationMode, declId)
      )
        ++ content)*
    )
  @JSExportTopLevel("universal")
  @JSExportTopLevel("u")
  def universal(name: String, content: TagDef*): TagDef =
    a(
      (Seq(
        target := "blank",
        href := s"https://pkg.go.dev/builtin#${name}"
      )
        ++ content)*
    )

  @JSExportTopLevel("tooltipToken")
  @JSExportTopLevel("tt")
  def tooltipToken(content: TagDef*) =
    div(content +++ (clazz := "tooltip")*)

  @JSExportTopLevel("tooltipText")
  @JSExportTopLevel("tt2")
  def tooltipText(declarationId: String, content: TagDef*) =
    span(
      content +++ (
        dataDeclId := declarationId,
        // href := "cock"
      )*
    )
  @JSExportTopLevel("lineNums")
  @JSExportTopLevel("ln")
  def lineNums(lineNum: Int, content: TagDef*) =
    val lineNumStr = lineNum.toString
    val href_ = generateHref(HrefMode.LineMode, lineNumStr)
    a(
      id := href_.slice(1, href_.length),
      clazz := "linenums-box",
      href := href_,
      lineNumStr
    )
  @JSExportTopLevel("scopeTriggers")
  @JSExportTopLevel("st")
  def scopeTriggers(scopeId: Int, lineNum: Int, togglable: Boolean) =
    div(
      id := s"scp-ln-${lineNum}",
      div(
        (if togglable then
           Seq(
             id := s"scp-tgl-${scopeId}",
             open := "true",
             clazz := "scopeTriggers-box scope-toggle"
           )
         else Seq(clazz := "scopeTriggers-box"))*
      )
    )

  @JSExportTopLevel("openScope")
  @JSExportTopLevel("os")
  def openScope(scopeId: Int, content: TagDef*) =
    div(
      content +++ (
        id := s"scp-${scopeId}",
        open := "true",
        clazz := "scope"
      )*
    )

  @JSExportTopLevel("closeScopeSummary")
  @JSExportTopLevel("ss")
  def closeScopeSummary() =
    div(
      clazz := "scope-summary",
      "..."
    )

  @JSExportTopLevel("scopeContent")
  @JSExportTopLevel("sc")
  def scopeContent(content: TagDef*) =
    div(
      content +++
        (clazz := "scope-content")*
    )

  @JSExportTopLevel("collect")
  @JSExportTopLevel("c")
  def collect(content: TagDef*) = 
    div(
      content*
    )
}
