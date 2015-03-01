package sml
import java.lang.reflect.Constructor, scala.collection.mutable.ArrayBuffer
class Translator(fileName: String) {
  private final val ADD = "add"
  private final val LIN = "lin"
  private final val MUL = "mul"
  private final val SUB = "sub"
  private final val OUT = "out"
  private final val BNZ = "bnz"
  

  def readAndTranslate(m: Machine): Machine = {
   
    val labels = m.labels
    var program = m.prog
    import scala.io.Source
    val lines = Source.fromFile(fileName).getLines
    for (line <- lines) {
      val fields = line.split(" ")
      if (fields.length > 0) {
        labels.add(fields(0))
        
        val newInstruction = "sml."+fields(1).charAt(0).toUpper+fields(1).substring(1)+"Instruction"
        val c = Class.forName(newInstruction);
        val constructor = c.getConstructors()(0);
        val parameterTypes = constructor.getParameterTypes();
        val allThings = ArrayBuffer[Object]()
        for(i <- 0 until parameterTypes.length){
         if(parameterTypes(i).toString=="class java.lang.String"){
           allThings += fields(i)
         }
         else {
           allThings += new Integer(fields(i));
         }
       }
       program = program :+ constructor.newInstance(allThings:_*).asInstanceOf[Instruction];
      }
    }
    new Machine(labels, program)
  }
}

object Translator {
  private val directory: String = "src/"

  def apply(file: String) =
    new Translator(directory + file)
}