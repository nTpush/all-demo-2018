# 读vue组件上传至npm踩坑记录

- 心路历程：网上文档千千万万，找到一篇适合自己的并不容易，所以多学、多看、多实践。

## 操作步骤：(简单回忆)

- 前期准备
  - 需要npm账号，官网地址：[npm](https://www.npmjs.com/)
  - 所用的操作命令：
    - npm login —— 第1次需要登录：用户名、密码、邮箱(需要验证)
    - npm publish —— 发布至npm

- 开发组件使用webpack-simple

```js
vue init webpack-simple <project-name>
```

- 主要是 src/ 目录下完成组件的开发
- 在 src/ 目录下新建 index.js

``` js
import WinnerSwitch from './packages/switch/winner-switch.vue'
// ...如果有的请继续添加
const components = [
  WinnerSwitch
  // ...如果有的请继续添加
]
const install = function (Vue, opts = {}) {
  components.map(component => {
    Vue.component(component.name, component)
  })
}
// 这里的判断很重要 支持使用标签的方式引入
if (typeof window !== 'undefined' && window.Vue) {
  install(window.vue)
}
export default {
  install,
  WinnerSwitch
}
```

- 重点需要修改 webpack.config.js 以及 package.json
  - webpack.config.js
    ```js
    // 执行环境
    + const NODE_ENV = process.env.NODE_ENV

    - entry: NODE_ENV == ./src/main.js,
    + entry: NODE_ENV == 'development' ? './src/main.js' : './src/index.js',

    - filename: 'build.js',
    + filename: 'jsh-ui.js',
    + library: 'jsh-ui', // 指定的就是你使用import时的模块名
    + libraryTarget: 'umd', // libraryTarget会生成不同umd的代码,可以只是commonjs标准的，也可以是指amd标准的，也可以只是通过script标签引入的
    + umdNamedDefine: true // 会对 UMD 的构建过程中的 AMD 模块进行命名。否则就使用匿名的 define
    ```
  - package.json
    ```js
    - "private": true,
    + "private": false,// 发布开源因此需要将这个字段改为 false
    // 这个指 import custom-ui 的时候它会去检索的路径
    + "main": "dist/jsh-ui.js",
    ```

- 完成之后就可以使用了

```js
import JshUi from 'jsh-ui'
Vue.use(JshUi)
```

- npm unpublish --force

- .gitignore 去掉忽略dist,因为我们打包的文件也需要提交；
- 每次上到npm上需要更改版本号，package.json 里的 version 字段