package com.example.cs512dsa;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


import javafx.scene.input.KeyCode;

import javafx.scene.layout.*;

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.Scene;
import javafx.geometry.Insets;


public class RBTrees extends Application{
    @Override


    public void start(Stage stage)
    {

        stage.setTitle("Red Black Tree");
        stage.setMinWidth(1000);
        RedBlack tree = new RedBlack();//create a tree
        BorderPane pane = new BorderPane();//create a borderpane
        RedBlackView redBlackView = new RedBlackView(tree); // Create a Red Black View
        pane.setCenter(redBlackView);


        // hbox is created
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(20);

        TextField value = new TextField("enter the value");
        value.setFont(new Font("TIMES ROMAN", 14));
        value.setMaxWidth(140);
        value.setStyle("-fx-text-fill: black");
        value.setAlignment(Pos.BOTTOM_LEFT);

        //Insert Button is initialised
        Button insert = new Button("Insert");
        insert.setStyle("-fx-font-size:15");
        insert.setPrefSize(120, 50);

        //Delete Button is initialised
        Button delete= new Button("Delete");
        delete.setStyle("-fx-font-size:15");
        delete.setPrefSize(120, 50);


        //Search Button is initialised
        Button search = new Button("Search\n");
        search.setStyle("-fx-font-size:14");
        search.setPrefSize(120, 50);


        hbox.getChildren().addAll(value, insert, delete, search);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);// Box elements are set at bottom

        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));//White Backgound is set
        pane.setRight(hbox);// HBOX is placed right of border pane

        Scene scene = new Scene(pane, 1100, 700);

        stage.setScene(scene);
        stage.show(); //show stage


        insert.setOnAction((ActionEvent e) -> { //Action event for Insert
            if(value.getText().trim().equals("")){
                WrongInput(value,"Please Enter a Value");
            }
            else{
                try {
                    int key = Integer.parseInt(value.getText());
                    if (tree.search(key)) { // This is used when value is already there in the tree
                        redBlackView.printRedBlackTree();//This is used to print tree on UI

                        redBlackView.setTreeNodes("No. of nodes: "+tree.countNodes()); // This will keep track of total no of nodes
                        redBlackView.setStatus(key + " is already in the tree");
                        value.clear();//Use to clear the text value
                    } else {
                        tree.insert(key, redBlackView); // A new node is inserted in the tree
                        redBlackView.printRedBlackTree();
                        redBlackView.setTreeNodes("No. of nodes: "+tree.countNodes());
                        redBlackView.setStatus(key + " is inserted in the tree");

                        value.clear();//Use to clear the text value
                    }
                }
                catch (NumberFormatException ex){
                    WrongInput(value,"Please only enter integer values");
                }
            }
        });


        //We can insert in the tree using enter key if there is any value.
        value.setOnKeyPressed(keyEvent -> {
            try {
                if (keyEvent.getCode()==(KeyCode.ENTER)) {
                    int key = Integer.parseInt(value.getText());
                    if (tree.search(key)) { // This is used when value is already there in the tree
                        redBlackView.printRedBlackTree();

                        WrongInput(value,"Key is already in the tree");
                        redBlackView.setTreeNodes("No. of Nodes: "+tree.countNodes());//Counts total no of nodes
                        redBlackView.setStatus(key + " is already in the tree");
                        value.clear();
                    } else {
                        tree.insert(key, redBlackView); // Insert a new key
                        redBlackView.printRedBlackTree();

                        value.clear();
                        redBlackView.setStatus(key + " is inserted in the tree");
                        redBlackView.setTreeNodes("No. of Nodes: "+tree.countNodes());
                        value.clear();
                    }
                }
            }
            catch (NumberFormatException ex){
                WrongInput(value,"Please only enter integer values");
            }
        });

        //delete on button press
        delete.setOnAction((ActionEvent e) -> {
            if(value.getText().trim().equals("")){
                WrongInput(value,"Please Enter a Value");
            }
            else{
                try {
                    int key = Integer.parseInt(value.getText());
                    if (!tree.search(key)) { // Value is not present in the true
                        redBlackView.printRedBlackTree();
                        redBlackView.setTreeNodes("No. of Nodes: "+tree.countNodes());
                        redBlackView.setStatus(key + " is not in the tree");
                        value.clear();
                    }

                    else {
                        tree.delete(key); // We will delete a value
                        redBlackView.printRedBlackTree();

                        redBlackView.setTreeNodes("No. of Nodes: "+tree.countNodes());
                        redBlackView.setStatus(key + " is deleted from the tree");
                        value.clear();
                    }
                }
                catch (NumberFormatException ex){
                    WrongInput(value,"Key must be an integer");
                }
            }
        });

        //Below fuction is called when clicking search button
        search.setOnAction((ActionEvent e) -> {
            if(value.getText().trim().equals("")){
                WrongInput(value,"No key entered!");
            }
            else{
                try {
                    int key = Integer.parseInt(value.getText());
                    if(!tree.search(key)){
                        redBlackView.printRedBlackTree();
                        redBlackView.setTreeNodes("No. of Nodes: "+tree.countNodes());
                        redBlackView.setStatus(key+" not found");
                        WrongInput(value,key + " is not present in the tree");
                        value.clear();
                    }

                    else{
                        redBlackView.printRedBlackTree();
                        redBlackView.setStatus(key+" found");
                        SearchInput(value,key + " is  present in the tree");
                        redBlackView.setTreeNodes("No. of Nodes: "+tree.countNodes());
                        value.clear();
                    }
                }
                catch(NumberFormatException ex){
                    WrongInput(value,"Key must be an integer");
                }
            }
        });




    }

    //alert when value is entered
    private void SearchInput(TextField value, String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(str);
        value.requestFocus();
        alert.showAndWait();
        value.clear();
    }
    //alert when wrong input is entered
    private void WrongInput(TextField value, String str){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(str);
        alert.setContentText("enter a correct value");
        value.requestFocus();
        alert.showAndWait();
        value.clear();
    }

    public static void main(String args[])
    {

        Application.launch(args);
    }
}

