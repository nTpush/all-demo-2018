# 一点点node

## 慎重考虑
- 极高并发数
- 密集CPU运算
- 高安全高可靠性
- 内存精密控制及释放

- promise
- 使用前
```js
function animate(ball,distance,cb) {
    setTimeout(function() {
        var marginLeft = parseInt(ball.style.marginLeft, 10)
        if(marginLeft === distance) {
            cb && cb()
        }
        else {
            if(marginLeft < distance) {
                marginLeft ++
            } else {
                marginLeft --
            }
            ball.style.marginLeft = marginLeft + 'px'
            animate(ball, distance, cb)
        }
    }, 13)
}

  animate(ball1, 100, function() {
      animate(ball2, 200, function() {
          animate(ball3, 300, function() {
              animate(ball2, 50, function() {
                  animate(ball1, 100, function() {

                  })
              })
          })
      })
  })
```
- 使用后
```js
var Promise = window.Promise
function promiseAnimate(ball, distance) {
    return new Promise(function(resolve, reject) {
         function _animate() {
            setTimeout(function() {
                var marginLeft = parseInt(ball.style.marginLeft, 10)
                if(marginLeft === distance) {
                    resolve()
                }
                else {
                    if(marginLeft < distance) {
                        marginLeft ++
                    } else {
                        marginLeft --
                    }
                    ball.style.marginLeft = marginLeft + 'px'
                    _animate()
                }
            }, 13)
        }
        _animate()
    })
}

  promiseAnimate(ball1, 100)
  .then(function() {
      return promiseAnimate(ball2, 200)
  })
  .then(function() {
      return promiseAnimate(ball3, 300)
  })
  .then(function() {
      return promiseAnimate(ball1, 300)
  })
  .then(function() {
      return promiseAnimate(ball2, 300)
  })
```

- Net
- Buffer
- Stream

Stream 种类
- Readable
- Writable
- Duplex
- Transform
