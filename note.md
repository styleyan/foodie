# 收获笔记
1. 通过 HttpServletRequest 设置 http session
```java
@PostMapping("/api/login")
public JsonResult login(@RequestBody UserBO userBO, HttpServletRequest request) throws Exception {
    // Session 设置
    HttpSession httpSession = request.getSession();
    httpSession.setAttribute("userName", "user");
    httpSession.setMaxInactiveInterval(36000);
}
```

2. 后端配置跨域

3. 在 pojo 中, 如果有哪个属性不能返回给前端, 可以通过添加 `@JsonIgnore`

4. md5加密

5. Json转字符串工具类

6. 设置 cookies 工具类

7. sl4j 日志配置打印

8. ibatis 配置打印出 sql 语句? 没配置成功

9. [关于java的 PO/BO/VO/DTO/POJO/DAO](https://www.cnblogs.com/EasonJim/p/7967949.html)

10. myBatis中的 resultMap 使用 collection 标签定义关联的 list 集合类型的封装规则

11. 在多种分类中，虽然可以分几张表来进行分表，但是有一种更好的方式是 无限递归表，可以把很多中分类集合在一张表上。

12. 原来 java controller 还可以被继承

13. 需要查看脱敏工具类是怎么实现的?

14. 在绝大多数情况下，只要涉及到金额存储的，数据库设计都会以分为单位存储

15. myBatis 下的 <choose><when></when></choose>语句

16. 购物车存储形式 
Cookie: 无需登录、无需查库、保存在浏览器端
- 优点: 性能好、访问速度快、没有和数据库交互
- 缺点1: 换电脑购物车数据会丢失
- 缺点2: 电脑被其他人登录，存在隐私安全

Session: 用户登陆后，购物车数据放入用户会话
- 优 点: 初期(用户量较小的情况下)性能比较好，访问块
- 缺点1: session 基于内存，用户量庞大影响服务器性能，就算企业在服务器上增加内存也不好解决这问题，会增加资金成本
- 缺点2: 只能存在于当前会话，是适用集群与分布式系统

数据库存储: 用户登录后，购物车存储到数据库
优点: 数据能持久化，可在任何地点任何时间访问
缺点: 频繁的读写数据库，造成数据库压力

Redis: 用户登录后，购物车数据存入 redis 缓存
优点1: 数据持久化，可在任何地点任何时间访问
优点2: 频繁读写只基于缓存，不会造成数据库压力
优点2: 适用于集群与分布式系统，可扩展性强

