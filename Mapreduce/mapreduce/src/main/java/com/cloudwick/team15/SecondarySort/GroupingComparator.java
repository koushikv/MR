package com.cloudwick.team15.SecondarySort;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by kaushik on 2/24/15.
 */
public class GroupingComparator extends WritableComparator{
    public GroupingComparator()
    {
        super(SecondarySort.class, true);
    }
    public int compare(WritableComparable w1, WritableComparable w2) {
        SecondarySort key1 = (SecondarySort) w1;
        SecondarySort key2 = (SecondarySort) w2;
        return key1.getEmpNum().compareTo(key2.getEmpNum());
    }
}
