import java.util.ArrayList;
import java.util.Collections;

public class RootTree {
    private ArrayList<Node> arrNode = new ArrayList<>();
    private Node lastNode;
    private String[] string;
    RootTree(String input){
        String[] str = input.split(" ");
        this.string = str;
        this.CreateNode(str);
        this.findParent();
    }
    
    public String[] getString() {
        return string;
    }
    public ArrayList<Node> getArrNode() {
        return arrNode;
    }
    public Node getLastNode() {
        return lastNode;
    }
    public void setLastNode(Node lastNode) {
        this.lastNode = lastNode;
    }

    private void CreateNode(String[] s){
        for (String str : s) {
            try {
                // System.out.println(Integer.parseInt(str));
                arrNode.add(new Node(Integer.parseInt(str)));
            } catch (Exception e) {
                arrNode.add(new Node(str));
            }
        }
    }

    private Node findParent(){
        while(this.getArrNode().size()>1){//loop all to find all oparetor frome left to right
            //find * or /  first
            for(int i=0;i<this.getArrNode().size();i++){
                Node thisNd = this.getArrNode().get(i);
                if(thisNd.isOparetor()&&
                (thisNd.getOparetor().equals("*")
                ||thisNd.getOparetor().equals("/"))){
                    Node nd = new Node(
                        this.getArrNode().get(i-1),this.getArrNode().get(i+1),thisNd.getOparetor());
                    this.getArrNode().remove(i+1);
                    this.getArrNode().remove(i);
                    this.getArrNode().add(i,nd);
                    this.getArrNode().remove(i-1);
                    break;
                }
            }
            //find + or -
            for(int i=0;i<this.getArrNode().size();i++){
                Node thisNd = this.getArrNode().get(i);
                if(thisNd.isOparetor()&&
                (thisNd.getOparetor().equals("+")||thisNd.getOparetor().equals("-"))){
                    Node nd = new Node(
                        this.getArrNode().get(i-1),this.getArrNode().get(i+1),thisNd.getOparetor());
                    this.getArrNode().remove(i+1);
                    this.getArrNode().remove(i);
                    this.getArrNode().add(i,nd);
                    this.getArrNode().remove(i-1);
                    break;
                }
            }
        }
        //result will make it 1 node left
        this.setLastNode(this.getArrNode().get(0));
        return this.getArrNode().get(0);
    }
    
    public void findPrefix(){ 
        ArrayList<Node> ndlist = new ArrayList<>();
        int step =0;
        ndlist.add(0, this.getLastNode());

        while(step < ndlist.size()){
            if(ndlist.get(step).haveChildLeft()){
                ndlist.add(step+1, ndlist.get(step).getChildLeft());
                ndlist.add(step+2, ndlist.get(step).getChildRight());
            }
            step++;
        }
        System.out.print("Prefix : ");
        for (Node node : ndlist) {
            System.out.print(node+" ");
        }
        System.out.println();
    }

    public void findPostFix(){
        ArrayList<Node> ndlist = new ArrayList<>();
        int step =0;
        ndlist.add(0, this.getLastNode());

        
        while(step < ndlist.size()){
            if(ndlist.get(step).haveChildLeft()){
                ndlist.add(step+1, ndlist.get(step).getChildRight());
                ndlist.add(step+2, ndlist.get(step).getChildLeft());
            }
            step++;
        }
        Collections.reverse(ndlist);
        System.out.print("Postfix : ");
        for (Node node : ndlist) {
            System.out.print(node+" ");
        }
        System.out.println();
    }
   
    public void findRootTree(){
        ArrayList<Node> ndlist = new ArrayList<>();
        int step =0;
        int level = 0;
        ndlist.add(0, this.getLastNode());
        ndlist.get(0).setLevel(level);
        level++;
        while(ndlist.size() != this.getString().length){
            for(step =0;step<ndlist.size();step++){
                if(ndlist.get(step).haveChildLeft()&&ndlist.get(step).getChildLeft().getLevel()<0){
                    level = ndlist.get(step).getLevel()+1;
                    ndlist.add(step, ndlist.get(step).getChildLeft());
                    ndlist.get(step).setLevel(level);
                    ndlist.add(step+2, ndlist.get(step+1).getChildRight());
                    ndlist.get(step+2).setLevel(level);
                    level++;
                }
            }
        }
        System.out.println("Tree");
        for(int i=0;i<level;i++){//each line
            String str ="";
            for(int j=0;j<ndlist.size();j++){
                if(ndlist.get(j).getLevel()==i)
                str+=ndlist.get(j);
                else
                str+=" ";
            }
            System.out.println("Level "+i+ " : "+str);
        }


    }
}
