import { observable, action } from 'mobx'

import React, { Component } from 'react'

import ReactDOM from 'react-dom'

import PropTypes from 'prop-types'

import { observer, PropTypes as ObservablePropTypes } from 'mobx-react'

class Store {
	@observable
	cache = {
		queue: []
	}

	@action.bound
	refresh() {
		this.cache.queue.push(3)
	}
}

const store = new Store()

@observer
class Bar extends Component {
	static propTypes = {
		queue: ObservablePropTypes.observableArray
	}
	render() {
		const queue = this.props.queue
		return <span>{queue.length}</span>
	}
}

class Foo extends Component {
	static propTypes = {
		cache: ObservablePropTypes.observableObject
	}

	shouldComponentUpdate() {
		console.log(3)
	}

	render() {
		const cache = this.props.cache
		return (
			<div>
				<Bar queue={cache.queue} />
				<button onClick={this.props.refresh}>refresh</button>
			</div>
		)
	}
}

ReactDOM.render(<Foo cache={store.cache} refresh={store.refresh} />, document.querySelector('#root'))
