package com.cloudwick.team15.SecondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by kaushik on 2/24/15.
 */
public class SecondarySortBasicSortComparator  extends WritableComparator {

    public SecondarySortBasicSortComparator() {
        super(SecondarySort.class, true);
    }

    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        SecondarySort s1 = (SecondarySort) w1;
        SecondarySort s2 = (SecondarySort) w2;
        int cmpResult=s1.getEmpNum().compareTo(s2.getEmpNum());
        if(cmpResult==0)
        {
            return -s1.getEmpIdPair().compareTo(s2.getEmpIdPair());
        }
        return cmpResult;
    }




}
