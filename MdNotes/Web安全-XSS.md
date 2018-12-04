# Web安全-XSS

- 理解xss的攻击原理和手段
  - ***反射型:*** 发出请求时，XSS代码出现在URL中，作为输入提交到服务端，服务端解析响应后，XSS代码随响应内容一起传回给浏览器，最后浏览器解析执行XSS代码。这个过程像一次反射，故叫反射型XSS。
  - ***存储型:*** 存储型XSS和反射型XSS的差别仅在于，提交的代码会存储在服务器端（数据库、内存、文件系统等），下次请求目标页面时不用再次提交XSS代码
- 掌握xss攻击的防范措施
  - ***编码*** 对用户的数据进行HTML Entity编码


  | 字 符 | 十 进 制 | 转 义 字 符 |
  |-----------|-----------|-----------|
  |"|& #34;| & quot;|
  |&|& #38;| & amp;|
  |<|& #60;| & lt;|
  |>|& #62;| & gt;|
  |不断开空格(non-breaking space)|& #160;|& nbsp;|

  - 过滤
    - 移除用户上传的DOM属性，如onerror等
    - 移除用户上传的style节点,Script节点,Iframe节点等
  - 校正
    - 避免直接对HTML Entity解码
    - 使用DOM Parse转换，校正不配对的DOM标签

## 模拟实例

- express -e ./
    ```js
    有人指出是express4的版本将命令工具分家了，所以需要我们安装命令工具：
    命令如下：npm install -g express-generator
    之后再次安装：npm install -g express

### 过滤方法
```js
<script src="/javascripts/domParse.js"></script>
<script src="/javascripts/encode.js"></script>
var parse = function(str) {
       var result = '';
       try {
           HTMLParser(he.unescape(str, {static: true}), {
               start: function(tag, attrs, unary) {
                   // 过滤不安全的标签
                   if(tag === 'script' || tag === 'style' || tag === 'link' || tag === 'iframe' || tag === 'frame') return
                   result += '<' + tag;
                   // 属性需要过滤
                   // for(var i=0, len=attrs.length; i<len; i++) {
                   //     result += " " + attrs[i].name + '="' + attrs[i].escaped + '"'
                   // }
                   result+=(unary?"/":"")+">"
               },
               end: function(tag) {
                   result+="</" + tag + ">"
               },
               chars: function(text) {
                   result += text
               },
               comment: function(text) {
                   result += "<!--" + text + "-->"
               }
           })
           return result
       } catch (error) {
           console.log(error)
       } finally {

       }
   }
```
- [encode.js](https://github.com/blowsie/Pure-JavaScript-HTML5-Parser/blob/master/htmlparser.js)
- [domParse.js](https://github.com/mathiasbynens/he/blob/master/he.js)
