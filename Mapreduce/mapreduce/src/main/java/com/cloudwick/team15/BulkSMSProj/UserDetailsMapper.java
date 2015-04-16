package com.cloudwick.team15.BulkSMSProj;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

/**
 * Created by kaushik on 2/23/15.
 */
public class UserDetailsMapper extends Mapper<LongWritable,Text,Text,Text> {

    private String cellNumber,tag="CD~";

    @Override
    protected void map(LongWritable offset, Text input, Context context) throws IOException, InterruptedException {
     String[] str =input.toString().split(",");

        context.write(new Text(str[0]),new Text(str[1]+tag));
    }
}
