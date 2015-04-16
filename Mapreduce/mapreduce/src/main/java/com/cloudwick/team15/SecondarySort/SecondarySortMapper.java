package com.cloudwick.team15.SecondarySort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by kaushik on 2/24/15.
 */
public class SecondarySortMapper extends Mapper<LongWritable,Text,SecondarySort,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //super.map(key, value, context);
if(value.toString().length()>0)
{
    String[] arrEmpSal=value.toString().split(",");

    context.write(new SecondarySort(arrEmpSal[0].toString(),arrEmpSal[1].toString()),new Text(arrEmpSal[2].toString()));
//context.write(new SecondarySort("sad","hadppy"), new Text("kau"));
        System.out.println("test");
}



    }
}
