package com.cloudwick.team15.Hbasesample;

/**
 * Created by kaushik on 3/30/15.
 */
import java.io.IOException;        import java.util.ArrayList;
        import java.util.List;

        import org.apache.hadoop.conf.Configuration;
        import org.apache.hadoop.hbase.HBaseConfiguration;
        import org.apache.hadoop.hbase.client.HTable;
        import org.apache.hadoop.hbase.client.Put;
        import org.apache.hadoop.hbase.util.Bytes;

public class HbasePut {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        //conf.set("yarn-master", "172.17.0.150:218");
        conf.set("hbase.zookeeper.quorum","172.17.0.150");
        conf.set("hbase.zookeeper.property.clientPort", "2181");


        HTable table = new HTable(conf, "mytable");

        List<Put> puts = new ArrayList<Put>();

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.add(Bytes.toBytes("cf1"), Bytes.toBytes("col1"),
                Bytes.toBytes("kaushik"));
        puts.add(put1);

        Put put2 = new Put(Bytes.toBytes("row2"));
        put2.add(Bytes.toBytes("cf1"), Bytes.toBytes("col2"),
                Bytes.toBytes("karthik"));
        puts.add(put2);

        Put put3 = new Put(Bytes.toBytes("row3"));
        put3.add(Bytes.toBytes("cf1"), Bytes.toBytes("col3"),
                Bytes.toBytes("kiran"));
        puts.add(put3);

        table.put(puts);

        table.close();
    }
}



