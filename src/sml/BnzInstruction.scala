package sml

case class BnzInstruction(label: String, op: String, r1: Int, nextIns: String) extends Instruction(label, op){
  override def toString(): String = {
     super.toString  
  }
    
  override def execute(m: Machine) = {

      if(m.regs(r1)>0){
      m.execute(m.labels.labels.indexOf(nextIns))
      }  
  }
}
object BnzInstruction {
  def apply(label: String, r1:Int, nextIns:String)=new BnzInstruction(label, "bnz", r1, nextIns)
}
