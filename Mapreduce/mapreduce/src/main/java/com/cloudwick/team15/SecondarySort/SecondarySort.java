package com.cloudwick.team15.SecondarySort;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by kaushik on 2/24/15.
 */
public class SecondarySort implements Writable,
        WritableComparable<SecondarySort> {
    private String empNum;
    private String empIdPair;

public SecondarySort()
{

}
    public  SecondarySort(String empNum,String empIdPair)
    {
        this.empNum=empNum;
        this.empIdPair=empIdPair;
    }
    public String toString() {
        return (new StringBuilder().append(empNum).append("\t")
                .append(empIdPair)).toString();
    }

    public void readFields(DataInput dataInput) throws IOException {
        empNum = WritableUtils.readString(dataInput);
        empIdPair = WritableUtils.readString(dataInput);
    }

    public void write(DataOutput dataOutput) throws IOException {
        WritableUtils.writeString(dataOutput, empNum);
        WritableUtils.writeString(dataOutput, empIdPair);
    }

    public  int compareTo(SecondarySort objKeyPair){
        int result = empNum.compareTo(objKeyPair.empNum);
        if (0 == result) {
            result = empIdPair.compareTo(objKeyPair.empIdPair);
        }
        return result;
    }





    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getEmpIdPair() {
        return empIdPair;
    }

    public void setEmpIdPair(String empIdPair) {
        this.empIdPair = empIdPair;
    }
}