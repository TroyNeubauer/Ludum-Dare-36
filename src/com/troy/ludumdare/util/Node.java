package com.troy.ludumdare.util;

import com.troy.troyberry.math.*;

public class Node {
	
	public Vector2i pos;
	public Node parent;
	public double fCost, gCost, hCost;
	
	public Node(Vector2i pos, Node parent, double gCost, double hCost) {
		
		this.pos = pos;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = gCost + hCost;
	}
	
	public boolean equals(Object other){
		if(!(other instanceof Node))return false;
		Vector2i vec = ((Node)other).pos;
		if(vec.equals(this.pos)){
			return true;
		}
		return false;
	}
}
