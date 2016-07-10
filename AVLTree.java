public class AVLTree {

		private Node _root;
		
		/**
		 * Constructor for AVLTree. Setting _root to null. 
		 */
		public AVLTree(){
			_root = null;
		}
		
		
		/**
		 * Getter method for instance variable _root
		 * @return: the root of this AVLTree
		 */
		public Node getRoot() {
			return _root;
		}
		

		/**
		 * Setter method for instance variable _root 
		 * @param Node root
		 */
		public void setRoot(Node root) {
			this._root = root;
		}
		
		/**
		 * This methods returns the height of the node's tallest child.
		 * @param Node node
		 * @return: the height of the node's tallest child
		 */
		public int maxChildHeight(Node node)
		{
			if (heightOf(node._left) > heightOf(node._right))
			{
				return (heightOf(node._left));
			}
			else 
			{
				return (heightOf(node._right));
			}
			
		}
		
		/**
		 * This method returns the height of the node. 
		 * @param Node node
		 * @return: zero if node is null. Otherwise, node._height.
		 */
		public int heightOf(Node node)
		{
			if(node == null)
			{
				return 0; 
			}
			else 
			{
				return node._height;
			}
		}
		
		/**
		 * This method determines if there is an imbalance at node.
		 * @param Node node
		 * @return: true iff the difference between the node's left child's height and
		 * the node's right child's height is greater than 1. 
		 */
		public boolean isImbalanced(Node node)
		{
			int difference = heightOf(node._left) - heightOf(node._right);
			if (difference < 0)
			{
				difference = difference * (-1);
			}
			return (difference > 1); 
		}
		
		/**
		 * This method performs a balancing operation at Node root. 
		 * @param Node root
		 * @return: the new root node of the subtree after the balancing operation. 
		 */
		public Node balance(Node root)
		{
			if (heightOf(root._left) > heightOf(root._right))
			{
				//Left Case 
				Node y = root._left;
				if (heightOf(y._left) >= heightOf(y._right))
				{
					//LL
					root = rightRotate(root); 
				}
				
				else
				{
					//LR
					Node bottom_fixed = leftRotate(y);
					root._left = bottom_fixed;
					root = rightRotate(root);
				}
			}
			else
			{
				//Right case
				Node y = root._right;
				if (heightOf(y._right) >= heightOf(y._left))
				{
					//RR
					root = leftRotate(root);
				}
				else
				{
					//RL
					Node bottom_fixed = rightRotate(y);
					root._right = bottom_fixed;
					root = leftRotate(root);
				}
			}
			return root;
		}
		
		/**
		 * This method performs a right rotation on node and then 
		 * adjusts the necessary heights. 
		 * @param Node node
		 * @return: the new root node of the subtree after the rotation operation. 
		 */
		public Node rightRotate(Node node)
		{
			Node b = node._left;
			node._left = b._right;
			b._right = node;
			//fix node's and b's heights because these are only nodes 
			//that had their subtrees altered possibly
			//must fix node's height first 
			node._height = (maxChildHeight(node)) + 1;
			b._height = (maxChildHeight(b)) + 1;
			return b; 
		}
		
		/**
		 * This method performs a left rotation on node and then 
		 * adjusts the necessary heights. 
		 * @param Node node
		 * @return: the new root node of the subtree after the rotation operation. 
		 */
		public Node leftRotate(Node node)
		{
			Node b = node._right;
			node._right = b._left;
			b._left = node;
			//fix node and b's heights because these are only node's 
			//that had their subtrees altered possibly
			//must fix node's height first
			node._height = (maxChildHeight(node)) + 1;
			b._height = (maxChildHeight(b)) + 1; 
			return b;
		}
		
		/**
		 * This method inserts a Node into this AVLTree by calling 
		 * the method insertGivenRoot(root, number).  
		 * @param Node number
		 */
		public void insert(Node number)
		{
			_root = insertGivenRoot(_root, number);
			System.out.println(number._data + " was inserted sucessfully!");
			
		}
		
		
		/**
		 * This method is called by the insert(number) method
		 * and does the work of inserting a Node into this AVLTree and adjusts the 
		 * heights of the nodes in this tree that are affected by this 
		 * insertion. In addition, this method will call the balance method
		 * if an imbalance is found after the insertion. 
		 * @param Node root
		 * @param Node number
		 * @return: the root Node of this AVLTree. 
		 */
		public Node insertGivenRoot(Node root, Node number)
		{
			if (root == null)
			{
				root = number;
			}
			
			else
			{
				if (number._data < root._data)
				{
					root._left = insertGivenRoot(root._left, number);
					if (isImbalanced(root))
					{
						root = balance(root);
					}
				}
				
				else if (number._data > root._data)
				{
					root._right = insertGivenRoot(root._right, number);
					if (isImbalanced(root))
					{
						root = balance(root);
					}
				}
			} 
			root._height = (maxChildHeight(root)) + 1;
			return root;	
		}
		
		
		/**
		 * This method searches the AVLTree for a node by calling the 
		 * searchGivenRoot(root, number) method. 
		 * @param Node number
		 * @return: true iff the Node number exists in the AVLTree. 
		 */
		public boolean search(Node number)
		{
			//I made this method for convenience so that you
			//don't need to have the root when calling the method search. 
			boolean found = searchGivenRoot(_root, number);
			if (found)
			{
				System.out.println("Found " + number._data + "!");
			}
			else
			{
				System.out.println(number._data + " does not exist in the BST.");
			}
			return found;
		}
		
		
		/**
		 * This method is called by the search(number) method and
		 * does the work of finding out if the Node number
		 * exists in this AVLTree. 
		 * @param Node root
		 * @param Node number
		 * @return: true iff the Node number exists in the AVLTree.
		 */
		public boolean searchGivenRoot(Node root, Node number)
		{
			//base case
			if (root == null)
			{
				return false ;
			}
			
			if (number._data < root._data) 
			{
				return searchGivenRoot(root._left, number);
			}
			else if (number._data > root._data)
			{
				return searchGivenRoot(root._right, number);
			}
			else
			{
				//if here means root._data==number._data
				return true; 
			}	
			
		}
		
		
		/**
		 * This method checks to see if Node number exists in the AVLTree
		 * and if it does then it deletes Node number from this AVLTree
		 * by calling the deleteGivenRoot(root, number) method.  
		 * @param Node number
		 */
		public void delete(Node number)
		{
			if (!search(number))
			{
				return; //exits this method
			}
			_root = deleteGivenRoot(_root, number);
			System.out.println(number._data + " deleted successfully!");
		}
		
		
		/**
		 * This method is called by delete(number) and does the work
		 * of deleting a Node from this AVLTree and adjusts the 
		 * heights of the nodes in this tree that are affected by this 
		 * deletion. In addition, this method will call the balance operation 
		 * so long as there exists an imbalance after deletion.  
		 * @param Node root
		 * @param Node number
		 * @return: the root Node of this AVLTree. 
		 */
		public Node deleteGivenRoot(Node root, Node number)
		{
			if ( number._data == root._data)
			{
				//this means you want to delete the current root 
				//CASE 1: root is a leaf
				if (root._left == null && root._right == null)
				{  
					return null;
				}
				
				//CASE 2: root has 2 children
				else if (root._left != null && root._right != null)
				{
					DeleteNodeWith2Children(root);
				}
				
				//CASE 3: root has 1 child
				else
				{
					return ChildOfNodeWith1Child(root);
				}
				
			}
			
			else if (number._data < root._data)
			{
				root._left = deleteGivenRoot(root._left, number);
				if (isImbalanced(root))
				{
					root = balance(root);
				}
			}
			
			else 
			{
				root._right = deleteGivenRoot(root._right, number);
				if (isImbalanced(root))
				{
					root = balance(root);	
				}
			}
			
			root._height = (maxChildHeight(root)) + 1;
			return root;
			
		}

		/**
		 * This method deletes a node, which has 2 children, from 
		 * the AVLTree. 
		 * @param Node root
		 */
		public void DeleteNodeWith2Children(Node root)
		{ 
			//find biggest number on left sub tree
			Node biggest_on_left = biggestOnLeftSubTree(root._left);
			int biggest_number = biggest_on_left._data;
			//now make root's data this biggest number
			root._data = biggest_number;
			//now delete this biggest number
			root._left = deleteGivenRoot(root._left, biggest_on_left);	
		}

		
		/**
		 * This method takes in a Node which has 1 child and returns
		 * a pointer to this 1 child. 
		 * @param Node root
		 * @return: a Node pointer to the child of the inputed
		 * node.
		 */
		public Node ChildOfNodeWith1Child(Node root)
		{
			if (root._left == null)
			{
					return root._right;
			}
			else	
			{
					return root._left;
			}
		}
		
		
		/**
		 * This method finds the biggest Node of the AVLTree rooted at 
		 * Node node. 
		 * @param Node node
		 * @return the biggest Node of the AVLTree rooted at Node node. 
		 */
		public Node biggestOnLeftSubTree(Node node)
		{
			//base case
			if (node._right == null)
			{
				return node;
			}
			else
			{
				return biggestOnLeftSubTree(node._right);
			}
		}
		
		/**
		 * This method prints the preorder traversal of the tree rooted at root.
		 * @param Node root
		 */
		public void PreOrderTraversal(Node root)
		{
			if(root == null)
			{
				return;
			}
			System.out.print(root._data + "  ");
			PreOrderTraversal(root._left);
			PreOrderTraversal(root._right);
		}
				
}