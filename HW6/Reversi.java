import java.util.*;

class State {
    char[] board;
    public int value;

    public State(char[] arr) {
        this.board = Arrays.copyOf(arr, arr.length);
    }

    public int getScore() {
    	int player1 = 0;
    	int player2 = 0;
    	for (int i = 0; i < this.board.length; i++){
    		if(this.board[i] == '1'){
    			player1++;
    		}
    		else if(this.board[i] == '2'){
    			player2++;
    		}
    	}
    	if(player1 < player2) return -1;
    	else if(player1 > player2) return 1;
    	else return 0;
    }
    
    public boolean isTerminal() {
    	boolean is_terminal = this.getSuccessors('1').length == 0 && this.getSuccessors('2').length == 0;
        return is_terminal;
    }
    
    public char[][] transferType(char[] board){
    	char[][] temp = new char[4][4];
    	int num = 0;
    	for (int i = 0; i < 4; i++){
    		for (int j = 0; j < 4; j++){
    			temp[i][j] = board[num];
    			num++;
    		}
    	}
		return temp;	
    }
    
    public char[] ToArray(char[][] curr){
    	char[] temp = new char[this.board.length];
    	int num = 0;
    	for (int i = 0; i < 4; i++){
    		for (int j = 0; j < 4; j++){
    			temp[num] = curr[i][j];
    			num++;
    		}
    	}
    	return temp;
    }
    
    public int flip(int i, int j, char[][] board, char player){
    	int place;
    	int yplace;
    	int m;
    	int n;
    	int result = 0;
    	
    	// check left up
       	place = -1;
    	yplace = -1;
    	for (m = i-1, n = j-1; m >= 0 && n >= 0; m--, n--){
            if (board[m][n] == '0') 
                break;
    		if(board[m][n] == player){
    			place = m;
    			yplace = n;
    			break;
    		}
    	}
    	if(place != -1 && yplace != -1 && board[i-1][j-1] != board[m][n]){
    		for (int a = i-1, b = j-1; a > place && b > yplace; a--, b--){
    			if(board[a][b] == '0') break;
    			board[a][b] = player; // flip
    			result = 1;
    			//System.out.println("nothing: " + i + " " + j + " " + board[i][j] + " " + a);
    		}
    	}
    	
    	// check up
    	place = -1;
    	for(m = i - 1; m >= 0; m--){
            if (board[m][j] == '0') 
                break;
    		if(board[m][j] == player){
    			place = m;
    			break;
    		}
    	}
    	if(place != -1 && board[i - 1][j] != board[m][j]){
    		for (int a = i - 1; a > place; a--){
    			if(board[a][j] == '0') break;
    			board[a][j] = player; // flip
    			result = 1;
    			//System.out.println("nothing: " + i + " " + j + " " + board[i][j] + " " + place);
    		}
    	}
    	
       	// check right up
    	place = -1;
    	yplace = -1;
    	for (m = i-1, n = j+1; m >= 0 && n < 4; m--, n++){
            if (board[m][n] == '0') 
                break;
    		if(board[m][n] == player){
    			place = m;
    			yplace = n;
    			//System.out.println(m + " " + n);
    			break;
    		}
    	}
    	if(place != -1 && yplace != -1 && board[i-1][j+1] != board[m][n]){
    		for (int a = i-1, b = j+1; a > place && b < yplace; a--, b++){
    			if(board[a][b] == '0') break;
    			board[a][b] = player; // flip
    			result = 1;
    			//System.out.println("nothing: " + i + " " + j + " " + board[i][j] + " " + a);
    		}
    	}
    	
       	// check left
    	place = -1;
    	for(m = j - 1; m >= 0; m--){
            if (board[i][m] == '0') 
                break;
    		if(board[i][m] == player){
    			place = m;
    			break;
    		}
    	}
    	if(place != -1 && board[i][j - 1] != board[i][m]){
    		for (int a = j - 1; a > place; a--){
    			if(board[i][a] == '0') break;
    			board[i][a] = player; // flip
    			result = 1;
    			//System.out.println("nothing: " + i + " " + j + " " + board[i][j] + " " + a);
    		}
    	}
    	
    	// check right
    	place = -1;
    	for(m = j + 1; m < 4; m++){
            if (board[i][m] == '0') 
                break;
    		if(board[i][m] == player){
    			place = m;
    			break;
    		}
    	}
    	if(place != -1 && board[i][j + 1] != board[i][m]){
    		for (int a = j + 1; a < place; a++){
    			if(board[i][a] == '0') break;
    			board[i][a] = player; // flip
    			//System.out.println("nothing: " + i + " " + j + " " + board[i][j] + " " + a);
    		}
    		result = 1;
    	}
    	
       	// check left down
       	place = -1;
    	yplace = -1;
    	for (m = i+1, n = j-1; m < 4 && n >= 0; m++, n--){
            if (board[m][n] == '0') 
                break;
    		if(board[m][n] == player){
    			place = m;
    			yplace = n;
    			break;
    		}
    	}
    	if(place != -1 && yplace != -1 && board[i+1][j-1] != board[m][n]){
    		for (int a = i+1, b = j-1; a < place && b > yplace; a++, b--){
    			if(board[a][b] == '0') break;
    			board[a][b] = player; // flip
    			result = 1;
    			//System.out.println("nothing: " + i + " " + j + " " + board[i][j] + " " + a);
    		}
    	}
    	
    	// check down
    	place = -1;
    	for(m = i + 1; m < 4; m++){
            if (board[m][j] == '0') 
                break;
    		if(board[m][j] == player){
    			place = m;
    			break;
    		}
    	}
    	if(place != -1 && board[i + 1][j] != board[m][j]){
    		for (int a = i + 1; a < place; a++){
    			if(board[a][j] == '0') break;
    			board[a][j] = player; // flip
    			result = 1;
    			//System.out.println("nothing: " + i + " " + j + " " + board[i][j] + " " + a);
    		}
    	}
    	
    	
    	// check right down
    	place = -1;
    	yplace = -1;
    	for (m = i+1, n = j+1; m < 4 && n < 4; m++, n++){
            if (board[m][n] == '0') 
                break;
    		if(board[m][n] == player){
    			place = m;
    			yplace = n;
    			break;
    		}
    	}
    	if(place != -1 && yplace != -1 && board[i+1][j+1] != board[m][n]){
    		for (int a = i+1, b = j+1; a < place && b < yplace; a++, b++){
    			if(board[a][b] == '0') break;
    			board[a][b] = player; // flip
    			result = 1;
    			//System.out.println("nothing: " + i + " " + j + " " + board[i][j] + " " + a);
    		}
    	}
    	return result;
    }

