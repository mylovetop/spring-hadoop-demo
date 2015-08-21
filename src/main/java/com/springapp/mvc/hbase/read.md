mac os
1. 启动
./bin/start-hbase.sh

2.启动是报如下提示：
starting master, logging to /Users/zdsoft/Desktop/AppleApp/hbase-1.1.1/logs/hbase-zdsoft-master-master.out
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=128m; support was removed in 8.0
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=128m; support was removed in 8.0
解决：
http://stackoverflow.com/questions/22634644/java-hotspottm-64-bit-server-vm-warning-ignoring-option-maxpermsize
Compatibility Guide for JDK 8 says that in Java 8 the command line flag MaxPermSize has been removed.
The reason is that the permanent generation was removed from the hotspot heap and was moved to native memory.
So in order to remove this message edit MAVEN_OPTS Environment User Variable:
Java 7
MAVEN_OPTS -Xmx512m -XX:MaxPermSize=128m
Java 8
MAVEN_OPTS -Xmx512m

3.执行$ hbase shell
显示 $hbase(main):001:0> 说明已经确认Java和HBase 安装成功

4.hbase(main):001:0> list
  TABLE
  0 row(s) in 0.6240 seconds

  => []
  //创建表
  hbase(main):002:0> create 'mytable', 'cf'
  0 row(s) in 1.3880 seconds

  => Hbase::Table - mytable
  hbase(main):003:0> list
  TABLE
  mytable
  1 row(s) in 0.0440 seconds

  => ["mytable"]
  hbase(main):004:0>

  //写数据
  hbase(main):005:0> put 'mytable', 'first', 'cf:message', 'hello HBase'
  0 row(s) in 0.6580 seconds

  //读取数据
  hbase(main):012:0> get 'mytable', 'first'
  COLUMN                      CELL
   cf:message                 timestamp=1440053300309, value=hello HBase
  1 row(s) in 0.1200 seconds

  //扫描表所有数据 scan
  hbase(main):013:0> scan 'mytable'
  ROW                         COLUMN+CELL
  first                      column=cf:message, timestamp=1440053300309, value=hello HBase
  second                     column=cf:foo, timestamp=1440053484325, value=OxO
  third                      column=cf:bar, timestamp=1440053505623, value=3.14159
  3 row(s) in 0.0480 seconds

  //查看users 表的 所有默认参数
  hbase(main):016:0> describe 'users'
  Table users is ENABLED
  users
  COLUMN FAMILIES DESCRIPTION
  {NAME => 'info', BLOOMFILTER => 'ROW', VERSIONS => '1', IN_MEMORY => 'false', KEEP_DELETED_CELLS => 'FALSE'
  , DATA_BLOCK_ENCODING => 'NONE', TTL => 'FOREVER', COMPRESSION => 'NONE', MIN_VERSIONS => '0', BLOCKCACHE =
  > 'true', BLOCKSIZE => '65536', REPLICATION_SCOPE => '0'}
  1 row(s) in 0.1180 seconds

5.zookeeper 启动
  启动ZK服务:       sh bin/zkServer.sh start
  查看ZK服务状态: sh bin/zkServer.sh status
  停止ZK服务:       sh bin/zkServer.sh stop
  重启ZK服务:       sh bin/zkServer.sh restart