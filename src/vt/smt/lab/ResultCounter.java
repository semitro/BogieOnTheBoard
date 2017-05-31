package vt.smt.lab;

import java.util.LinkedList;
import java.util.List;

public class ResultCounter {
	List<Result> mainList = new LinkedList<>();
	ResultCounter(){
		
	}
	
	public void addResult(Result result){
		mainList.add(result);
	}
	
	public LinkedList<Result> getResultList(){
		return (LinkedList<Result>)mainList;
	}
	
	public static class Result{
		public float x1,x2,t1,t2;
		public Result(float x1, float x2, float t1, float t2){
			this.x1 = x1;
			this.x2 = x2;
			this.t1 = t1;
			this.t2 = t2;
		}
	}
}
