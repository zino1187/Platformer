package main;

import java.util.ArrayList;

public class ObjectManager {
	ArrayList<GameObject> objectList;
	
	public ObjectManager() {
		objectList= new ArrayList();
	}
	
	public void addObject(GameObject obj){
		objectList.add(obj);
	}
	public void removeObject(GameObject obj) {
		objectList.remove(obj);
	}

}
