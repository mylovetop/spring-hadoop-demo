#hadoop hdfs
1、假设有一个 HDFS 集群,那么这个集群有且仅有一台计算机做名字节点 NameNode,
有且仅有一台计算机做第二名字节点 SecondaryNameNode,其他机器都是数据节点 DataNode。
在伪分布式的运行方式下 NameNode,SecodaryNameNode,DataNode 都由同一台机器担任。

2、NameNode 是 HDFS 的管理者。SecondaryNameNode 是 NameNode 的辅助者,帮助 NameNode 处理一些合并事宜
注意,它不是 NameNode 的热备份,它的功能跟 NameNode 是不同的。
DataNode 以数据块的方式分散存储 HDFS 的 文件。
HDFS 将大文件分割成数据块,每个数据块是 64M,也可以设置成 128M 或者 256M,然后将这些数据块以普通文件的形式存放到数据节点上,
为了防止 DataNode 意外失效,HDFS 会将每个数据块复制若干份放到不同的数据 节点。