class RedBlackView extends Pane {
    // Red Black view is defined
    private RedBlack tree = new RedBlack();
    private final double levelGap = 120; // space between two levels in a tree
    public double radius = 20; // Tree node radius
    RedBlackView(RedBlack tree) {
        this.tree = tree;
        setTreeNodes("");
        setStatus("Tree is empty");

    }


    public final void setStatus(String msg) { // keep on updating the status message
        Text t = new Text(200, 20, msg);
        t.setFont(Font.font("VERDANA", FontWeight.BOLD,FontPosture.REGULAR, 14));
        t.setFill(Color.RED);
        t.setFill(Color.BLACK);
        getChildren().add(t);
    }

    public final void setTreeNodes(String v){ // used to print total number of tree nodes
        Text t = new Text(600, 20, v);
        t.setFont(Font.font("CALIBRI", FontWeight.BOLD,FontPosture.REGULAR, 14));
        t.setFill(Color.RED);
        getChildren().add(t);
    }

    public void printRedBlackTree(){
        this.getChildren().clear();
        if(tree.getRoot() != null){
            printRedBlackTree(tree.getRoot(), getWidth()/1.75, levelGap /2, getWidth() / 4);
        }
    }
    //It is used to display nodes and its lines
    protected void printRedBlackTree(RedBlack.Node root, double x, double y, double hGap){
        if(root.left != null){
            Line l = new Line(x - hGap, y + levelGap, x, y);
            l.setStroke(Color.rgb(8,91,185));
            l.setStrokeWidth(5.0f);
            getChildren().add(l);
            printRedBlackTree(root.left, x - hGap, y + levelGap, hGap / 2);
        }

        if (root.right != null){
            Line l = new Line(x + Math.max(hGap,20), y + levelGap, x, y);
            l.setStroke(Color.rgb(8,91,185));
            l.setStrokeWidth(5.0f);
            getChildren().add(l);
            printRedBlackTree(root.right, x + Math.max(hGap,20), y + levelGap, hGap/2);
        }
        Circle c; // node is defined
        if(root.nill==false){
            c = new Circle(x, y, radius);
        }
        else {
            c = new Circle(x, y, 8);
        }

        c.setFill(root.color);
        c.setStroke(Color.rgb(8,91,185));
        c.setStrokeWidth(4.0f);
        Text node;
        if(root.nill==false){
            node = new Text(x - 4, y + 4, root.value+ "");
        }
        else {
            node = new Text(x - 4, y + 4,  "");
        }

        node.setStyle("-fx-font-size: 15");
        node.setFill(Color.WHITE);
        getChildren().addAll(c, node);
    }

}
//class containing all functions performed on tree
class RedBlack {
    public Node root;
    private Node TNULL;


