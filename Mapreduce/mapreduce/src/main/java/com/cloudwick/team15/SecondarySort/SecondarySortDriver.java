package com.cloudwick.team15.SecondarySort;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
/**
 * Created by kaushik on 2/24/15.
 */
public class SecondarySortDriver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.out
                    .printf("Two parameters are required for SecondarySortBasicDriver- <input dir> <output dir>\n");
            return -1;
        }

        Job job = new Job(getConf());
        job.setJobName("Secondary sort example");

        job.setJarByClass(SecondarySortDriver.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(SecondarySortMapper.class);
        job.setMapOutputKeyClass(SecondarySort.class);
        job.setMapOutputValueClass(Text.class);
        job.setPartitionerClass(SecondarySortBasicPartitioner.class);
        job.setSortComparatorClass(SecondarySortBasicSortComparator.class);
        job.setGroupingComparatorClass(GroupingComparator.class);
        job.setReducerClass(SecondarySortReducer.class);
        job.setOutputKeyClass(SecondarySort.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(2);
        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Configuration(),
                new SecondarySortDriver(), args);
        System.exit(exitCode);
    }
}
