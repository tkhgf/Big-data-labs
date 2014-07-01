package hbase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import com.jscape.inet.scp.Scp;
import com.jscape.inet.scp.ScpException;
import com.jscape.inet.scp.events.*;
import com.jscape.inet.ssh.util.SshParameters;

@Path("generic")
public class test {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public test() {
        // TODO Auto-generated constructor stub
    }
  
    @GET
    @Produces("application/x-javascript")
    @Path("hbaseCreate/{tablename:.+}/{columnFamilies:.+}")
    public String hbaseCreate(@QueryParam("callback") String callback, @PathParam("tablename") String tablename,
    		@PathParam("columnFamilies") String columnFamilies) {
        String line="";
    	
        Configuration config = HBaseConfiguration.create();		 
		config.clear();
         config.set("hbase.zookeeper.quorum", "134.193.136.127");
         config.set("hbase.zookeeper.property.clientPort","2181");
         config.set("hbase.master", "134.193.136.127:60010");
		
         HBaseAdmin hba = null;
		try {
			// HBaseConfiguration hc = new HBaseConfiguration(new Configuration());
			
			  HTableDescriptor ht = new HTableDescriptor(tablename); 
			  
			  String cf1 = columnFamilies.split(":")[0];
			  String cf2 = columnFamilies.split(":")[1];
			  String cf3 = columnFamilies.split(":")[2];
			  String cf4 = columnFamilies.split(":")[3];
			  String cf5 = columnFamilies.split(":")[4];
			  
			  
			  ht.addFamily( new HColumnDescriptor(cf1));

		//	  ht.addFamily( new HColumnDescriptor("longitude"));
			  
			  ht.addFamily( new HColumnDescriptor(cf2));
			  
			  ht.addFamily( new HColumnDescriptor(cf3));
			  
			  ht.addFamily( new HColumnDescriptor(cf4));
			  
			  ht.addFamily( new HColumnDescriptor(cf5));
			  
		//	  ht.addFamily( new HColumnDescriptor("y"));
			  
		//	  ht.addFamily( new HColumnDescriptor("z"));
			  
			//  System.out.println( "connecting" );

			  
			try {
				hba = new HBaseAdmin( config );
				// System.out.println( "Creating Table" );

				  hba.createTable( ht );

				 line = "Create Success";
			} catch (MasterNotRunningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ZooKeeperConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 
			  
			  	
        } finally {
            try {
				hba.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		return line;
    }
  
    
    
    @GET
    @Produces("application/x-javascript")
    @Path("hbaseInsert/{tablename:.+}/{filepath:.+}/{columnFamilies:.+}")
    public String hbaseInsert(@QueryParam("callback") String callback,@PathParam("tablename") String tablename, 
    		@PathParam("filepath") String filepath,@PathParam("columnFamilies") String columnFamilies) {
        String line="insert success";
    	
        Configuration config = HBaseConfiguration.create();		 
		config.clear();
         config.set("hbase.zookeeper.quorum", "134.193.136.127");
         config.set("hbase.zookeeper.property.clientPort","2181");
         config.set("hbase.master", "134.193.136.127:60010");
         
         
       //  String latitude="",longitude="",Date="",x="",y="",z="";
         

		  HTable table = null;
		try {
			table = new HTable(config, tablename);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		  //Put p = new Put(Bytes.toBytes("row1"));
		  
		  int count=1;
		  int timestamp=10000;
         
        BufferedReader br = null;
        
        
        String finalpath = filepath.replace("-", "/");
      
        
         
 		try {
  
 			String sCurrentLine;
  
 			br = new BufferedReader(new FileReader(finalpath));
  
 			while ((sCurrentLine = br.readLine()) != null) {
 				
 				 Put p = new Put(Bytes.toBytes("row1"),timestamp);
 				
 				if(sCurrentLine.equals(""))
 				{
 					continue;
 				}
 				String latitude="",longitude="",Date="",x="",y="",z="",humid="",temp="";
 				String[] array = sCurrentLine.split("\t");
 				String delim="#";
 				int check=0;
 				while(array[check].contains("*")==false)
 				{
 					System.out.println("\nInside while :" + array[check]+"\t" +check);
 					String[] words=array[check].split(delim);
 					if (words[0].contains("Latitude"))
 						latitude = words[1];
 					if (words[0].contains("Longitude"))
 						longitude=words[1];
 					if (words[0].contains("Date"))
 						Date=words[1];
 					if (words[0].contains("X"))
 						x=words[1];
 					if (words[0].contains("Y"))
 						y=words[1];
 					if (words[0].contains("Z"))
 						z=words[1];
 					if (words[0].contains("Humidity"))
 						humid = words[1];
 					if (words[0].contains("Hum Ambient temperature"))
 						temp=words[1];
 					check++;
 					System.out.println("Date : "+Date);
 					System.out.println("Latitude : "+latitude);
 					System.out.println("Longitude : "+longitude);
 				//	System.out.println("Date : "+x);
 				
 				}

				 String cf1 = columnFamilies.split(":")[0];
				 String cf2 = columnFamilies.split(":")[1];
				 String cf3 = columnFamilies.split(":")[2];
				 String cf4 = columnFamilies.split(":")[3];
				 String cf5 = columnFamilies.split(":")[4];
 				if (latitude!="" || longitude!="")
 				{
 					p.add(Bytes.toBytes(cf1), Bytes.toBytes("col"+count),Bytes.toBytes(latitude));
 					p.add(Bytes.toBytes(cf1), Bytes.toBytes("col"+(count+1)),Bytes.toBytes(longitude));
 				//	System.out.println(" Inside if condition Latitude : "+latitude);
 				} 
 				// p.add(Bytes.toBytes("longitude"), Bytes.toBytes("col"+(count+1)),Bytes.toBytes(longitude));
 				if (Date!="")
 				 p.add(Bytes.toBytes(cf2), Bytes.toBytes("col"+(count+2)),Bytes.toBytes(Date));
 				//if (x!="" || y!="" || z!="")
 				 p.add(Bytes.toBytes(cf3), Bytes.toBytes("col"+(count+3)),Bytes.toBytes(x));
 				p.add(Bytes.toBytes(cf3), Bytes.toBytes("col"+(count+4)),Bytes.toBytes(y));
 				p.add(Bytes.toBytes(cf3), Bytes.toBytes("col"+(count+5)),Bytes.toBytes(z));
 				 if (humid !="")
 					 p.add(Bytes.toBytes(cf4), Bytes.toBytes("col"+(count+6)),Bytes.toBytes(humid));
 				 if (temp!="")
 					 p.add(Bytes.toBytes(cf5), Bytes.toBytes("col"+(count+7)),Bytes.toBytes(temp));
 			//	p.add(Bytes.toBytes("y"), Bytes.toBytes("col"+(count+4)),Bytes.toBytes(y));
 				
 			//	p.add(Bytes.toBytes("z"), Bytes.toBytes("col"+(count+5)),Bytes.toBytes(z));
 				 
 			      table.put(p);
 			     // check++;
 			      count=count+8;
 			      timestamp=timestamp+1;
 				
 			}
 			System.out.println("Insert Successfully");
  
  
 		} catch (IOException e) {
 			e.printStackTrace();
 			line = e.toString();
 		} finally {
 			try {
 				if (br != null)br.close();
 			} catch (IOException ex) {
 				ex.printStackTrace();
 				line = ex.toString();
 			}
 		}
		
		return line;
    }
    
    
    
    @GET
    @Produces("application/x-javascript")
    @Path("hbaseRetrieveAll/{tablename:.+}")
    public String hbaseRetrieveAll(@QueryParam("callback") String callback,@PathParam("tablename") String tablename) {
        String line="";
    	
        Configuration config = HBaseConfiguration.create();		 
		config.clear();
         config.set("hbase.zookeeper.quorum", "134.193.136.127");
         config.set("hbase.zookeeper.property.clientPort","2181");
         config.set("hbase.master", "134.193.136.127:60010");
         
      
 		try{
             HTable table = new HTable(config, tablename);
             Scan s = new Scan();
             ResultScanner ss = table.getScanner(s);
             for(Result r:ss){
                 for(KeyValue kv : r.raw()){
                	 line = line+ new String(kv.getRow()) + " ";
                	 line = line + new String(kv.getFamily()) + ":";
                	 line = line + new String(kv.getQualifier()) + " ";
                	 line = line + kv.getTimestamp() + " ";
                	 line = line + new String(kv.getValue());
                	 line = line + "/n";
                   /* System.out.print(new String(kv.getRow()) + " ");
                    System.out.print(new String(kv.getFamily()) + ":");
                    System.out.print(new String(kv.getQualifier()) + " ");
                    System.out.print(kv.getTimestamp() + " ");
                    System.out.println(new String(kv.getValue()));*/
                 }
             }
        } catch (IOException e){
            e.printStackTrace();
        }
 		
		return line;
    }
    
    
    
    @GET
    @Produces("application/x-javascript")
    @Path("hbasedeletel/{tablename:.+}")
    public String hbasedeletel(@QueryParam("callback") String callback,@PathParam("tablename") String tablename) {
        String line="delete success";
    	
        Configuration config = HBaseConfiguration.create();		 
		config.clear();
         config.set("hbase.zookeeper.quorum", "134.193.136.127");
         config.set("hbase.zookeeper.property.clientPort","2181");
         config.set("hbase.master", "134.193.136.127:60010");
         
      
       
         try {
        	  HBaseAdmin admin = new HBaseAdmin(config);
			admin.disableTable(tablename);
			admin.deleteTable(tablename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			line = e.toString();
		}
         
		return line;
    }


    /**
     * PUT method for updating or creating an instance of HDFRestWSJar
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}