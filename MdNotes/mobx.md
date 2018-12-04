# mobx https://mobx.js.org

- mobx的核心思想
  - 状态变化引起的副作用应该被自动触发

```js
npm i webpack webpack-cli babel-core babel-preset-env babel-loader -D

npm i babel-plugin-transform-class-properties -D

npm i babel-loader@7 -D

npm install babel-plugin-transform-decorators-legacy -D

npm i react react-dom prop-types mobx-react -S

npm i babel-preset-react -D
```

- js 继承

```js
function Animal() {}
function Dog() {}
Object.defineProperties(Animal.prototype, {
name: {
    value() {
        return 'Animal'
    }
},
say: {
    value() {
        return `i am ${this.name()}`
    }
}
})
Dog.prototype = Object.create(Animal.prototype, {
constructor: {
    value: Dog,
    enumerable: false
},
name: {
    value() {
        return 'Dog'
    }
}
})
document.write(new Dog().say())
```

- ES6语法实现继承

```js
class Animal {
name() {
    return 'Animal'
}
say() {
    return `i am ${this.name()}`
}
}

class Dog extends Animal {
food = 'bone'
name() {
    return 'Dog'
}
}

console.log(new Dog() instanceof Animal)
```

- decorator修饰器语法
  - Decorator是在声明阶段实现类与类成员注解的一种语法

  - 类修饰器，入参只有一个target(类的实例对象)
    ```js
    function log(target) {
        const desc = Object.getOwnPropertyDescriptors(target.prototype)
        for (const key of Object.keys(desc)) {
            if (key === 'constructor') {
                continue
            }

            const func = desc[key].value

            if ('function' === typeof func) {
                Object.defineProperty(target.prototype, key, {
                    value(...args) {
                        console.log('before' + key)
                        const ret = func.apply(this, args)
                        console.log('after' + key)
                        return ret
                    }
                })
            }
        }
    }
    @log
    class Numberic {
        @readonly PI = 3.1415926 // 类成员修饰器，target(类的实例对象),key(该类的成员变量),descriptor(该类成员的描述符)

        @validate
        add(...nums) {
            return nums.reduce((p, n) => p + n, 0)
        }
    }

    new Numberic().add(1, 3, 4)

    new Numberic().add(1, '2')
    ```
  - 类成员修饰器，入参有，target(类的实例对象),key(该类的成员变量),descriptor(该类成员的描述符)
    ```js
    function readonly(target, key, descriptor) {
        descriptor.writable = false
    }
    ```
  - 类成员方法修饰器， 入参有，target(类的实例对象),key(该类的成员变量),descriptor(该类成员的描述符)
    ```js
    function validate(target, key, descriptor) {
        const func = descriptor.value
            descriptor.value = function(...args) {
                for (let num of args) {
                    if ('number' != typeof num) {
                        throw new Error(`${num} is not a number`)
                    }
                }
                return func.apply(this, args)
            }
        }
    ```
- mobx常用API
  - 可观察的数据(observable)
    - 是一种让数据的变化可以被观察的方法
    ```js
    import { observable } from 'mobx'
    class Store {
      @observable array = []
      @observable obj = {}
      @observable map = new Map()

      @observable string = 'hello'
      @observable number = 20
      @observable bool = false
    }
    ```
  - 观察数据变化的方式
    - computed
    ```js
    能将多个可观察数据组合成一个可观察数据
    ```
    - autorun
     ```js
    能自动追踪其引用的可观察数据，并在数据发生变化时重新触发
    ```
    - when
     ```js
    提供了条件执行逻辑，autorun的一种变种
    ```
    - Reaction
     ```js
    通过分离可观察数据声明以副作用的方式对autorun进行改进
    ```
- mobx-react

- 性能提升
  - 细粒度拆分视图组件
  - 使用专用组件处理列表
  - 尽可能晚地解构可观察数据

- 常用工具函数
  - observe  监控数据变化
    ```js
        constructor() {
            observe(this.todos, (change) => {
                console.log(change, 'change')
                this.disposers.forEach((disposer) => disposer())
                this.disposers = []
                for (let todo of change.object) {
                    var disposer = observe(todo, (changex) => {
                        console.log(changex)
                    })
                    this.disposers.push(disposer)
                }
                console.log(change, 'change')
            })
        }
    ```
  - toJS
  - trace
  - spy
    ```js
    spy((event) => {
        console.log(event, 'event')
    })
    ```
