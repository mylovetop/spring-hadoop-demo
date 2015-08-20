#hadoop hdfs
1、假设有一个 HDFS 集群,那么这个集群有且仅有一台计算机做名字节点 NameNode,
有且仅有一台计算机做第二名字节点 SecondaryNameNode,其他机器都是数据节点 DataNode。
在伪分布式的运行方式下 NameNode,SecodaryNameNode,DataNode 都由同一台机器担任。

2、NameNode 是 HDFS 的管理者。SecondaryNameNode 是 NameNode 的辅助者,帮助 NameNode 处理一些合并事宜
注意,它不是 NameNode 的热备份,它的功能跟 NameNode 是不同的。
DataNode 以数据块的方式分散存储 HDFS 的 文件。
HDFS 将大文件分割成数据块,每个数据块是 64M,也可以设置成 128M 或者 256M,然后将这些数据块以普通文件的形式存放到数据节点上,
为了防止 DataNode 意外失效,HDFS 会将每个数据块复制若干份放到不同的数据 节点。

#hadoop mapreduce 运行机制
1. 一个 Hadoop 集群,有 5 种节点。
1.1 NameNode,有且仅有一个,负责管理 HDFS 文件系统。
1.2 DataNode,至少有一个,通常有很多个。具体地说,每台负责做集群计算的的计算机都是一个 DataNode。 1.3 SecondaryNameNode,有且仅有一个,只辅助处理 NameNode,不做其他任务。
1.4 JobTracker,有且仅有一个,负责 Hadoop 的作业管理,所有的 MapReduce 的执行由它处理。
1.5 TaskTracker,至少有一个,通常有很多个。具体地说,每台负责做集群计算的的计算机都是一个 TaskTracker。
2. 伪分布式 Hadoop 集群,这个集群只有一台计算机,这台计算机上 5 种节点都有,启动 Hadoop 之后,用”jps” 命令可以看到这 5 个节点进程。
3. 分布式 Hadoop 集群,如果集群内计算机足够多,会有一台机器做 NameNode,一台机器做 SecondaryNameNode,一台机器做 JobTracker。其他机器负责做集群计算,这些机器每台上面各有一个 DataNode 和 TaskNode 进程。
4. Hadoop 配置好之后,第一个命令是执行”./bin/hadoop namenode -format”。这个命令会对 NameNode 进行格式化, 然后再关闭 NameNode。
5. 执行完第 4 步之后,用”./bin/start-all.sh”启动 Hadoop 集群。
6. 前处理
6.1 写 MapReduce 代码,编译,将代码打成 tar 包。
6.2 在 Linux 命令终端,运行 HDFS 的命令,将待处理的数据文件放到 HDFS 上。这时候,NameNode 节点会负
责将文件分割成块,分散存储在各个 DataNode 节点上。
6.3 在 Hadoop 集群运行的时候,每隔一定时间,SecondaryNameNode 会将 NameNode 上的数据进行合并处理。
7. 在一个 Linux 终端执行 Hadoop 命令,如"./bin/hadoop jar hadoop-examples-1.2.1.jar wordcount readme.txt output"。 在 Hadoop 运行这个命令的时候,这个 Linux 终端就是一个”客户端”,它发的这个命令就是在“提交 MapReduce 作 业”。
8. 客户端在做什么
8.1 客户端向 JobTracker 节点申请一个作业 ID。
8.2 检查作业的输出目录。如果目录没指定或者已存在,客户端会打印错误信息,然后退出。
8.3 根据 InputFormat,将输入的数据格式进行分割,也就是分割成若干个 InputSplit。如果出错会退出。
8.4 将 Mapreduce 程序的 jar 包,配置文件,InputSplit 信息都复制到 HDFS 的一个目录,这个目录名是作业 ID。 8.5 通知 JobTracker 执行作业。至此,提交作业完毕。
￼8.6 客户端提交了作业之后,每秒会查询一次作业执行到了什么状态,如果有变化,就在冲端上打印出来,以 便大家看到进度。
9. JobTracker 在做什么
9.1 客户端告诉 JobTracker 去执行作业。 9.2 JobTracker 将作业放到一个队列。
9.3 作业调度器对作业进行初始化,它取出 InputSplit 信息,为每个 InputSplit 创建一个 Map 任务,根据 map.reduce.reduce 参数创建若干个 Reduce 任务。
9.4 Hadoop 集群启动后,JobTracker 从始至终一直接收所有 TaskTracker 发送过来的心跳。如果某个心跳附带的 信息说这个 TaskTracker 是空闲的,就发一个新任务给这个 TaskTracker 去做。
9.5 JobTracker 根据心跳,将作业和各种任务的执行进度计算出来。如果客户端查询,就将这些信息返回给它。 9.6 JobTracker 根据心跳,处理各种执行失败问题。
10. TaskTracker 在做什么
10.1 在 Hadoop 集群启动后,TaskTracker 定期向 JobTracker 发送“心跳”。心跳附带的信息会告诉 JobTracer,现
在 TaskTracker 的状态是什么,是否可以运行新任务,是否在忙碌状态。
10.2 如果 TaskTracker 可以运行新任务,那么,JobTracer 会在心跳的返回值里给 TaskTracker 分配一个新任务。 10.3 TaskTracker 接到新任务,将这个任务所在的作业的 jar 文件以及所有需要处理的数据文件从 JobTracker 和
HDFS 复制到本地磁盘上。
10.4 TaskTracker 创建一个 TasckRunner 实例运行这个任务。每隔一段是时间,将任务执行的进度通过心跳发送
给 JobTracker。
10.5 任务运行的时候,处理各种失败问题。
#hadoop mapreduce 运行机制 End


