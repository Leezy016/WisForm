# 闫文玉
任务完成总结：
1.明确了项目具体目标：用户自定义收集表单（可先初步实现几种固定收集表），填写表单并存储到数据库，从数据库匹配检索表单项实现自动填写
2.配置了实验软件以及环境，安装了idea，jdk，Maven，neo4j
3.学习了spring boot基本知识
4.学习了neo4j图形数据库的基本使用方法
5.学习并实现了springboot集成neo4j
6.学习idea中操作neo4j的基本方法
7.后端连接上neo4j数据库
8.实现了在idea对数据库节点的添加和删除
9.明白了团队协作的重要性，分工合作，加强沟通  
  
遇到的问题：  
1.找不到 spring-boot-starter-parent 包  
Could not find artifact org.springframework.  
boot:spring-boot-starter-parent:pom:3.1.1 in alimave  
解决方法：  
maven镜像配置阿里云,而自己使用的springboot版本是最新版本,阿里云镜像还没有更新到.  
将spring-boot-starter-parent版本降到2.7.5解决  
2.cannot resolve plugin org.apache.maven.plugins:maven-compiler-plugin:3.1  
解决方法：设置IDEA中对maven的配置  
Rebuild一下工程  
 
3.lombok和jdk版本不兼容  
org.projectlombok:lombok:pom:1.18.22 failed to transfer from https://repo.maven.apache.org/maven2 during a previous attempt. This failure was cached in the local repository and resolution is not reattempted until the update interval of central has elapsed or updates are forced. Original error: Could not transfer artifact org.projectlombok:lombok:pom:1.18.22 from/to central (https://repo.maven.apache.org/maven2): transfer failed for https://repo.maven.apache.org/maven2/org/projectlombok/lombok/1.18.22/lombok-1.18.22.pom  
解决方法：不指定版本，自动导入  
 
4.Dependency 'org.springframework.boot:spring-boot-starter-data-neo4j:2.6.1' not found  
解决方法：导包  
 
5.localhost//8080/createNode error  
解决方法：application.Properties端口号为7687，不是7474  
 
 6.Cannot invoke.PersonRepository.findById(Object)" because "this.  
personRepository" is null  
Test映射错误  
未解决  

  
收获感悟：  
1.	软件以及环境之间要适配，不然会出现各种报错  
2.	Neo4j是一款高效图形数据存储和查询软件，使用方便。  
3.	利用Maven自动导入jar包，快捷高效。  
4.	Spring Boot自有的starter中提供了一些可以快捷使用的依赖，让整合或集成一些常用的功能更便捷。Spring Boot提供了一些默认的配置，比如端口号默认为8080等。  
  
安排：  
1.	在idea中实现neo4j节点关系的增加删除  
2.	实现前端后端数据库正常连接，前端填写收集表数据存储至neo4j数据库  

# 王佳 李斯嘉
A每周纪要：
1.这周的任务完成总结
·学习了vue框架：利用node.js的官方包管理工具npm,和VUE官方命令行工具CLI快速搭建Vue.js项目（CLI创建的项目默认配置本地开发服务器），通过npm run serve命令启动本地服务器；学习了Vue基础语法知识，包括对组件的理解，路由配置的理解
·学习了Axios，利用其提供的客户端库，发送http请求实现前后端数据交互
·重写了WebMvcConfigurer接口的addCorsMappings方法来配置跨域请求的规则，允许前后端跨域数据交互

B中期汇报：
2.课程开始至今任务完成总结
课程开始至今初步画出了大致需要的前端界面，完整实现了登陆功能

3.课设过程中遇到的问题，哪些解决了，如何解决的，哪些还没解决？
·刚开始用vue画前端界面时候，各个界面之间跳转不成功，在助教老师帮助下，发现路由配置有问题，App.vue作为整个应用程序的根组件，它的内容会在每个页面之间共享的，加上 < router-view > 标签才会在界面跳转时显示跳转界面组件内容

4.过程中自己的收获和感悟：
在前半段时间的课设开发中收获了技术经验和解决问题的成就感。认识到完成一个项目开发既要有对的心态，又要有对的思路。对的心态是热情和专注，面对报错不丧气，面对技术不侥幸。对的思路是要先对项目所需全部技术有大致了解，找到正确的方向，才能坚定地开发。

5.	之后负责开发部分
前后端接口设计和开发

初步画出了项目大概所需界面
1.
 
2.登陆界面（实现前、后端和数据库的连通）
  
3.普通用户界面
 
4.管理员界面


# 卢涵秋
中期汇报：
1.后端任务演进：
	首先明确了我们这个课设的任务，进行需求的细化。对应进行小组的任务分工。确定下来我们要使用的工具。通过网上的学习资料，进行快速的熟悉。完成编译工具环境的搭建。
	其次，学习Neo4j数据库，了解数据库的存储方式以及操作方式。学习对应的Cypher操作语言。做到对数据库能进行基本的使用和操作。
	接着就是要建立前后数据库的联通，构建后端的工程架构，实现前端的方法以及后端接口。跑通后，体现为前端的请求可以通过后端最终在数据库有正确的反映。至此完成了基本的构建，接下来面临的就是根据形成的功能性需求分档，结合前端的实现。不断推进构建功能性的完善。

2.问题
	Q1：（已经解决）
在搭建环境时，对于版本选择的纠结。
	Q2：（部分解决）
在后端实现时，对于Springboot的注解标记的使用往往不太顺畅。
	Q3：（为解决但是无伤大雅）
Springboot的自动注入在测试时失效，没法解决。

3.收获感悟：
	在项目刚起步的阶段，最难的就是开始这个过程，但是在实践的过程中我发现自己一个人埋头苦干往往效率不高，往往会钻牛角尖。比如我在实现后端连接数据库时，在Test里跑模块测试，对于接口的实例的自动注入就一直出现反射调用的错误，一直在网络上查找对应的解决办法，进展一直不佳。但是在与前端的联合中，我直接在三端进行测试，实现简单的前端的方法，通过浏览器反而顺利的实现了前后端的联通。遇到问题了分享出来大家一起解决反而会事半功倍。

4.安排：
	由于前端的业务进展较快，我预计按照小组共同的商讨的功能需求的文档，结合前端的使用，尽快依次实现后端的接口。以及在数据库构建的过程中，进行类和操作方法的同步完善和测试。

