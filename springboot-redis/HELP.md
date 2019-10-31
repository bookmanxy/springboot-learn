# Springboot 集成Redis模块

### 1：springboot-learn-redis-simple 模块 
* 简单配置及使用
* 对比StringRedisTemplate 和 RedisTemplate 的使用

### 2：springboot-learn-redis-lock 模块
* 设计并优化Jedis分布式锁
* springboot集成jedis，配置JedisPool

### 3：springboot-learn-redis-geo 模块
* 保存人员（批量）的地理信息
* 获取多个人的地理信息
* 计算两个人的距离
* 获取某个point附近的人
* 获取某个人附近的人
* 获取某个地理位置的 geohash 值
* 移除某个人的地理位置

### 4：springboot-learn-redis-rank 模块
* 实现排行榜功能
* 游戏实践：见GameTest
* 1.能够记录每个玩家的分数；
* 2.能够对玩家的分数进行更新； 
* 3.能够查询每个玩家的分数和名次； 
* 4.能够按名次查询排名前N名的玩家； 
* 5.能够查询排在指定玩家前后M名的玩家