    public RedBlack(){
        TNULL = new Node();
        TNULL.left = null;
        TNULL.right = null;
        TNULL.nill = true;
        TNULL.color = Color.BLACK;
        root = TNULL;
    }

    public static class Node {
        public int value;
        public int balance;
        public int height;
        Color color;
        public Node left;
        public Node right;
        public Node parent;
        public boolean nill;


    }

    public void insert(int value, RedBlackView view){
        //initialise a new ndoe
        Node node = new Node();
        node.color = Color.RED; //new node must be RED
        node.left = TNULL;
        node.right = TNULL;
        node.value = value;
        node.nill = false;
        node.parent = null;

        Node traverse = this.root;
        Node p_traverse = null;

        //traverse till the correct position where new node needs to be inserted, BST style insertion
        while(traverse != TNULL){
            p_traverse = traverse;
            if(node.value < traverse.value){
                traverse = traverse.left;
            }
            else{
                traverse = traverse.right;
            }
        }

        node.parent = p_traverse;
        //Case 1) if new node is the only node inserted, make it root node and change color to BLACK
        if(node.parent == null){
            node.color = Color.BLACK;
            root = node;
            return;
        }
        else if(node.value < p_traverse.value){
            p_traverse.left = node;
        }
        else if(node.value > p_traverse.value){
            p_traverse.right = node;
        }


        //Case 2) if parent node is BLACK, return
        if(node.parent.color == Color.BLACK){
            return;
        }

        //Case 3) if parent node is RED, adjust tree
        adjustTreeInsertion(node);
    }

    // function to adjust tree after insertion
    private void adjustTreeInsertion(Node node){
        Node p_sibling;
        while(node != root && node.parent.color == Color.RED){
            //if parent is right child
            if(node.parent == node.parent.parent.right){
                p_sibling = node.parent.parent.left; //parent's sibling
                //Case 4.1) parent's sibling is red, change color of parent and parent's sibling
                if(p_sibling.color == Color.RED){
                    p_sibling.color = Color.BLACK;
                    node.parent.color = Color.BLACK;
                    //make parent's parent as current node
                    node = node.parent.parent;
                    node.color = Color.RED;
                }
                //Case 4.2) parent's sibling is black or null, perform suitable rotation and recoloring
                else{
                    //if node is a left child, RL rotation
                    if(node == node.parent.left){
                        node = node.parent;
                        rotateRight(node);
                    }
                    //if node is right child, L rotation
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rotateLeft(node.parent.parent);
                }
            }
            //if parent is right child
            else{
                p_sibling = node.parent.parent.right; //parent's sibling
                //Case 4.1) parent's sibling is red, change color of parent and parent's sibling
                if(p_sibling.color == Color.RED){
                    p_sibling.color = Color.BLACK;
                    node.parent.color = Color.BLACK;
                    //make parent's parent as current node
                    node = node.parent.parent;
                    node.color = Color.RED;
                }
                //if parent's sibling is black or null, perform suitable rotation and recoloring
                else{
                    //if node is right child, LR rotation
                    if(node == node.parent.right){
                        node = node.parent;
                        rotateLeft(node);
                    }
                    //if node is left child, R rotation
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rotateRight(node.parent.parent);
                }
            }
        }
        //ensure that root node is always black
        root.color = Color.BLACK;
    }


