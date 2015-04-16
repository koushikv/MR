package com.cloudwick.team15.BulkSMSProj;

/**
 * Created by kaushik on 2/23/15.
 */

        import org.apache.hadoop.io.LongWritable;
        import org.apache.hadoop.io.Text;
        import org.apache.hadoop.mapreduce.Mapper;
        import java.io.IOException;


public class DelivaryDetailsMapper extends Mapper<LongWritable,Text,Text,Text> {

    private String cellNumber,tag="CD~";

    @Override
    protected void map(LongWritable offset, Text input, Context context) throws IOException, InterruptedException {
        String[] str =input.toString().split(",");

        context.write(new Text(str[0].trim()),new Text((str[1].trim())+tag));

    }
}
