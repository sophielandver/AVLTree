import java.util.Scanner;


public class AVLDriver {

	/**
	 * This main function reads in instructions on what to do with a 
	 * AVL tree. The instructions must be given in the format 
	 * "insert n" or "delete n" or "search n" or "traverse" or "exit" where n is an
	 * integer. This method will continually accept instructions 
	 * until exit is entered. 
	 * @param args
	 */
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		String command;
		String operation = " ";
		Scanner input = new Scanner(System.in);
		do 
		{
			command = input.nextLine();
			String[] command_array = command.split(" ");
			operation = command_array[0];
			
			if (operation.equals("insert"))
			{
				int number = Integer.parseInt(command_array[1]);
				Node node = new Node(number);
				tree.insert(node);
				
			}
			
			else if (operation.equals("delete"))
			{
				int number = Integer.parseInt(command_array[1]);
				Node node = new Node(number);
				tree.delete(node);
			}
			
			else if (operation.equals("search"))
			{
				int number = Integer.parseInt(command_array[1]);
				Node node = new Node(number);
				tree.search(node);
			}
			
			else if (operation.equals("traverse"))
			{
				tree.PreOrderTraversal(tree.getRoot());
				System.out.println();
			}
			
		} while (!(operation.equals("exit")));
		
		System.out.println("Exiting!");
		input.close();
		
	
	}

}

