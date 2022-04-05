# RBtrees

Data Structures are a specialized means of organizing and storing data in computers in such a way that can perform operations on the stored data more efficiently. Out of the numerous data structures present, binary search trees play an important role when it comes to efficient operations. In computer science, a self-balancing binary search tree is any node-based binary search tree that automatically keeps its height (maximal number of levels below the root) small in the face of arbitrary item insertions and deletions. Examples of these are AVL trees, Red-Black trees, Splay trees etc. A red-black tree is a special kind of self-balancing binary search tree where each node has an extra bit, and that bit is often interpreted as the color (red or black). These colors are used to ensure that tree remains balanced during insertions and deletions. In this project we attempt to implement the red-black tree and investigate its efficiency by analyzing time and space complexity. The end goal is then to visualize the red-black tree operations with the help of a user interface.

## Installation

1) JavaFX

```bash
https://docs.oracle.com/javafx/2/installation/jfxpub-installation.htm
```

## Usage

```java
import javafx.*;

# opens up A GUI
javac RBtrees.java
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

![rotation in RB trees](https://github.com/rb-rishika/RBtrees/blob/main/Capture.PNG)
