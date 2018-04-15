package assign5_S3;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

public class App 
{
	private static String bucketName = "Your Bucket name";
	private static String key = "your file name";
	  
    public static void main( String[] args )
    {
    	 AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
    	//AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_1).build();
		
        try {
          System.out.println("Downloading an object");
          S3Object s3object = s3Client.getObject(
              new GetObjectRequest(bucketName, key));
          displayTextInputStream(s3object.getObjectContent());
        }
        catch(AmazonServiceException ase) {
          System.err.println("Exception was thrown by the service");
          ase.printStackTrace();
        }
        catch(AmazonClientException ace) {
          System.err.println("Exception was thrown by the client");
        }
        catch(IOException ioex) {
        	System.err.println("Exception was thrown while reading file");
        }
      }

      private static void displayTextInputStream(InputStream input) throws IOException
      {
        // Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while(true)
        {
          String line = reader.readLine();
          if(line == null) break;
          System.out.println( "    " + line );
        }
        System.out.println();
      }
}
