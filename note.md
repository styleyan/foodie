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
