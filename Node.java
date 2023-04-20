public class Node{
    private int value;
    private Node childLeft;
    private Node childRight;
    private boolean haveChildLeft =false;
    private boolean haveChildRight =false;
    private int level =-1;
    private boolean isOparetor;
    private String oparetor;

    Node(String op)
    {
        this.isOparetor = true;
        this.oparetor = op;
    }
    Node(int num){
        this.value = num;
        this.isOparetor = false;
    }
    Node(Node n1,Node n2,String op){
        this.value = calculateOperation(n1.getValue(), n2.getValue(),op);
        this.childLeft =n1;
        this.haveChildLeft = true;
        this.childRight =n2;
        this.haveChildRight = true;
        this.isOparetor = false;
        this.oparetor = op;
    }

    private int calculateOperation(int n1, int n2,String op){
        int result=0;
        if(op.equals("*"))
        result = n1*n2;
        else if(op.equals("/"))
        result = n1/n2;
        else if(op.equals("+"))
        result = n1+n2;
        else if(op.equals("-"))
        result = n1-n2;
        return result;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public Node getChildLeft() {
        return childLeft;
    }
    public void setChildLeft(Node childLeft) {
        this.childLeft = childLeft;
    }
    public Node getChildRight() {
        return childRight;
    }
    public void setChildRight(Node childRight) {
        this.childRight = childRight;
    }
    public boolean haveChildLeft() {
        return haveChildLeft;
    }
    public boolean haveChildRight() {
        return haveChildRight;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public boolean isOparetor() {
        return isOparetor;
    }
    public void setIsOparetor(boolean isOparetor) {
        this.isOparetor = isOparetor;
    }
    public String getOparetor() {
        return oparetor;
    }
    public void setOparetor(String oparetor) {
        this.oparetor = oparetor;
    }
    
    @Override
    public String toString() {// Print Opearator first if it can
        try {
            if(this.getOparetor()!=null)
                return this.getOparetor();
            return this.getValue()+"";
        } catch (Exception e) {
            return this.getValue()+"";
        }
    }
    
}