    public State[] getSuccessors(char player) {
    	List<State> successors = new ArrayList<>();
    	char[][] curr = new char[4][4];
		curr = transferType(this.board);
    	for (int i = 0; i < 4; i++){
    		for (int j = 0; j < 4; j++){
    			if(curr[i][j] != '0'){
    				continue;
    			}
    			else {
    				curr[i][j] = player;
    				if(flip(i,j,curr,player) == 1){
    					//System.out.println(i + " " + j); 
    					successors.add(new State(ToArray(curr)));
    				}
    				curr = transferType(this.board);
    				
    			}
    		}
    	}
    	State[] Ssuccessors= new State[successors.size()];
		Ssuccessors= successors.toArray(Ssuccessors);
        return Ssuccessors;
    }
 
    public void printState(int option, char player) {

        // TO DO: print a State based on option (flag)
		if(option == 1){
			State[] successors = this.getSuccessors(player);
			if(successors.length != 0){
				for (int i = 0; i < successors.length; i++){
					System.out.println(successors[i].getBoard());
					//System.out.println(successors[i].getScore());
				} 
			}
			else if(successors.length == 0 && !this.isTerminal()){
				System.out.println(this.getBoard());
			}
				
		}
		else if(option == 2){
			if (!this.isTerminal()){
				System.out.println("non-terminal");
			}
			else {
				System.out.println(this.getScore());
			}
		}
		else if(option == 3){
            System.out.println(Minimax.run(this, player));
            System.out.println(Minimax.num);
		}
		else if(option == 4){
			State[] successors = getSuccessors(player);
            if (successors.length == 0 && !this.isTerminal()) {
                System.out.println(this.getBoard());
            } else{
            	int max = Integer.MIN_VALUE;
            	int min = Integer.MAX_VALUE;
            	int index = -1;
            	int value;
            	if(player == '1'){
            		for (int i = 0; i < successors.length; i++){
            			if ((value = Minimax.run(this, player)) > max){
            				max = value;
            				index = i;
            			}
            		}
            	}
            	else {
            		for(int i = 0; i < successors.length; i++){
            			if((value = Minimax.run(this, player)) < min){
            				min = value;
            				index = i;
            			}
            		}
            	}
            	if(index != -1){
            		System.out.println(successors[index].getBoard());
            	}
            }
		}
		else if(option ==  5){
            System.out.println(Minimax.run_with_pruning(this, player));
            System.out.println(Minimax.num);
		}
		else if(option == 6){
			State[] successors = getSuccessors(player);
            if (successors.length == 0 && !this.isTerminal()) {
                System.out.println(this.getBoard());
            } else{
            	int max = Integer.MIN_VALUE;
            	int min = Integer.MAX_VALUE;
            	int index = -1;
            	int value;
            	if(player == '1'){
            		for (int i = 0; i < successors.length; i++){
            			if ((value = Minimax.run_with_pruning(this, player)) > max){
            				max = value;
            				index = i;
            			}
            		}
            	}
            	else {
            		for(int i = 0; i < successors.length; i++){
            			if((value = Minimax.run_with_pruning(this, player)) < min){
            				min = value;
            				index = i;
            			}
            		}
            	}
            	if(index != -1){
            		System.out.println(successors[index].getBoard());
            	}
            }
		}
    }

