import java.util.*;

class State {
	int[] board;
	State parentPt;
	int depth;

	public State(int[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
		this.parentPt = null;
		this.depth = 0;
	}
	
	public void switchNum(int i, int j)
	{
		this.board[i] = this.board[j];
		this.board[j] = 0;
	}
	
	public int compareTo(State a){
		for(int i = 0; i < 9; i++){
			if (this.board[i] == a.board[i])
				continue;
			else if (this.board[i] < a.board[i])
				return 1; //this < a
			else return -1; //this > a
		}
		return 0;
	}
	
	public State[] getSuccessors() {
		State[] successors = new State[4];
		for (int i = 0; i < 4; i++){
			successors[i] = new State(this.board);
		}
		int zero = 0;
		for (int i = 0; i < 9; i++){
			if (this.board[i] == 0){
				zero = i;
				break;
			}
		}
		if((zero + 1) % 3 != 0){ //change zero with right
				successors[0].switchNum(zero, zero + 1);
		}else{
			successors[0].switchNum(zero, zero - 2);
		}
		
		if(zero < 6){ //change zero with below
				successors[1].switchNum(zero, zero + 3);
		}else{
			successors[1].switchNum(zero, zero - 6);
		}
		
		if(zero % 3 != 0){ //change zero with left
				successors[2].switchNum(zero, zero - 1);
		}else {
			successors[2].switchNum(zero, zero + 2);
		}
		
		if(zero > 2){ //change zero with above
				successors[3].switchNum(zero, zero - 3);
		}else{
			successors[3].switchNum(zero, zero + 6);
		}
		
		for(int i = 0; i < successors.length; i++){
			successors[i].parentPt = this;
			successors[i].depth = this.depth + 1;
		}
		
		for(int i = 0; i < successors.length - 1; i++){
			for(int j = 0; j < successors.length - 1 - i; j++){
				State temp = successors[j];
				int isLarge = successors[j].compareTo(successors[j + 1]);
				if (isLarge == -1){ //successors j > j + 1
					successors[j] = successors[j + 1];
					successors[j + 1] = temp;
				}
			}
		}
		
		
		return successors;
	}

	public void printState(int option) {
		
		// TO DO: print a torus State based on option (flag)
		if((option == 1)||(option == 2)||(option == 4))
		{
			System.out.println(this.getBoard());
		}
		else if(option == 3)
		{
			if(this.parentPt == null){
				System.out.println(this.getBoard() + " parent 0 0 0 0 0 0 0 0 0");
			}else{
				System.out.println(this.getBoard() + " parent " + this.parentPt.getBoard());
			}
		}
	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			builder.append(this.board[i]).append(" ");
		}
		return builder.toString().trim();
	}

	public boolean isGoalState() {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != (i + 1) % 9)
				return false;
		}
		return true;
	}

	public boolean equals(State src) {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != src.board[i])
				return false;
		}
		return true;
	}
}

public class Torus {

	public static void main(String args[]) {
		if (args.length < 10) {
			System.out.println("Invalid Input");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		int[] board = new int[9];
		for (int i = 0; i < 9; i++) {
			board[i] = Integer.valueOf(args[i + 1]);
		}
		int option = flag / 100;
		int cutoff = flag % 100;
		if (option == 1) {
			State init = new State(board);
			State[] successors = init.getSuccessors();
			for (State successor : successors) {
				successor.printState(option);
			}
		} else {
			State init = new State(board);
			Stack<State> stack = new Stack<>();
			List<State> prefix = new ArrayList<>();
			int goalChecked = 0;
			int maxStackSize = Integer.MIN_VALUE;

			// needed for Part E
			while (true) {		
				prefix.clear();
				stack.push(init);
				while (!stack.isEmpty()) {
					//TO DO: perform iterative deepening; implement prefix list
					State current = stack.pop();
					
					//clear the list
					if (current.parentPt != null){
						int parent = prefix.indexOf(current.parentPt);
						prefix = new ArrayList<>(prefix.subList(0, parent + 1));
					}

					//add the current state to the list
					prefix.add(current);

					//check if goal
					goalChecked++;
					if(current.isGoalState())
						break;
					
					if (option == 2 || option == 3){
						current.printState(option);
					}
					
					if (option == 4 && current.depth == cutoff){
						break;
					}
					
					//get successors
					if(current.depth < cutoff){
						State[] successors = current.getSuccessors();
						
						for(int i = 0; i < successors.length; i++){
							//if already exist
							boolean isExist = false;
							for (int j = 0; j < prefix.size(); j++){
								if(successors[i].equals(prefix.get(j))){
									isExist = true;
								}
							}
							if (!isExist) 
								stack.push(successors[i]);
						}
					}
					
					//get maxStackSize
					if(maxStackSize < stack.size())
						maxStackSize = stack.size();
				}
				
				if (option == 4){
					for(int i = 0; i < prefix.size(); i++)
						prefix.get(i).printState(4);
				}
				
				if (option != 5)
					break;
				
				//TO DO: perform the necessary steps to start a new iteration
			        //       for Part E
				else{
					if(prefix.get(prefix.size() - 1).isGoalState()){
						for(int i = 0; i < prefix.size(); i++)
							prefix.get(i).printState(4);
						System.out.println("Goal-check " + goalChecked);
						System.out.println("Max-stack-size " + maxStackSize);
						break;
					}else{
						cutoff++;
					}
				}

			}
		}
	}
}
