/**
 * Created by xiyaoma on 4/1/17.
 */
public class arraylist_node {

    public int key;
    public String value;
    public arraylist_node leftchild;
    public arraylist_node rightchild;
    public boolean isleaf;

    public arraylist_node(int key, String value, arraylist_node leftchild, arraylist_node rightchild){

        this.key = key;
        this.value = value;
        this.leftchild = leftchild;
        this.rightchild = rightchild;
        this.isleaf = true;

    }

    public arraylist_node(int key, String value){
        this.key = key;
        this.value = value;
        this.leftchild = null;
        this.rightchild = null;
        this.isleaf = true;

    }

    public arraylist_node(){
        this.isleaf = true;

    }

}
