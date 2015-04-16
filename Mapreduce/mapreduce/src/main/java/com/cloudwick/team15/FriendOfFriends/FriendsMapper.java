package com.cloudwick.team15.FriendOfFriends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaushik on 2/24/15.
 */
public class FriendsMapper extends Mapper<LongWritable,Text,Text,SortComparator>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String person1 = value.toString();
        String[] persons = person1.split("-");
        //String fromUser=persons[0];
        List<String> toUsers=new ArrayList<String>();
        if(persons.length==2) {
            String[] listOfFriends = persons[1].split(" ");
            int i=0;
            for (String frn : listOfFriends) {

               toUsers.add(frn);
                context.write(new Text(persons[0].toString()),new SortComparator(toUsers.get(i),"No"));
                i++;
            }
        }
        for(int i=0;i<toUsers.size();i++)
        {
            for(int j=i+1;j<toUsers.size();j++)
            {
                context.write(new Text(toUsers.get(i)),new SortComparator(toUsers.get(j),persons[0].toString()));
                context.write(new Text(toUsers.get(j)),new SortComparator(toUsers.get(i),persons[0].toString()));
            }
        }



    }
}
