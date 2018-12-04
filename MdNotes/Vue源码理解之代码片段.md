# 代码片段

- DocumentFragment文档版本
    ```js
    js
    var flag = document.createDocumentFragment()
    var span = document.createElement('span')
    var textNode = document.createTextNode('hello world')
    span.appendChild(textNode)
    flag.appendChild(span)
    document.querySelector('#app').appendChild(flag)

    html
    <div id='app'></div>
     ```