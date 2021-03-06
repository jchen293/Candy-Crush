package code.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Board {
	private ArrayList<ArrayList<String>> _board;
	private ArrayList<String> _colorFileNames;
	private Random _rand;
	
	private static int MAX_COLORS = 6; // max possible is 6

	public Board(int rows, int cols) {
		_board = new ArrayList<ArrayList<String>>();
		_rand = new Random();
		_colorFileNames = new ArrayList<String>();
		for (int i=0; i<MAX_COLORS; i=i+1) {
			_colorFileNames.add("Images/Tile-"+i+".png");
		}
		for (int r=0; r<rows; r=r+1) {
			ArrayList<String> row = new ArrayList<String>();
			for (int c=0; c<cols; c=c+1) {
				row.add(_colorFileNames.get(_rand.nextInt(_colorFileNames.size())));
			}
			_board.add(row);
		}
	}

	public int rows() { 
		return _board.size(); 
		}
	public int cols() { 
		return _board.get(0).size();
		}

	public String get(Point p) {
		return _board.get(p.x).get(p.y);
	}

	private String set(Point p, String s) {
		return _board.get(p.x).set(p.y, s);
	}

	public void exchange(Point p, Point q) {
		String temp = get(p);
		set(p, get(q));
		set(q, temp);
		if (match().size() > 0) {
			System.out.println("The board has a match.");
		}
		else {
			System.out.println("The board has no match.");
		}
		
		
	}
	
	private HashSet<Point> match() {
		return match(3);
	}

	private HashSet<Point> match(int runLength) {
		HashSet<Point> matches = verticalMatch(runLength);
		matches.addAll(horizontalMatch(runLength));
		return matches;
	}
	
	
	
	
	

	private HashSet<Point> horizontalMatch(int runLength) {
		HashSet<Point> matches = new HashSet<Point>();
		int minCol = 0;
		int maxCol = cols() - runLength;
		for (int r = 0; r < rows(); r = r + 1) {
			for (int c = minCol; c <= maxCol; c = c + 1) {  // The cols we can START checking in
				HashSet<String> values = new HashSet<String>();
				HashSet<Point> points = new HashSet<Point>();
				for (int offset = 0; offset < runLength; offset = offset + 1) {
					Point p = new Point(r,c+offset);
					points.add(p);
					String s = get(p);
					values.add(s);
				}
				if (values.size() == 1) { matches.addAll(points); }
				
				
			}
		}
		return matches;
	}
public void replace(){
	while(horizontalMatch(0)==verticalMatch(0)){
		new Board(5, 5);
		
		
	}
}
	private HashSet<Point> verticalMatch(int runLength) {
		HashSet<Point> matches = new HashSet<Point>();
		int minRow = 0;
		int maxRow = rows() - runLength;
		for (int c = 0; c < cols(); c = c + 1) {
			for (int r = minRow; r <= maxRow; r = r + 1) {  // The rows we can START checking in
				HashSet<String> values = new HashSet<String>();
				HashSet<Point> points = new HashSet<Point>();
				for (int offset = 0; offset < runLength; offset = offset + 1) {
					Point p = new Point(r+offset,c);
					points.add(p);
					String s = get(p);
					values.add(s);
				}
				if (values.size() == 1) { matches.addAll(points); }
			}
		}
		return matches;
	}


}
