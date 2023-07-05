# admin4j-json

admin4j-json 是一款高效实用的 JSON 解析和生成工具，提供简单易用的 API，可轻松完成 JSON 数据的读写操作。不仅如此，admin4j-json
还通过插件SPI的模式适配了多个主流框架，如 fastjson，fastjson2，jackson，gson 等，解决了 JSON 解析第三方框架多变/更换的问题，让您随时替换
json 解析框架，轻松应对各种开发需求。

# usage

## maven 引入

### 使用 jackson

```xml

<dependency>
    <groupId>com.admin4j.json</groupId>
    <artifactId>admin4j-json-jackson</artifactId>
    <version>0.7.0</version>
</dependency>
```

最新版查询 [https://central.sonatype.com/artifact/com.admin4j.json/admin4j-json-jackson](https://central.sonatype.com/artifact/com.admin4j.json/admin4j-json-jackson)

### 使用 fastjson

```xml

<dependency>
    <groupId>com.admin4j.json</groupId>
    <artifactId>admin4j-json-fastjson</artifactId>
    <version>0.7.0</version>
</dependency>
```

### 使用 fastjson2

```xml

<dependency>
    <groupId>com.admin4j.json</groupId>
    <artifactId>admin4j-json-fastjson2</artifactId>
    <version>0.7.0</version>
</dependency>
```

### 使用 gson

```xml

<dependency>
    <groupId>com.admin4j.json</groupId>
    <artifactId>admin4j-json-gson</artifactId>
    <version>0.7.0</version>
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

``` 
<dependency>
    <groupId>io.github.admin4j</groupId>
    <artifactId>http</artifactId>
    <version>0.7.0</version>
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

# 添加自己的JSON解析框架

本项目使用JDK的SPI原理加载插件，使用者也可以使用SPI功能加入自己的JSON解析框架
參考 [FastjsonConvertor.java](https://github.com/admin4j/admin4j-json/blob/master/admin4j-json-fastjson/src/main/java/com/admin4j/json/FastjsonConvertor.java)

## 第一步，引入依赖，实现JSONConvertor接口

```
 <dependencies>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency>
        <dependency>
            <groupId>com.admin4j.json</groupId>
            <artifactId>admin4j-json</artifactId>
            <version>0.2.0</version>
        </dependency>
  </dependencies>
```

```java
public class FastjsonConvertor implements JSONConvertor {

    /**
     * 解析/发序列化成对象
     *
     * @param is
     * @param clazz
     * @param charset
     */
    @Override
    public <T> T parseObject(InputStream is, Charset charset, Class<T> clazz) {

        try {
            return JSON.parseObject(is, charset, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析/发序列化成对象
     *
     * @param bytes
     * @param clazz
     * @param charset
     */
    @Override
    public <T> T parseObject(byte[] bytes, Charset charset, Class<T> clazz) {

        return JSON.parseObject(bytes, clazz);

    }

    /**
     * 解析/发序列化成对象
     *
     * @param input
     * @param clazz
     */
    @Override
    public <T> T parseObject(String input, Class<T> clazz) {
        return JSON.parseObject(input, clazz);
    }


    /**
     * JSON 转 Map
     */
    @Override
    public Map<String, Object> parseMap(String input) {
        return JSON.parseObject(input);
    }

    @Override
    public Map<String, Object> parseMap(InputStream is) {
        try {
            return JSON.parseObject(is, StandardCharsets.UTF_8, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> parseList(String input, Class<T> clazz) {
        return JSON.parseArray(input, clazz);
    }

    @Override
    public <T> List<T> parseList(InputStream is, Class<T> clazz) {

        String str = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        return JSON.parseArray(str, clazz);
    }

    /**
     * 序列化成json字符串
     *
     * @param object
     */
    @Override
    public String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);

    }
}
```

## 第二步，配置SPI

![在这里插入图片描述](https://img-blog.csdnimg.cn/d131f63b1fc14e6fbfe0e938dcf33dd3.png)

## 第三部，测试使用

end