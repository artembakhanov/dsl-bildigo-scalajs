package tags

case class AttrDef(name: String, value: String) extends Def 

enum Attr(val name: String):
  case href extends Attr("href")
  case clazz extends Attr("class")
  case id extends Attr("id")
  case target extends Attr("target")
  case dataDeclId extends Attr("data-declid")
  case open extends Attr("open")
  def :=(value: String): AttrDef = AttrDef(name, value)


import Attr._
val attrMap =  Attr.values.map(obj => (obj.name, obj)).toMap

given Conversion[(String, String), AttrDef] = {
  case (name, value) => 
    if !attrMap.contains(name) then throw new RuntimeException(s"attribute ${name} does not exist")

    attrMap(name) := value
}

given Conversion[AttrDef, (String, String)] with
  def apply(attrDef: AttrDef) =
    (attrDef.name, attrDef.value)
    
extension (content: Seq[Def])
  def +++(other: Def*) =
    content.appendedAll(other)