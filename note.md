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