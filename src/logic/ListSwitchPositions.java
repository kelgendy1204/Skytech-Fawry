package logic;

import java.util.LinkedList;

public class ListSwitchPositions {
	
	
	public <T> void switchUp(LinkedList<T> list,T t){
		try {
			T upperT;
			int positionOfT = list.indexOf(t);
			int positionOfUpperT = positionOfT - 1;
			if (positionOfUpperT == -1){
				throw (new IndexOutOfBoundsException());
			}
			upperT = list.get(positionOfUpperT);
			list.set(positionOfT, upperT);
			list.set(positionOfUpperT, t);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public <T> void switchDown(LinkedList<T> list,T t){
		try {
			int size = list.size(); 
			T lowerT;
			int positionOfT = list.indexOf(t);
			int positionOfLowerT = positionOfT + 1;
			if(positionOfLowerT == size){
				throw (new IndexOutOfBoundsException());
			}
			lowerT = list.get(positionOfLowerT);
			list.set(positionOfT, lowerT);
			list.set(positionOfLowerT, t);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
