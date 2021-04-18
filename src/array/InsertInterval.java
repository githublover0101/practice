package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Interval {
    int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}

public class InsertInterval {

	//first insert, then <merge intervals>
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
       //sort intervals
       Collections.sort(intervals, new IntervalComparator());
        
       int i = 0;
       int len = intervals.size();
       while(i < len && newInterval.start >= intervals.get(i).start) i++;
       if(i < len) {
           int k = len;
           while(k > i) {
               intervals.set(k, intervals.get(k-1));
               k--;
           }
           intervals.set(i, newInterval);
       } else {
           intervals.add(newInterval);
       }
       return merge(intervals);
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        //inplace remove or update
        for(int i = 0; i < intervals.size()-1; ) {
            Interval one = intervals.get(i);
            Interval two = intervals.get(i+1);
            if((one.start <= two.start && two.start <= one.end) || (two.start <= one.start && one.start <= two.end)) { //needs merge
                Interval newInterval = mergeTwoInterval(one, two);
                intervals.remove(i+1); //remove i+1
                intervals.set(i,newInterval); //update i
            } else {
                i++;
            }
        }
        return intervals;
    }
    
    public Interval mergeTwoInterval(Interval one, Interval two) {
        int start = one.start < two.start ? one.start : two.start;
        int end = one.end < two.end ? two.end : one.end;
        Interval newInterval = new Interval(start, end);
        return newInterval;
    }
    
    class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval o1, Interval o2) {
            return o1.start - o2.start;
        }
    }
	
    public static void main(String[] args) {
    	List<Interval> intervals = new ArrayList<Interval>();
    	intervals.add(new Interval(1, 5));
    	Interval newInterval = new Interval(0, 3);
    	InsertInterval test = new InsertInterval();
//    	test.insert(intervals, newInterval);
    	
    	List<Integer> list = new ArrayList<Integer>();
    	list.add(0);
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	
    	list.add(1, 787);
//    	for(int e: list) {
//    		System.out.print(e + ",");
//    	}
    	
    	List<String> sList = new ArrayList<String>();
    	sList.add("a");
    	sList.add("b");
    	sList.add("a");
    	sList.remove("a");
    	for(String e: sList) {
    		System.out.println(e);
    	}
    }
}
