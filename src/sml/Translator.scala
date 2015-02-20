package sml
import java.lang.reflect.Constructor;
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
        val test = "sml.AddInstruction"
        val c = Class.forName(newInstruction);
        val constructor = c.getConstructors()(0);
        val parameterTypes = constructor.getParameterTypes();
   
        //an example of how to loop through parameter types
        for(i <- 0 until parameterTypes.length){
         println(parameterTypes(i));
         if(parameterTypes(i).toString=="class java.lang.String"){
           
         }
       }
       
       println("newInstruction is "+newInstruction);
       
       println("and the constructor is ...... "+constructor)
      
        
        fields(1) match {
          case ADD =>
            program = program :+ AddInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
         case SUB =>
            program = program :+ SubInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
         case MUL =>
            program = program :+ MulInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
          case LIN =>
            program = program :+ LinInstruction(fields(0), fields(2).toInt, fields(3).toInt)
          case OUT =>
            program = program :+ OutInstruction(fields(0), fields(2).toInt)
            case BNZ =>
            program = program :+ BnzInstruction(fields(0), fields(2).toInt, fields(3).toString())
          case x =>
            println(s"Unknown instruction $x")
        }
  

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