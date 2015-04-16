package com.cloudwick.team15.SecondarySort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

/**
 * Created by kaushik on 2/24/15.
 */
public class SecondarySortBasicPartitioner extends Partitioner<SecondarySort,Text> {

    public int getPartition(SecondarySort key, Text value,
                            int numReduceTasks) {

        return (key.getEmpNum().hashCode() % numReduceTasks);

    }
}
