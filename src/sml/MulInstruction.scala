package sml

case class MulInstruction(label: String, opcode: String,  val result: Int, val op1: Int, val op2: Int) extends Instruction(label, opcode) {
  override def toString(): String = {
     super.toString  + " register " +op1 + " * " + " register " +op2 + " to register " + result + "\n"
    
  }

  override def execute(m: Machine) = {
    val value1 = m.regs(op1)
    val value2 = m.regs(op2)
    m.regs(result) = value1 * value2
  }
}

object MulInstruction{
  
  def apply(label: String, result: Int, op1: Int, op2: Int) = new MulInstruction(label, "mul ", result, op1, op2)
  
}
