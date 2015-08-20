package com.springapp.mvc.hbase;


import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;

import org.apache.hadoop.hbase.rest.protobuf.generated.CellMessage;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zdsoft on 15-8-20.
 */
public class HBaseTest {

    ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});

    BeanFactory factory = (BeanFactory)context;
    HbaseTemplate htemplate = (HbaseTemplate)factory.getBean("hbaseTemplate");
    Map hMap = new HashMap<String, List<Object>>();

    public String key;
    public String familyName ;
    public String qualifier;
    public String value;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public String getQualifier() {
        return qualifier;
    }
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }



    public static void main(String[] args) {

    }

}
