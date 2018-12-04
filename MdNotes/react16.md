# react

- express + mongoess + ant design
- 按需加载

- 自定义组件
  - alert
  - btn
  - banner
  - checkbox
  - image
  - input
  - loading

- 模块定义别名

```js
webpack.config.js
alias: {
'loading': path.join(__dirname, './src/home/components/loading'),//loading组件
}
```

- redux调试代码

```js
 window.devToolsExtension && window.devToolsExtension({ actionCreators }),
```

- 热加载,及时跟新reducer

```js
if (module.hot) {
module.hot.accept('../reducers', () => {
    const nextReducer = require('../reducers');
    store.replaceReducer(nextReducer);
});
}
```

- 错误：Module build failed: BrowserslistError: Unknown browser query `dead`

```js
// 解决办法是在webpack.json配置文件下配置一段代码
"browserslist": [ "last 2 versions", "android 4", "opera 12" ],
```

- react-router-dom

```js
hashHistory 老版本浏览器的history
browserHistory h5的history
memoryHistory node环境下的history，存储在memory中

BrowserRouter、HashRouter、MemoryRouter这三种Router
```
- 在一个web 应用中，改变url无非是2种方式
  - 一种是利用超链接进行跳转
  - 另一种是使用浏览器的前进和回退功能(history的listen方法来监听url的变化)

- react-router-dom
```js
<Link to={{pathname:'/courses', search:'?sort=name', hash:'#the-hash', state:{formDashboard: true}}}
```

- 配置redux
```js
npm install redux react-redux --save
```

- react转义
```js
<div dangerouslySetInnerHTML={{ __html: content }} />
```

- react-router-dom 获取history对象
```js
import PropTypes from 'prop-types'
static contextTypes = {
	router: PropTypes.object
}
constructor(props, context) {
	super(props, context)
	this.state = {}
}
componentDidMount() {
	console.log(this.context.router.history.path)
}

 //场景中需要返回上级页面的时候使用：
this.props.history.goBack();
//有些场景下，重复使用push或a标签跳转会产生死循环，为了避免这种情况出现，react-router-dom提供了replace。在可能会出现死循环的地方使用replace来跳转：
this.props.history.replace('/detail');
// 父传子
//父
export default class B extends Component {
	render() {
		return (
			<div>
				<A m={'mmmm'} />
			</div>
		)
	}
}
//子
export default class A extends Component {
render() {
  return <div>{this.props.m}, 哈哈</div>
}
}
```

- react升级16.7 使用 Hooks

```js
yarn add -D react-test-renderer@next
"react": "^16.7.0-alpha.0",
"react-dom": "^16.7.0-alpha.0",
"react-test-renderer": "^16.7.0-alpha.0",
```

 使用npm-check -u可以检测有那些包可以升级