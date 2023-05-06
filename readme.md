# admin4j-json

强大的 JSON 适配器工具类。适配各大框架 fastjson，fastjson2，jackson，gson。
解决JSON解析第三方框架多变/更换问题。随时替换json解析框架

# usage

## maven 映入

### 使用 jackson

```xml

<dependency>
    <groupId>com.admin4j.json</groupId>
    <artifactId>admin4j-json-jackson</artifactId>
    <version>0.2.0</version>
</dependency>
```

### 使用 fastjson

```xml

<dependency>
    <groupId>com.admin4j.json</groupId>
    <artifactId>admin4j-json-fastjson</artifactId>
    <version>0.2.0</version>
</dependency>
```

### 使用 fastjson2

```xml

<dependency>
    <groupId>com.admin4j.json</groupId>
    <artifactId>admin4j-json-fastjson2</artifactId>
    <version>0.2.0</version>
</dependency>
```

### 使用 gson

```xml

<dependency>
    <groupId>com.admin4j.json</groupId>
    <artifactId>admin4j-json-gson</artifactId>
    <version>0.2.0</version>
</dependency>
```

### 具体使用

```java
public class JSONTest {

    @Test
    public void testToJSONString() throws Exception {

        User admin4j = new User("admin4j", 18);
        String jsonString = JSONUtil.toJSONString(admin4j);
        System.out.println("jsonString = " + jsonString);
    }

    @Test
    public void testParseObject() throws Exception {

        String json = "{\"age\":18,\"name\":\"admin4j\"}";
        User user = JSONUtil.parseObject(json, User.class);
        System.out.println("user = " + user);
    }

    @Test
    public void testParseMap() throws Exception {

        String json = "{\"age\":18,\"name\":\"admin4j\"}";
        Map<String, Object> stringObjectMap = JSONUtil.parseMap(json);
        System.out.println("stringObjectMap = " + stringObjectMap);
    }
}
```

### 切换json解析框架

比如自己开发的框架，默认使用的 fastjson。但是使用者使用的是 fastjson2，不想再引入fastjson 依赖，怎么办？
参考 [https://github.com/admin4j/common-http](https://github.com/admin4j/common-http)

#### 随时替换json解析框架

```xml

<dependency>
    <groupId>io.github.admin4j</groupId>
    <artifactId>http</artifactId>
    <version>0.5.0</version>
    <exclusions>
        <exclusion>
            <groupId>com.admin4j.json</groupId>
            <artifactId>admin4j-json-fastjson</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
<groupId>com.admin4j.json</groupId>
<artifactId>admin4j-json-jackson</artifactId>
<version>0.2.0</version>
</dependency>
```