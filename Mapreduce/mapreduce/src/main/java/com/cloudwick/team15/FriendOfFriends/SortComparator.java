package com.cloudwick.team15.FriendOfFriends;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by kaushik on 2/24/15.
 */
public class SortComparator implements Writable {



    private String keyFriendName;
    private String hisFriend;
  /*  int result=0;
    int result1=0;*/
  public String getKeyFriendName() {
      return keyFriendName;
  }

    public String getHisFriend() {
        return hisFriend;
    }

    public SortComparator() {
        this("No","No");
    }

    public SortComparator(String keyFriendName, String hisFriend) {
        this.keyFriendName = keyFriendName;
        this.hisFriend = hisFriend;
    }
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        WritableUtils.writeString(dataOutput, getKeyFriendName());
        WritableUtils.writeString(dataOutput, getHisFriend());

    }

    @Override
    public String toString() {
        return ( " toUser: "+getKeyFriendName()+"\t"+"mutual Friend"+getHisFriend()).toString();
    }

    public void readFields(DataInput dataInput) throws IOException {
        keyFriendName = WritableUtils.readString(dataInput);
        hisFriend = WritableUtils.readString(dataInput);
    }


   /* public int compareTo(SortComparator objKeyPair) {

        result = keyFriendName.compareTo(objKeyPair.hisFriend);
        if (0 == result) {
            result1 = hisFriend.compareTo(objKeyPair.keyFriendName);


        }
        return result;
    }*/


}