    // function to adjust tree after deletion
    private void adjustTreeDeletion(Node db){
        Node db_sibling;
        while(db != root && db.color == Color.BLACK){
            //if db is left child
            if(db == db.parent.left){
                db_sibling = db.parent.right;
                if(db_sibling.color == Color.BLACK){
                    //Case 3.1) if db's sibling is black and both it's children are black
                    if(db_sibling.left.color == Color.BLACK && db_sibling.right.color == Color.BLACK){
                        db_sibling.color = Color.RED;
                        db = db.parent;//if parent was black, now it becomes db, else if it was red- loop breaks
                    }
                    else{
                        //Case 3.2) db's sibling is black and sibling's far child is black but near child is red
                        if(db_sibling.right.color == Color.BLACK && db_sibling.left.color == Color.RED){
                            db_sibling.color = Color.RED;
                            db_sibling.left.color = Color.BLACK;
                            rotateRight(db_sibling);
                            db_sibling = db.parent.right;
                        }
                        //Case 3.3) db's sibling is black but far child is red
                        db_sibling.color = db.parent.color;
                        db.parent.color = Color.BLACK; //sibling's color must have been black as one of it's child is red
                        db_sibling.right.color = Color.BLACK; //make red child as black
                        rotateLeft(db.parent);
                        break;
                    }
                }
                //Case 3.4) if db's sibling is red
                else{
                    db.parent.color = Color.RED;
                    db_sibling.color = Color.BLACK;
                    rotateLeft(db.parent);
                    db_sibling = db.parent.right;
                }
            }
            //if db is right child
            else{
                db_sibling = db.parent.left;
                if(db_sibling.color == Color.BLACK){
                    //Case 3.1) if db's sibling is black and both it's children are black
                    if(db_sibling.left.color == Color.BLACK && db_sibling.right.color == Color.BLACK){
                        db_sibling.color = Color.RED;
                        db = db.parent;//if parent was black, now it becomes db, else if it was red- loop breaks
                    }
                    else{
                        //Case 3.2) db's sibling is black and sibling's far child is black but near child is red
                        if(db_sibling.left.color == Color.BLACK && db_sibling.right.color == Color.RED){
                            db_sibling.color = Color.RED;
                            db_sibling.right.color = Color.BLACK;
                            rotateLeft(db_sibling);
                            db_sibling = db.parent.left;
                        }
                        //Case 3.3) db's sibling is black but far child is red
                        db_sibling.color = db.parent.color;
                        db.parent.color = Color.BLACK; //sibling's color must have been black as one of it's child is red
                        db_sibling.left.color = Color.BLACK; //make red child as black
                        rotateRight(db.parent);
                        break;
                    }
                }
                //Case 3.4) if db's sibling is red
                else{
                    db.parent.color = Color.RED;
                    db_sibling.color = Color.BLACK;
                    rotateRight(db.parent);
                    db_sibling = db.parent.left;
                }
            }
        }
        //when db is removed, single black (original color) remains
        db.color = Color.BLACK;
    }

    // function to establish relation between replaced deleted node and tree
    private void replace(Node del, Node leaf){
        //if node to be deleted is root node, make the other node as root
        if(del.parent == null){
            this.root = leaf;
        }
        //if node to be deleted is right child, make leaf as right child of del node's parent
        else if(del == del.parent.right){
            del.parent.right = leaf;
        }
        //if node to be deleted is left child, make leaf as left child of del node's parent
        else if(del == del.parent.left){
            del.parent.left = leaf;
        }
        //make proper connections of leaf node with del node's parent
        leaf.parent = del.parent;
    }

