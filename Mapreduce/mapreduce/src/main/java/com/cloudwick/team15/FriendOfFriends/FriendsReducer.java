package com.cloudwick.team15.FriendOfFriends;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.codehaus.jackson.xc.DomElementJsonSerializer;

/**
 * Created by kaushik on 2/24/15.
 */


public class FriendsReducer
        extends
        Reducer<Text, SortComparator,Text, Text> {
HashMap<String,List<String>> friends=new HashMap<String, List<String>>();
    @Override
    public void reduce(Text key, Iterable<SortComparator> values,
                       Context context) throws IOException, InterruptedException {

        for(SortComparator val:values)
        {
           final Boolean isAlreadyFriend=(val.getHisFriend().equals("No"));
           final String toUser=val.getKeyFriendName();
            final String mutualFriend=val.getHisFriend();
            ArrayList<String> frnlist=new ArrayList<String>();
            if(friends.containsKey(toUser))
            {
                if(isAlreadyFriend)
                {
                    friends.put(toUser,null);
                }
                else if (friends.get(toUser) != null) {
                    frnlist.add(mutualFriend);
                    friends.put(toUser,frnlist);
                }
                else
                {
                    continue;
                }
            }
            else{

                if(!isAlreadyFriend){
                    frnlist.add(mutualFriend);
                    friends.put(toUser,frnlist);
                     }

                else
                {
                    friends.put(toUser, null);
                }

            }
        }
       /* SortedMap<String,List<String>> sortedFriends=new TreeMap<String, List<String>>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int cnt1=friends.get(o1).size();
                        int cnt2=friends.get(o2).size();
                        if(cnt1>cnt2)
                        {
                            return -1;
                        }
                        else if(cnt1>cnt2&&(o1.equals(o2)))
                        {
                            return -1;
                        }
                        else
                        {
                            return 1;
                        }

                    }
                });*/
        for (Map.Entry<String, List<String>> entry : friends.entrySet()) {
            if (entry.getValue() != null) {
              // sortedFriends.put(entry.getKey(), entry.getValue());
            context.write(new Text(entry.getKey()),new Text(entry.getValue().toString()));

            }
        }

        /*int i=0;
        String output="";
        for (java.util.Map.Entry<String, List<String>> entry : sortedFriends.entrySet()) {
            if (i == 0) {
                output = entry.getKey().toString() + " (" + entry.getValue().size() + ": " + entry.getValue() + ")";
            } else {
                output += "," + entry.getKey().toString() + " (" + entry.getValue().size() + ": " + entry.getValue() + ")";
            }
            ++i;
        }*/
       // context.write(key, new Text(output));

    }
}