    public String getBoard() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            builder.append(this.board[i]);
        }
        return builder.toString().trim();
    }

    public boolean equals(State src) {
        for (int i = 0; i < 16; i++) {
            if (this.board[i] != src.board[i])
                return false;
        }
        return true;
    }
}

class Minimax {
	public static int num = 0;
	
	private static int max_value(State curr_state) {
		num++;
		int a = 0;
		if (curr_state.isTerminal())
			return curr_state.getScore();
		else{
			State[] successors = curr_state.getSuccessors('1');
			a = Integer.MIN_VALUE;
			if (successors.length == 0) {
				return min_value(curr_state);
			}
			for(State successor : successors){
				a = Math.max(a, min_value(successor));
				//System.out.println(a);
			}
			return a;
		}
	}
	
	private static int min_value(State curr_state) {
		num++;
		int b = 0;
		if (curr_state.isTerminal()){
			//System.out.println(curr_state.getScore());
			return curr_state.getScore();
		}
		else{
			State[] successors = curr_state.getSuccessors('2');
			b = Integer.MAX_VALUE;
			if (successors.length == 0) {
				return max_value(curr_state);
			}
			for(State successor : successors){
				b = Math.min(b, max_value(successor));
			}
			return b;
		}
	}

	
	
	private static int max_value_with_pruning(State curr_state, int alpha, int beta) {
		num++;
		
		if (curr_state.isTerminal()){
			return (curr_state.getScore());
		}
		else{
			State[] successors = curr_state.getSuccessors('1');
			if (successors.length == 0){
				return min_value_with_pruning(curr_state, alpha, beta);
			}
			for(State successor : successors){
				alpha = Math.max(alpha,min_value_with_pruning(successor, alpha,beta));
				if (alpha >= beta)
					return beta;
			}
			return alpha;
		}
	}
	
	private static int min_value_with_pruning(State curr_state, int alpha, int beta) {
		num++;
		
		if (curr_state.isTerminal()){
			return (curr_state.getScore());
		}
		else{
			State[] successors = curr_state.getSuccessors('2');
			if (successors.length == 0){
				return max_value_with_pruning(curr_state, alpha, beta);
			}
			for(State successor : successors){
				beta = Math.min(beta,max_value_with_pruning(successor, alpha,beta));
				if (alpha >= beta)
					return alpha;  
			}
			return beta;
		}
	}
	
	
	public static int run(State curr_state, char player) {
		if (player == '1'){
			return Minimax.max_value(curr_state);
		} else {
			return Minimax.min_value(curr_state);
		}

	}

	public static int run_with_pruning(State curr_state, char player){
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		if(player == '1'){
			return max_value_with_pruning(curr_state, alpha, beta);
		} else {
			return min_value_with_pruning(curr_state, alpha, beta);
		}
	}
}


public class Reversi {
    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }
        int flag = Integer.valueOf(args[0]);
        char[] board = new char[16];
        for (int i = 0; i < 16; i++) {
            board[i] = args[2].charAt(i);
        }
        int option = flag / 100;
        char player = args[1].charAt(0);
        if ((player != '1' && player != '2') || args[1].length() != 1) {
            System.out.println("Invalid Player Input");
            return;
        }
        State init = new State(board);
        init.printState(option, player);
    }
}