    // find successor for replacement while deletion
    private Node successor(Node node){
        while(node.left != TNULL){
            node = node.left;
        }
        return node;
    }

    // function to delete a node from re-black tree
    public void delete(int value){
        Node node = this.root;
        Node current = TNULL;
        Node db, del;
        Color originalColor;
        //traverse the tree to find the node that contains the value to be deleted
        while(node != TNULL){
            if(node.value == value){
                current = node;
                break;
            }
            if(node.value < value){
                node = node.right;
            }
            else{
                node = node.left;
            }
        }

        //if value to be deleted not present in the tree
        if(current == TNULL){
            System.out.println(value + " not present in the tree!");
            return;
        }

        //BST deletion, we don't delete internal nodes, replace node to be deleted with appropriate predecessor/successor,
        //and then delete that leaf node
        del = current;
        originalColor = del.color;
        if(current.left == TNULL){
            db = current.right;
            replace(current, current.right);
        }
        else if(current.right == TNULL){
            db = current.left;
            replace(current, current.left);
        }
        else{
            del = successor(current.right);
            originalColor = del.color;
            db = del.right;
            if(del.parent == current){
                db.parent = del;
            }
            else{
                replace(del, del.right);
                del.right = current.right;
                del.right.parent = del;
            }
            replace(current, del);
            del.left = current.left;
            del.left.parent = del;
            del.color = current.color;
        }
        //if double black case arises, adjust tree
        if(originalColor == Color.BLACK){
            adjustTreeDeletion(db);
        }
    }



    private void rotateLeft(Node node){
        Node nnode = node.right;
        Node c_nnode = nnode.left;
        node.right = c_nnode;
        if(c_nnode != TNULL){
            c_nnode.parent = node;
        }
        //make node's parent as "nnode's" parent
        nnode.parent = node.parent;
        //if node was root node, make "nnode" as root node
        if(node.parent == null){
            this.root = nnode;
        }
        //if node was left child of it's parent, make "nnode" as left child of node's parent
        else if(node == node.parent.left){
            node.parent.left = nnode;
        }
        //if node was right child of it's parent, make "nnode" as right child of node's parent
        else if(node == node.parent.right){
            node.parent.right = nnode;
        }
        //make node's parent as nnode
        node.parent = nnode;
        nnode.left = node;
    }

    private void rotateRight(Node node){
        Node nnode = node.left;
        Node c_nnode = nnode.right;
        node.left = c_nnode;
        if(c_nnode != TNULL){
            c_nnode.parent = node;
        }
        //make node's parent as "nnode's" parent
        nnode.parent = node.parent;
        //if node was root node, make "nnode" as root node
        if(node.parent == null){
            this.root = nnode;
        }
        //if node was left child of it's parent, make "nnode" as left child of node's parent
        else if(node == node.parent.left){
            node.parent.left = nnode;
        }
        //if node was right child of it's parent, make "nnode" as right child of node's parent
        else if(node == node.parent.right){
            node.parent.right = nnode;
        }
        //make node's parent as nnode
        node.parent = nnode;
        nnode.right = node;
    }



    public int height(Node n) {
        if (n == null)
            return -1;
        return n.height;
    }



    public Node getRoot() {
        return root;
    }
    public boolean search(int val)
    {
        return search(root, val);
    }
    private boolean search(Node r, int val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            int rval = r.value;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
    public int countNodes()
    {
        return countNodes(root);
    }
    private int countNodes(Node r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            if(r.left.nill==false){
                l += countNodes(r.left);
            }
            if(r.right.nill==false){
                l += countNodes(r.right);
            }

            return l;
        }
    }


}