package com.cloudwick.team15.BulkSMSProj;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SmsDriver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

       /* if (args.length != 2) {
            System.out.printf("Two parameters are required- <input dir> <output dir>\n");
            return -1;
        }
*/
        Job job = new Job(getConf());
        Configuration conf = job.getConfiguration();
        job.setJobName("Map-side join with text lookup file in DCache");
        DistributedCache.addCacheFile(new URI("/home/kaushik/IdeaProjects/mapreduce/Input/DelivaryStatusCodes"),conf);

        job.setJarByClass(SmsDriver.class);

//specifying the custom reducer class
        job.setReducerClass(SmsReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

      //Specifying the input directories(@ runtime) and Mappers independently for inputs from multiple sources
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, UserDetailsMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, DelivaryDetailsMapper.class);

//Specifying the output directory @ runtime
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Configuration(),new SmsDriver(), args);
        System.exit(exitCode);
    }
}