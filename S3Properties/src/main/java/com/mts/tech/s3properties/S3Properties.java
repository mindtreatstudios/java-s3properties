package com.mts.tech.s3properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by reyda_000 on 12/24/2014.
 */
public class S3Properties extends Properties {
    static final Logger Log = LoggerFactory.getLogger(S3Properties.class);

    private AmazonS3 s3;

    public S3Properties(AWSCredentials awsCredentials, String regionName)
    {
        this(awsCredentials, regionName, null);
    }

    public S3Properties(AWSCredentials awsCredentials, String regionName, Properties defaults)
    {
        super(defaults);
        s3 = new AmazonS3Client(awsCredentials);
        s3.setRegion(Region.getRegion(Regions.fromName(regionName)));
    }

    public synchronized void load(String bucket, String key) throws IOException
    {
        Log.info("Loading properties from bucket:{}, key:{}", bucket, key);
        GetObjectRequest request = new GetObjectRequest(bucket, key);
        S3Object object = s3.getObject(request);
        load(object.getObjectContent());
    }

    public String mustGetProperty(String key) throws Exception
    {
        String value = getProperty(key);
        if (value == null)
            throw new Exception ("Could not find property "+key);

        return value;
    }

    public boolean getBoolean(String key, boolean defaultValue)
    {
        String value = getProperty(key);
        return value == null ? defaultValue : Boolean.parseBoolean(value);
    }

    public int getInt(String key, int defaultValue)
    {
        String value = getProperty(key);
        return value == null ? defaultValue : Integer.parseInt(value);
    }

    public long getLong(String key, long defaultValue)
    {
        String value = getProperty(key);
        return value == null ? defaultValue : Long.parseLong(value);
    }
}
