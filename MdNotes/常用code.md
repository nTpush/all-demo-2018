# code

- 阅code

``` css
<style module lang="postcss"> </style>
```

``` postcss
.button {
    &:focus {
    }
}
```

``` js
@input="$emit('input', $event.target.value)"
```

``` js
 return new RegExp(searchText.replace(/[[(){}^$|?*+.\\-]/g, '\\$&'), 'ig')
```

``` vue
props: {
    onConfirmClick: { type: Function, required: true },
    text: { type: String, required: true }
}
```

``` vue
:style="{width:`calc(100% / ${len})`}"
:class="[{[$style['panel-active']]:(i === index)}, $style['panel']]"
```

- 字逐个逐个蹦出来

```js
import React from 'react';
export default class T extends React.Component {
state = {
    text: '',
    i: 0
};

componentDidMount() {
    this.timer = setInterval(() => this.textLine(), 1000);
}

componentWillUnmount() {
    clearInterval(this.timer);
}

textLine() {
    let str = '字逐个逐个蹦出来';
    str.split('');
    if (this.state.i < str.length) {
        console.log(str[this.state.i]);
        this.setState({
            text: this.state.text + str[this.state.i]
        });
        this.state.i++;
    }
}

render() {
    const greet = getGreeting(this.state.name);
    return <div>{this.state.text}</div>;
}
}
function getGreeting(user) {
if (user) {
    return <h1>hello, {user}</h1>;
} else {
    return <h1>hello, stranger</h1>;
}
}

```