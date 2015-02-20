package sml

case class OutInstruction(label: String, opcode: String, val r1:Int)extends Instruction(label, opcode){
  override def toString(): String = {
    super.toString()+" register "+ r1  
  }
  
  override def execute(m: Machine) = {
    println(m.regs(r1))
  }
  
}

object OutInstruction{
  def apply(label:String, r1:Int) = new OutInstruction(label,"out",  r1)
}